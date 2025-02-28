package tasks;

import java.util.HashSet;

public class Task {
	private final int MIN_FIELD_LENGTH = 1;
	private final int MAX_TASK_ID_LENGTH = 10;
	private final int MAX_NAME_SIZE = 20;
	private final int MAX_DESCRIPTION_SIZE = 50;
	
	private final String taskID;
	private String name;
	private String description;
	//This guarantees each ID is unique
	private static HashSet<String> existentIds;
	
	/**
	 * @param ID a unique, non empty, non null string no longer than 10 characters long
	 * @param name a non empty, non null string no longer than 50 characters long
	 * @param description a non empty, no null string no longer than 50 characters long */
	public Task(String ID, String name, String description) {
		//build the set of existent Id's if this is the first task
		if(existentIds == null) {
			existentIds = new HashSet<String>();
		}
		//Verify each field is the appropriate size, non empty, and non null
		if(!isRightStringSizeAndNonNull(ID, MIN_FIELD_LENGTH, MAX_TASK_ID_LENGTH)) {
			throw new IllegalArgumentException("Invalid ID");
		}
		
		if(!isRightStringSizeAndNonNull(name, MIN_FIELD_LENGTH, MAX_NAME_SIZE)) {
			throw new IllegalArgumentException("Invalid Name");
		}
		
		if(!isRightStringSizeAndNonNull(description, MIN_FIELD_LENGTH, MAX_DESCRIPTION_SIZE)) {
			throw new IllegalArgumentException("Invalid Description");
		}
		//make sure this is a unique ID
		if(existentIds.contains(ID)) {
			throw new IllegalArgumentException("invalid ID");
		}
		
		//Set every mandatory field
		existentIds.add(ID);
		this.taskID = ID;
		this.name = name;
		this.description = description;
	}
	
	/**
	 * @return a string name for this task no longer than 20 characters*/
	public String GetName() {
		return this.name;
	}
	
	/**
	 * @return a string description of this task no longer than 50 characters*/
	public String GetDescription() {
		return this.description;
	}
	
	/**
	 * @return this tasks unique ID*/
	public String GetID() {
		return this.taskID;
	}
	
	/**
	 * @param name The new name for the task, which must be non null, non empty, and less than 20 characters long*/
	public void SetName(String name) {
		if(!isRightStringSizeAndNonNull(name, MIN_FIELD_LENGTH, MAX_NAME_SIZE)) {
			throw new IllegalArgumentException("Invalid Name");
		}
		else this.name = name;
	}
	
	/**
	 * @param description a non empty and non null string no longer than 50 characters, */
	public void SetDescription(String description) {
		if(!isRightStringSizeAndNonNull(description, MIN_FIELD_LENGTH, MAX_DESCRIPTION_SIZE)) {
			throw new IllegalArgumentException("Invalid Description");
		}
		else this.description = description;
	}
	
	/**
	 * @param str 
	 * @param minSize
	 * @param maxSize
	 * @return returns true if a string is non null, and its length is between the minimum and maximum sizes inclusive*/
	private boolean isRightStringSizeAndNonNull(String str, int minSize, int maxSize) {
		if(str == null || str.length() < minSize || str.length() > maxSize) {
			return false;
		}
		return true;
	}
	
	/**
	 * Reset the set of existent IDs*/
	public static void ClearMemory() {
		if(existentIds != null) {
		existentIds.clear();
		}
	}
}
