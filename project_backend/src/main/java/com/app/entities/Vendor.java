package com.app.entities;

import java.util.Set;

import javax.persistence.Entity;
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
	@OneToMany
	private Set<Service> services;
	private String name;
	private String email;
	private String password;
	private String contactNo;
	private String address;
}
