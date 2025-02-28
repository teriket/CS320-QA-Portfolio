package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import tasks.*;

class TaskTest {
	private final int MIN_FIELD_SIZE = 1;
	private final int MAX_ID_SIZE = 10;
	private final int MAX_NAME_SIZE = 20;
	private final int MAX_DESCRIPTION_SIZE = 50;
	
	private final String GENERIC_VALID_NAME = GenerateTestString(MAX_NAME_SIZE);
	private final String GENERIC_VALID_DESCRIPTION = GenerateTestString(MAX_DESCRIPTION_SIZE);
	private final String GENERIC_VALID_ID = GenerateTestString(MAX_ID_SIZE);
	
	//A helper function to generate strings of a specific length
	String GenerateTestString(int length) {
		String str = "";
		for(int i = 0; i < length; i++) {
			str = str + "a";
		}
		return str;
	}
	
	@Nested
	class TaskInstantiation{
		@BeforeEach
		void InitTests() {
			Task.ClearMemory();
		}
		/**
		 *Ensures Tasks accept valid ID's, names, and descriptions */
		@Test
		void ValidTaskInstantiation() {
			assertAll(
					//valid Task for maximum size
					() -> new Task(
							GENERIC_VALID_ID,
							GENERIC_VALID_NAME,
							GENERIC_VALID_DESCRIPTION),
					
					//valid Task for minimum size
					() -> new Task(
							GenerateTestString(MIN_FIELD_SIZE),
							GenerateTestString(MIN_FIELD_SIZE),
							GenerateTestString(MIN_FIELD_SIZE))
					);
		}
	
		/**
		 * ensures the TaskID throws an exception if a null value is passed through*/
		@Test
		void NullTaskID() {
			Exception exc = assertThrows(IllegalArgumentException.class, () -> {
				new Task(null, GENERIC_VALID_NAME, GENERIC_VALID_DESCRIPTION);
			});
			
			assertEquals(IllegalArgumentException.class, exc.getClass());
		}
		
		/**
		 * */
		@Test
		void NoRepeatTaskIDsAccepted() {
			assertThrows(IllegalArgumentException.class, () -> {
				Task task1 = new Task("id", "name", "desc");
				Task task2 = new Task("id", "name", "desc");
			});
		}
		
		/**
		 * All tests surrounding ID length:
		 * empty ID's are rejected,
		 * ID's longer than 10 characters are rejected*/
		@Test
		void TaskIDInvalidLengths() {
			//long Id's are rejected
			assertThrows(IllegalArgumentException.class, () -> new Task(
					GenerateTestString(MAX_ID_SIZE + 1), 
					GENERIC_VALID_NAME, 
					GENERIC_VALID_DESCRIPTION));
			
			//Empty ID's are rejected
			assertThrows(IllegalArgumentException.class, () -> new Task(
					GenerateTestString(MIN_FIELD_SIZE - 1),
					GENERIC_VALID_NAME,
					GENERIC_VALID_DESCRIPTION));
		}
		
		/**
		 * Ensures the Task throws an exception if the name is null*/
		@Test
		void NullTaskName() {
			assertThrows(IllegalArgumentException.class, () -> new Task(
					GENERIC_VALID_ID,
					null,
					GENERIC_VALID_DESCRIPTION));
		}
		
		/**
		 * Ensures the Task Name throws an exception if its too long or empty*/
		@Test
		void TaskNameInvalidLengths() {	
			//check for too long of names
			assertThrows(IllegalArgumentException.class,() -> new Task(
					GENERIC_VALID_ID,
					GenerateTestString(MAX_NAME_SIZE + 1),
					GENERIC_VALID_DESCRIPTION));
			
			//check for too short of names
			assertThrows(IllegalArgumentException.class, () -> new Task(
					GENERIC_VALID_ID,
					GenerateTestString(MIN_FIELD_SIZE - 1),
					GENERIC_VALID_DESCRIPTION));
	
		}
	
		/**
		 * Ensure null descriptions are rejected*/
		@Test
		void NullDescription() {
			assertThrows(IllegalArgumentException.class, () -> new Task(
					GENERIC_VALID_ID,
					GENERIC_VALID_NAME,
					null));
		}
		
		/**
		 * Ensure too long or empty descriptions are rejected*/
		@Test
		void TaskDescriptionInvalidLengths() {
			//Reject too long of descriptions
			assertThrows(IllegalArgumentException.class, () -> {
				new Task(
						GENERIC_VALID_ID,
						GENERIC_VALID_NAME,
						GenerateTestString(MAX_DESCRIPTION_SIZE + 1));
			});
			
			//Reject too short of descriptions
			assertThrows(IllegalArgumentException.class, () -> {
				new Task(
						GENERIC_VALID_ID,
						GENERIC_VALID_NAME,
						GenerateTestString(MIN_FIELD_SIZE - 1));
			});
		}
		}
	
	@Nested
	class PublicMethodTests{
		Task testTask;
		
		@BeforeEach
		void InitTest() {
			Task.ClearMemory();
			testTask = new Task(GENERIC_VALID_ID, GENERIC_VALID_NAME, GENERIC_VALID_DESCRIPTION);
		}
		
		
		/**Ensure using GetName returns the correct name*/
		@Test
		void TaskGetName() {
			assertEquals(testTask.GetName(), GENERIC_VALID_NAME);
		}
		
		/**Ensure using GetDescription returns the correct Description*/
		@Test
		void TaskGetDescription() {
			assertEquals(testTask.GetDescription(), GENERIC_VALID_DESCRIPTION);
		}
		
		/**Ensure using GetID returns the correct ID*/
		@Test
		void TaskGetID() {
			assertEquals(testTask.GetID(), GENERIC_VALID_ID);
		}
		
		/**Ensure using Set name updates the field, and does not accept any invalid inputs*/
		@Test
		void TaskSetName() {
			//validate setting a name changes the field
			String setName = "Set Name";
			testTask.SetName(setName);
			assertEquals(testTask.GetName(), setName);
			
			//ensure using SetName to set a null, empty, or long name fails
			assertThrows(IllegalArgumentException.class, 
					() -> testTask.SetName(
							GenerateTestString(MAX_NAME_SIZE + 1))
					);
			
			assertThrows(IllegalArgumentException.class, 
					() -> testTask.SetName(null));
			
			assertThrows(IllegalArgumentException.class,
					() -> testTask.SetName(
							GenerateTestString(MIN_FIELD_SIZE - 1))
					);
		}
		
		/**Ensure using SetDescription updates the field and doesn't accept any invalid inputs*/
		@Test
		void TaskSetDescription() {
			//validate setting a description changes the field
			String setDescription = "set a description";
			testTask.SetDescription(setDescription);
			assertEquals(testTask.GetDescription(), setDescription);
			
			//ensure using SetDescription to set a null, empty, or long description fails
			assertThrows(IllegalArgumentException.class, 
					() -> testTask.SetDescription(
							GenerateTestString(MAX_DESCRIPTION_SIZE + 1))
					);
			
			assertThrows(IllegalArgumentException.class, 
					() -> testTask.SetDescription(null));
			
			assertThrows(IllegalArgumentException.class,
					() -> testTask.SetDescription(
							GenerateTestString(MIN_FIELD_SIZE - 1))
					);
		}
	}

}
