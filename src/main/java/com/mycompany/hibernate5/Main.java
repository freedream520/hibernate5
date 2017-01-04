/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hibernate5;

import com.mycompany.hibernate5.sakila.domain.Address;
import com.mycompany.hibernate5.sakila.domain.Customer;
import com.mycompany.hibernate5.sakila.domain.Customer_;
import com.mycompany.hibernate5.sakila.domain.Address_;
import com.mycompany.hibernate5.sakila.domain.Payment;
import com.mycompany.hibernate5.sakila.domain.Rental;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

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

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sakila");

		try {
			criteriaQuery(emf);
//			nativeQuery(emf);
//			testJPA(emf);
		} finally {
			if (emf != null && emf.isOpen()) {
				emf.close();
			}
			lg.info("Closing EM Factory");
		}
	}

	public static void nativeQuery(EntityManagerFactory emf) {

		EntityManager em = emf.createEntityManager();
		try {
			String q = "select c from Customer c WHERE c.lastName = :lastName";
			em.getTransaction().begin();
			List<Customer> list = em.createQuery(q, Customer.class)
				.setParameter("lastName", "Vest")
				//				.setHint( "org.hibernate.readOnly", true )
				.getResultList();

			for (Customer c : list) {
				lg.info("Customer Name: " + c.getFirstName() + " " + c.getLastName());
				c.setFirstName("JULIAN");
				lg.info("Address: " + c.getAddressId().getAddress());
			}

			//Native queries
			List<Object[]> cust1 = em.createNativeQuery(
				"SELECT * FROM customer LIMIT 5")
				.getResultList();

			for (Object[] c : cust1) {
				lg.info("Name: " + c[2]);
			}

			//Entity Queries
			List<Customer> cust2 = em.createNativeQuery(
				"SELECT * FROM customer LIMIT 5", Customer.class)
				.getResultList();

			em.getTransaction().commit();
			em.close();

		} catch (Exception e) {
			lg.log(Level.SEVERE, e.getMessage(), e);
//			e.printStackTrace();
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
	}

	public static void testJPA(EntityManagerFactory emf) {
		//Testing JPA

		EntityManager em = null;
		try {
			em = emf.createEntityManager();

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
				for (Rental r : c.getRentalList()) {
					lg.log(Level.INFO, "Rental ID: {0} rental date {1}", new Object[]{r.getRentalId(), r.getRentalDate()});
					++i;
					lg.info("Int i: " + i);
					if (i > 4) {
						break;
					}
				}
				for (Payment p : c.getPaymentList()) {
					lg.log(Level.INFO, "Payment id: {0} Amount: {1}", new Object[]{p.getPaymentId(), p.getAmount()});
				}
			}

		} catch (Exception e) {
			lg.log(Level.SEVERE, e.getMessage(), e);
//			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	private static void criteriaQuery(EntityManagerFactory emf) {
		
		EntityManager em = emf.createEntityManager();
		try {
			lg.info("testing criteria query in hibernate");
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Customer> criteria = builder.createQuery( Customer.class );
			Root<Customer> root = criteria.from( Customer.class );
			Join<Customer, Address> address = root.join(Customer_.addressId);
			criteria.select(root);
			criteria.where( builder.and( builder.equal(root.get( Customer_.lastName ), "Vest")
				, builder.like(address.get( Address_.address ), "923%") ) );
			
			List<Customer> customers = em.createQuery( criteria ).getResultList();
			for (Customer cust : customers) {
				lg.info("Customer Name: " + cust.getFirstName() + " " + cust.getLastName());
				lg.info("Address: " + cust.getAddressId().getAddress());
			}
			
		} catch (Exception e) {
			lg.log(Level.SEVERE, e.getMessage(), e);
//			e.printStackTrace();
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
		
	}

}
