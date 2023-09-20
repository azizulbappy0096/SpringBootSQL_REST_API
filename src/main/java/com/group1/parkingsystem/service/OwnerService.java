package com.group1.parkingsystem.service;

import java.util.List;

import com.group1.parkingsystem.model.Owner;
import com.group1.parkingsystem.response.Response;

public interface OwnerService {
	// GET
	Response<List<Owner>> getOwner();
	Response<Owner> getOwnerById(int id);
	
	// POST
	Response<String> createOwner(String name, String address);
	
	// PUT
	Response<Owner> updateOwner(int id, String name, String address);
	
	// DELETE
}
