package contacts;

import java.util.HashSet;
import java.util.Set;


//Tanner Hunt
//1/21/2025
//SNHU CS320 software testing, QA automation
public class Contact {
	private final int MAXIMUM_ID_LENGTH = 10;
	private final int MAXIMUM_FIRST_NAME_LENGTH = 10;
	private final int MAXIMUM_LAST_NAME_LENGTH = 10;
	private final int PHONE_NUMBER_LENGTH = 10;
	private final int MAXIMUM_ADDRESS_LENGTH = 30;
	private final int MINIMUM_FIELD_LENGTH = 1;
	
	private final String contactID;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String address;
	
	//An object that tracks the existence of contact Id's to guarantee every contact ID is unique
	private static Set<String> existantContactIDs;
	
	public Contact(String id, String firstName, String lastName,String phoneNumber, String address) {
		//Initialize the existantContactID's set if it hasn't done so yet.
		if(existantContactIDs == null) {
			existantContactIDs = new HashSet<String>();
		}
		
		//verify the contact ID is not null, an appropriate length, and is unique
		if(!isValidName(id, MAXIMUM_ID_LENGTH, MINIMUM_FIELD_LENGTH) || existantContactIDs.contains(id)) {
			throw new IllegalArgumentException("Invalid ID");
		}
		else {
			this.contactID = id;
			existantContactIDs.add(id);
		}
		
		//initialize all other values associated with this data object.  Error handling is already handled in their methods.
		SetFirstName(firstName);
		SetLastName(lastName);
		SetPhoneNumber(phoneNumber);
		SetAddress(address);
		
	}
	
	public void SetFirstName(String name) {
		if(!isValidName(name, MAXIMUM_FIRST_NAME_LENGTH, MINIMUM_FIELD_LENGTH)) {
			throw new IllegalArgumentException("invalid first name");
		}
		else {
			this.firstName = name;
		}
		
	}
	
	public void SetLastName(String name) {
		if(!isValidName(name, MAXIMUM_LAST_NAME_LENGTH, MINIMUM_FIELD_LENGTH)) {
			throw new IllegalArgumentException("invalid last name");
		}
		else {
			this.lastName = name;	
		}
	}
	
	public void SetPhoneNumber(String phoneNumber) {
		if(!isValidName(phoneNumber, PHONE_NUMBER_LENGTH, PHONE_NUMBER_LENGTH) || !isNumeric(phoneNumber)) {
			throw new IllegalArgumentException("Invalid Phone Number");
		}
		else {
			this.phoneNumber = phoneNumber;
		}
	}
	
	public void SetAddress(String address) {

		if(!isValidName(address, MAXIMUM_ADDRESS_LENGTH, MINIMUM_FIELD_LENGTH)) {
			throw new IllegalArgumentException("input an invalid Address");
		}
		else {
			this.address = address;
		}
	}
	
	public String GetFirstName() {
		return this.firstName;
	}
	
	public String GetLastName() {
		return this.lastName;
	}
	
	public String GetPhoneNumber() {
		return this.phoneNumber;
	}
	
	public String GetAddress() {
		return this.address;
	}
	
	public String GetContactID() {
		return this.contactID;
	}
	
	/**
	 * Checks that an input string is not null and is sized between the minimum and maximum
	 * */
	private boolean isValidName(String name, int maximumSize, int minimumSize) {
		if(name == null || name.length() > maximumSize || name.length() < minimumSize) {
			return false;
		}
		return true;
	}
	
	/**
	 * returns true if a string only contains digits 0-9
	 * */
	private boolean isNumeric(String string) {
		final int minimumAsciiBound = 48;
		final int maximumAsciiBound = 57;
		
		for(int i = 0; i < string.length(); i++) {
			int characterAsciiCode = (int)string.charAt(i);
			
			if( characterAsciiCode < minimumAsciiBound || characterAsciiCode > maximumAsciiBound) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Generates a random sequence of 10 alpha-numeric characters, verifies the ID is unique, and generates a 
	 * new one if it is not unique.
	 * @return a unique sequence of 10 alpha-numeric characters
	 * */
	public static String GenerateUniqueID() {
		String id = "";
		//The set of possible character a unique ID may contain
		String validChars = "0123456789"
				+ "abcdefghijklmonpqrstuvwxyz"
				+ "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		//iteratively add a new character to the id string
		for(int i = 0; i < 10; i++) {
			//Generate a random character index in the validChars string
			int randomNum = (int)(Math.random() * validChars.length());
			id = id + validChars.charAt(randomNum);
		}
		
		//generate a new contactID if a collision occurs (as unlikely as that is).
		//An uninitialized existantContactIDs set implies that someone is trying to generate an ID before
		//any others have been created, guaranteeing uniqueness of the first ID
		if(existantContactIDs != null && existantContactIDs.contains(id)) {
			return GenerateUniqueID();
		}
		else return id;
	}

}
