package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long> {

}
