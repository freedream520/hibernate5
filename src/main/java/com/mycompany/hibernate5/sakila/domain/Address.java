/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hibernate5.sakila.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pkipping
 */
@Entity
@Table(name = "address")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a")})
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "address_id")
	private Short addressId;
	@Basic(optional = false)
    @Column(name = "address")
	private String address;
	@Column(name = "address2")
	private String address2;
	@Basic(optional = false)
    @Column(name = "district")
	private String district;
	@Column(name = "postal_code")
	private String postalCode;
	@Basic(optional = false)
    @Column(name = "phone")
	private String phone;
	@Basic(optional = false)
    @Lob
    @Column(name = "location")
	private byte[] location;
	@Basic(optional = false)
    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdate;
	@JoinColumn(name = "city_id", referencedColumnName = "city_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private City cityId;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "addressId", fetch = FetchType.LAZY)
	private List<Staff> staffList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "addressId", fetch = FetchType.LAZY)
	private List<Store> storeList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "addressId", fetch = FetchType.LAZY)
	private List<Customer> customerList;

	public Address() {
	}

	public Address(Short addressId) {
		this.addressId = addressId;
	}

	public Address(Short addressId, String address, String district, String phone, byte[] location, Date lastUpdate) {
		this.addressId = addressId;
		this.address = address;
		this.district = district;
		this.phone = phone;
		this.location = location;
		this.lastUpdate = lastUpdate;
	}

	public Short getAddressId() {
		return addressId;
	}

	public void setAddressId(Short addressId) {
		this.addressId = addressId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public byte[] getLocation() {
		return location;
	}

	public void setLocation(byte[] location) {
		this.location = location;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public City getCityId() {
		return cityId;
	}

	public void setCityId(City cityId) {
		this.cityId = cityId;
	}

	@XmlTransient
	public List<Staff> getStaffList() {
		return staffList;
	}

	public void setStaffList(List<Staff> staffList) {
		this.staffList = staffList;
	}

	@XmlTransient
	public List<Store> getStoreList() {
		return storeList;
	}

	public void setStoreList(List<Store> storeList) {
		this.storeList = storeList;
	}

	@XmlTransient
	public List<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (addressId != null ? addressId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Address)) {
			return false;
		}
		Address other = (Address) object;
		if ((this.addressId == null && other.addressId != null) || (this.addressId != null && !this.addressId.equals(other.addressId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.mycompany.hibernate5.sakila.domain.Address[ addressId=" + addressId + " ]";
	}
	
}
