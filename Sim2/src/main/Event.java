package main;

public class Event implements Comparable<Event> {
	public enum EventEnum {
		PATIENT_ARRIVE, 
		PATIENT_START_TREATMENT,
		PATIENT_OUT_OF_DANGER, 
		PATIENT_FULL_RECOVERY,
		PATIENT_DEATH,
		
		DOCTOR_AVAILABLE,
		DOCTOR_UNAVAILABLE
	}
	protected long time;
	protected EventEnum eventType;
	protected int patient;

	public Event(long time, EventEnum eventType, int patient) {
		this.time = time;
		this.eventType = eventType;
		this.patient = patient;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Event [time=" + time + ", eventType=" + eventType + "]";
	}

	@Override
	public int compareTo(Event arg0) {
		return Long.compare(time,  arg0.time);
	} 
}
