package business.rental;

import java.util.Date;

public class TRental {

	private Integer id;
	private Integer id_vehicle;
	private boolean active;
	private Integer km_rented;
	private Integer id_client;
	private Date dateFrom;
	private Date dateTo;

	public TRental(){}

	public TRental(Integer id, Integer id_vehicle, boolean active, Integer km_rented, Integer id_client, Date dateFrom, Date dateTo) {
		this.id = id;
		this.id_vehicle = id_vehicle;
		this.active = active;
		this.km_rented = km_rented;
		this.id_client = id_client;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getId_vehicle() {
		return id_vehicle;
	}

	public void setId_vehicle(Integer id_vehicle) {
		this.id_vehicle = id_vehicle;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Integer getKm_rented() {
		return km_rented;
	}

	public void setKm_rented(Integer km_rented) {
		this.km_rented = km_rented;
	}

	public Integer getId_client() {
		return id_client;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}

	public Date getDateTo() { return dateTo; }

	public void setDateTo(Date dateTo) { this.dateTo = dateTo; }

	public Date getDateFrom() { return dateFrom; }

	public void setDateFrom(Date dateFrom) { this.dateFrom = dateFrom; }
}
