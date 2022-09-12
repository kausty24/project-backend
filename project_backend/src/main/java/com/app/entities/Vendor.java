package com.app.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "vendors")
public class Vendor extends BaseEntity {
	@ManyToMany
	private Set<Service> services;
	private String name;
	@Column(unique = true)
	private String email;
	private String password;
	@Column(unique = true)
	private String contactNo;
	private String address;
	private String city;
	private String state;
	private String pincode;
	
	public void addService(ServiceType serv) {
		Service service = new Service();
		service.setServiceType(serv);
		services.add(service);
	}
}
