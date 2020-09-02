/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author gamma
 */
public class EntityTested {
    public static void main(String[] args) {
        ArrayList<Customer> customers = new ArrayList();
        
        customers.add( new Customer("Jake", "Peralta") );
        customers.add( new Customer("Amy", "Santiago") );
        customers.add( new Customer("Charles", "Boyle") );
        customers.add( new Customer("Terry", "Jeffords") );
        customers.add( new Customer("Rosa", "Diaz") );
        customers.add( new Customer("Raymond", "Holt") );
        

        
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        for(Customer cust : customers) {
            //em.persist(cust);
        }
  
        //em.getTransaction().commit();
        
        //Print all
        List<Customer> customers2 = EntityFacade.allCustomers();
        System.out.println("All Customers:");
        for(Customer cust : customers2) {
            System.out.println(cust.getFirstName() + " " + cust.getLastName());
        }
        
        //Find by ID
        Customer customer2 = EntityFacade.findByID(2);
        System.out.println("\nName of customer 2: " + customer2.getFirstName() + "\n");
        
        //Find by last name
        List<Customer> customers3 = EntityFacade.findByLastName("Jeffords");
        System.out.println("There are " + customers3.size() + " Jeffords\n");
       
        //Get number of customers
        long amountOfCustomers = EntityFacade.getNumberOfCustomers();
        System.out.println("There are " + amountOfCustomers + " customers in total.\n");
        
        //Add customer
        EntityFacade.addCustomer("Gina", "Linetti");
        System.out.println("There are now " + EntityFacade.getNumberOfCustomers() + " number of customers\n");
        
        em.close();
        emf.close();
    }
}
