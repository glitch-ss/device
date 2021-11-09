package dao;

import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pojo.CPUCategory;
import pojo.HDCategory;
import pojo.SQLinfo;

public class HDCategoryDAO {
	public HDCategoryDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() throws SQLException{
		return DriverManager.getConnection(SQLinfo.sql, SQLinfo.username, SQLinfo.password);
	}
	
	public int getTotal() {
		int total = 0;
		try(Connection c = getConnection(); Statement s = c.createStatement();){
			String sql = "select count(*) from HDCategory";
			
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
	
	public void add(HDCategory hdc) {
		String sql = "insert into HDCategory value(null, ?, ?, ?, ?, ?)";
		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			
			ps.setString(1, hdc.partnumber);
			ps.setString(2, hdc.size);
			ps.setString(3, hdc.manufacture);
			ps.setString(4, hdc.type);
			ps.setInt(5,  hdc.capacity);
			
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				int id = rs.getInt(1);
				hdc.id = id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(HDCategory hdc) {
		String sql = "update HDCategory set partnumber = ?, size = ?, manufacture = ?, type = ?, capacity = ? where id = ?";
		try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			   
			ps.setString(1, hdc.partnumber);
			ps.setString(2, hdc.size);
			ps.setString(3, hdc.manufacture);
			ps.setString(4, hdc.type);
			ps.setInt(5,  hdc.capacity);

			ps.setInt(6, hdc.id);
   
            ps.execute();
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
	}
	
	public void delete(int id) {
		   
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
   
            String sql = "delete from HDCategory where id = " + id;
   
            s.execute(sql);
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
    }
	public HDCategory get(int id) {
		HDCategory hdc = null;
   
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
   
            String sql = "select * from HDCategory where id = " + id;
   
            ResultSet rs = s.executeQuery(sql);
   
            if (rs.next()) {
            	hdc = new HDCategory();
                String partnumber = rs.getString(2);
                String size = rs.getString(3);
                String manufacture = rs.getString(4);
                String type = rs.getString(5);
                int capacity = rs.getInt(6);
                hdc.partnumber = partnumber;
                hdc.size = size;
                hdc.manufacture = manufacture;
                hdc.type = type;
                hdc.capacity = capacity;
                hdc.id = id;
            }
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
        return hdc;
    }
	
	public HDCategory get(String partnumber) {
        HDCategory hdc = null;
        String sql = "";
        if (partnumber != null) {
        	sql = "select * from HDCategory where partnumber=?";
        }
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
        	if (partnumber != null) {
            	ps.setString(1, partnumber);
            }
            ResultSet rs = ps.executeQuery();
   
            if (rs.next()) {
            	hdc = new HDCategory();
            	int id = rs.getInt(1);
                String size = rs.getString(3);
                String manufacture = rs.getString(4);
                String type = rs.getString(5);
                int capacity = rs.getInt(6);
                hdc.partnumber = partnumber;
                hdc.size = size;
                hdc.manufacture = manufacture;
                hdc.type = type;
                hdc.capacity = capacity;
                hdc.id = id;
            }
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
        return hdc;
    }
	
	public List<HDCategory> list(){
		return  list(0, Short.MAX_VALUE);
	}
	
	public List<HDCategory> list(int start, int count){
		List<HDCategory> hdcs = new ArrayList<HDCategory>();
		
		String sql = "select * from HDCategory order by id asc limit ?, ?";
		
		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
			ps.setInt(1, start);
			ps.setInt(2, count);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				HDCategory hdc = new HDCategory();
				int id = rs.getInt(1);
				String partnumber = rs.getString(2);
                String size = rs.getString(3);
                String manufacture = rs.getString(4);
                String type = rs.getString(5);
                int capacity = rs.getInt(6);
                hdc.partnumber = partnumber;
                hdc.size = size;
                hdc.manufacture = manufacture;
                hdc.type = type;
                hdc.id = id;
                hdc.capacity = capacity;
				hdcs.add(hdc);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return hdcs;
	}
}
