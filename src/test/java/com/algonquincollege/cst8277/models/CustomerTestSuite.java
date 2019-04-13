package com.algonquincollege.cst8277.models;

import static com.algonquincollege.cst8277.models.TestSuiteConstants.buildEntityManagerFactory;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.h2.tools.Server;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerTestSuite implements TestSuiteConstants {
    private static final Class<?> _thisClaz = MethodHandles.lookup().lookupClass();
    private static final Logger logger = LoggerFactory.getLogger(_thisClaz);
    
    public static EntityManagerFactory emf;
    public static Server server;
    
    @BeforeClass
    public static void oneTimeSetUp() {
        try {
            logger.debug("oneTimeSetUp");
            server = Server.createTcpServer().start();
            emf = buildEntityManagerFactory(_thisClaz.getSimpleName());
        }
        catch (Exception e) {
            logger.error("something went wrong building EntityManagerFactory", e);
        }
    }
    
    @AfterClass
    public static void oneTimeTearDown() {
        logger.debug("oneTimeTearDown");
        if (emf != null) {
            emf.close();
        }
        if (server != null) {
            server.stop();
        }
    }
    
    @Test
    public void add_customer() {
        EntityManager em = emf.createEntityManager();
       
        Customer customer = new Customer();
        customer.setFirstName("Bilal");
        customer.setLastName("Awneh");
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
        em.refresh(customer);
        Customer cus1 = em.find(Customer.class, customer.getId());
        assertEquals(cus1.getFirstName(), "Bilal");
        
        em.close();
    }
    
    @Test
    public void delete_customer() {
        EntityManager em = emf.createEntityManager();
       
        Customer customer = new Customer();
        customer.setFirstName("Bilal2");
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
        em.refresh(customer);
        Customer cus1 = em.find(Customer.class, customer.getId());
        assertEquals(cus1.getFirstName(), "Bilal2");
        
        em.getTransaction().begin();
        em.remove(customer);
        em.getTransaction().commit();
        
        assertNull(em.find(Customer.class, customer.getId()));
        em.close();

    }
    
    @Test
    public void add_carts_to_customer() {
        EntityManager em = emf.createEntityManager();
        Customer customer = new Customer();
        customer.setFirstName("Bilal3");
        ArrayList<Cart> carts = new ArrayList<>();
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();
        Cart cart3 = new Cart();
        carts.add(cart1);
        carts.add(cart2);
        carts.add(cart3);
        customer.setCarts(carts);
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
        Customer cust1 = em.find(Customer.class, customer.getId());
        assertEquals(cust1.getCarts().size(), 3);
        
        em.close();
    }
    
    @Test
    public void delete_cart_from_customer() {
        EntityManager em = emf.createEntityManager();
        Customer customer = new Customer();
        customer.setFirstName("Bilal3");
        ArrayList<Cart> carts = new ArrayList<>();
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();
        Cart cart3 = new Cart();
        carts.add(cart1);
        carts.add(cart2);
        carts.add(cart3);
        customer.setCarts(carts);
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
        Customer cust1 = em.find(Customer.class, customer.getId());
        assertEquals(cust1.getCarts().size(), 3);
        
        em.getTransaction().begin();
        em.remove(cart2);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        em.refresh(cust1);
        em.getTransaction().commit();
        
        assertEquals(cust1.getCarts().size(), 2);

        
        em.close();
    }
    
}
