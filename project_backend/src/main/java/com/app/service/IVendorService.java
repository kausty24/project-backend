package com.app.service;

import com.app.dto.LoginRequest;
import com.app.dto.VendorRegistrationDTO;
import com.app.entities.Vendor;

public interface IVendorService {
	public Vendor addNewVendor(VendorRegistrationDTO newVendor);
	public Vendor authenticateVendor(LoginRequest loginCredentials);
}
