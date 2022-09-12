package com.app.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "orders")
public class Order extends BaseEntity {
	@ManyToOne
	private Customer customer;
	@OneToOne
	private Service service;
	private String customerComments;
	private LocalDateTime orderPlacedTime;
	private int lockoutTimeInMinutes;
	@OneToOne
	private Vendor finalVendor;
	private double finalAmount;
	private String vendorComments;
	@Column(unique = true)
	private String tempTableName;
}
