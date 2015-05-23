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

public class Patient implements Comparable<Patient>{
	////////////////////////////////////////////////////////////////////////////
	// ENUMS
	public enum TriageEnum { RED, YELLOW, GREEN, WHITE, BLACK };
	public enum StatusEnum { SAFE, INJURED_QUEUING, INJURED_TREATED, DEAD };

	private int id;
	protected String name;
	protected TriageEnum triage;
	protected StatusEnum status;
	protected long arrived;

	/**
	 * @return the arrived
	 */
	public long getArrived() {
		return arrived;
	}
	/**
	 * @param arrived the arrived to set
	 */
	public void setArrived(long arrived) {
		this.arrived = arrived;
	}
	////////////////////////////////////////////////////////////////////////////
	// Canonical hash & equals
	@Override
	public int hashCode() {	
		long hash = id * 2654435761L;	// dr. knuth magic number
		return (int)hash;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	////////////////////////////////////////////////////////////////////////////
	// Getters & Setters
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status.toString();
	}
	public StatusEnum getStatusE() {
		return status;
	}
	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	public void setStatus(String status) {
		// valueOf() would have been simpler, but CaSe SeNsItIvE
		if(status.compareToIgnoreCase("Safe")==0)
			this.status = StatusEnum.SAFE;
		else if(status.compareToIgnoreCase("Injured_Queuing")==0)
			this.status = StatusEnum.INJURED_QUEUING;
		else if(status.compareToIgnoreCase("Injured_Treated")==0)
			this.status = StatusEnum.INJURED_TREATED;
		else if(status.compareToIgnoreCase("Dead")==0)
			this.status = StatusEnum.DEAD;
		else
			throw new ClassCastException("Unknown status: \"" + status + "\"");
	}	
	public String getTriage() {
		return triage.toString();
	}
	public TriageEnum getTriageE() {
		return triage;
	}
	public void setTriage(TriageEnum triage) {
		this.triage = triage;
	}
	public void setTriage(String triage) {
		// valueOf() would have been simpler, but CaSe SeNsItIvE
		if(triage.compareToIgnoreCase("Black")==0)
			this.triage = TriageEnum.BLACK;
		else if(triage.compareToIgnoreCase("Red")==0)
			this.triage = TriageEnum.RED;
		else if(triage.compareToIgnoreCase("Yellow")==0)
			this.triage = TriageEnum.YELLOW;
		else if(triage.compareToIgnoreCase("Green")==0)
			this.triage = TriageEnum.GREEN;
		else if(triage.compareToIgnoreCase("White")==0)
			this.triage = TriageEnum.WHITE;
		else
			throw new ClassCastException("Unknown triage: \"" + triage + "\"");
	}

	////////////////////////////////////////////////////////////////////////////
	// Constructor
	public Patient() {
	}
	public Patient(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.status = StatusEnum.SAFE;
		this.triage = TriageEnum.WHITE;
	}
	
	////////////////////////////////////////////////////////////////////////////
	// Spare utilities
	@Override
	public String toString() {
		return name + " (#" + id + ", " + status + ", " + triage + ")";
	}
	@Override
	public int compareTo(Patient arg0) {
		if(triage.equals(arg0.triage))
			return Long.compare(arrived, arg0.arrived);
		if(triage.equals("Red"))
			return -1;
		if(arg0.equals("Red"))
			return 1;
		if(triage.equals("Yellow"))
			return -1;
		if(arg0.equals("Yellow"))
			return 1;
		if(triage.equals("Green"))
			return -1;
		if(arg0.equals("Green"))
			return 1;
		if(triage.equals("White"))
			return -1;
		if(arg0.equals("White"))
			return 1;
		return 0;
	}
}
