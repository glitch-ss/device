package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pojo.MortherBoard;
import pojo.MortherBoardDetail;

public class MortherBoardDAO {
	public MortherBoardDAO() {
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
			String sql = "select count(*) from MortherBoard";
			
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) {
				total = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
	
	public void add(MortherBoard mortherboard) {
		String sql = "insert into MortherBoard value(null, ?, ?, ?, ?, ?)";

		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, mortherboard.categoryId);
			ps.setString(2, mortherboard.serialnumber);
			ps.setString(3, mortherboard.owner);
			ps.setString(4, mortherboard.label);
			ps.setString(5, mortherboard.macaddress);
			
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				int id = rs.getInt(1);
				mortherboard.id = id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(MortherBoard mortherboard) {
		String sql = "update MortherBoard set categoryId=?,owner=?,macaddress=?,serialnumber=?,label=? where id=?";
		
		try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			   
			ps.setInt(1, mortherboard.categoryId);
			ps.setString(2, mortherboard.owner);
			ps.setString(3, mortherboard.macaddress);
			ps.setString(4, mortherboard.serialnumber);
			ps.setString(5, mortherboard.label);
			ps.setInt(6, mortherboard.id);

            ps.executeUpdate();
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
	}
	
	public void delete(int id) {
		   
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
   
            String sql = "delete from MortherBoard where id = " + id;
   
            s.execute(sql);
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
    }
	public MortherBoard get(int id) {
		MortherBoard mortherboard = null;
   
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
   
            String sql = "select * from MortherBoard where id = " + id;
   
            ResultSet rs = s.executeQuery(sql);
   
            if (rs.next()) {
            	mortherboard = new MortherBoard();
                int categoryId = rs.getInt(2);
                String serialnumber = rs.getString(3);
                String owner = rs.getString(4);
                String label = rs.getString(5);
                String macaddress = rs.getString(6);
                mortherboard.categoryId = categoryId;
                mortherboard.owner = owner;
                mortherboard.macaddress = macaddress;
                mortherboard.serialnumber = serialnumber;
                mortherboard.label = label;
                mortherboard.id = id;
            }
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
        return mortherboard;
    }
	
	public List<MortherBoard> list(){
		return  list(0, Short.MAX_VALUE);
	}
	
	public List<MortherBoard> list(int start, int count){
		List<MortherBoard> mortherboards = new ArrayList<MortherBoard>();
		
		String sql = "select * from MortherBoard order by id asc limit ?, ?";
		
		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
			ps.setInt(1, start);
			ps.setInt(2, count);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				MortherBoard mortherboard = new MortherBoard();
				int id = rs.getInt(1);
				int categoryId = rs.getInt(2);
				String serialnumber = rs.getString(3);
				String owner = rs.getString(4);
                String label = rs.getString(5);
                String macaddress = rs.getString(6);
                mortherboard.categoryId = categoryId;
                mortherboard.owner = owner;
                mortherboard.macaddress = macaddress;
                mortherboard.serialnumber = serialnumber;
                mortherboard.label = label;
                mortherboard.id = id;
                mortherboards.add(mortherboard);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return mortherboards;
	}
	public List<MortherBoardDetail> listDetail(){
		return  listDetail(0, Short.MAX_VALUE);
	}
	
	public List<MortherBoardDetail> listDetail(int start, int count){
		List<MortherBoardDetail> mbds = new ArrayList<MortherBoardDetail>();
		
		String sql = "select * from MortherBoard, MortherBoardCategory where MortherBoard.categoryId=MortherBoardCategory.id order by MortherBoard.id asc limit ?, ?";
		
		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
			ps.setInt(1, start);
			ps.setInt(2, count);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				MortherBoardDetail mbd = new MortherBoardDetail();
				int id = rs.getInt(1);
				int categoryId = rs.getInt(2);
				String serialnumber = rs.getString(3);
				String owner = rs.getString(4);
                String label = rs.getString(5);
                String macaddress = rs.getString(6);
                String description = rs.getString(8);
                String partnumber = rs.getString(9);
                String manufacture = rs.getString(10);
                mbd.categoryId = categoryId;
                mbd.owner = owner;
                mbd.macaddress = macaddress;
                mbd.serialnumber = serialnumber;
                mbd.label = label;
                mbd.id = id;
                mbd.description = description;
                mbd.partnumber = partnumber;
                mbd.manufacture = manufacture;
                mbds.add(mbd);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return mbds;
	}
	
	public MortherBoardDetail GetByLocation(String qowner){

		String sql = "select * from MortherBoard, MortherBoardCategory where MortherBoard.categoryId=MortherBoardCategory.id and MortherBoard.owner=?";
		MortherBoardDetail mbd = new MortherBoardDetail();
		
		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
			ps.setString(1, qowner);

			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				int id = rs.getInt(1);
				int categoryId = rs.getInt(2);
				String serialnumber = rs.getString(3);
				String owner = rs.getString(4);
                String label = rs.getString(5);
                String macaddress = rs.getString(6);
                String description = rs.getString(8);
                String partnumber = rs.getString(9);
                String manufacture = rs.getString(10);
                mbd.categoryId = categoryId;
                mbd.owner = owner;
                mbd.macaddress = macaddress;
                mbd.serialnumber = serialnumber;
                mbd.label = label;
                mbd.id = id;
                mbd.description = description;
                mbd.partnumber = partnumber;
                mbd.manufacture = manufacture;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return mbd;
	}
	
	public MortherBoardDetail GetBySerialnumber(String qserialnumber){

		String sql = "select * from MortherBoard, MortherBoardCategory where MortherBoard.categoryId=MortherBoardCategory.id and MortherBoard.serialnumber=?";
		MortherBoardDetail mbd = new MortherBoardDetail();
		
		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
			ps.setString(1, qserialnumber);

			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				int id = rs.getInt(1);
				int categoryId = rs.getInt(2);
				String serialnumber = rs.getString(3);
				String owner = rs.getString(4);
                String label = rs.getString(5);
                String macaddress = rs.getString(6);
                String description = rs.getString(8);
                String partnumber = rs.getString(9);
                String manufacture = rs.getString(10);
                mbd.categoryId = categoryId;
                mbd.owner = owner;
                mbd.macaddress = macaddress;
                mbd.serialnumber = serialnumber;
                mbd.label = label;
                mbd.id = id;
                mbd.description = description;
                mbd.partnumber = partnumber;
                mbd.manufacture = manufacture;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return mbd;
	}
}
