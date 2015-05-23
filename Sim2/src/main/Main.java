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

package main;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import bean.Arrival;
import bean.Patient;
import dao.FieldHospitalDAO;

public class Main {
	private Hospital hospital;
	
	public static void main(String[] args) {
		Main m = new Main();
		m.run();
	}

	protected void run() {
		FieldHospitalDAO dao = new FieldHospitalDAO();
		hospital = new Hospital();
		
		// //////////////////////////////////////////////////////////////////////////
		// Kickstart: parse events & patients
		Map<Integer, Patient> patientsMap = dao.GetAllPatients();
		hospital.setPatientsMap(patientsMap);
		List<Arrival> l = dao.GetAllArrivals();
		Iterator<Arrival> itr = l.iterator();
		while (itr.hasNext()) {
			Arrival a = itr.next();
			Event e = new Event(a.getTime(), Event.EventEnum.PATIENT_ARRIVE, a.getPatient());
			hospital.addEvent(e);
			long td = a.getTime();
			if(a.getTriage().equals("Red"))
				td += 60 * 60 * 1000;
			else if(a.getTriage().equals("Yellow"))
				td += 6 * 60 * 60 * 1000;
			else if(a.getTriage().equals("Green"))
				td += 12 * 60 * 60 * 1000;
			else 
				td = 0;
			Event d = new Event(td, Event.EventEnum.PATIENT_DEATH, a.getPatient());
			if(td != 0)
				hospital.addEvent(d);
		}
		
		// super semplificato
		while(hospital.moreEvents()) {
			hospital.step();
		}
	}
}
