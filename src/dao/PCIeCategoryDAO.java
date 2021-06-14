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
			String sql = "select count(*) from PCIeCategory";
			
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
		String sql = "insert into PCIeCategory value(null, ?, ?, ?, ?, ?, ?)";
		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setString(1, pcic.nickname);
			ps.setString(2, pcic.devicecategory);
			ps.setString(3, pcic.description);
			ps.setString(4, pcic.partnumber);
			ps.setString(5, pcic.subsystem);
			ps.setString(6, pcic.productname);
			
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
		String sql = "udpate PCIeCategory set nickname = ?, devicecategory = ?, description = ?, partnumber=?, subsystem=?, productname=? where id = ?";
		try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			   
			ps.setString(1, pcic.nickname);
			ps.setString(2, pcic.devicecategory);
			ps.setString(3, pcic.description);
			ps.setString(4, pcic.partnumber);
			ps.setString(5, pcic.subsystem);
			ps.setString(6, pcic.productname);

			ps.setInt(7, pcic.id);
   
            ps.execute();
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
	}
	
	public void delete(int id) {
		   
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
   
            String sql = "delete from PCIeCategory where id = " + id;
   
            s.execute(sql);
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
    }
	public PCIeCategory get(int id) {
		PCIeCategory pcic = null;
   
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
   
            String sql = "select * from PCIeCategory where id = " + id;
   
            ResultSet rs = s.executeQuery(sql);
   
            if (rs.next()) {
            	pcic = new PCIeCategory();
                String nickname = rs.getString(2);
                String devicecategory = rs.getString(3);
                String description = rs.getString(4);
                String partnumber = rs.getString(5);
                String subsystem = rs.getString(6);
                String productname = rs.getString(7);
                
                pcic.nickname = nickname;
                pcic.devicecategory = devicecategory;
                pcic.description = description;
                pcic.partnumber = partnumber;
                pcic.subsystem = subsystem;
                pcic.productname = productname;
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
		
		String sql = "select * from PCIeCategory order by id asc limit ?, ?";
		
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
                String subsystem = rs.getString(6);
                String productname = rs.getString(7);
                
                pcic.nickname = nickname;
                pcic.devicecategory = devicecategory;
                pcic.description = description;
                pcic.partnumber = partnumber;
                pcic.subsystem = subsystem;
                pcic.productname = productname;
                pcic.id = id;
                pcis.add(pcic);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return pcis;
	}
}
