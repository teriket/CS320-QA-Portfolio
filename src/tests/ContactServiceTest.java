package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import contacts.Contact;
import contacts.ContactService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;

class ContactServiceTest {
	protected ContactService contactService;
	protected Contact contact;
	
	@BeforeEach
	void SetupContactService() {
		contactService = new ContactService();
		contact = contactService.Add(
				"Bart",
				"Simpson",
				"7871452123",
				"Springfield");
	}
	
	@Test
	void ValidGetContact() {
		assertEquals(contact, contactService.GetContact(contact.GetContactID()));
	}
	
	@Test
	void InvalidGetContact() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			contactService.GetContact("0");
		});
		assertEquals(IllegalArgumentException.class, exception.getClass());
	}
	
	@Test
	void ValidContactExists() {
		assertTrue(contactService.ContactExists(contact.GetContactID()));
	}
	
	@Test
	void InvalidContactExists() {
		assertFalse(contactService.ContactExists("0"));
	}
	
	@Test
	void AddNewContacts() {
		Contact contact2 = contactService.Add(
				"Marge",
				"Simpson",
				"7871452123",
				"Springfield");
		assertEquals(contact2, contactService.GetContact(contact2.GetContactID()));
	}
	
	@Test
	void DeleteContacts() {
		contactService.Delete(contact.GetContactID());
		assertFalse(contactService.ContactExists(contact.GetContactID()));
	}
	
	@Test
	void InvalidDeleteOperation() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			contactService.Delete("0");
		});
		
		assertEquals(IllegalArgumentException.class, exception.getClass());
	}
	
	@Nested
	class ContactEditing{
		
		@Test
		void EditFirstName() {
			contactService.UpdateContactFirstName(contact.GetContactID(), "Stacy");
			assert(contact.GetFirstName().equals("Stacy"));
		}
		
		@Test
		void EditNonExistantContactFirstName() {
			Exception exception = assertThrows(IllegalArgumentException.class, () -> {
				contactService.UpdateContactFirstName("invalidID", "value");
			});
			
			assertEquals(IllegalArgumentException.class, exception.getClass());
		}
		
		@Test
		void EditLastName() {
			contactService.UpdateContactLastName(contact.GetContactID(), "Malibu");
			assert(contact.GetLastName().equals("Malibu"));
		}
		
		@Test
		void EditNonExistantContactLastName() {
			Exception exception = assertThrows(IllegalArgumentException.class, () -> {
				contactService.UpdateContactLastName("invalidID", "value");
			});
			
			assertEquals(IllegalArgumentException.class, exception.getClass());
		}
		
		@Test
		void EditPhoneNumber() {
			contactService.UpdateContactPhoneNumber(contact.GetContactID(), "0123456789");
			assert(contact.GetPhoneNumber().equals("0123456789"));
		}
		
		@Test
		void EditNonExistantContactPhoneNumber() {
			Exception exception = assertThrows(IllegalArgumentException.class, () -> {
				contactService.UpdateContactPhoneNumber("invalidID", "0123456789");
			});
			
			assertEquals(IllegalArgumentException.class, exception.getClass());
		}
		
		@Test
		void EditAddress() {
			contactService.UpdateContactAddress(contact.GetContactID(), "Springfield Lane");
			assert(contact.GetAddress().equals("Springfield Lane"));
		}
		
		@Test
		void EditNonExistantContactAddress() {
			Exception exception = assertThrows(IllegalArgumentException.class, () -> {
				contactService.UpdateContactAddress("invalidID", "value");
			});
			
			assertEquals(IllegalArgumentException.class, exception.getClass());
		}
	}

}
