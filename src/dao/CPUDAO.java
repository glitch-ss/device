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
import pojo.SQLinfo;

public class CPUDAO {
	public CPUDAO() {
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
			String sql = "select count(*) from CPU";
			
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) {
				total = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
	
	public void add(CPU cpu) {
		String sql = "insert into CPU value(null, ?, ?, ?, ?, ?)";

		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, cpu.categoryId);
			ps.setString(2, cpu.owner);
			ps.setString(3, cpu.location);
			ps.setString(4, cpu.serialnumber);
			ps.setString(5, cpu.label);
			
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
		String sql = "update CPU set categoryId=?,owner=?,location=?,serialnumber=?,label=? where id=?";
		
		try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			   
			ps.setInt(1, cpu.categoryId);
			ps.setString(2, cpu.owner);
			ps.setString(3, cpu.location);
			ps.setString(4, cpu.serialnumber);
			ps.setString(5, cpu.label);
			ps.setInt(6, cpu.id);

            ps.executeUpdate();
   
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
                String owner = rs.getString(3);
                String location = rs.getString(4);
                String serialnumber = rs.getString(5);
                String label = rs.getString(6);
                cpu.categoryId = categoryId;
                cpu.owner = owner;
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
		
		String sql = "select * from CPU order by id asc limit ?, ?";
		
		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
			ps.setInt(1, start);
			ps.setInt(2, count);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				CPU cpu = new CPU();
				int id = rs.getInt(1);
				int categoryId = rs.getInt(2);
				String owner = rs.getString(3);
                String location = rs.getString(4);
                String serialnumber = rs.getString(5);
                String label = rs.getString(6);
                cpu.categoryId = categoryId;
                cpu.owner = owner;
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
		
		String sql = "select * from CPU, CPUCategory where CPU.categoryId=CPUCategory.id order by CPU.id asc limit ?, ?";
		
		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
			ps.setInt(1, start);
			ps.setInt(2, count);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				CPUdetail cpu = new CPUdetail();
				int id = rs.getInt(1);
				int categoryId = rs.getInt(2);
				String owner = rs.getString(3);
                String location = rs.getString(4);
                String serialnumber = rs.getString(5);
                String label = rs.getString(6);
                String name = rs.getString(8);
                int cores = rs.getInt(9);
                String nickname = rs.getString(10);
                String brand = rs.getString(11);
                String platform = rs.getString(12);
                Float frequency = rs.getFloat(13);
                String sspec = rs.getString(14);
                String category = rs.getString(15);
                float maxHz = rs.getFloat(16);
                float minHz = rs.getFloat(17);
                cpu.categoryId = categoryId;
                cpu.owner = owner;
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
                cpu.maxHz = maxHz;
                cpu.minHz = minHz;
				cpus.add(cpu);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return cpus;
	}
	
	public CPUdetail GetByLocation(String qowner, String qlocation) {
		String sql = "select * from CPU, CPUCategory where CPU.categoryId=CPUCategory.id and CPU.owner=? and CPU.location=?";
		CPUdetail cpu = new CPUdetail();
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
                int cores = rs.getInt(9);
                String nickname = rs.getString(10);
                String brand = rs.getString(11);
                String platform = rs.getString(12);
                Float frequency = rs.getFloat(13);
                String sspec = rs.getString(14);
                String category = rs.getString(15);
                Float maxHz = rs.getFloat(16);
                Float minHz = rs.getFloat(17);
                System.out.println(minHz);
                cpu.categoryId = categoryId;
                cpu.owner = owner;
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
                cpu.minHz = minHz;
                cpu.maxHz = maxHz;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return cpu;
	}
	
	public CPUdetail GetBySerialnumber(String qserialnumber) {
		String sql = "select * from CPU, CPUCategory where CPU.categoryId=CPUCategory.id and CPU.serialnumber=?";
		CPUdetail cpu = new CPUdetail();
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
                int cores = rs.getInt(9);
                String nickname = rs.getString(10);
                String brand = rs.getString(11);
                String platform = rs.getString(12);
                Float frequency = rs.getFloat(13);
                String sspec = rs.getString(14);
                String category = rs.getString(15);
                Float maxHz = rs.getFloat(16);
                Float minHz = rs.getFloat(17);
                System.out.println(minHz);
                cpu.categoryId = categoryId;
                cpu.owner = owner;
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
                cpu.minHz = minHz;
                cpu.maxHz = maxHz;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return cpu;
	}
}
