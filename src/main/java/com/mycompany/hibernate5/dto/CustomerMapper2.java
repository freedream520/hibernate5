/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hibernate5.dto;

import com.mycompany.hibernate5.sakila.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author pkipping
 */
@Mapper
public interface CustomerMapper2 {
	
	CustomerMapper2 INSTANCE = Mappers.getMapper(CustomerMapper2.class);
	
	@Mapping(target = "addressId", ignore = true)
	@Mapping(target = "storeId", ignore = true)
	@Mapping(target = "rentalList", ignore = true)
	@Mapping(target = "paymentList", ignore = true)
	Customer customerMapper(Customer cust);
	
}
