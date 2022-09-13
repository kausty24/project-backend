package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	public Optional<Customer> findByEmailAndPassword(String email, String password);
}
