package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pojo.PowerSupplyCategory;
import pojo.SQLinfo;

public class PowerSupplyCategoryDAO {
	public PowerSupplyCategoryDAO() {
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
			String sql = "select count(*) from PowerSupplyCategory";
			
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
	
	public void add(PowerSupplyCategory psc) {
		String sql = "insert into PowerSupplyCategory value(null, ?, ?, ?)";
		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setString(1, psc.description);
			ps.setString(2, psc.partnumber);
			ps.setString(3, psc.manufacture);
			
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				int id = rs.getInt(1);
				psc.id = id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(PowerSupplyCategory psc) {
		String sql = "udpate PowerSupplyCategory set description = ?, partnumber = ?, manufacture = ? where id = ?";
		try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			   
			ps.setString(1, psc.description);
			ps.setString(2, psc.partnumber);
			ps.setString(3, psc.manufacture);

			ps.setInt(4, psc.id);
   
            ps.execute();
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
	}
	
	public void delete(int id) {
		   
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
   
            String sql = "delete from PowerSupplyCategory where id = " + id;
   
            s.execute(sql);
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
    }
	public PowerSupplyCategory get(int id) {
		PowerSupplyCategory psc = null;
   
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
   
            String sql = "select * from PowerSupplyCategory where id = " + id;
   
            ResultSet rs = s.executeQuery(sql);
   
            if (rs.next()) {
                psc = new PowerSupplyCategory();
                String description = rs.getString(2);
                String partnumber = rs.getString(3);
                String manufacture = rs.getString(4);
                psc.description = description;
                psc.partnumber = partnumber;
                psc.manufacture = manufacture;
                psc.id = id;
            }
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
        return psc;
    }
	
	public List<PowerSupplyCategory> list(){
		return  list(0, Short.MAX_VALUE);
	}
	
	public List<PowerSupplyCategory> list(int start, int count){
		List<PowerSupplyCategory> cpus = new ArrayList<PowerSupplyCategory>();
		
		String sql = "select * from PowerSupplyCategory order by id asc limit ?, ?";
		
		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
			ps.setInt(1, start);
			ps.setInt(2, count);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				PowerSupplyCategory psc = new PowerSupplyCategory();
				int id = rs.getInt(1);
				String description = rs.getString(2);
                String partnumber = rs.getString(3);
                String manufacture = rs.getString(4);
                psc.description = description;
                psc.partnumber = partnumber;
                psc.manufacture = manufacture;
                psc.id = id;
				cpus.add(psc);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return cpus;
	}
}
