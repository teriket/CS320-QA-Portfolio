package tasks;

import java.util.HashMap;

public class TaskService {
	private HashMap<String, Task> tasks = new HashMap<String, Task>();
	private static TaskService instance;
	
	private TaskService() {}
	
	/**
	 * @return the singleton instance of the task service*/
	public static TaskService GetInstance() {
		if(instance == null) {
			instance = new TaskService();
		}
		return instance;
	}
	
	/**
	 * @param ID a unique sequence of 10 alpha-numeric characters
	 * @return a Task with a given ID*/
	public Task GetTask(String ID) {
		if(tasks.containsKey(ID)) {
			return tasks.get(ID);
		}
		throw new IllegalArgumentException("No Task with ID " + ID + " exists");
	}
	
	/**
	 * Generate a new task within the task service with a unique ID
	 * @param name the name of the task, up to 20 characters long.  Names may not be null or empty
	 * @param description The description of the task, up to 50 characters long.  Names may not be null or empty
	 * @return The TaskID string
	 * */
	public String AddTask(String name, String description) throws IllegalArgumentException {
		Task task = new Task(GenerateUniqueID(), name, description);
		tasks.put(task.GetID(), task);
		return task.GetID();
	}
	
	/**
	 * Removes a task from the set of tasks
	 * @param ID the string Id of the task to remove
	 * @return false if the taskID does not exist*/
	public boolean DeleteTask(String ID) {
		if(tasks.containsKey(ID)) {
			tasks.remove(ID);
			return true;
		}
		return false;
	}
	
	/**
	 * Changes a tasks name
	 * @param ID the 10 character ID of the task to update
	 * @param name the new string name for the task, which must be less than 20 characters, nonempty, and nonnull
	 * @return false if the ID does not exist within the set of created tasks*/
	public boolean UpdateTaskName(String ID, String name) {
		if(tasks.containsKey(ID)) {
			tasks.get(ID).SetName(name);
			return true;
		}
		return false;
	}
	
	/**
	 * Changes the description of a task
	 * @param ID The 10 character ID of the task to update
	 * @param description the string description for the task, which must be less than 50 characters, nonempty, and nonnull
	 * @return false if the ID does not exist within the set of created tasks*/
	public boolean UpdateTaskDescription(String ID, String description) {
		if(tasks.containsKey(ID)) {
			tasks.get(ID).SetDescription(description);
			return true;
		}
		return false;
	}
	
	/**
	 * @return A unique string of 10 alpha-numeric characters*/
	private String GenerateUniqueID() {
		String id = "";
		String validChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		
		for(int i = 0; i < 10; i++) {
			int randomNum = (int)(Math.random() * validChars.length());
			char character = validChars.charAt(randomNum);
			id = id + character;
		}
		
		//check of the ID already exists, and generate a new one if it does.  Otherwise return the task ID.
		
		if(tasks.containsKey(id)) {
			return GenerateUniqueID();
		}
		else return id;
	}
}
