/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author gamma
 */
public class EmployeeFacade {
    
    private static EmployeeFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private EmployeeFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public Employee getEmployeeById(long id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("SELECT e FROM Employee e WHERE e.id = :id");
            q.setParameter("id", id);
            Employee employee = (Employee) q.getSingleResult();
            return employee;
        } finally {
            em.close();
        }
    }

    public List<Employee> getEmployeesByName(String name) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery<Employee> q = em.createQuery("SELECT e FROM Employee e WHERE e.name = :name", Employee.class);
            q.setParameter("name", name);
            List<Employee> employees = q.getResultList();
            return employees;
        } finally {
            em.close();
        }
    }

    public List<Employee> getAllEmployees() {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery<Employee> q = em.createQuery("SELECT e FROM Employee e", Employee.class);
            List<Employee> employees = q.getResultList();
            return employees;
        } finally {
            em.close();
        }
    }

    public List<Employee> getEmployeesWithHighestSalary() {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery<Employee> q = em.createQuery("SELECT e FROM Employee e WHERE e.salary = (SELECT MAX(e.salary) FROM Employee e)", Employee.class);
            List<Employee> employees = q.getResultList();
            return employees;
        } finally {
            em.close();
        }
    }

    public Employee createEmploye(Employee employee) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
            return employee;
        } finally {
            em.close();
        }
    }
}
