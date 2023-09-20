package com.group1.parkingsystem.service.implementation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.group1.parkingsystem.database.Conn;
import com.group1.parkingsystem.model.Manager;
import com.group1.parkingsystem.response.Response;
import com.group1.parkingsystem.service.ManagerService;


@Service
public class ManagerServiceImp implements ManagerService {

	@Override
	public Response<String> create(String name, float salary, String role, int phone, int owner_id) {
		try {
			PreparedStatement stmt = Conn.oracle.prepareStatement("insert into manager (mgr_name, mgr_salary, mgr_role, owner_id)"
					+ " values(?, ?, ?, ?)");
			stmt.setString(1, name);
			stmt.setFloat(2, salary);
			stmt.setString(3, role);
			stmt.setInt(4, owner_id);
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
	public Response<List<Manager>> getAll() {
		try {
			List<Manager> list = new ArrayList<>();
			Statement stmt = Conn.oracle.createStatement();
			ResultSet rs = stmt.executeQuery("select m1.*, mc.mgr_phone_num from manager m1, mgr_contact mc where"
					+ " m1.mgr_id = mc.mgr_id(+)");
			while (rs.next()) {
				Manager m = new Manager(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getString(4), rs.getInt(6), rs.getInt(5));
				list.add(m);
			}
            return new Response<>(true, "ok", list);
		}catch (Exception e) {
            System.out.println(e);
            return new Response<>(false, "failed", null);
        }
	}
	
	@Override
	public Response<List<Manager>> getByOwner(int owner_id) {
		try {
			List<Manager> list = new ArrayList<>();
			PreparedStatement stmt = Conn.oracle.prepareStatement("select * from manager where owner_id = ?");
            stmt.setInt(1, owner_id);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Manager m = new Manager(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getString(4), rs.getInt(5));
				list.add(m);
			}
            return new Response<>(true, "ok", list);
		}catch (Exception e) {
            System.out.println(e);
            return new Response<>(false, "failed", null);
        }
	}
	
	@Override
	public Response<String> setPhone(int id, int phone) {
		try {
            PreparedStatement stmt = Conn.oracle.prepareStatement("insert into mgr_contact (mgr_phone_num, mgr_id) values(?, ?)");
            stmt.setInt(1, phone);
            stmt.setInt(2, id);
            stmt.execute();
            
            if (stmt.getUpdateCount() == 1) {
                System.out.println("Set Successful!!!");
                return new Response<>(true, "ok", null );
            }
       
            return new Response<>(false, "failed", "Something went wrong" );
        } catch (Exception e) {
            System.out.println(e);
            return new Response<>(false, "failed", "Something went wrong" );
        }
	}

	@Override
	public Response<Manager> update(int id, Manager manager) {
		try {
            PreparedStatement stmt = Conn.oracle.prepareStatement("update manager set mgr_name = ? where id = ?");
            stmt.setString(1, manager.getMgr_name());
            stmt.setInt(2, id);
            stmt.execute();
            
            stmt = Conn.oracle.prepareStatement("update mgr_contact set mgr_phone = ? where id = ?");
            stmt.setInt(1, manager.getMgr_phone_num());
            stmt.setInt(2, id);
            stmt.execute();
            
            if (stmt.getUpdateCount() == 1) {
                System.out.println("Delete Successful!!!");
                return new Response<>(true, "ok", manager );
            }
       
            return new Response<>(false, "failed", null );
        } catch (Exception e) {
            System.out.println(e);
            return new Response<>(false, "failed", null );
        }
	}
	
	@Override
	public Response<String> delete(int id) {
		try {
            PreparedStatement stmt = Conn.oracle.prepareStatement("delete from manager where mgr_id = ?");
            stmt.setInt(1, id);
            stmt.execute();
            if (stmt.getUpdateCount() == 1) {
                System.out.println("Delete Successful!!!");
                return new Response<>(true, "ok", null );
            }
       
            return new Response<>(false, "failed", "Manager not found with ID" + id );
        } catch (Exception e) {
            System.out.println(e);
            return new Response<>(false, "failed", "Something went wrong" );
        }
	}
	
	@Override
	public Response<List<Manager>> findByOwnerName(String name) {
		try {
			List<Manager> list = new ArrayList<>();
			PreparedStatement stmt = Conn.oracle.prepareStatement("select * from manager where owner_id = ("
					+ "select owner_id from owner where owner_name = ?)");
            stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Manager m = new Manager(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getString(4), rs.getInt(5));
				list.add(m);
			}
			
            return new Response<>(true, "ok", list);
		}catch (Exception e) {
            System.out.println(e);
            return new Response<>(false, "failed", null);
        }
	}
}
