package com.group1.parkingsystem.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Owner {
	private int owner_id;
	private String owner_name;
	private String owner_address;
	
	public Owner(int owner_id, String owner_name, String owner_address) {
		this.owner_id = owner_id;
		this.owner_name = owner_name;
		this.owner_address = owner_address;
	}
}
