package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.VendorRegistrationDTO;
import com.app.entities.ServiceType;
import com.app.service.IVendorService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class VendorController {
	
	@Autowired
	private IVendorService vendorService;
	
	@PostMapping("/reg/vendor")
	public ResponseEntity<?> registerVendor(@RequestBody @Valid VendorRegistrationDTO vendorDetails) {
		return new ResponseEntity<>(vendorService.addNewVendor(vendorDetails), HttpStatus.CREATED);
	}
	
	@GetMapping("/reg/list")
	public ResponseEntity<?> getAllServices() {
		return new ResponseEntity<>(ServiceType.values(), HttpStatus.OK);
	}
	
	
}
