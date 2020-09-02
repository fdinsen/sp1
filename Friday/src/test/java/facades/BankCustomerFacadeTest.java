package facades;

import dto.BankCustomerDTO;
import utils.EMF_Creator;
import entities.BankCustomer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class BankCustomerFacadeTest {

    private static EntityManagerFactory emf;
    private static BankCustomerFacade facade;

    public BankCustomerFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = BankCustomerFacade.getFacadeExample(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("BankCustomer.deleteAllRows").executeUpdate();
            em.persist(new BankCustomer("Jake", "Peralta", "9876", 50.0, 100, "Terrible payer"));
            em.getTransaction().commit();
            em.getTransaction().begin();
            em.persist(new BankCustomer("Amy", "Santiago", "1234", 10000.0, 900, "A joy to have as customer"));
            em.getTransaction().commit();
            em.getTransaction().begin();
            em.persist(new BankCustomer("Charles", "Boyle", "5678", 5000.0, 654, "Easily sold anything"));
            em.getTransaction().commit();
            em.getTransaction().begin();
            em.persist(new BankCustomer("Rosa", "Diaz", "666", 4300.0, 666, "Don't piss her off"));
            em.getTransaction().commit();
            em.getTransaction().begin();
            em.persist(new BankCustomer("Terry", "Jeffords", "5555", 3000.0, 500, "Have some yoghurt ready"));
            em.getTransaction().commit();
            em.getTransaction().begin();
            em.persist(new BankCustomer("Raymond", "Holt", "100", 20000.0, 900, "Never caused an issue"));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    @Test
    public void testGetAllCustomers() {
        //Arrange
        List<BankCustomer> expList = new ArrayList();
        expList.add(new BankCustomer("Jake", "Peralta", "9876", 50.0, 100, "Terrible payer"));
        expList.add(new BankCustomer("Amy", "Santiago", "1234", 10000.0, 900, "A joy to have as customer"));
        expList.add(new BankCustomer("Charles", "Boyle", "5678", 5000.0, 654, "Easily sold anything"));
        expList.add(new BankCustomer("Rosa", "Diaz", "666", 4300.0, 666, "Don't piss her off"));
        expList.add(new BankCustomer("Terry", "Jeffords", "5555", 3000.0, 500, "Have some yoghurt ready"));
        expList.add(new BankCustomer("Raymond", "Holt", "100", 20000.0, 900, "Never caused an issue"));
        
        //Act
        List<BankCustomer> actualList = facade.getAllBankCustomers();
        
        //Assert
        assertTrue(actualList.get(0).equals(expList.get(0)));
        assertTrue(actualList.get(1).equals(expList.get(1)));
        assertTrue(actualList.get(2).equals(expList.get(2)));
        assertTrue(actualList.get(3).equals(expList.get(3)));
        assertTrue(actualList.get(4).equals(expList.get(4)));
        assertTrue(actualList.get(5).equals(expList.get(5)));
    }
    
    @Test
    public void testGetCustomerByName() {
        //Arrange
        String expName = "Jake Peralta";
        String expAccountNum = "9876";
        double expBalance = 50.0;
        
        //Act
        BankCustomerDTO actual = facade.getCustomerByName("Jake").get(0);
        
        //Assert
        assertEquals(expName, actual.getFullName());
        assertEquals(expAccountNum, actual.getAccountNumber());
        assertEquals(expBalance, actual.getBalance(), 0.5);   
    }
    
    @Test
    public void testAddCustomer() {
        //Arrange
        String expFirstName = "Gina";
        String expLastName = "Linetti";
        String expAccountNum = "1";
        double expBalance = 1000000.0;
        int expAccountRating = 800;
        String expInternalInfo = "#1";
        BankCustomer cust = new BankCustomer(expFirstName, expLastName, 
                expAccountNum, expBalance, expAccountRating, expInternalInfo);
        
        //Act
        facade.addCustomer(cust);
        BankCustomer actual = facade.getAllBankCustomers().get(6);
        
        //Assert
        assertEquals(expFirstName, actual.getFirstName());
        assertEquals(expLastName, actual.getLastName());
        assertEquals(expAccountNum, actual.getAccountNumber());
        assertEquals(expBalance, actual.getBalance(), 0.5);   
        assertEquals(expAccountRating, actual.getCustomerRating());   
        assertEquals(expInternalInfo, actual.getInternalInfo());   
    }
    
    @Test
    public void testGetCustomerById() {
        //Arrange
        BankCustomer expCust = facade.getAllBankCustomers().get(0);
        int idToCheck = expCust.getId().intValue();
        String expName = expCust.getFirstName() + " " + expCust.getLastName();
        
        //Act
        BankCustomerDTO actualCust = facade.getCustomerByID(idToCheck);
        
        //Assert
        assertEquals(expName, actualCust.getFullName());
        assertEquals(expCust.getBalance(), actualCust.getBalance(), 0.1);
        assertEquals(expCust.getAccountNumber(), actualCust.getAccountNumber());
    }
    
    @Test
    public void testFacadeCount() {
        assertEquals(6, facade.getCustomerCount(), "Expects two rows in the database");
    }

}
