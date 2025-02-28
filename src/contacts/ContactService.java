package contacts;

import java.util.Map;
import java.util.HashMap;

//Tanner Hunt
// 1/21/2025
// SNHU CS320 software testing, QA automation
public class ContactService {
	private Map<String, Contact> contacts = new HashMap<String, Contact>(); 
	
	public Contact GetContact(String ID) {
		if(ContactExists(ID)) {
			return contacts.get(ID);
		}
		else {
			throw new IllegalArgumentException("Contact not found");
		}
	}
	
	/**
	 * Generates a new contact with a unique ID
	 * */
	public Contact Add(String firstName, String lastName, String phoneNumber, String address) {
		Contact contact = new Contact(Contact.GenerateUniqueID(), firstName, lastName, phoneNumber, address);
		
		contacts.put(contact.GetContactID(), contact);
		return contact;
	}
	
	public void Delete(String contactID) {
		if(!ContactExists(contactID)) {
			throw new IllegalArgumentException("Contact not found, cannot delete this contact ID");
		}
		else {
			contacts.remove(contactID);
		}
	}
	
	public void UpdateContactFirstName(String contactID, String newName) {
		if(!ContactExists(contactID)) {
			throw new IllegalArgumentException("Cannot find a contact with this ID");
		}
		else {
			contacts.get(contactID).SetFirstName(newName);
		}
	}
	
	public void UpdateContactLastName(String contactID, String newName) {
		if(!ContactExists(contactID)) {
			throw new IllegalArgumentException("Cannot find a contact with this ID");
		}
		else {
			contacts.get(contactID).SetLastName(newName);
		}
	}
	
	public void UpdateContactPhoneNumber(String contactID, String newPhoneNumber) {
		if(!ContactExists(contactID)) {
			throw new IllegalArgumentException("Cannot find a contact with this ID");
		}
		else {
			contacts.get(contactID).SetPhoneNumber(newPhoneNumber);
		}
	}
	
	public void UpdateContactAddress(String contactID, String newAddress) {
		if(!ContactExists(contactID)) {
			throw new IllegalArgumentException("Cannot find a contact with this ID");
		}
		else {
			contacts.get(contactID).SetAddress(newAddress);
		}
	}
	
	public boolean ContactExists(String contact) {
		if(contacts.containsKey(contact)) {
			return true;
		}
		return false;
	}
}
