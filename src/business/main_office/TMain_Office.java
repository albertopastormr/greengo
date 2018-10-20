package business.main_office;

public class TMain_Office {

	private int id;
	private String city;
	private String adress;
	private boolean active;

	public TMain_Office(int id, String city, String adress, boolean active) {
		this.id = id;
		this.city = city;
		this.adress = adress;
		this.active = active;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
