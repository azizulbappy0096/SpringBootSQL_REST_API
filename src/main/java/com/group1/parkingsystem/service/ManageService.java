package com.group1.parkingsystem.service;

import java.util.List;

import com.group1.parkingsystem.model.ParkingSlip;
import com.group1.parkingsystem.model.ParkingSpace;
import com.group1.parkingsystem.model.ParkingSpot;
import com.group1.parkingsystem.model.Rate;
import com.group1.parkingsystem.response.Response;

public interface ManageService {
	// Parking space
	Response<String> createParkingSpace(String type, String address);
	Response<List<ParkingSpace>> getAllParkingSpace();
	Response<ParkingSpace> getParkingSpaceById(int id);
	Response<String> deleteParkingSpaceById(int id);
	
	// Parking spot
	Response<String> createParkingSpot(String type, int space_id);
	Response<List<ParkingSpot>> getAllParkingSpot();
	Response<ParkingSpot> getParkingSpotById(int id);
	Response<String> deleteParkingSpotById(int id);
	
	// Parking rate
	Response<String> addRateToParkingSpot(int spot_id, Rate rate);
	Response<List<ParkingSpot>> allRateOfParkingSpots();
	Response<Rate> getRatebyParkingSpot(int spot_id);
	Response<Rate> updateRateToParkingSpot(int spot_id, Rate rate);
	Response<String> deleteRateToParkingSpot(int spot_id);
	
	// Parking slip
	Response<String> generateParkingSlip(int spot_id, ParkingSlip slip);
	Response<ParkingSlip> getParkingSlipById(int id);
	Response<List<ParkingSlip>> getParkingSlipBySpot(int spot_id);
	Response<List<ParkingSlip>> getParkingSlipBySpace(int space_id);
	Response<List<ParkingSlip>> getParkingSlipAuthoredByManager(int mgr_id);
	
	Response<List<ParkingSpace>> getParkingSpacesByManager(int id);
	Response<List<ParkingSpot>> getParkingSpotsByManager(int id);
	
	// assign
	Response<String> assignManagerToParkingSpace(int mgr_id, int pk_id);
	Response<String> deassignManagerToParkingSpace(int mgr_id);
}
