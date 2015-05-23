import java.util.PriorityQueue;
import java.util.Queue;

public class SimCore {
	private Queue<Event> eventList;

	public SimCore() {
		eventList = new PriorityQueue<Event>();
	}
	
	public boolean moreEvents() {
		return !eventList.isEmpty();
	}
	
	public Event nextEvent() {
		return eventList.remove();
	}
	
	public void addEvent(Event e) {
		eventList.add(e);
	}
}
