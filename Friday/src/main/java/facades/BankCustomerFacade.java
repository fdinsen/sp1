package facades;

import dto.BankCustomerDTO;
import entities.BankCustomer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;


public class BankCustomerFacade {

    private static BankCustomerFacade instance;
    private static EntityManagerFactory emf;
    
    public BankCustomerDTO getCustomerByID(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<BankCustomer> q = em.createQuery("SELECT bc FROM BankCustomer bc WHERE bc.id = :id", BankCustomer.class);
            q.setParameter("id", id);
            return new BankCustomerDTO( q.getSingleResult() );
        } finally {
            em.close();
        }
    }
    
    public List<BankCustomerDTO> getCustomerByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<BankCustomer> q = em.createQuery("SELECT bc FROM BankCustomer bc WHERE bc.firstName = :name", BankCustomer.class);
            q.setParameter("name", name);
            List<BankCustomerDTO> customers = new ArrayList();
            q.getResultList().stream().forEach(c -> {
                customers.add(new BankCustomerDTO(c));
            });
            return customers;
        }finally {
            em.close();
        }
    }
    
    public BankCustomer addCustomer(BankCustomer cust) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cust);
            return cust;
        }finally {
            em.getTransaction().commit();
            em.close();
        }
    }
    
    public List<BankCustomer> getAllBankCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<BankCustomer> q = em.createQuery("SELECT bc FROM BankCustomer bc", BankCustomer.class);
            List<BankCustomer> allCustomers = q.getResultList();
            return allCustomers;
        }finally {
            em.close();
        }
    }
    
    //TODO Remove/Change this before use
    public long getCustomerCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long customerCount = (long)em.createQuery("SELECT COUNT(c) FROM BankCustomer c").getSingleResult();
            return customerCount;
        }finally{  
            em.close();
        }
        
    }

    
    
    //Private Constructor to ensure Singleton
    private BankCustomerFacade() {}
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static BankCustomerFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BankCustomerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

}
