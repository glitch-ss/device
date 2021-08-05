package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pojo.PowerSupply;
import pojo.PowerSupplyDetail;
import pojo.SQLinfo;

public class PowerSupplyDAO {
	public PowerSupplyDAO() {
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
			String sql = "select count(*) from PowerSupply";
			
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) {
				total = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
	
	public void add(PowerSupply powersupply) {
		String sql = "insert into PowerSupply value(null, ?, ?, ?, ?, ?)";

		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, powersupply.categoryId);
			ps.setString(2, powersupply.serialnumber);
			ps.setString(3, powersupply.owner);
			ps.setString(4, powersupply.location);
			ps.setString(5, powersupply.label);
			
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				int id = rs.getInt(1);
				powersupply.id = id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(PowerSupply powersupply) {
		String sql = "update PowerSupply set categoryId=?,owner=?,location=?,serialnumber=?,label=? where id=?";
		
		try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			   
			ps.setInt(1, powersupply.categoryId);
			ps.setString(2, powersupply.owner);
			ps.setString(3, powersupply.location);
			ps.setString(4, powersupply.serialnumber);
			ps.setString(5, powersupply.label);
			ps.setInt(6, powersupply.id);

            ps.executeUpdate();
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
	}
	
	public void delete(int id) {
		   
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
   
            String sql = "delete from PowerSupply where id = " + id;
   
            s.execute(sql);
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
    }
	public PowerSupply get(int id) {
		PowerSupply powersupply = null;
   
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
   
            String sql = "select * from PowerSupply where id = " + id;
   
            ResultSet rs = s.executeQuery(sql);
   
            if (rs.next()) {
                powersupply = new PowerSupply();
                int categoryId = rs.getInt(2);
                String serialnumber = rs.getString(3);
                String owner = rs.getString(4);
                String location = rs.getString(5);
                String label = rs.getString(6);
                powersupply.categoryId = categoryId;
                powersupply.owner = owner;
                powersupply.location = location;
                powersupply.serialnumber = serialnumber;
                powersupply.label = label;
                powersupply.id = id;
            }
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
        return powersupply;
    }
	
	public List<PowerSupply> list(){
		return  list(0, Short.MAX_VALUE);
	}
	
	public List<PowerSupply> list(int start, int count){
		List<PowerSupply> powersupplys = new ArrayList<PowerSupply>();
		
		String sql = "select * from PowerSupply order by id asc limit ?, ?";
		
		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
			ps.setInt(1, start);
			ps.setInt(2, count);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				PowerSupply powersupply = new PowerSupply();
				int id = rs.getInt(1);
				int categoryId = rs.getInt(2);
				String serialnumber = rs.getString(3);
				String owner = rs.getString(4);
                String location = rs.getString(5);
                String label = rs.getString(6);
                powersupply.categoryId = categoryId;
                powersupply.owner = owner;
                powersupply.location = location;
                powersupply.serialnumber = serialnumber;
                powersupply.label = label;
                powersupply.id = id;
                powersupplys.add(powersupply);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return powersupplys;
	}
	public List<PowerSupplyDetail> listDetail(){
		return  listDetail(0, Short.MAX_VALUE);
	}
	
	public List<PowerSupplyDetail> listDetail(int start, int count){
		List<PowerSupplyDetail> psds = new ArrayList<PowerSupplyDetail>();
		
		String sql = "select * from PowerSupply, PowerSupplyCategory where PowerSupply.categoryId=PowerSupplyCategory.id order by PowerSupply.id asc limit ?, ?";
		
		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
			ps.setInt(1, start);
			ps.setInt(2, count);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				PowerSupplyDetail psd = new PowerSupplyDetail();
				int id = rs.getInt(1);
				int categoryId = rs.getInt(2);
				String serialnumber = rs.getString(3);
				String owner = rs.getString(4);
                String location = rs.getString(5);
                String label = rs.getString(6);
                String description = rs.getString(8);
                String partnumber = rs.getString(9);
                String manufacture = rs.getString(10);
                psd.categoryId = categoryId;
                psd.owner = owner;
                psd.location = location;
                psd.serialnumber = serialnumber;
                psd.label = label;
                psd.id = id;
                psd.description = description;
                psd.partnumber = partnumber;
                psd.manufacture = manufacture;
                psds.add(psd);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return psds;
	}
	
	public PowerSupplyDetail GetByLocation(String qowner, String qlocation){
		
		String sql = "select * from PowerSupply, PowerSupplyCategory where PowerSupply.categoryId=PowerSupplyCategory.id and PowerSupply.owner=? and PowerSupply.location=?";
		PowerSupplyDetail psd = new PowerSupplyDetail();
		
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
                String description = rs.getString(8);
                String partnumber = rs.getString(9);
                String manufacture = rs.getString(10);
                psd.categoryId = categoryId;
                psd.owner = owner;
                psd.location = location;
                psd.serialnumber = serialnumber;
                psd.label = label;
                psd.id = id;
                psd.description = description;
                psd.partnumber = partnumber;
                psd.manufacture = manufacture;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return psd;
	}
	
public PowerSupplyDetail GetBySerialnumber(String qserialnumber){
		
		String sql = "select * from PowerSupply, PowerSupplyCategory where PowerSupply.categoryId=PowerSupplyCategory.id and PowerSupply.serialnumber=?";
		PowerSupplyDetail psd = new PowerSupplyDetail();
		
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
                String description = rs.getString(8);
                String partnumber = rs.getString(9);
                String manufacture = rs.getString(10);
                psd.categoryId = categoryId;
                psd.owner = owner;
                psd.location = location;
                psd.serialnumber = serialnumber;
                psd.label = label;
                psd.id = id;
                psd.description = description;
                psd.partnumber = partnumber;
                psd.manufacture = manufacture;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return psd;
	}
}
