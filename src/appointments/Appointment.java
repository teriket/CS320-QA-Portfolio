package appointments;
import java.util.Date;
import java.util.HashSet;

public class Appointment {
	
	public static class MemberSizes{
		public static final int MINIMUM_FIELD_LENGTH = 1;
		public static final int MAX_ID_LENGTH = 10;
		public static final int MAX_DESCRIPTION_LENGTH=50;
	}
	
	//this is a collection of Appointment IDS that have been created to guarantee uniqueness
	private static HashSet<String> existantIds;
	
	private final String ID;
	private Date date;
	private String description;
	
	public Appointment(String id, Date date, String description) throws IllegalArgumentException {
		//initialize the static set that tracks whether the ID has already been used
		if(existantIds == null) {
			existantIds = new HashSet<String>();
		}
		
		//ID is non Null, less than 10 characters, and no duplicate ID's exist
		if(FieldIsNonNullAndAppropriateLength(id, MemberSizes.MINIMUM_FIELD_LENGTH, MemberSizes.MAX_ID_LENGTH)
				&& !existantIds.contains(id)) {
			this.ID = id;
			existantIds.add(id);
		}
		else {
			throw new IllegalArgumentException("invalid id entered");
		}
		
		//Date is non null and not in the past
		if(date != null 
				&&  !date.before(new Date())) {
			this.date = date;
		}
		else {
			throw new IllegalArgumentException("invalid date entered");
		}
		
		//Description is non null, less than 50 characters
		if(FieldIsNonNullAndAppropriateLength(description, MemberSizes.MINIMUM_FIELD_LENGTH, MemberSizes.MAX_DESCRIPTION_LENGTH)) {
			this.description = description;
		}
		else {
			throw new IllegalArgumentException("invalid description entered");
		}
	}
	
	public String GetID() {
		return this.ID;
	}
	
	public Date GetDate() {
		return this.date;
	}
	
	public String GetDescription() {
		return this.description;
	}
	
	public static String GenerateUniqueID() {
		String str = "";
		String validChars = "1234567890qwertyuiopasdfghjklzxcvbnm_";
		
		//build a random ID one character at a time
		for(int i = 0; i < 10; i++) {
			int index = (int)(Math.random() * validChars.length());
			char randomChar = validChars.charAt(index);
			str = str + randomChar;
		}
		
		//Make sure this is not a duplicate ID (the likelihood is very low)
		if(existantIds != null && existantIds.contains(str)) {
			return GenerateUniqueID();
		}
		
		return str;
	}
	
	private boolean FieldIsNonNullAndAppropriateLength(String field, int min, int max) {
		return(field != null && field.length() >= min && field.length() <= max);
	}
}
