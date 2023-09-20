package com.group1.parkingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.group1.parkingsystem.model.Owner;
import com.group1.parkingsystem.response.Response;
import com.group1.parkingsystem.service.OwnerService;

@RestController
public class OwnerController {
	
	@Autowired
	private OwnerService oService;
	
	@GetMapping("/owner")
	public Response<List<Owner>> getOwner() {
		Response<List<Owner>> res = oService.getOwner();
		return res;
	}
	
	@GetMapping("/owner/{id}")
	public Response<Owner> getOwnerById(@PathVariable("id") int id) {
		Response<Owner> res = oService.getOwnerById(id);
		return res;
	}
	
	@PostMapping("/create/owner")
	public Response<String> createOwner(@RequestBody Owner owner) {
		Response<String> res = oService.createOwner(owner.getOwner_name(), owner.getOwner_address());
		return res;
	}
	
	@PutMapping("/owner/{id}")
	public Response<Owner> updateOwner(@PathVariable("id") int id, @RequestBody Owner owner) {
		Response<Owner> res = oService.updateOwner(id, owner.getOwner_name(), owner.getOwner_address());
		return res;
	}
}
