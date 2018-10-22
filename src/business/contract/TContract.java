package business.contract;

public class TContract {

	private Integer id;
	private int service_level;
	private Integer id_main_office;
	private int id_service;
	private boolean active;

	public TContract(Integer id, int service_level, Integer id_main_office, Integer id_service, boolean active) {
		this.id = id;
		this.service_level = service_level;
		this.id_main_office = id_main_office;
		this.id_service = id_service;
		this.active = active;
	}

	public  TContract(){
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getService_level() {
		return service_level;
	}

	public void setService_level(int service_level) {
		this.service_level = service_level;
	}

	public Integer getId_main_office() {
		return id_main_office;
	}

	public void setId_main_office(Integer id_main_office) {
		this.id_main_office = id_main_office;
	}

	public Integer getId_service() {
		return id_service;
	}

	public void setId_service(Integer id_service) {
		this.id_service = id_service;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
