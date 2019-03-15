package com.todo.test;

import static org.junit.Assert.*;

import javax.faces.validator.ValidatorException;

import org.junit.Before;
import org.junit.Test;

import com.todo.dao.TaskDao;
import com.todo.model.Task;
import com.todo.util.NoBlankSpaceValidator;

public class TaskTest {

	private TaskDao dao;
	private Task task;
	
	@Before
	public void setUp() throws Exception {
		this.dao = new TaskDao();
		this.task = new Task();
		task.setDescription("First Insert");
		dao.saveOrUpdate(task);
	}

	    /**
	     * Testing NoBlankSpaceValidator
	     */
		@Test(expected = ValidatorException.class)
		public void testValidate() {
			NoBlankSpaceValidator spaceValidator = new NoBlankSpaceValidator();	
			spaceValidator.validate(null, null, "        ");
		}
		
		/**
		 * Testing Task insert
		 */
		@Test
		public void testInsert() {
			Task newTask = new Task("New Task");
			dao.saveOrUpdate(newTask);
			assertNotNull(this.dao.findTask(newTask.getId()));
		}
		
		/**
		 * Testing Task update 
		 */
		@Test
		public void testEdit() {
			String newDescription = "Updated";
			task.setDescription(newDescription);
			this.dao.saveOrUpdate(task);
			assertEquals(newDescription, this.task.getDescription());
		}
		
		/**
		 * Testing Task Delete
		 */
		@Test
		public void testeDelete() {
			this.dao.delete(task);
			assertNull(this.dao.findTask(task.getId()));
		}

}
