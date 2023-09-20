package com.group1.parkingsystem.model;

import lombok.Data;

@Data
public class ParkingSpace {
	private int space_id;    
	private String space_type;          
	private String space_address;
	private Manager managedby;
	
	public ParkingSpace() {}
	
	public ParkingSpace(int space_id, String space_type, String space_address) {
		this.space_id = space_id;
		this.space_type = space_type;
		this.space_address = space_address;
	}
	
	public ParkingSpace(int space_id, String space_type, String space_address, Manager managedby) {
		this.space_id = space_id;
		this.space_type = space_type;
		this.space_address = space_address;
		this.managedby = managedby;
	}
}
