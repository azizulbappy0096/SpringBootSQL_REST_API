package com.group1.parkingsystem.model;

import lombok.Data;

@Data
public class ParkingSlip {
	private int slip_id;
	private String slip_type;
	private float duration;
	private String issue_date;
	private int spot_id;
	
	public ParkingSlip() {}

	public ParkingSlip(int slip_id, String slip_type, float duration, String issue_date, int spot_id) {
		this.slip_id = slip_id;
		this.slip_type = slip_type;
		this.duration = duration;
		this.issue_date = issue_date;
		this.spot_id = spot_id;
	}
	
}
