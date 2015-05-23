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

package bean;

import java.sql.Timestamp;

public class Arrival {
	private Timestamp time;
	private String triage;
	private int patient;
	
	////////////////////////////////////////////////////////////////////////////
	// Getters
	public long getTime() {
		return time.getTime();
	}
	public String getTriage() {
		return triage;
	}
	public int getPatient() {
		return patient;
	}
	
	public Arrival(Timestamp time, int patient, String triage) {
		super();
		this.patient = patient;
		this.time = time;
		this.triage = triage;
	}

	@Override
	public String toString() {
		return patient + " [" + triage + "] @ " + time;
	}
}
