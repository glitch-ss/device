package pojo;

public class User {
	public int id;
	public String name;
	public String email;
	public String password;
	public String privilege;
	public String loginkey;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	String getName() {
		return name;
	}
	void setName(String name) {
		this.name=name;
	}
	String getEmail() {
		return email;
	}
	void setEmail(String email) {
		this.email = email;
	}
	String getPassword() {
		return password;
	}
	void setPassword(String password) {
		this.password = password;
	}
}
