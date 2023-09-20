package com.group1.parkingsystem.model;

import lombok.Data;

@Data
public class ParkingSpot {

	private int spot_id;    
	private String spot_type;          
	private int space_id;
	private Rate rate;
	
	public ParkingSpot() {}
	
	public ParkingSpot(int spot_id, String spot_type, int space_id) {
		this.spot_id = spot_id;
		this.spot_type = spot_type;
		this.space_id = space_id;
	}

}
