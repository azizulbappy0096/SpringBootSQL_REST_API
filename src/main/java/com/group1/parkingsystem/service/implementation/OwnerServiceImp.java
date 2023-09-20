package com.group1.parkingsystem.service.implementation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.group1.parkingsystem.database.Conn;
import com.group1.parkingsystem.model.Owner;
import com.group1.parkingsystem.response.Response;
import com.group1.parkingsystem.service.OwnerService;

@Service
public class OwnerServiceImp implements OwnerService {
	
	@Override
	public Response<List<Owner>> getOwner() {
		try {
			List<Owner> list = new ArrayList<>();
			Statement stmt = Conn.oracle.createStatement();
			ResultSet rs = stmt.executeQuery("select * from owner");
			while (rs.next()) {
				Owner o = new Owner(rs.getInt(1), rs.getString(2), rs.getString(3));
				list.add(o);
			}
            return new Response<>(true, "ok", list);
		}catch (Exception e) {
            System.out.println(e);
            return new Response<>(false, "failed", null);
        }
	
	}
	
	@Override
	public Response<Owner> getOwnerById(int id) {
		try {
			PreparedStatement stmt = Conn.oracle.prepareStatement("select * from owner where owner_id = ?");
            stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				Owner o = new Owner(rs.getInt(1), rs.getString(2), rs.getString(3));
				 return new Response<>(true, "ok", o);
			}
			
			return new Response<>(false, "failed", null);
		}catch (Exception e) {
            System.out.println(e);
            return new Response<>(false, "failed", null);
        }
	}

	@Override
	public Response<String> createOwner(String name, String address) {
		try {
			PreparedStatement stmt = Conn.oracle.prepareStatement("insert into owner (owner_name, owner_address) values(?, ?)");
			stmt.setString(1, name);
			stmt.setString(2, address);
			
			stmt.execute();
            if (stmt.getUpdateCount() == 1) {
                System.out.println("Create Successful!!!");
                return new Response<>(true, "ok", null );
            }
            return new Response<>(false, "failed", "Something went wrong" );
        } catch (Exception e) {
            System.out.println(e);
            return new Response<>(false, "failed", "Something went wrong" );
        }
	}
	
	@Override
	public Response<Owner> updateOwner(int id, String name, String address) {
		try {
			PreparedStatement stmt = Conn.oracle.prepareStatement("update owner set owner_name = ?, owner_address = ? where owner_id = ?");
			stmt.setString(1, name);
			stmt.setString(2, address);
			stmt.setInt(3, id);
			stmt.execute();
			
            if (stmt.getUpdateCount() == 1) {
                System.out.println("Update Successful!!!");
                return this.getOwnerById(id);
            }
            return new Response<>(false, "failed", null );
        } catch (Exception e) {
            System.out.println(e);
            return new Response<>(false, "failed", null);
        }
	}

}
