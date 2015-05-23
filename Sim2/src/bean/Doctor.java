package bean;

import bean.Patient.StatusEnum;
import bean.Patient.TriageEnum;

public class Doctor implements Comparable<Doctor> {
	////////////////////////////////////////////////////////////////////////////
	// ENUMS
	public enum StatusEnum { WORKING, XYZAQ};

	private int id;
	protected String name;
	protected StatusEnum status;
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		long hash = id * 2654435761L;	// dr. knuth magic number
		return (int)hash;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Doctor)) {
			return false;
		}
		Doctor other = (Doctor) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(Doctor arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}
