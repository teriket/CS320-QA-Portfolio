package appointments;

import java.util.HashMap;
import java.util.Date;

public class AppointmentService {
	private static AppointmentService instance;
	private HashMap<String, Appointment> appointments;

	private AppointmentService() {
		appointments = new HashMap<String, Appointment>();
	}
	
	public static AppointmentService GetInstance() {
		if(instance == null) {
			instance = new AppointmentService();
		}
		return instance;
	}
	
	public String Add(String id, Date date, String desc) throws IllegalArgumentException {
		try {
			Appointment appointment = new Appointment(id, date, desc);
			appointments.put(id, appointment);
			return appointment.GetID();
		}
		catch(Exception e) {
			throw new IllegalArgumentException("Error in creating appointment");
		}
	}
	
	public boolean Delete(String id) {
		if(appointments.containsKey(id)) {
			appointments.remove(id);
			return true;
		}
		return false;
	}
	
	public Appointment Get(String id) throws IllegalArgumentException {
		if(appointments.containsKey(id)) {
			return appointments.get(id);
		}
		throw new IllegalArgumentException("the appointment with id: " + id + " does not exist.");
	}
	
	
}
