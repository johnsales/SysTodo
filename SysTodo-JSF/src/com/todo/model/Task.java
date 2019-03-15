package com.todo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Task", schema = "public")
public class Task implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String description;
	@Column(nullable=false)
	private Boolean completedTask;
	
	
	public Task() {
		super();
		this.completedTask = false;
	}
	
	public Task(String description) {
		super();
		this.description = description;
		this.completedTask = false;
	}
	
	public Task(Long id, String description) {
		super();
		this.id = id;
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
