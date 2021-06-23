package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pojo.PCIe;
import pojo.PCIeDetail;

public class PCIeDAO {
	public PCIeDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() throws SQLException{
		return DriverManager.getConnection("jdbc:mysql://10.182.92.28:3306/Device?jdbcCompliantTruncation=false&characterEncoding=UTF-8", "raven","Familymart");
	}
	
	public int getTotal() {
		int total = 0;
		try(Connection c = getConnection(); Statement s = c.createStatement();){
			String sql = "select count(*) from PCI";
			
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
	
	public void add(PCIe pci) {
		String sql = "insert into PCI value(null, ?, ?, ?, ?, ?)";

		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, pci.categoryId);
			ps.setString(2, pci.serialnumber);
			ps.setString(3, pci.owner);
			ps.setString(4, pci.location);
			ps.setString(5, pci.label);
			
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				int id = rs.getInt(1);
				pci.id = id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(PCIe pci) {
		String sql = "update PCI set categoryId=?,owner=?, location=?,serialnumber=?,label=? where id=?";
		
		try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			   
			ps.setInt(1, pci.categoryId);
			ps.setString(2, pci.owner);
			ps.setString(3, pci.location);
			ps.setString(4, pci.serialnumber);
			ps.setString(5, pci.label);
			ps.setInt(6, pci.id);

            ps.executeUpdate();
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
	}
	
	public void delete(int id) {
		   
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
   
            String sql = "delete from PCI where id = " + id;
   
            s.execute(sql);
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
    }
	public PCIe get(int id) {
		PCIe pci = null;
   
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
   
            String sql = "select * from PCI where id = " + id;
   
            ResultSet rs = s.executeQuery(sql);
   
            if (rs.next()) {
            	pci = new PCIe();
                int categoryId = rs.getInt(2);
                String serialnumber = rs.getString(3);
                String owner = rs.getString(4);
                String location = rs.getString(5);
                String label = rs.getString(6);
                pci.categoryId = categoryId;
                pci.owner = owner;
                pci.location = location;
                pci.serialnumber = serialnumber;
                pci.label = label;
                pci.id = id;
            }
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
        return pci;
    }
	
	public List<PCIe> list(){
		return  list(0, Short.MAX_VALUE);
	}
	
	public List<PCIe> list(int start, int count){
		List<PCIe> pcis = new ArrayList<PCIe>();
		
		String sql = "select * from PCI order by id asc limit ?, ?";
		
		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
			ps.setInt(1, start);
			ps.setInt(2, count);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				PCIe pci = new PCIe();
				int id = rs.getInt(1);
				int categoryId = rs.getInt(2);
				String serialnumber = rs.getString(3);
				String owner = rs.getString(4);
                String location = rs.getString(5);
                String label = rs.getString(6);
                pci.categoryId = categoryId;
                pci.owner = owner;
                pci.location = location;
                pci.serialnumber = serialnumber;
                pci.label = label;
                pci.id = id;
                pcis.add(pci);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return pcis;
	}
	public List<PCIeDetail> listDetail(){
		return  listDetail(0, Short.MAX_VALUE);
	}
	
	public List<PCIeDetail> listDetail(int start, int count){
		List<PCIeDetail> pcis = new ArrayList<PCIeDetail>();
		
		String sql = "select * from PCI, PCICategory where PCI.categoryId=PCICategory.id order by PCI.id asc limit ?, ?";
		
		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
			ps.setInt(1, start);
			ps.setInt(2, count);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				PCIeDetail pci = new PCIeDetail();
				int id = rs.getInt(1);
				int categoryId = rs.getInt(2);
				String serialnumber = rs.getString(3);
				String owner = rs.getString(4);
                String location = rs.getString(5);
                String label = rs.getString(6);
                String nickname = rs.getString(8);
                String devicecategory = rs.getString(9);
                String description = rs.getString(10);
                String partnumber = rs.getString(11);
                String partnumberext = rs.getString(12);
                String subsystem = rs.getString(13);
                String productname = rs.getString(14);
                int width = rs.getInt(15);
                String vendorid = rs.getString(16);
                String deviceid = rs.getString(17);
                String subvendorid = rs.getString(18);
                String subdeviceid = rs.getString(19);
                String kernelmodule = rs.getString(20);
                pci.categoryId = categoryId;
                pci.owner = owner;
                pci.location = location;
                pci.serialnumber = serialnumber;
                pci.label = label;
                pci.id = id;
                pci.nickname = nickname;
                pci.devicecategory = devicecategory;
                pci.description = description;
                pci.partnumber = partnumber;
                pci.partnumberext = partnumberext;
                pci.subsystem = subsystem;
                pci.productname = productname;
                pci.width = width;
                pci.vendorid = vendorid;
                pci.deviceid = deviceid;
                pci.subvendorid = subvendorid;
                pci.subdeviceid = subdeviceid;
                pci.kernelmodule = kernelmodule;
                pcis.add(pci);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return pcis;
	}
	
	public PCIeDetail GetByLocation(String qowner, String qlocation) {
		String sql = "select * from PCI, PCICategory where PCI.categoryId=PCICategory.id and PCI.owner=? and PCI.location=?";
		PCIeDetail pci = new PCIeDetail();
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
                String nickname = rs.getString(8);
                String devicecategory = rs.getString(9);
                String description = rs.getString(10);
                String partnumber = rs.getString(11);
                String partnumberext = rs.getString(12);
                String subsystem = rs.getString(13);
                String productname = rs.getString(14);
                int width = rs.getInt(15);
                String vendorid = rs.getString(16);
                String deviceid = rs.getString(17);
                String subvendorid = rs.getString(18);
                String subdeviceid = rs.getString(19);
                String kernelmodule = rs.getString(20);
                pci.categoryId = categoryId;
                pci.owner = owner;
                pci.location = location;
                pci.serialnumber = serialnumber;
                pci.label = label;
                pci.id = id;
                pci.nickname = nickname;
                pci.devicecategory = devicecategory;
                pci.description = description;
                pci.partnumber = partnumber;
                pci.partnumberext = partnumberext;
                pci.subsystem = subsystem;
                pci.productname = productname;
                pci.width = width;
                pci.vendorid = vendorid;
                pci.deviceid = deviceid;
                pci.subvendorid = subvendorid;
                pci.subdeviceid = subdeviceid;
                pci.kernelmodule = kernelmodule;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return pci;
	}
	
	public PCIeDetail GetBySerialnumber(String qserialnumber) {
		String sql = "select * from PCI, PCICategory where PCI.categoryId=PCICategory.id and PCI.serialnumber=?";
		PCIeDetail pci = new PCIeDetail();
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
                String nickname = rs.getString(8);
                String devicecategory = rs.getString(9);
                String description = rs.getString(10);
                String partnumber = rs.getString(11);
                String partnumberext = rs.getString(12);
                String subsystem = rs.getString(13);
                String productname = rs.getString(14);
                int width = rs.getInt(15);
                String vendorid = rs.getString(16);
                String deviceid = rs.getString(17);
                String subvendorid = rs.getString(18);
                String subdeviceid = rs.getString(19);
                String kernelmodule = rs.getString(20);
                pci.categoryId = categoryId;
                pci.owner = owner;
                pci.location = location;
                pci.serialnumber = serialnumber;
                pci.label = label;
                pci.id = id;
                pci.nickname = nickname;
                pci.devicecategory = devicecategory;
                pci.description = description;
                pci.partnumber = partnumber;
                pci.partnumberext = partnumberext;
                pci.subsystem = subsystem;
                pci.productname = productname;
                pci.width = width;
                pci.vendorid = vendorid;
                pci.deviceid = deviceid;
                pci.subvendorid = subvendorid;
                pci.subdeviceid = subdeviceid;
                pci.kernelmodule = kernelmodule;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return pci;
	}
}
