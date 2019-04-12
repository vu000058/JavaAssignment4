package com.algonquincollege.cst8277.models;

import static com.algonquincollege.cst8277.models.TestSuiteConstants.buildEntityManagerFactory;
import static org.junit.Assert.assertEquals;

import java.lang.invoke.MethodHandles;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.h2.tools.Server;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ItemTestSuite implements TestSuiteConstants {
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
    public void add_item() {
        EntityManager em = emf.createEntityManager();
       
        Item item = new Item();
        item.setName("Book");
        item.setPrice(1000);
        em.getTransaction().begin();
        em.persist(item);
        em.getTransaction().commit();
        
        em.refresh(item);
        
        Item emp1 = em.find(Item.class, 1);
      
        assertEquals(emp1.getPrice(), 1000, 0.00001);

        em.close();
    }
}
