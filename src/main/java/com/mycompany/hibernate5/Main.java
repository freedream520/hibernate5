/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hibernate5;

import com.mycompany.hibernate5.sakila.domain.Customer;
import com.mycompany.hibernate5.sakila.domain.Payment;
import com.mycompany.hibernate5.sakila.domain.Rental;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author pkipping
 */
public class Main {

	private static final Logger lg = Logger.getLogger(Main.class.getName());
	
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Hello World!");
		lg.info("Testing logger");
		
		//Testing JPA
        EntityManagerFactory factory = null;
        EntityManager em = null;
        try {
            factory = Persistence.createEntityManagerFactory("sakila");
            em = factory.createEntityManager();

            //do queries
            String ql = "select c from Customer c "
                    + "join fetch c.rentalList r "
//                    + "join fetch c.paymentList p "
                    + "where c.lastName = :lastName";
            List<Customer> list = em.createQuery(ql).setParameter("lastName", "Vest")
                    .setMaxResults(10).getResultList();

            for (Customer c : list) {
                lg.log(Level.INFO, "Customer Name {0}", c.getLastName());
                int i = 0;
                for (Rental r: c.getRentalList()) {
                    lg.log(Level.INFO, "Rental ID: {0} rental date {1}", new Object[]{r.getRentalId(), r.getRentalDate()});
                    ++i;
                    lg.info("Int i: " + i);
                    if (i>4) break;
                }
                for (Payment p : c.getPaymentList()) {
                    lg.info("Payment id: " + p.getPaymentId() + " Amount: " + p.getAmount());
                }
            }

        } catch (Exception e) {
            lg.log(Level.SEVERE, e.getMessage(), e);
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
            if (factory != null) {
                factory.close();
            }
        }
		
    }
    
}
