package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pojo.HD;
import pojo.HDDetail;
import pojo.SQLinfo;

public class HDDAO {
	public HDDAO() {
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
			String sql = "select count(*) from HD";
			
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) {
				total = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
	
	public void add(HD hd) {
		String sql = "insert into HD value(null, ?, ?, ?, ?, ?)";

		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, hd.categoryId);
			ps.setString(2, hd.serialnumber);
			ps.setString(3, hd.owner);
			ps.setString(4, hd.location);
			ps.setString(5, hd.label);
			
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				int id = rs.getInt(1);
				hd.id = id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(HD hd) {
		String sql = "update HD set categoryId=?,owner=?,location=?,serialnumber=?,label=? where id=?";
		
		try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			   
			ps.setInt(1, hd.categoryId);
			ps.setString(2, hd.owner);
			ps.setString(3, hd.location);
			ps.setString(4, hd.serialnumber);
			ps.setString(5, hd.label);
			ps.setInt(6, hd.id);

            ps.executeUpdate();
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
	}
	
	public void delete(int id) {
		   
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
   
            String sql = "delete from HD where id = " + id;
   
            s.execute(sql);
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
    }
	public HD get(int id) {
		HD hd = null;
   
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
   
            String sql = "select * from HD where id = " + id;
   
            ResultSet rs = s.executeQuery(sql);
   
            if (rs.next()) {
            	hd = new HD();
                int categoryId = rs.getInt(2);
                String serialnumber = rs.getString(3);
                String owner = rs.getString(4);
                String location = rs.getString(5);
                String label = rs.getString(6);
                hd.categoryId = categoryId;
                hd.owner = owner;
                hd.location = location;
                hd.serialnumber = serialnumber;
                hd.label = label;
                hd.id = id;
            }
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
        return hd;
    }
	
	public List<HD> list(){
		return  list(0, Short.MAX_VALUE);
	}
	
	public List<HD> list(int start, int count){
		List<HD> hds = new ArrayList<HD>();
		
		String sql = "select * from HD order by id asc limit ?, ?";
		
		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
			ps.setInt(1, start);
			ps.setInt(2, count);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				HD hd = new HD();
				int id = rs.getInt(1);
				int categoryId = rs.getInt(2);
				String serialnumber = rs.getString(3);
				String owner = rs.getString(4);
                String location = rs.getString(5);
                String label = rs.getString(6);
                hd.categoryId = categoryId;
                hd.owner = owner;
                hd.location = location;
                hd.serialnumber = serialnumber;
                hd.label = label;
                hd.id = id;
                hds.add(hd);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return hds;
	}
	public List<HDDetail> listDetail(){
		return  listDetail(0, Short.MAX_VALUE);
	}
	
	public List<HDDetail> listDetail(int start, int count){
		List<HDDetail> hdds = new ArrayList<HDDetail>();
		
		String sql = "select * from HD, HDCategory where HD.categoryId=HDCategory.id order by HD.id asc limit ?, ?";
		
		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
			ps.setInt(1, start);
			ps.setInt(2, count);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				HDDetail hdd = new HDDetail();
				int id = rs.getInt(1);
				int categoryId = rs.getInt(2);
				String serialnumber = rs.getString(3);
				String owner = rs.getString(4);
                String location = rs.getString(5);
                String label = rs.getString(6);
                String partnumber = rs.getString(8);
                int size = rs.getInt(9);
                String manufacture = rs.getString(10);
                String type = rs.getString(11);
                hdd.categoryId = categoryId;
                hdd.owner = owner;
                hdd.location = location;
                hdd.serialnumber = serialnumber;
                hdd.label = label;
                hdd.id = id;
                hdd.size = size;
                hdd.partnumber = partnumber;
                hdd.manufacture = manufacture;
                hdd.type = type;
                hdds.add(hdd);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return hdds;
	}
	
	public HDDetail GetByLocation(String qowner, String qlocation){
		
		String sql = "select * from HD, HDCategory where HD.categoryId=HDCategory.id and HD.owner=? and HD.location=?";
		HDDetail hdd = new HDDetail();
		
		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
			ps.setString(1, qowner);
			ps.setString(2, qlocation);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt(1);
				int categoryId = rs.getInt(2);
				String serialnumber = rs.getString(3);
				String owner = rs.getString(4);
                String location = rs.getString(5);
                String label = rs.getString(6);
                String partnumber = rs.getString(8);
                int size = rs.getInt(9);
                String manufacture = rs.getString(10);
                String type = rs.getString(11);
                hdd.categoryId = categoryId;
                hdd.owner = owner;
                hdd.location = location;
                hdd.serialnumber = serialnumber;
                hdd.label = label;
                hdd.id = id;
                hdd.size = size;
                hdd.partnumber = partnumber;
                hdd.manufacture = manufacture;
                hdd.type = type;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return hdd;
	}
	
public HDDetail GetBySerialnumber(String qserialnumber){
		
		String sql = "select * from HD, HDCategory where HD.categoryId=HDCategory.id and HD.serialnumber=?";
		HDDetail hdd = new HDDetail();
		
		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
			ps.setString(1, qserialnumber);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				int id = rs.getInt(1);
				int categoryId = rs.getInt(2);
				String serialnumber = rs.getString(3);
				String owner = rs.getString(4);
                String location = rs.getString(5);
                String label = rs.getString(6);
                String partnumber = rs.getString(8);
                int size = rs.getInt(9);
                String manufacture = rs.getString(10);
                String type = rs.getString(11);
                hdd.categoryId = categoryId;
                hdd.owner = owner;
                hdd.location = location;
                hdd.serialnumber = serialnumber;
                hdd.label = label;
                hdd.id = id;
                hdd.size = size;
                hdd.partnumber = partnumber;
                hdd.manufacture = manufacture;
                hdd.type = type;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return hdd;
	}
}
