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
@Table(name = "city")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "City.findAll", query = "SELECT c FROM City c")})
public class City implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "city_id")
	private Short cityId;
	@Basic(optional = false)
    @Column(name = "city")
	private String city;
	@Basic(optional = false)
    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdate;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cityId", fetch = FetchType.LAZY)
	private List<Address> addressList;
	@JoinColumn(name = "country_id", referencedColumnName = "country_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Country countryId;

	public City() {
	}

	public City(Short cityId) {
		this.cityId = cityId;
	}

	public City(Short cityId, String city, Date lastUpdate) {
		this.cityId = cityId;
		this.city = city;
		this.lastUpdate = lastUpdate;
	}

	public Short getCityId() {
		return cityId;
	}

	public void setCityId(Short cityId) {
		this.cityId = cityId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@XmlTransient
	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}

	public Country getCountryId() {
		return countryId;
	}

	public void setCountryId(Country countryId) {
		this.countryId = countryId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (cityId != null ? cityId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof City)) {
			return false;
		}
		City other = (City) object;
		if ((this.cityId == null && other.cityId != null) || (this.cityId != null && !this.cityId.equals(other.cityId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.mycompany.hibernate5.sakila.domain.City[ cityId=" + cityId + " ]";
	}
	
}
