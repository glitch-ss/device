package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pojo.CPUdetail;
import pojo.DIMM;
import pojo.DIMMDetail;
import pojo.SQLinfo;

public class DIMMDAO {
	public DIMMDAO() {
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
			String sql = "select count(*) from DIMM";
			
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
	
	public void add(DIMM dimm) {
		String sql = "insert into DIMM value(null, ?, ?, ?, ?, ?)";

		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, dimm.categoryId);
			ps.setString(2, dimm.owner);
			ps.setString(3, dimm.location);
			ps.setString(4, dimm.serialnumber);
			ps.setString(5, dimm.label);
			
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
	
	public void update(DIMM dimm) {
		String sql = "update DIMM set categoryId=?,owner=?, location=?,serialnumber=?,label=? where id=?";
		
		try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			   
			ps.setInt(1, dimm.categoryId);
			ps.setString(2, dimm.owner);
			ps.setString(3, dimm.location);
			ps.setString(4, dimm.serialnumber);
			ps.setString(5, dimm.label);
			ps.setInt(6, dimm.id);

            ps.executeUpdate();
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
	}
	
	public void delete(int id) {
		   
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
   
            String sql = "delete from DIMM where id = " + id;
   
            s.execute(sql);
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
    }
	public DIMM get(int id) {
        DIMM dimm = null;
   
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
   
            String sql = "select * from DIMM where id = " + id;
   
            ResultSet rs = s.executeQuery(sql);
   
            if (rs.next()) {
                dimm = new DIMM();
                int categoryId = rs.getInt(2);
                String owner = rs.getString(3);
                String location = rs.getString(4);
                String serialnumber = rs.getString(5);
                String label = rs.getString(6);
                dimm.categoryId = categoryId;
                dimm.owner = owner;
                dimm.location = location;
                dimm.serialnumber = serialnumber;
                dimm.label = label;
                dimm.id = id;
            }
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
        return dimm;
    }
	
	public List<DIMM> list(){
		return  list(0, Short.MAX_VALUE);
	}
	
	public List<DIMM> list(int start, int count){
		List<DIMM> dimms = new ArrayList<DIMM>();
		
		String sql = "select * from DIMM order by id asc limit ?, ?";
		
		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
			ps.setInt(1, start);
			ps.setInt(2, count);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				DIMM dimm = new DIMM();
				int id = rs.getInt(1);
				int categoryId = rs.getInt(2);
				String owner = rs.getString(3);
                String location = rs.getString(4);
                String serialnumber = rs.getString(5);
                String label = rs.getString(6);
                dimm.categoryId = categoryId;
                dimm.owner = owner;
                dimm.location = location;
                dimm.serialnumber = serialnumber;
                dimm.label = label;
                dimm.id = id;
                dimms.add(dimm);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return dimms;
	}
	public List<DIMMDetail> listDetail(){
		return  listDetail(0, Short.MAX_VALUE);
	}
	
	public List<DIMMDetail> listDetail(int start, int count){
		List<DIMMDetail> dimms = new ArrayList<DIMMDetail>();
		
		String sql = "select * from DIMM, DIMMCategory where DIMM.categoryId=DIMMCategory.id order by DIMM.id asc limit ?, ?";
		
		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
			ps.setInt(1, start);
			ps.setInt(2, count);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				DIMMDetail dimm = new DIMMDetail();
				int id = rs.getInt(1);
				int categoryId = rs.getInt(2);
				String owner = rs.getString(3);
                String location = rs.getString(4);
                String serialnumber = rs.getString(5);
                String label = rs.getString(6);
                String name = rs.getString(8);
                String nickname = rs.getString(9);
                int size = rs.getInt(10);
                String brand = rs.getString(11);
                int speed = rs.getInt(12);
                String platform = rs.getString(13);
                String partnumber = rs.getString(14);
                String type = rs.getString(15);
                String rank = rs.getString(16);
                dimm.categoryId = categoryId;
                dimm.owner = owner;
                dimm.location = location;
                dimm.serialnumber = serialnumber;
                dimm.label = label;
                dimm.id = id;
                dimm.name = name;
                dimm.size = size;
                dimm.nickname = nickname;
                dimm.brand = brand;
                dimm.platform = platform;
                dimm.speed = speed;
                dimm.partnumber = partnumber;
                dimm.type = type;
                dimm.rank = rank;
				dimms.add(dimm);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return dimms;
	}
	
	public DIMMDetail GetByLocation(String qowner, String qlocation) {
		String sql = "select * from DIMM, DIMMCategory where DIMM.categoryId=DIMMCategory.id and DIMM.owner=? and DIMM.location=?";
		DIMMDetail dimm = new DIMMDetail();
		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
			ps.setString(1, qowner);
			ps.setString(2, qlocation);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt(1);
				int categoryId = rs.getInt(2);
				String owner = rs.getString(3);
                String location = rs.getString(4);
                String serialnumber = rs.getString(5);
                String label = rs.getString(6);
                String name = rs.getString(8);
                String nickname = rs.getString(9);
                int size = rs.getInt(10);
                String brand = rs.getString(11);
                int speed = rs.getInt(12);
                String platform = rs.getString(13);
                String partnumber = rs.getString(14);
                String type = rs.getString(15);
                String rank = rs.getString(16);
                dimm.categoryId = categoryId;
                dimm.owner = owner;
                dimm.location = location;
                dimm.serialnumber = serialnumber;
                dimm.label = label;
                dimm.id = id;
                dimm.name = name;
                dimm.size = size;
                dimm.nickname = nickname;
                dimm.brand = brand;
                dimm.platform = platform;
                dimm.speed = speed;
                dimm.partnumber = partnumber;
                dimm.type = type;
                dimm.rank = rank;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return dimm;
	}
	
	public DIMMDetail GetBySerialnumber(String qserialnumber) {
		String sql = "select * from DIMM, DIMMCategory where DIMM.categoryId=DIMMCategory.id and DIMM.serialnumber=?";
		DIMMDetail dimm = new DIMMDetail();
		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
			ps.setString(1, qserialnumber);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt(1);
				int categoryId = rs.getInt(2);
				String owner = rs.getString(3);
                String location = rs.getString(4);
                String serialnumber = rs.getString(5);
                String label = rs.getString(6);
                String name = rs.getString(8);
                String nickname = rs.getString(9);
                int size = rs.getInt(10);
                String brand = rs.getString(11);
                int speed = rs.getInt(12);
                String platform = rs.getString(13);
                String partnumber = rs.getString(14);
                String type = rs.getString(15);
                String rank = rs.getString(16);
                dimm.categoryId = categoryId;
                dimm.owner = owner;
                dimm.location = location;
                dimm.serialnumber = serialnumber;
                dimm.label = label;
                dimm.id = id;
                dimm.name = name;
                dimm.size = size;
                dimm.nickname = nickname;
                dimm.brand = brand;
                dimm.platform = platform;
                dimm.speed = speed;
                dimm.partnumber = partnumber;
                dimm.type = type;
                dimm.rank = rank;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return dimm;
	}
	
}
