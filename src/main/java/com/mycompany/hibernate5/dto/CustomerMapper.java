/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hibernate5.dto;

import com.mycompany.hibernate5.sakila.domain.Address;
import com.mycompany.hibernate5.sakila.domain.Customer;
import com.mycompany.hibernate5.sakila.domain.Rental;
import java.util.List;
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

//	@Mapping(target = "addressId", ignore = true)
	@Mapping(target = "storeId", ignore = true)
	@Mapping(target = "paymentList", ignore = true)
	Customer customerMapper(Customer cust);

	List<Rental> rentalListMapper(List<Rental> list);

	@Mapping(target = "inventoryId", ignore = true)
	@Mapping(target = "staffId", ignore = true)
	@Mapping(target = "customerId", ignore = true)
	@Mapping(target = "paymentList", ignore = true)
	Rental rentalMapper(Rental rent);

	@Mapping(target = "cityId", ignore = true)
	//JAXB seems to ignore object collections that don't have @XmlElementWrapper
	//which is fine since they are unintialized anyway.
	//but Mapstruct Lazy Loads the collections so it is ineffiecent.
	@Mapping(target = "staffList", ignore = true)
	@Mapping(target = "storeList", ignore = true)
	@Mapping(target = "customerList", ignore = true)
	Address addressMapper(Address adr);

}
