package tests;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import org.junit.jupiter.api.Test;
import appointments.*;

public class AppointmentServiceTest {
	AppointmentService as = AppointmentService.GetInstance();

	
	@Test
	void AddAppointmentWithUniqueID() {
		assertAll(
				() -> {
					String id1 = as.Add(Appointment.GenerateUniqueID(), new Date(), "desc");
					String id2 = as.Add(Appointment.GenerateUniqueID(), new Date(), "desc");
					as.Get(id1);
					as.Get(id2);
					assert(!id1.equals(id2));
					}
				);
	}
	
	@Test
	void DeleteAppointmentRemovesAppointment() {
		assertThrows(IllegalArgumentException.class, () -> {
			String id = as.Add(Appointment.GenerateUniqueID(), new Date(), "desc");
			as.Delete(id);
			as.Get(id);
		});
	}
	
}
