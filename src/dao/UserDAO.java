package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pojo.User;

public class UserDAO {
	public UserDAO() {
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
			String sql = "select count(*) from User";
			
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
	
	public void add(User user) {
		String sql = "insert into User value(null, ?, ?, ?, 'user', null)";

		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setString(1, user.name);
			ps.setString(2, user.email);
			ps.setString(3, user.password);
			
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				int id = rs.getInt(1);
				user.id = id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(User user) {
		String sql = "update User set name=?,email=?,password=?, privilege=?, loginkey=? where id=?";
		
		try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			   
			ps.setString(1, user.name);
			ps.setString(2, user.email);
			ps.setString(3, user.password);
			ps.setInt(6, user.id);
			ps.setString(4, user.privilege);
			ps.setString(5,  user.loginkey);

            ps.executeUpdate();
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
	}
	
	public void delete(int id) {
		   
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
   
            String sql = "delete from User where id = " + id;
   
            s.execute(sql);
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
    }
	public User get(int id) {
		User user = null;
   
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
   
            String sql = "select * from User where id = " + id;
   
            ResultSet rs = s.executeQuery(sql);
   
            if (rs.next()) {
                user = new User();
                String name = rs.getString(2);
                String email = rs.getString(3);
                String password = rs.getString(4);
                String privilege = rs.getString(5);
                String loginkey = rs.getString(6);
                user.name = name;
                user.email = email;
                user.password = password;
                user.privilege = privilege;
                user.loginkey = loginkey;
                user.id = id;
            }
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
        return user;
    }
	
	public User get(String email) {
		User user = null;
   
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
   
            String sql = "select * from User where email = '" + email + "'";
   
            ResultSet rs = s.executeQuery(sql);
   
            if (rs.next()) {
                user = new User();
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String password = rs.getString(4);
                String privilege = rs.getString(5);
                String loginkey = rs.getString(6);
                user.name = name;
                user.email = email;
                user.password = password;
                user.privilege = privilege;
                user.loginkey = loginkey;
                user.id = id;
            }
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
        return user;
    }
	
	public String getPasswordByEmail(String email) {
		String sql = "select password from User where email='" + email + "'";
		String password = null;
		
		try (Connection c = getConnection(); Statement s = c.createStatement();) { 
   
            ResultSet rs = s.executeQuery(sql);
            
            if (rs.next()) {
    			password = rs.getString(1);
    		}
            return password;
   
        } catch (SQLException e) {
            e.printStackTrace();
            return password;
        }
	}
	
	public void setLoginKey(User user) {
		String sql = "update User set loginkey=? where id=?";
		
		try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			   
			ps.setString(1, user.loginkey);
			ps.setInt(2, user.id);

            ps.executeUpdate();
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
	}
	
	public List<User> list(){
		return  list(0, Short.MAX_VALUE);
	}
	
	public List<User> list(int start, int count){
		List<User> users = new ArrayList<User>();
		
		String sql = "select * from User order by id asc limit ?, ?";
		
		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
			ps.setInt(1, start);
			ps.setInt(2, count);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				User user = new User();
				int id = rs.getInt(1);
				String name = rs.getString(2);
                String email = rs.getString(3);
                String password = rs.getString(4);
                String privilege = rs.getString(5);
                String loginkey = rs.getString(6);
                user.name = name;
                user.email = email;
                user.password = password;
                user.id = id;
                user.privilege = privilege;
                user.loginkey = loginkey;
                users.add(user);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
}
