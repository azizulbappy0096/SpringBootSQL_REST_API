package com.group1.parkingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group1.parkingsystem.model.Manager;
import com.group1.parkingsystem.response.Response;
import com.group1.parkingsystem.service.ManagerService;

@RestController
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	private ManagerService mService;
	
	@GetMapping("/all")
	public Response<List<Manager>> getAll() {
		Response<List<Manager>> res = mService.getAll();
		return res;
	}
	
	@GetMapping("/{ownerId}")
	public Response<List<Manager>> getByOwner(@PathVariable("ownerId") int ownerId) {
		Response<List<Manager>> res = mService.getByOwner(ownerId);
		return res;
	}
	
	@GetMapping("/query/{owner_name}")
	public Response<List<Manager>> getByOwner(@PathVariable("owner_name") String owner_name) {
		Response<List<Manager>> res = mService.findByOwnerName(owner_name);
		return res;
	}
	
	@PostMapping("")
	public Response<String> create(@RequestBody Manager manager) {
		Response<String> res = mService.create(manager.getMgr_name(), manager.getMgr_salary(), manager.getMgr_role(), manager.getMgr_phone_num(), manager.getOwner_id());
		return res;
	}
	
	@PostMapping("/{id}/set_phone")
	public Response<String> setPhone(@PathVariable("id") int id, @RequestBody Manager manager) {
		Response<String> res = mService.setPhone(id, manager.getMgr_phone_num());
		return res;
	}
	
	@PutMapping("/{id}")
	public Response<Manager> update(@PathVariable("id") int id, @RequestBody Manager manager) {
		Response<Manager> res = mService.update(id, manager);
		return res;
	}
	
	@DeleteMapping("/{id}")
	public Response<String> delete(@PathVariable("id") int id) {
		Response<String> res = mService.delete(id);
		return res;
	}
}
