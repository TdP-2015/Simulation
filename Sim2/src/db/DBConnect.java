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

package db;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

	static private final String jdbcUrl = "jdbc:mysql://localhost/emergency?user=root";
	static private DBConnect instance = null ;
	
	private DBConnect () {
		instance = this ;
		//System.out.println("instance created") ;
	}
	
	public static DBConnect getInstance() {
		if(instance == null)
			return new DBConnect() ;
		else {
			//System.out.println("instance reused") ;
			return instance ;
		}
	}
	
	public Connection getConnection() {
		try {
			Connection conn = DriverManager.getConnection(jdbcUrl) ;
			return conn ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Cannot get connection "+jdbcUrl, e) ;
		}	
	}

}
