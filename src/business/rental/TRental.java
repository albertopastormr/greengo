package business.rental;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class TRental {

	private Integer id;
	private Integer idVehicle;
	private Integer idClient;
	private Boolean active;
	private Integer numKmRented;
	private Date dateFrom;
	private Date dateTo;

	public TRental(){}

	public TRental(Integer id, Integer idVehicle, Boolean active, Integer numKmRented,
                   Integer idClient, Date dateFrom, Date dateTo) {
		this.id = id;
		this.idVehicle = idVehicle;
		this.active = active;
		this.numKmRented = numKmRented;
		this.idClient = idClient;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdVehicle() {
		return idVehicle;
	}

	public void setIdVehicle(Integer idVehicle) {
		this.idVehicle = idVehicle;
	}

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Integer getNumKmRented() {
		return numKmRented;
	}

	public void setNumKmRented(Integer numKmRented) {
		this.numKmRented = numKmRented;
	}

	public Integer getIdClient() {
		return idClient;
	}

	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
}
