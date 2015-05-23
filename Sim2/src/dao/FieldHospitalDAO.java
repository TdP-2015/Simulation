////////////////////////////////////////////////////////////////////////////////
//             //                                                             //
//   #####     // Field hospital simulator                                    //
//  ######     // (!) 2014 Giovanni Squillero <giovanni.squillero@polito.it>  //
//  ###   \    //                                                             //
//   ##G  c\   // Event descriptor                                            //
//   #     _\  // Describe a single event in the simulator                    //
//   |   _/    //                                                             //
//   |  _/     //                                                             //
//             // 03FYZ - Tecniche di programmazione 2013-14                  //
////////////////////////////////////////////////////////////////////////////////

package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBConnect;
import bean.Arrival;
import bean.Patient;

public class FieldHospitalDAO {
	public boolean GetPatient(Patient p) {
		final String sql = "SELECT nome FROM patients WHERE id = ?";

		try {
			Connection conn = DBConnect.getInstance().getConnection();	
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, p.getId());
			ResultSet rs = st.executeQuery();
			
			boolean ok = false;
			if(rs.next()) {
				p.setName(rs.getString("name"));
				p.setStatus(rs.getString("status"));
				ok = true;
			}
			
			st.close();
			conn.close();
			
			return ok;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public Map<Integer, Patient> GetAllPatients() {
		final String sql = "SELECT * FROM patients";
		Map<Integer,Patient> p = new HashMap<Integer,Patient>(); 
		
		try {
			Connection conn = DBConnect.getInstance().getConnection();	
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Patient tp = new Patient(rs.getInt("id"), rs.getString("name"));
				p.put(rs.getInt("id"), tp);
			}
			st.close();
			conn.close();		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return p;
	}
	
	public List<Arrival> GetAllArrivals() {
		final String sql = "SELECT * FROM arrivals ORDER BY timestamp ASC LIMIT 20";
		List<Arrival> l = new ArrayList<Arrival>();

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while( rs.next() ) {
				l.add(new Arrival(rs.getTimestamp("timestamp"), rs.getInt("patient"), rs.getString("triage")));
			}
			st.close();
			conn.close();		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}		
		return l;
	}
}
