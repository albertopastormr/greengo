package business.contract;

public class TContract {

	private int id;
	private int service_level;
	private int id_main_office;
	private int id_service;
	private boolean active;

	public TContract(int id, int service_level, int id_main_office, int id_service, boolean active) {
		this.id = id;
		this.service_level = service_level;
		this.id_main_office = id_main_office;
		this.id_service = id_service;
		this.active = active;
	}

	public  TContract(){
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getService_level() {
		return service_level;
	}

	public void setService_level(int service_level) {
		this.service_level = service_level;
	}

	public int getId_main_office() {
		return id_main_office;
	}

	public void setId_main_office(int id_main_office) {
		this.id_main_office = id_main_office;
	}

	public int getId_service() {
		return id_service;
	}

	public void setId_service(int id_service) {
		this.id_service = id_service;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
