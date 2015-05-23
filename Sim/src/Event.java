public class Event implements Comparable<Event> {
	public enum EventType {
		CUSTOMER_ARRIVES,
		CUSTOMER_SERVED
	}
	
	public long timeStamp;
	EventType eventType;
	
	@Override
	public int compareTo(Event arg0) {
		return Long.compare(timeStamp, arg0.timeStamp);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Event [timeStamp=" + timeStamp + ", eventType=" + eventType
				+ "]";
	}
}
