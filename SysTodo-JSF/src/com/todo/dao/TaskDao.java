package com.todo.dao;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.todo.model.Task;

public class TaskDao {

	private EntityManager em;
	
	public TaskDao() {
		em = com.todo.util.HibernateUtil
                .geteEntityManagerFactory().createEntityManager();
	}
	
	public void saveOrUpdate(Task task) {
        
        try {
            em.getTransaction().begin();
            if (task.getId() == null) {
                em.persist(task);
                if(FacesContext.getCurrentInstance() != null)
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Task saved successfully"));
            }else {
                em.merge(task);
                if(FacesContext.getCurrentInstance() != null)
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Task updated successfully"));
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }
	
    public void delete(Task task) {
        try {
            em.getTransaction().begin();
            Task al = em.find(Task.class, task.getId());
            em.remove(al);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }
    public List<Task> list() {
        List<Task> result = null;
        try {
            Query query = em.createQuery("FROM Task ORDER BY id DESC");
            result = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public Task findTask(Long id) {
		return em.find(Task.class, id);
	}
}
