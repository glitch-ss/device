package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pojo.DIMMCategory;

public class DIMMCategoryDAO {
	public DIMMCategoryDAO() {
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
			String sql = "select count(*) from DIMMCategory";
			
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
	
	public void add(DIMMCategory dimm) {
		String sql = "insert into DIMMCategory value(null, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setString(1, dimm.name);
			ps.setString(2, dimm.nickname);
			ps.setInt(3, dimm.size);
			ps.setString(4, dimm.brand);
			ps.setInt(5, dimm.speed);
			ps.setString(6, dimm.platform);
			ps.setString(7, dimm.partnumber);
			ps.setString(8, dimm.type);
			ps.setString(9, dimm.rank);
			
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				int id = rs.getInt(1);
				dimm.id = id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(DIMMCategory dimm) {
		String sql = "update DIMMCategory set name = ?, nickname = ?, size = ?, brand = ?, speed = ?, platform = ?, part_number = ?, type = ?, rank = ? where id = ?";
		try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			   
			ps.setString(1, dimm.name);
			ps.setString(3, dimm.nickname);
			ps.setInt(3, dimm.size);
			ps.setString(4, dimm.brand);
			ps.setInt(5,  dimm.speed);
			ps.setString(6, dimm.platform);
			ps.setString(7, dimm.partnumber);
			ps.setString(8, dimm.type);
			ps.setString(9, dimm.rank);
			ps.setInt(10, dimm.id);
   
            ps.execute();
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
	}
	
	public void delete(int id) {
		   
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
   
            String sql = "delete from DIMMCategory where id = " + id;
   
            s.execute(sql);
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
    }
	public DIMMCategory get(int id) {
        DIMMCategory dimm = null;
   
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
   
            String sql = "select * from DIMMCategory where id = " + id;
   
            ResultSet rs = s.executeQuery(sql);
   
            if (rs.next()) {
                dimm = new DIMMCategory();
                String name = rs.getString(2);
                String nickname = rs.getString(3);
                int size = rs.getInt(4);
                String brand = rs.getString(5);
                int speed = rs.getInt(6);
                String platform = rs.getString(7);
                String partnumber = rs.getString(8);
                String type = rs.getString(9);
                String rank = rs.getString(10);
                dimm.name = name;
                dimm.size = size;
                dimm.nickname = nickname;
                dimm.brand = brand;
                dimm.platform = platform;
                dimm.speed = speed;
                dimm.partnumber = partnumber;
                dimm.type = type;
                dimm.rank = rank;
                dimm.id = id;
            }
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
        return dimm;
    }
	
	public List<DIMMCategory> list(){
		return  list(0, Short.MAX_VALUE);
	}
	
	public List<DIMMCategory> list(int start, int count){
		List<DIMMCategory> dimms = new ArrayList<DIMMCategory>();
		
		String sql = "select * from DIMMCategory order by id asc limit ?, ?";
		
		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
			ps.setInt(1, start);
			ps.setInt(2, count);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				DIMMCategory dimm = new DIMMCategory();
				int id = rs.getInt(1);
                String name = rs.getString(2);
                String nickname = rs.getString(3);
                int size = rs.getInt(4);
                String brand = rs.getString(5);
                int speed = rs.getInt(6);
                String platform = rs.getString(7);
                String partnumber = rs.getString(8);
                String type = rs.getString(9);
                String rank = rs.getString(10);
                dimm.name = name;
                dimm.size = size;
                dimm.nickname = nickname;
                dimm.brand = brand;
                dimm.platform = platform;
                dimm.speed = speed;
                dimm.partnumber = partnumber;
                dimm.type = type;
                dimm.rank = rank;
                dimm.id = id;
				dimms.add(dimm);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return dimms;
	}
}
