package com.group1.parkingsystem.service.implementation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.group1.parkingsystem.database.Conn;
import com.group1.parkingsystem.model.Manager;
import com.group1.parkingsystem.model.ParkingSlip;
import com.group1.parkingsystem.model.ParkingSpace;
import com.group1.parkingsystem.model.ParkingSpot;
import com.group1.parkingsystem.model.Rate;
import com.group1.parkingsystem.response.Response;
import com.group1.parkingsystem.service.ManageService;

@Service
public class ManageServicceImp implements ManageService {
	// Parking space
	@Override
	public Response<String> createParkingSpace(String type, String address) {
		try {
			PreparedStatement stmt = Conn.oracle.prepareStatement("insert into parking_space (space_type, space_address)"
					+ " values(?, ?)");
			stmt.setString(1, type);
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
	public Response<List<ParkingSpace>> getAllParkingSpace() {
		try {
			List<ParkingSpace> list = new ArrayList<>();
			Statement stmt = Conn.oracle.createStatement();
			ResultSet rs = stmt.executeQuery("select p.*, m.* from parking_space p, manager m, mgr_space mc"
					+ " where mc.space_id=p.space_id and mc.mgr_id=m.mgr_id");
			while (rs.next()) {
				   System.out.println("sdf"+rs.getString(7));
				ParkingSpace p = new ParkingSpace(rs.getInt(1), rs.getString(2), rs.getString(3));
				Manager m = new Manager(rs.getInt(4), rs.getString(5), rs.getString(7));
				p.setManagedby(m);
				list.add(p);
			}
            return new Response<>(true, "ok", list);
		}catch (Exception e) {
            System.out.println(e);
            return new Response<>(false, "failed", null);
        }
	}

	@Override
	public Response<ParkingSpace> getParkingSpaceById(int id) {
		try {
			PreparedStatement stmt = Conn.oracle.prepareStatement("select * from parking_space where space_id = ?");
            stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ParkingSpace p = new ParkingSpace(rs.getInt(1), rs.getString(2), rs.getString(3));
				return new Response<>(true, "ok", p);
			}
            return new Response<>(true, "ok", null);
		}catch (Exception e) {
            System.out.println(e);
            return new Response<>(false, "failed", null);
        }
	}
	
	@Override
	public Response<String> deleteParkingSpaceById(int id) {
		try {
            PreparedStatement stmt = Conn.oracle.prepareStatement("delete from parking_space where space_id = ?");
            stmt.setInt(1, id);
            stmt.execute();
            if (stmt.getUpdateCount() == 1) {
                System.out.println("Delete Successful!!!");
                return new Response<>(true, "ok", null );
            }
       
            return new Response<>(false, "failed", "Parking space not found with ID" + id );
        } catch (Exception e) {
            System.out.println(e);
            return new Response<>(false, "failed", "Something went wrong" );
        }
	}
	
	// Parking spot
	@Override
	public Response<String> createParkingSpot(String type, int space_id) {
		try {
			PreparedStatement stmt = Conn.oracle.prepareStatement("insert into parking_spot (spot_type, space_id)"
					+ " values(?, ?)");
			stmt.setString(1, type);
			stmt.setInt(2, space_id);
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
	public Response<List<ParkingSpot>> getAllParkingSpot() {
		try {
			List<ParkingSpot> list = new ArrayList<>();
			Statement stmt = Conn.oracle.createStatement();
			ResultSet rs = stmt.executeQuery("select * from parking_spot");
			while (rs.next()) {
				ParkingSpot p = new ParkingSpot(rs.getInt(1), rs.getString(2), rs.getInt(3));
				list.add(p);
			}
            return new Response<>(true, "ok", list);
		}catch (Exception e) {
            System.out.println(e);
            return new Response<>(false, "failed", null);
        }
	}
	
	@Override
	public Response<ParkingSpot> getParkingSpotById(int id){
		try {
			PreparedStatement stmt = Conn.oracle.prepareStatement("select * from parking_spot where spot_id = ?");
            stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ParkingSpot p = new ParkingSpot(rs.getInt(1), rs.getString(2), rs.getInt(3));
				return new Response<>(true, "ok", p);
			}
            return new Response<>(true, "ok", null);
		}catch (Exception e) {
            System.out.println(e);
            return new Response<>(false, "failed", null);
        }
	}
	
	@Override
	public Response<String> deleteParkingSpotById(int id){
		try {
            PreparedStatement stmt = Conn.oracle.prepareStatement("delete from parking_spot where spot_id = ?");
            stmt.setInt(1, id);
            stmt.execute();
            if (stmt.getUpdateCount() == 1) {
                System.out.println("Delete Successful!!!");
                return new Response<>(true, "ok", null );
            }
       
            return new Response<>(false, "failed", "Parking spot not found with ID" + id );
        } catch (Exception e) {
            System.out.println(e);
            return new Response<>(false, "failed", "Something went wrong" );
        }
	}
	
	// Parking rate
	@Override
	public Response<String> addRateToParkingSpot(int spot_id, Rate rate) {
		try {
			PreparedStatement stmt = Conn.oracle.prepareStatement("insert into rate (rate_type, hourly_rate, day_rate, spot_id)"
					+ " values(?, ?, ?, ?)");
			stmt.setString(1, rate.getRate_type());
			stmt.setFloat(2, rate.getHourly_rate());
			stmt.setFloat(3, rate.getDay_rate());
			stmt.setInt(4, spot_id);
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
	public Response<List<ParkingSpot>> allRateOfParkingSpots() {
		try {
			List<ParkingSpot> list = new ArrayList<>();
			Statement stmt = Conn.oracle.createStatement();
			ResultSet rs = stmt.executeQuery("select ps.*, r.rate_id, r.rate_type, r.hourly_rate, r.day_rate "
					+ "from rate r, parking_spot ps where r.spot_id (+)= ps.spot_id ");
			while (rs.next()) {
				ParkingSpot p = new ParkingSpot(rs.getInt(1), rs.getString(2), rs.getInt(3));
				Rate r;
				if(rs.getInt(4) == 0) {
					r = null;
				}else {
					r = new Rate(rs.getInt(4), rs.getString(5), rs.getFloat(6), rs.getFloat(7));
				}
				p.setRate(r);
				list.add(p);
			}
            return new Response<>(true, "ok", list);
		}catch (Exception e) {
            System.out.println(e);
            return new Response<>(false, "failed", null);
        }
	}
	
	@Override
	public Response<Rate> getRatebyParkingSpot(int spot_id) {
		try {
			PreparedStatement stmt = Conn.oracle.prepareStatement("select * from rate where spot_id = ?");
            stmt.setInt(1, spot_id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Rate r = new Rate(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getFloat(4), rs.getInt(5));
				return new Response<>(true, "ok", r);
			}
            return new Response<>(true, "ok", null);
		}catch (Exception e) {
            System.out.println(e);
            return new Response<>(false, "failed", null);
        }
	}
	
	@Override
	public Response<Rate> updateRateToParkingSpot(int spot_id, Rate rate) {
		try {
            PreparedStatement stmt = Conn.oracle.prepareStatement("update rate set rate_type = ?, hourly_rate = ?"
            		+ ", day_rate = ? where spot_id = ?");
            stmt.setString(1, rate.getRate_type());
            stmt.setFloat(2, rate.getHourly_rate());
            stmt.setFloat(3, rate.getDay_rate());
            stmt.setInt(4, spot_id);
            stmt.execute();
            
            if (stmt.getUpdateCount() == 1) {
                System.out.println("Delete Successful!!!");
                return this.getRatebyParkingSpot(spot_id);
            }
       
            return new Response<>(false, "failed", null );
        } catch (Exception e) {
            System.out.println(e);
            return new Response<>(false, "failed", null );
        }
	}
	
	@Override
	public Response<String> deleteRateToParkingSpot(int spot_id) {
		try {
            PreparedStatement stmt = Conn.oracle.prepareStatement("delete from rate where spot_id = ?");
            stmt.setInt(1, spot_id);
            stmt.execute();
            if (stmt.getUpdateCount() == 1) {
                System.out.println("Delete Successful!!!");
                return new Response<>(true, "ok", null );
            }
       
            return new Response<>(false, "failed", "Rate not associated with Parking spot " + spot_id );
        } catch (Exception e) {
            System.out.println(e);
            return new Response<>(false, "failed", "Something went wrong" );
        }
	}
	
	// Parking slip
	@Override
	public Response<String> generateParkingSlip(int spot_id, ParkingSlip slip) {
		try {
			LocalDate currentDate = LocalDate.now();
			java.sql.Date date = java.sql.Date.valueOf(currentDate);
			PreparedStatement stmt = Conn.oracle.prepareStatement("insert into parking_slip (slip_type, duration,"
					+ " issue_Date, spot_id)"
					+ " values(?, ?, ?, ?)");
			stmt.setString(1, slip.getSlip_type());
			stmt.setFloat(2, slip.getDuration());
			stmt.setDate(3, new java.sql.Date(date.getTime()));
			stmt.setInt(4, spot_id);
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
	public Response<ParkingSlip> getParkingSlipById(int id) {
		try {
			PreparedStatement stmt = Conn.oracle.prepareStatement("select * from parking_slip where slip_id = ?");
            stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ParkingSlip p = new ParkingSlip(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getString(4), rs.getInt(5));
				return new Response<>(true, "ok", p);
			}
            return new Response<>(true, "ok", null);
		}catch (Exception e) {
            System.out.println(e);
            return new Response<>(false, "failed", null);
        }
	}
	
	@Override
	public Response<List<ParkingSlip>> getParkingSlipBySpot(int spot_id) {
		try {
			List<ParkingSlip> list = new ArrayList<>();
			PreparedStatement stmt = Conn.oracle.prepareStatement("select * from parking_slip where spot_id = ?");
            stmt.setInt(1, spot_id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ParkingSlip p = new ParkingSlip(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getString(4), rs.getInt(5));
				list.add(p);
			}
            return new Response<>(true, "ok", list);
		}catch (Exception e) {
            System.out.println(e);
            return new Response<>(false, "failed", null);
        }
	}
	
	@Override
	public Response<List<ParkingSlip>> getParkingSlipBySpace(int space_id) {
		try {
			List<ParkingSlip> list = new ArrayList<>();
			PreparedStatement stmt = Conn.oracle.prepareStatement("select * from parking_slip where spot_id = "
					+ "(select spot_id from parking_spot where space_id = ?)");
            stmt.setInt(1, space_id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ParkingSlip p = new ParkingSlip(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getString(4), rs.getInt(5));
				list.add(p);
			}
            return new Response<>(true, "ok", list);
		}catch (Exception e) {
            System.out.println(e);
            return new Response<>(false, "failed", null);
        }
	}
	
	@Override
	public Response<List<ParkingSlip>> getParkingSlipAuthoredByManager(int mgr_id) {
		try {
			List<ParkingSlip> list = new ArrayList<>();
			PreparedStatement stmt = Conn.oracle.prepareStatement("select * from parking_slip where spot_id = "
					+ "(select spot_id from parking_spot where space_id = "
					+ "(select space_id from mgr_space where mgr_id = ?)"
					+ ")");
            stmt.setInt(1, mgr_id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ParkingSlip p = new ParkingSlip(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getString(4), rs.getInt(5));
				list.add(p);
			}
            return new Response<>(true, "ok", list);
		}catch (Exception e) {
            System.out.println(e);
            return new Response<>(false, "failed", null);
        }
	}
	
	@Override
	public Response<List<ParkingSpace>> getParkingSpacesByManager(int id) {
		return null;
//		try {
//			List<ParkingSpace> list = new ArrayList<>();
//			PreparedStatement stmt = Conn.oracle.prepareStatement("select ps.*, m.mgr_id from parking_space ps, manager m where, mgr_space ms"
//					+ " where");
//            stmt.setInt(1, mgr_id);
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				ParkingSpace p = new ParkingSpace(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getString(4), rs.getInt(5));
//				list.add(p);
//			}
//            return new Response<>(true, "ok", list);
//		}catch (Exception e) {
//            System.out.println(e);
//            return new Response<>(false, "failed", null);
//        }
	}
	
	@Override
	public Response<List<ParkingSpot>> getParkingSpotsByManager(int id) {
		try {
			List<ParkingSpot> list = new ArrayList<>();
			PreparedStatement stmt = Conn.oracle.prepareStatement("select ps.*, m.mgr_id, m.mgr_name from parking_spot ps, manager m"
				+ ", mgr_space ms where ps.space_id = ms.space_id and m.mgr_id = ms.mgr_id");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			ParkingSpot p = new ParkingSpot(rs.getInt(1), rs.getString(2), rs.getInt(3));
			list.add(p);
		}
        	return new Response<>(true, "ok", list);
		}catch (Exception e) {
			System.out.println(e);
			return new Response<>(false, "failed", null);
		}
	}
	
	// assign
	@Override
	public Response<String> assignManagerToParkingSpace(int mgr_id, int pk_id) {
		try {
			PreparedStatement stmt = Conn.oracle.prepareStatement("insert into mgr_space (mgr_id, space_id)"
					+ " values(?, ?)");
			stmt.setInt(1, mgr_id);
			stmt.setInt(2, pk_id);
			
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
	public Response<String> deassignManagerToParkingSpace(int mgr_id) {
		try {
            PreparedStatement stmt = Conn.oracle.prepareStatement("delete from mgr_space where mgr_id = ?");
            stmt.setInt(1, mgr_id);
            stmt.execute();
            if (stmt.getUpdateCount() == 1) {
                System.out.println("Delete Successful!!!");
                return new Response<>(true, "ok", null );
            }
       
            return new Response<>(false, "failed", "Manager not associated with any Parking space");
        } catch (Exception e) {
            System.out.println(e);
            return new Response<>(false, "failed", "Something went wrong" );
        }
	}
}
