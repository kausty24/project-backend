package com.app.service;

import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.ServiceRepository;
import com.app.dao.VendorRepository;
import com.app.dto.LoginRequest;
import com.app.dto.VendorRegistrationDTO;
import com.app.entities.Vendor;

@Service
@Transactional
public class VendorServiceImpl implements IVendorService {

	@Autowired
	private VendorRepository vendorRepo;
	
	@Autowired
	private ServiceRepository serviceRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public Vendor addNewVendor(VendorRegistrationDTO newVendor) {
		Vendor transientVendor = mapper.map(newVendor, Vendor.class);
		// to add services manually
		Set<com.app.entities.Service> services = newVendor.getServiceEnums().stream()
				.map(e->serviceRepo.findByServiceType(e).orElseThrow(()->new com.app.custom_exception.ResourceNotFoundException("No Service Found")))
				.collect(Collectors.toSet());
		transientVendor.setServices(services);
		Vendor persistentVendor = vendorRepo.save(transientVendor);
		return persistentVendor;
	}

	@Override
	public Vendor authenticateVendor(LoginRequest loginCredentials) {
		return vendorRepo.findByEmailAndPassword(loginCredentials.getEmail(), loginCredentials.getPassword()).orElseThrow(() -> new ResourceNotFoundException("Invalid Credentials"));
	}

}
