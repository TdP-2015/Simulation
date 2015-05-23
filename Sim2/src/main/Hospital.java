package main;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import main.Event.EventEnum;
import bean.*;

public class Hospital {
	protected Queue<Event> eventList;
	protected int availableDoctors;
	Map<Integer, Patient> patientsMap;
	protected Queue<Patient> waitingList;
	Queue<Doctor> doctors;
	
	public Hospital() {
		this.eventList = new PriorityQueue<Event>();
		this.waitingList = new PriorityQueue<Patient>();
		availableDoctors = 1000;
	}

	/**
	 * @return the doctors
	 */
	public int getAvailableDoctors() {
		return availableDoctors;
	}

	/**
	 * @param doctors the doctors to set
	 */
	public void setAvailableDoctors(int doctors) {
		this.availableDoctors = doctors;
	}

	/**
	 * @return the patientsMap
	 */
	public Map<Integer, Patient> getPatientsMap() {
		return patientsMap;
	}

	/**
	 * @param patientsMap the patientsMap to set
	 */
	public void setPatientsMap(Map<Integer, Patient> patientsMap) {
		this.patientsMap = patientsMap;
	}

	public boolean moreEvents() {
		return !eventList.isEmpty();
	}
	
	public void addEvent(Event e) {
		eventList.add(e);
	}
	
	public void step() {
		Event e = eventList.poll();
		long now = e.time;
		Patient p = patientsMap.get(e.patient);
		System.err.println(">>>>>" + e);
		switch(e.eventType) {
		case PATIENT_ARRIVE:
			System.err.println("PATIENT_ARRIVE");
			p.setArrived(e.time);
			p.setStatus(Patient.StatusEnum.INJURED_QUEUING);
			waitingList.add(p);
			break;
		case PATIENT_DEATH:
			if(p.getStatusE() == Patient.StatusEnum.SAFE) {
				// ignore!
			} else if(p.getStatusE() == Patient.StatusEnum.DEAD) {
				// error???
			} else if(p.getStatusE() == Patient.StatusEnum.INJURED_QUEUING) {
				System.err.println("PATIENT_DEATH while Queuing");
				p.setStatus(Patient.StatusEnum.DEAD);
				waitingList.remove(p);
			} else if(p.getStatusE() == Patient.StatusEnum.INJURED_TREATED) {
				System.err.println("PATIENT_DEATH while Treated");
				p.setStatus(Patient.StatusEnum.DEAD);
				++availableDoctors;
			}
			break;
		case PATIENT_FULL_RECOVERY:
			System.err.println("PATIENT_FULL_RECOVERY");
			++availableDoctors;
			break;
		case PATIENT_OUT_OF_DANGER:
			System.err.println("PATIENT_OUT_OF_DANGER");
			p.setStatus(Patient.StatusEnum.SAFE);
			break;
		case PATIENT_START_TREATMENT:
			System.err.println("PATIENT_START_TREATMENT");
			p.setStatus(Patient.StatusEnum.INJURED_TREATED);
			break;
		default:
			break;
		}
		
		// TREAT!
		while(availableDoctors > 0 && !waitingList.isEmpty()) {
			--availableDoctors;
			Patient pt = waitingList.remove();
			Event e0 = new Event(now+1, EventEnum.PATIENT_START_TREATMENT, pt.getId());
			eventList.add(e0);
			Event e1 = new Event(now+15*60*1000, EventEnum.PATIENT_OUT_OF_DANGER, pt.getId());
			eventList.add(e1);
			Event e2 = new Event(now+30*60*1000, EventEnum.PATIENT_FULL_RECOVERY, pt.getId());
			eventList.add(e2);
		}
	}
}
