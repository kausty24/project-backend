package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {
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
}
