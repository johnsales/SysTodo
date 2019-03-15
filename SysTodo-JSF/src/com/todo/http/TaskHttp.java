package com.todo.http;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TaskHttp {
 
	private Long id;
	private String description;
	private Boolean completedTask;
 
	public TaskHttp(){
 
	}
 
	public TaskHttp(String description) {
		super();
		this.description = description;
		this.completedTask = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getCompletedTask() {
		return completedTask;
	}

	public void setCompletedTask(Boolean completedTask) {
		this.completedTask = completedTask;
	}
 
	
}
