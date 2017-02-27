/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hibernate5.dto;

import com.mycompany.hibernate5.sakila.domain.Address;
import com.mycompany.hibernate5.sakila.domain.Customer;
import com.mycompany.hibernate5.sakila.domain.Payment;
import com.mycompany.hibernate5.sakila.domain.Rental;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author pkipping
 */
@Mapper
public interface CustomerMapper3 {

	CustomerMapper3 INSTANCE = Mappers.getMapper(CustomerMapper3.class);

	default Customer customerMapper(Customer cust) {

		//Testing custom methods
		if (cust == null) {

			return null;
		}

		PersistenceUtil pu = Persistence.getPersistenceUtil();

		Customer customer = new Customer();

		if (pu.isLoaded(cust.getFirstName())) {
			customer.setFirstName(cust.getFirstName());
		}

		if (pu.isLoaded(cust.getLastName())) {
			customer.setLastName(cust.getLastName());
		}

		List<Rental> list = cust.getRentalList();

		if (list != null && pu.isLoaded(list)) {

			customer.setRentalList(new ArrayList<Rental>(list));
		}

		List<Payment> list_ = cust.getPaymentList();

		if (list_ != null && pu.isLoaded(list)) {

			customer.setPaymentList(new ArrayList<Payment>(list_));
		}

		if (pu.isLoaded(cust.getAddressId())) {
			customer.setAddressId(cust.getAddressId());
		}

		if (pu.isLoaded(cust.getStoreId())) {
			customer.setStoreId(cust.getStoreId());
		}

		return customer;

	}

//	default Object mapLoadedEntity(Object obj) {
//
//		PersistenceUtil pu = Persistence.getPersistenceUtil();
//
//		if (pu.isLoaded(obj)) {
//			return obj;
//		} else {
//			return null;
//		}
//
//	}
//	default boolean isLoadedEntity(Object obj) {
//
//		PersistenceUtil pu = Persistence.getPersistenceUtil();
//		return pu.isLoaded(obj);
//	}
}
