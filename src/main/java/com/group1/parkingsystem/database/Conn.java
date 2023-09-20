package com.group1.parkingsystem.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conn {
	
	private String URL = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
	private String username = "db_group_1";
	private String password = "letsgo";
	private String driverClassName = "oracle.jdbc.OracleDriver";
	
	public static Connection oracle;
	
	public Conn() {
		try {
			Class.forName(this.driverClassName);
	        Connection con = DriverManager.getConnection(this.URL, this.username, this.password);
	        
	        Conn.oracle = con;
	        System.out.println("Database connected");
		}catch(Exception e) {
			System.out.println("Database connection Error: ");
			System.out.println(e);
		}
		
	}
	


}
