package pojo;

public class CPUdetail extends CPU{

	public String name;
	public int cores;
	public String nickname;
	public String brand;
	public String platform;
	public float frequency;
	public String sspec;
	public String category;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCores() {
		return cores;
	}
	public void setCores(int cores) {
		this.cores = cores;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public float getFrequency() {
		return frequency;
	}
	public void setFrequency(float frequency) {
		this.frequency = frequency;
	}
	public String getSspec() {
		return sspec;
	}
	public void setSspec(String sspec) {
		this.sspec = sspec;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
}
