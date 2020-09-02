/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author gamma
 */
public class EntityFacade {
    public static Customer findByID(int id) {
        Customer custToReturn = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        Query query = em.createQuery("SELECT c FROM Customer c WHERE c.id = :value");
        query.setParameter("value", id);
        custToReturn = (Customer) query.getSingleResult();
        
        return custToReturn;
    }
    
    public static List<Customer> findByLastName(String name) {
        List<Customer> customerList = new ArrayList();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c WHERE c.lastName = :value", Customer.class);
        query.setParameter("value", name);
        customerList = query.getResultList();
        
        return customerList;
    }
    
    public static long getNumberOfCustomers() {
        long numberOfCustomers = 0;
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        Query query = em.createQuery("SELECT COUNT(c) FROM Customer c");
        numberOfCustomers = (Long) query.getSingleResult();
        
        return numberOfCustomers;
    }
    
    public static List<Customer> allCustomers() {
        List<Customer> customerList = new ArrayList();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c", Customer.class);
        customerList = query.getResultList();
        
        return customerList;
    }
    
    public static Customer addCustomer(String fName, String lName) {
        Customer cust = new Customer(fName, lName);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        em.persist(cust);
        em.getTransaction().commit();
        
        
        return cust;
    }

}
