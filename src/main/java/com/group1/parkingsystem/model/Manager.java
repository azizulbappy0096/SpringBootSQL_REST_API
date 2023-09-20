package com.group1.parkingsystem.model;

import lombok.Data;


@Data
public class Manager {

	private int mgr_id;
	private String mgr_name;
	private float mgr_salary;
	private String mgr_role;
	private int owner_id;
	private int mgr_phone_num;
	
	public Manager() {}
	
	public Manager(int mgr_id, String mgr_name, float mgr_salary, String mgr_role, int mgr_phone_num, int owner_id) {
		System.out.println("I am fal");
		this.mgr_id = mgr_id;
		this.mgr_name = mgr_name;
		this.mgr_salary = mgr_salary;
		this.mgr_role = mgr_role;
		this.owner_id = owner_id;
		this.mgr_phone_num = mgr_phone_num;
	}
	
	public Manager(int mgr_id, String mgr_name, float mgr_salary, String mgr_role, int owner_id) {
		this.mgr_id = mgr_id;
		this.mgr_name = mgr_name;
		this.mgr_salary = mgr_salary;
		this.mgr_role = mgr_role;
		this.owner_id = owner_id;
	}

	public Manager(int mgr_id, String mgr_name, String mgr_role) {
		this.mgr_id = mgr_id;
		this.mgr_name = mgr_name;
		this.mgr_role = mgr_role;
	}
}
  
