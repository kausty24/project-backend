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

import com.app.dto.FindContact;
import com.app.dto.FindEmail;
import com.app.dto.LoginRequest;
import com.app.dto.VendorRegistrationDTO;
import com.app.dto.VendorUpdateDTO;
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
	
	@PostMapping("/login/vendor")
	public ResponseEntity<?> loginCustomer(@RequestBody @Valid LoginRequest loginCredentials) {
		return new ResponseEntity<>(vendorService.authenticateVendor(loginCredentials), HttpStatus.OK);
	}
	
	@PostMapping("/edit/vendor")
	public ResponseEntity<?> updateVendor(@RequestBody @Valid VendorUpdateDTO updateDetails){
		return new ResponseEntity<>(vendorService.updateVendorDetail(updateDetails),HttpStatus.OK);
	}
	
	@PostMapping("/edit/vendor/emailExist")
	public ResponseEntity<?> searchEmail(@RequestBody @Valid FindEmail emailId){
		return new ResponseEntity<>(vendorService.findEmailId(emailId),HttpStatus.OK);
	}
	
	@PostMapping("/edit/vendor/contactExists")
	public ResponseEntity<?> searchContact(@RequestBody @Valid FindContact contactNo){
		return new ResponseEntity<>(vendorService.findContactNo(contactNo),HttpStatus.OK);
	}
}
