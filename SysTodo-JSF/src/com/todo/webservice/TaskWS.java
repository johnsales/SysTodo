package com.todo.webservice;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.todo.dao.TaskDao;
import com.todo.model.Task;

@Path("/taskWS")
public class TaskWS {

	private final TaskDao dao = new TaskDao();

	/**
	 * AllTasks service
	 * */
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response AllTasks(){
		return Response.ok()
				.status(200)
	            .entity(dao.list())
	            .build();
	}
	
	/**
	 * addTask service
	 * */
    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
	public void addTask(String description)
	{
       Task task = new Task(description.replaceAll("\"", ""));
	   dao.saveOrUpdate(task);
	}
 
    /**
	 * updateTask service
	 * */
    @POST
    @Path("/update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
	public void updateTask(@PathParam("id") Long id, String description)
	{
    	
    	
    	Task task = dao.findTask(id);
    	if(description.startsWith("\"changeBoolean")) {
    		task.setCompletedTask(!task.getCompletedTask());
    	}else {
        	task.setDescription(description.replaceAll("\"", ""));

    	}
    	dao.saveOrUpdate(task);  
	}
 
    /**
	 * deleteTask service
	 * */
    @POST
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
	public void deleteTask(@PathParam("id") Long id)
	{
	  dao.delete(dao.findTask(id));
	}
}
