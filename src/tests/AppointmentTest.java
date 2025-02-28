package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Nested;

import java.util.Date;
import org.junit.jupiter.api.Test;
import appointments.Appointment;

public class AppointmentTest {
	private String GenerateString(int length) {
		String str = "";
		for(int i = 0; i < length; i++) {
			str = str + "a";
		}
		return str;
	}
	
	@Nested
	class IDRequirements{
		@Test
		void AppointmentIDIsNonNull() {
			assertThrows(IllegalArgumentException.class, () -> new Appointment(null, new Date(), "desc."));
		}
		
		@Test
		void AppointmentIDIsUnique() {
			assertThrows(IllegalArgumentException.class, () -> {
				new Appointment("1", new Date(), "desc.");
				new Appointment("1", new Date(), "desc.");
			});
		}
		
		@Test
		void AppointmentIDTooLong() {
			assertThrows(IllegalArgumentException.class, () -> { 
				String tooLongID = GenerateString(Appointment.MemberSizes.MAX_ID_LENGTH+1);
				new Appointment(tooLongID, new Date(), "desc.");
				}
			);
		}
		
		@Test
		void ValidIDsAccepted() {
			assertAll(
					() -> new Appointment(Appointment.GenerateUniqueID(), new Date(), "desc"),
					() -> new Appointment(Appointment.GenerateUniqueID(), new Date(), "desc")
					);
		}
	}
	
	@Nested
	class DateRequirements{
		
		@Test
		void NullAppointmentDate(){
			assertThrows(IllegalArgumentException.class, 
					() -> new Appointment(Appointment.GenerateUniqueID(), null, "desc."));
		}
		
		@Test
		void AppointmentDateInPastRejected() {
			assertThrows(IllegalArgumentException.class,
					() -> new Appointment(Appointment.GenerateUniqueID(), new Date(0), "desc"));
		}
		
		@Test
		void ValidDateAccepted() {
			long epochTime = new Date().getTime();
			assertAll(
					() -> new Appointment(Appointment.GenerateUniqueID(), new Date(), "desc"),
					() -> new Appointment(Appointment.GenerateUniqueID(), new Date(epochTime + 10000), "desc")
					);
		}

	}
	
	@Nested
	class DescriptionRequirements{
		@Test
		void DescriptionNonNull() {
			assertThrows(IllegalArgumentException.class, 
					() -> new Appointment(Appointment.GenerateUniqueID(), new Date(), null) 
					);
		}
		
		@Test 
		void DescriptionTooLong() {
			String tooLongDescription = GenerateString(Appointment.MemberSizes.MAX_DESCRIPTION_LENGTH + 1);
			assertThrows(IllegalArgumentException.class, 
					() -> new Appointment(Appointment.GenerateUniqueID(), new Date(), tooLongDescription)
					);
		}
		
		@Test
		void ValidDescriptionAccepted() {
			String desc = GenerateString(Appointment.MemberSizes.MAX_DESCRIPTION_LENGTH);
			assertAll(() -> new Appointment(Appointment.GenerateUniqueID(), new Date(), desc));
		}
	}

}
