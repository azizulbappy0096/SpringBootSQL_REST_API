package com.group1.parkingsystem.service;

import java.util.List;

import com.group1.parkingsystem.model.Manager;
import com.group1.parkingsystem.response.Response;

public interface ManagerService {
	Response<String> create(String name, float salary, String role, int phone, int owner_id);
	
	Response<List<Manager>> getAll();
	Response<List<Manager>> getByOwner(int owner_id);
	
	Response<String> delete(int id);
	
	Response<String> setPhone(int id, int phone);
	Response<Manager> update(int id, Manager manager);
	
	Response<List<Manager>> findByOwnerName(String name);
}
