/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hibernate5.dto;

import com.mycompany.hibernate5.sakila.domain.Customer;
import com.mycompany.hibernate5.sakila.domain.Rental;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author pkipping
 */
@Mapper
public interface CustomerMapper {

	CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

	@Mapping(target = "addressId", ignore = true)
	@Mapping(target = "storeId", ignore = true)
	Customer customerMapper(Customer cust);

	@Mapping(target = "inventoryId", ignore = true)
	@Mapping(target = "staffId", ignore = true)
	Rental rentalMapper(Rental rent);

}
