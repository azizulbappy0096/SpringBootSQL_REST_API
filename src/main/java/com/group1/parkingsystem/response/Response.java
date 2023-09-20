package com.group1.parkingsystem.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Response<T> {
	private boolean success;
	private String status;
	private T data;
	
	 public Response(boolean success, String status, T data) {
		 System.out.println("Res cons" + data);
		 this.success = success;
		 this.status = status;
	     this.data = data;
	 }
}
