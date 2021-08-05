package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pojo.MortherBoardCategory;
import pojo.SQLinfo;

public class MortherBoardCategoryDAO {
	public MortherBoardCategoryDAO() {
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
			String sql = "select count(*) from MortherBoardCategory";
			
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
	
	public void add(MortherBoardCategory mbc) {
		String sql = "insert into MortherBoardCategory value(null, ?, ?, ?)";
		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setString(1, mbc.description);
			ps.setString(2, mbc.partnumber);
			ps.setString(3, mbc.manufacture);
			
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				int id = rs.getInt(1);
				mbc.id = id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(MortherBoardCategory mbc) {
		String sql = "udpate MortherBoardCategory set description = ?, partnumber = ?, manufacture = ? where id = ?";
		try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			   
			ps.setString(1, mbc.description);
			ps.setString(2, mbc.partnumber);
			ps.setString(3, mbc.manufacture);

			ps.setInt(4, mbc.id);
   
            ps.execute();
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
	}
	
	public void delete(int id) {
		   
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
   
            String sql = "delete from MortherBoardCategory where id = " + id;
   
            s.execute(sql);
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
    }
	public MortherBoardCategory get(int id) {
		MortherBoardCategory mbc = null;
   
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
   
            String sql = "select * from MortherBoardCategory where id = " + id;
   
            ResultSet rs = s.executeQuery(sql);
   
            if (rs.next()) {
            	mbc = new MortherBoardCategory();
                String description = rs.getString(2);
                String partnumber = rs.getString(3);
                String manufacture = rs.getString(4);
                mbc.description = description;
                mbc.partnumber = partnumber;
                mbc.manufacture = manufacture;
                mbc.id = id;
            }
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
        return mbc;
    }
	
	public List<MortherBoardCategory> list(){
		return  list(0, Short.MAX_VALUE);
	}
	
	public List<MortherBoardCategory> list(int start, int count){
		List<MortherBoardCategory> mbs = new ArrayList<MortherBoardCategory>();
		
		String sql = "select * from MortherBoardCategory order by id asc limit ?, ?";
		
		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
			ps.setInt(1, start);
			ps.setInt(2, count);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				MortherBoardCategory mbc = new MortherBoardCategory();
				int id = rs.getInt(1);
				String description = rs.getString(2);
                String partnumber = rs.getString(3);
                String manufacture = rs.getString(4);
                mbc.description = description;
                mbc.partnumber = partnumber;
                mbc.manufacture = manufacture;
                mbc.id = id;
                mbs.add(mbc);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return mbs;
	}
}
