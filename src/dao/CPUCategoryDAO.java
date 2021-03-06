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

import pojo.SQLinfo;
import pojo.CPUCategory;

public class CPUCategoryDAO {
	public CPUCategoryDAO() {
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
			String sql = "select count(*) from CPUCategory";
			
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
	
	public void add(CPUCategory cpu) {
		String sql = "insert into CPUCategory value(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setString(1, cpu.name);
			ps.setInt(2, cpu.cores);
			ps.setString(3, cpu.nickname);
			ps.setString(4, cpu.brand);
			ps.setString(5, cpu.platform);
			ps.setFloat(6, cpu.frequency);
			ps.setString(7, cpu.sspec);
			ps.setString(8, cpu.category);
			ps.setFloat(9, cpu.maxHz);
			ps.setFloat(10, cpu.minHz);
			
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				int id = rs.getInt(1);
				cpu.id = id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(CPUCategory cpu) {
		String sql = "update CPUCategory set name = ?, cores = ?, nickname = ?, brand = ?, platform = ?, frequency = ?, sspec = ?, category = ?, MaxHz = ?, MinHz = ? where id = ?";
		try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			   
			ps.setString(1, cpu.name);
			ps.setInt(2, cpu.cores);
			ps.setString(3, cpu.nickname);
			ps.setString(4, cpu.brand);
			ps.setString(5, cpu.platform);
			ps.setFloat(6, cpu.frequency);
			ps.setString(7, cpu.sspec);
			ps.setString(8, cpu.category);
			ps.setFloat(9, cpu.maxHz);
			ps.setFloat(10, cpu.minHz);
			ps.setInt(11, cpu.id);
   
            ps.execute();
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
	}
	
	public void delete(int id) {
		   
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
   
            String sql = "delete from CPUCategory where id = " + id;
   
            s.execute(sql);
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
    }
	public CPUCategory get(int id) {
        CPUCategory cpu = null;
   
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
   
            String sql = "select * from CPUCategory where id = " + id;
   
            ResultSet rs = s.executeQuery(sql);
   
            if (rs.next()) {
                cpu = new CPUCategory();
                String name = rs.getString(2);
                int cores = rs.getInt(3);
                String nickname = rs.getString(4);
                String brand = rs.getString(5);
                String platform = rs.getString(6);
                float frequency = rs.getFloat(7);
                String sspec = rs.getString(8);
                String category = rs.getString(9);
                float maxHz = rs.getFloat(10);
                float minHz = rs.getFloat(11);
                cpu.name = name;
                cpu.cores = cores;
                cpu.nickname = nickname;
                cpu.brand = brand;
                cpu.platform = platform;
                cpu.frequency = frequency;
                cpu.sspec = sspec;
                cpu.category = category;
                cpu.maxHz = maxHz;
                cpu.minHz = minHz;
                cpu.id = id;
            }
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
        return cpu;
    }
	
	public CPUCategory get(String partnumber, String name) {
        CPUCategory cpu = null;
        String sql = "";
        if (partnumber != null) {
        	sql = "select * from CPUCategory where sspec=?";
        }else if(name != null){
        	sql = "select * from CPUCategory where name=?";
        }
        name = URLDecoder.decode(name);
        System.out.println(name);
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
        	if (partnumber != null) {
            	ps.setString(1, partnumber);
            }else if(name != null){
            	ps.setString(1, name);
            }
            ResultSet rs = ps.executeQuery();
   
            if (rs.next()) {
                cpu = new CPUCategory();
                int id = rs.getInt(1);
                String modelname = rs.getString(2);
                int cores = rs.getInt(3);
                String nickname = rs.getString(4);
                String brand = rs.getString(5);
                String platform = rs.getString(6);
                float frequency = rs.getFloat(7);
                String sspec = rs.getString(8);
                String category = rs.getString(9);
                float maxHz = rs.getFloat(10);
                float minHz = rs.getFloat(11);
                cpu.name = modelname;
                cpu.cores = cores;
                cpu.nickname = nickname;
                cpu.brand = brand;
                cpu.platform = platform;
                cpu.frequency = frequency;
                cpu.sspec = sspec;
                cpu.category = category;
                cpu.maxHz = maxHz;
                cpu.minHz = minHz;
                cpu.id = id;
            }
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
        return cpu;
    }
	
	public List<CPUCategory> list(){
		return  list(0, Short.MAX_VALUE);
	}
	
	public List<CPUCategory> list(int start, int count){
		List<CPUCategory> cpus = new ArrayList<CPUCategory>();
		
		String sql = "select * from CPUCategory order by id asc limit ?, ?";
		
		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
			ps.setInt(1, start);
			ps.setInt(2, count);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				CPUCategory cpu = new CPUCategory();
				int id = rs.getInt(1);
				String name = rs.getString(2);
                int cores = rs.getInt(3);
                String nickname = rs.getString(4);
                String brand = rs.getString(5);
                String platform = rs.getString(6);
                float frequency = rs.getFloat(7);
                String sspec = rs.getString(8);
                String category = rs.getString(9);
                float maxHz = rs.getFloat(10);
                float minHz = rs.getFloat(11);
                cpu.name = name;
                cpu.cores = cores;
                cpu.nickname = nickname;
                cpu.brand = brand;
                cpu.platform = platform;
                cpu.frequency = frequency;
                cpu.sspec = sspec;
                cpu.category = category;
                cpu.id = id;
                cpu.maxHz = maxHz;
                cpu.minHz = minHz;
				cpus.add(cpu);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return cpus;
	}
}
