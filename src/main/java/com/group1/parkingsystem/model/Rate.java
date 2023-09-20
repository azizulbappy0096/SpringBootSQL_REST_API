package com.group1.parkingsystem.model;

import lombok.Data;

@Data
public class Rate {
	private int rate_id;
	private String rate_type;
	private float hourly_rate;
	private float day_rate;
	private int spot_id;
	
	public Rate() {}
	
	public Rate(int rate_id, String rate_type, float hourly_rate, float day_rate) {
		this.rate_id = rate_id;
		this.rate_type = rate_type;
		this.hourly_rate = hourly_rate;
		this.day_rate = day_rate;
	}
	
	public Rate(int rate_id, String rate_type, float hourly_rate, float day_rate, int spot_id) {
		this.rate_id = rate_id;
		this.rate_type = rate_type;
		this.hourly_rate = hourly_rate;
		this.day_rate = day_rate;
		this.spot_id = spot_id;
	}
}
