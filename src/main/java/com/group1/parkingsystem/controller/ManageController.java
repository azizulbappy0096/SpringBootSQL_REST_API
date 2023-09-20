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

import com.group1.parkingsystem.model.ParkingSlip;
import com.group1.parkingsystem.model.ParkingSpace;
import com.group1.parkingsystem.model.ParkingSpot;
import com.group1.parkingsystem.model.Rate;
import com.group1.parkingsystem.response.Response;
import com.group1.parkingsystem.service.ManageService;

@RestController
@RequestMapping("/manage")
public class ManageController {
	
	@Autowired
	private ManageService mService;
	
	// Parking space
	@PostMapping("/parking_space/create")
	public Response<String> createParkingSpace(@RequestBody ParkingSpace pSpace) {
		Response<String> res = mService.createParkingSpace(pSpace.getSpace_type(), pSpace.getSpace_address());
		return res;
	}
	
	@GetMapping("/parking_space")
	public Response<List<ParkingSpace>> getAllParkingSpace() {
		Response<List<ParkingSpace>> res = mService.getAllParkingSpace();
		return res;
	}
	
	@GetMapping("/parking_space/{id}")
	public Response<ParkingSpace> getParkingSpaceById(@PathVariable("id") int id) {
		Response<ParkingSpace> res = mService.getParkingSpaceById(id);
		return res;
	}
	
	@DeleteMapping("/parking_space/{id}")
	public Response<String> deleteParkingSpaceById(@PathVariable("id") int id) {
		Response<String> res = mService.deleteParkingSpaceById(id);
		return res;
	}

	// Parking spot
	@PostMapping("/parking_spot/create")
	Response<String> createParkingSpot(@RequestBody ParkingSpot pSpot) {
		Response<String> res = mService.createParkingSpot(pSpot.getSpot_type(), pSpot.getSpace_id());
		return res;
	}
	
	@GetMapping("/parking_spot")
	Response<List<ParkingSpot>> getAllParkingSpot() {
		Response<List<ParkingSpot>> res = mService.getAllParkingSpot();
		return res;
	}
	
	@GetMapping("/parking_spot/{id}")
	Response<ParkingSpot> getParkingSpotById(@PathVariable("id") int id) {
		Response<ParkingSpot> res = mService.getParkingSpotById(id);
		return res;
	}
	
	@DeleteMapping("/parking_spot/{id}")
	Response<String> deleteParkingSpotById(@PathVariable("id") int id) {
		Response<String> res = mService.deleteParkingSpotById(id);
		return res;
	}
	
	// Parking rate
	@PostMapping("/rate/{parking_spot}")
	Response<String> addRateToParkingSpot(@PathVariable("parking_spot") int spot_id, @RequestBody Rate rate) {
		Response<String> res = mService.addRateToParkingSpot(spot_id, rate);
		return res;
	}
	
	@GetMapping("/rate")
	Response<List<ParkingSpot>> allRateOfParkingSpots() {
		Response<List<ParkingSpot>> res = mService.allRateOfParkingSpots();
		return res;
	}
	
	@GetMapping("/rate/parking_spot/{spot_id}")
	Response<Rate> getRatebyParkingSpot(@PathVariable("spot_id") int spot_id) {
		Response<Rate> res = mService.getRatebyParkingSpot(spot_id);
		return res;
	}
	
	@PutMapping("/rate/parking_spot/{spot_id}")
		Response<Rate> updateRateToParkingSpot(@PathVariable("spot_id") int spot_id, @RequestBody Rate rate) {
		Response<Rate> res = mService.updateRateToParkingSpot(spot_id, rate);
		return res;
	}
	
	@DeleteMapping("/rate/parking_spot/{spot_id}")
	Response<String> deleteRateToParkingSpot(@PathVariable("spot_id") int spot_id) {
		Response<String> res = mService.deleteRateToParkingSpot(spot_id);
		return res;
	}
		
	// Parking slip
	@PostMapping("/parking_slip/{spot_id}")
	Response<String> generateParkingSlip(@PathVariable("spot_id") int spot_id, @RequestBody ParkingSlip slip) {
		Response<String> res = mService.generateParkingSlip(spot_id, slip);
		return res;
	}
	
	@GetMapping("/parking_slip/{id}")
	Response<ParkingSlip> getParkingSlipById(@PathVariable("id") int id) {
		Response<ParkingSlip> res = mService.getParkingSlipById(id);
		return res;
	}
	
	@GetMapping("/parking_slip/spot/{spot_id}")
	Response<List<ParkingSlip>> getParkingSlipBySpot(@PathVariable("spot_id") int spot_id) {
		Response<List<ParkingSlip>> res = mService.getParkingSlipBySpot(spot_id);
		return res;
	}
	
	@GetMapping("/parking_slip/space/{space_id}")
	Response<List<ParkingSlip>> getParkingSlipBySpace(@PathVariable("space_id") int space_id) {
		Response<List<ParkingSlip>> res = mService.getParkingSlipBySpace(space_id);
		return res;
	}
	
	@GetMapping("/parking_slip/authorized/{mgr_id}")
	Response<List<ParkingSlip>> getParkingSlipAuthoredByManager(@PathVariable("mgr_id") int mgr_id) {
		Response<List<ParkingSlip>> res = mService.getParkingSlipAuthoredByManager(mgr_id);
		return res;
	}
	
	@GetMapping("/parking_spaces/assigned/{mgr_id}")
	Response<List<ParkingSpace>> getParkingSpacesByManager(@PathVariable("mgr_id") int id) {
		Response<List<ParkingSpace>> res = mService.getParkingSpacesByManager(id);
		return res;
	}
		
//		Response<List<ParkingSpot>> getParkingSpotsByManager(int id);
		
	// assign
	@PostMapping("/assign/manager/{mgr_id}")
	Response<String> assignManagerToParkingSpace(@PathVariable("mgr_id") int mgr_id, @RequestBody ParkingSpace pSpace) {
		Response<String> res = mService.assignManagerToParkingSpace(mgr_id, pSpace.getSpace_id());
		return res;
	}
	
	@DeleteMapping("/deassign/manager/{mgr_id}")
	Response<String> deassignManagerToParkingSpace(@PathVariable("mgr_id") int mgr_id) {
		Response<String> res = mService.deassignManagerToParkingSpace(mgr_id);
		return res;
	}
}
