package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.BeforeEach;

import tasks.TaskService;



class TaskServiceTest {
	TaskService ts = TaskService.GetInstance();
	
	@Nested
	class UpdatingTaskFields{
		String taskID;

		@BeforeEach
		void AddTasksToTaskService() {
			taskID = ts.AddTask("task Name", "task Description");
		}
		
		/**
		 * Ensures UpdateTaskName will change the name of a task with a given ID*/
		@Test
		void UpdateTaskName() {
			String updatedTaskName = "yowza";
			ts.UpdateTaskName(taskID, updatedTaskName);
			
			assertEquals(updatedTaskName, ts.GetTask(taskID).GetName());
		}
		
		/**
		 * Ensures UpdateTaskDescription will change the description of a task with a given ID*/
		@Test
		void UpdateTaskDescription() {
			String updatedTaskDescription = "Peter Piper Picked A Pickled Pepper";
			ts.UpdateTaskDescription(taskID, updatedTaskDescription);
			
			assertEquals(updatedTaskDescription, ts.GetTask(taskID).GetDescription());
		}
		
		/**
		 * Trying to update a Task that is not within the database should return false.*/
		@Test
		void UpdatingNonExistentTasksReturnsFalse() {
			assertFalse(ts.UpdateTaskDescription("hehe", "youtube.com"));
			assertFalse(ts.UpdateTaskName("World", "of Warcraft"));
		}
	}
	
	
	@Nested
	class DeleteAddAndGetTasks{
		/**
		 * Check if the GetTask() method will throw an error if a non-existent task Id is given to it*/
		@Test
		void GetTaskThrowsErrorsIfATaskDoesNotExist() {
			assertThrows(IllegalArgumentException.class, () -> ts.GetTask("beep"));
		}
		
		/**
		 * Check both that a Task is saved to the database of tasks and its ID's are unique*/
		@Test
		void AddTaskCreatesATaskWithUniqueID() {
			String taskID = ts.AddTask("name", "desc");
			String taskID2 = ts.AddTask("name2", "desc2");
			
			//This test will fail if the created task is not within the TaskService
			assertAll(() -> ts.GetTask(taskID));
			
			//This test will fail if two tasks are created with the same ID
			assertNotEquals(taskID, taskID2);
		}
		
		/**
		 * Ensure that a deleted task is actually removed from the database*/
		@Test
		void DeleteTaskRemovesATaskPerID() {
			String taskId = ts.AddTask("woot", "boot");
			ts.DeleteTask(taskId);
			
			assertThrows(IllegalArgumentException.class, () -> ts.GetTask(taskId));
		}
	}

}
