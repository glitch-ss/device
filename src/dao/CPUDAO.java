package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pojo.CPU;
import pojo.CPUdetail;

public class CPUDAO {
	public CPUDAO() {
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
			String sql = "select count(*) from CPU";
			
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
	
	public void add(CPU cpu) {
		String sql = "insert into CPUCategory value(null, ?, ?, ?, ?)";
		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, cpu.categoryId);
			ps.setString(2, cpu.location);
			ps.setString(3, cpu.serialnumber);
			ps.setString(4, cpu.label);
			
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
	
	public void update(CPU cpu) {
		String sql = "udpate CPU set categoryId = ?, location = ?, serialnumber = ?, label = ? where id = ?";
		try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			   
			ps.setInt(1, cpu.categoryId);
			ps.setString(2, cpu.location);
			ps.setString(3, cpu.serialnumber);
			ps.setString(4, cpu.label);
			ps.setInt(5, cpu.id);
   
            ps.execute();
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
	}
	
	public void delete(int id) {
		   
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
   
            String sql = "delete from CPU where id = " + id;
   
            s.execute(sql);
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
    }
	public CPU get(int id) {
        CPU cpu = null;
   
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
   
            String sql = "select * from CPU where id = " + id;
   
            ResultSet rs = s.executeQuery(sql);
   
            if (rs.next()) {
                cpu = new CPU();
                int categoryId = rs.getInt(2);
                String location = rs.getString(3);
                String serialnumber = rs.getString(4);
                String label = rs.getString(5);
                cpu.categoryId = categoryId;
                cpu.location = location;
                cpu.serialnumber = serialnumber;
                cpu.label = label;
                cpu.id = id;
            }
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
        return cpu;
    }
	
	public List<CPU> list(){
		return  list(0, Short.MAX_VALUE);
	}
	
	public List<CPU> list(int start, int count){
		List<CPU> cpus = new ArrayList<CPU>();
		
		String sql = "select * from CPU order by id desc limit ?, ?";
		
		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
			ps.setInt(1, start);
			ps.setInt(2, count);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				CPU cpu = new CPU();
				int id = rs.getInt(1);
				int categoryId = rs.getInt(2);
                String location = rs.getString(3);
                String serialnumber = rs.getString(4);
                String label = rs.getString(5);
                cpu.categoryId = categoryId;
                cpu.location = location;
                cpu.serialnumber = serialnumber;
                cpu.label = label;
                cpu.id = id;
				cpus.add(cpu);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return cpus;
	}
	public List<CPUdetail> listDetail(){
		return  listDetail(0, Short.MAX_VALUE);
	}
	
	public List<CPUdetail> listDetail(int start, int count){
		List<CPUdetail> cpus = new ArrayList<CPUdetail>();
		
		String sql = "select * from CPU, CPUCategory where CPU.categoryId=CPUCategory.id order by CPU.id desc limit ?, ?";
		
		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
			ps.setInt(1, start);
			ps.setInt(2, count);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				CPUdetail cpu = new CPUdetail();
				int id = rs.getInt(1);
				int categoryId = rs.getInt(2);
                String location = rs.getString(3);
                String serialnumber = rs.getString(4);
                String label = rs.getString(5);
                String name = rs.getString(7);
                int cores = rs.getInt(8);
                String nickname = rs.getString(9);
                String brand = rs.getString(10);
                String platform = rs.getString(11);
                Float frequency = rs.getFloat(12);
                String sspec = rs.getString(13);
                String category = rs.getString(14);
                cpu.categoryId = categoryId;
                cpu.location = location;
                cpu.serialnumber = serialnumber;
                cpu.label = label;
                cpu.id = id;
                cpu.name = name;
                cpu.cores = cores;
                cpu.nickname = nickname;
                cpu.brand = brand;
                cpu.platform = platform;
                cpu.frequency = frequency;
                cpu.sspec = sspec;
                cpu.category = category;
				cpus.add(cpu);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return cpus;
	}
}
