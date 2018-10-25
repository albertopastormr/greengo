package business.mainoffice;

public class TMainOffice {

	private Integer id;
	private String city;
	private String address;
	private boolean active;

	public TMainOffice(){}

	public TMainOffice(Integer id, String city, String address, boolean active) {
		this.id = id;
		this.city = city;
		this.address = address;
		this.active = active;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAdress(String address) {
		this.address = address;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
}
