/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Employee;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author gamma
 */
public class EmployeeFacadeTest {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static final EmployeeFacade facade = EmployeeFacade.getEmployeeFacade(emf);
    
    public EmployeeFacadeTest() {
    }

    /**
     * Test of getEmployeeById method, of class EmployeeFacade.
     */
    @Test
    public void testGetEmployeeById() {
        System.out.println("getEmployeeById");
        long id = 5L;
        String expName = "Charles";
        String expAddress = "Boylane 3";
        Employee result = facade.getEmployeeById(id);
        assertEquals(expName, result.getName());
        assertEquals(expAddress, result.getAddress());
    }

    /**
     * Test of getEmployeesByName method, of class EmployeeFacade.
     */
    @Test
    public void testGetEmployeesByName() {
        System.out.println("getEmployeesByName");
        String name = "Jake";
        int expSize = 2;
        List<Employee> result = facade.getEmployeesByName(name);
        assertEquals(expSize, result.size());
    }

    /**
     * Test of getAllEmployees method, of class EmployeeFacade.
     */
    @Test
    public void testGetAllEmployees() {
        System.out.println("getAllEmployees");
        int expSize = 6;
        List<Employee> result = facade.getAllEmployees();
        assertEquals(expSize, result.size());
    }

    /**
     * Test of getEmployeesWithHighestSalary method, of class EmployeeFacade.
     */
    @Test
    public void testGetEmployeesWithHighestSalary() {
        System.out.println("getEmployeesWithHighestSalary");
        int expSize = 2;
        List<Employee> result = facade.getEmployeesWithHighestSalary();
        assertEquals(expSize, result.size());
    }

    /**
     * Test of createEmploye method, of class EmployeeFacade.
     */
//    @Test
//    public void testCreateEmploye() {
//        System.out.println("createEmploye");
//        Employee employee = new Employee("Peter", "Roadieroad", 12345L);
//        EmployeeFacade instance = new EmployeeFacade();
//        Employee expResult = null;
//        Employee result = instance.createEmploye(employee);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

}
