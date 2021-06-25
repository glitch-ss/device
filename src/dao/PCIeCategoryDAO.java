package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pojo.PCIeCategory;

public class PCIeCategoryDAO {
	public PCIeCategoryDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() throws SQLException{
		return DriverManager.getConnection("jdbc:mysql://10.182.92.28:3306/Device?characterEncoding=UTF-8", "raven","Familymart");
	}
	
	public int getTotal() {
		int total = 0;
		try(Connection c = getConnection(); Statement s = c.createStatement();){
			String sql = "select count(*) from PCICategory";
			
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) {
				total = rs.getInt(1);
			}
			
			System.out.println("total:" +total);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
	
	public void add(PCIeCategory pcic) {
		String sql = "insert into PCICategory value(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		System.out.println("width: " + pcic.width);
		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setString(1, pcic.nickname);
			ps.setString(2, pcic.devicecategory);
			ps.setString(3, pcic.description);
			ps.setString(4, pcic.partnumber);
			ps.setString(5, pcic.partnumberext);
			ps.setString(6, pcic.subsystem);
			ps.setString(7, pcic.productname);
			ps.setInt(8,  pcic.width);
			ps.setString(9, pcic.vendorid);
			ps.setString(10, pcic.deviceid);
			ps.setString(11, pcic.subvendorid);
			ps.setString(12, pcic.subdeviceid);
			ps.setString(13, pcic.kernelmodule);
			
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				int id = rs.getInt(1);
				pcic.id = id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(PCIeCategory pcic) {
		String sql = "update PCICategory set nickname = ?, devicecategory = ?, description = ?, partnumber=?, partnumber_ext=?, subsystem=?, productname=?, width=?, vendor_id=?, device_id=?, subvendor_id=?, subdevice_id=?, kernel_module=? where id = ?";
		try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			   
			ps.setString(1, pcic.nickname);
			ps.setString(2, pcic.devicecategory);
			ps.setString(3, pcic.description);
			ps.setString(4, pcic.partnumber);
			ps.setString(5, pcic.partnumberext);
			ps.setString(6, pcic.subsystem);
			ps.setString(7, pcic.productname);
			ps.setInt(8, pcic.width);
			ps.setString(9, pcic.vendorid);
			ps.setString(10, pcic.deviceid);
			ps.setString(11, pcic.subvendorid);
			ps.setString(12, pcic.subdeviceid);
			ps.setString(13, pcic.kernelmodule);

			ps.setInt(14, pcic.id);
   
            ps.execute();
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
	}
	
	public void delete(int id) {
		   
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
   
            String sql = "delete from PCICategory where id = " + id;
   
            s.execute(sql);
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
    }
	public PCIeCategory get(int id) {
		PCIeCategory pcic = null;
   
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
   
            String sql = "select * from PCICategory where id = " + id;
   
            ResultSet rs = s.executeQuery(sql);
   
            if (rs.next()) {
            	pcic = new PCIeCategory();
                String nickname = rs.getString(2);
                String devicecategory = rs.getString(3);
                String description = rs.getString(4);
                String partnumber = rs.getString(5);
                String partnumberext = rs.getString(6);
                String subsystem = rs.getString(7);
                String productname = rs.getString(8);
                int width = rs.getInt(9);
                String vendorid = rs.getString(10);
                String deviceid = rs.getString(11);
                String subvendorid = rs.getString(12);
                String subdeviceid = rs.getString(13);
                String kernelmodule = rs.getString(14);
                
                pcic.nickname = nickname;
                pcic.devicecategory = devicecategory;
                pcic.description = description;
                pcic.partnumber = partnumber;
                pcic.partnumberext = partnumberext;
                pcic.subsystem = subsystem;
                pcic.productname = productname;
                pcic.width = width;
                pcic.vendorid = vendorid;
                pcic.deviceid = deviceid;
                pcic.subvendorid = subvendorid;
                pcic.subdeviceid = subdeviceid;
                pcic.kernelmodule = kernelmodule;
                pcic.id = id;
            }
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
        return pcic;
    }
	
	public List<PCIeCategory> list(){
		return  list(0, Short.MAX_VALUE);
	}
	
	public List<PCIeCategory> list(int start, int count){
		List<PCIeCategory> pcis = new ArrayList<PCIeCategory>();
		
		String sql = "select * from PCICategory order by id asc limit ?, ?";
		
		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
			ps.setInt(1, start);
			ps.setInt(2, count);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				PCIeCategory pcic = new PCIeCategory();
				int id = rs.getInt(1);
				String nickname = rs.getString(2);
                String devicecategory = rs.getString(3);
                String description = rs.getString(4);
                String partnumber = rs.getString(5);
                String partnumberext = rs.getString(6);
                String subsystem = rs.getString(7);
                String productname = rs.getString(8);
                int width = rs.getInt(9);
                String vendorid = rs.getString(10);
                String deviceid = rs.getString(11);
                String subvendorid = rs.getString(12);
                String subdeviceid = rs.getString(13);
                String kernelmodule = rs.getString(14);
                
                pcic.nickname = nickname;
                pcic.devicecategory = devicecategory;
                pcic.description = description;
                pcic.partnumber = partnumber;
                pcic.partnumberext = partnumberext;
                pcic.subsystem = subsystem;
                pcic.productname = productname;
                pcic.width = width;
                pcic.vendorid = vendorid;
                pcic.deviceid = deviceid;
                pcic.subvendorid = subvendorid;
                pcic.subdeviceid = subdeviceid;
                pcic.kernelmodule = kernelmodule;
                pcic.id = id;
                pcis.add(pcic);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return pcis;
	}
}
