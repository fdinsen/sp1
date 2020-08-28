/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author gamma
 */
public class MakeTestData {
    
    public static void main(String[] args) {
        create();
    }
    
    public static void create() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new BankCustomer("Jake", "Peralta", "9876", 50.0, 100, "Terrible payer"));
        em.persist(new BankCustomer("Amy", "Santiago", "1234", 10000.0, 900, "A joy to have as customer"));
        em.persist(new BankCustomer("Charles", "Boyle", "5678", 5000.0, 654, "Easily sold anything"));
        em.persist(new BankCustomer("Rosa", "Diaz", "666", 4300.0, 666, "Don't piss her off"));
        em.persist(new BankCustomer("Terry", "Jeffords", "5555", 3000.0, 500, "Have some yoghurt ready"));
        em.persist(new BankCustomer("Raymond", "Holt", "1", 20000.0, 900, "Never caused an issue"));
        em.getTransaction().commit();
        em.close();
    }
}
