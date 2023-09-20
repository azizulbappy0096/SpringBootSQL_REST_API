package com.group1.parkingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.group1.parkingsystem.database.Conn;

@SpringBootApplication
public class ParkingsystemApplication implements AutoCloseable {

	public static void main(String[] args) {
		SpringApplication.run(ParkingsystemApplication.class, args);
		new Conn();
	}

	@Override
    public void close() {
		try {
			Conn.oracle.close();
		}catch(Exception e) {
			System.out.println("Database close Error: ");
			System.out.println(e);
		}
		
    }
}
