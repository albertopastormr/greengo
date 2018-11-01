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

	@Override
	public String toString(){

		SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");
		GregorianCalendar dateFromCal = new GregorianCalendar();
		dateFromCal.setTimeInMillis(dateFrom.getTime());
		GregorianCalendar dateToCal = new GregorianCalendar();
		dateToCal.setTimeInMillis(dateTo.getTime());

		fmt.setCalendar(dateFromCal);
		String dateFromS = fmt.format(dateFromCal.getTime());
		fmt.setCalendar(dateToCal);
		String dateToS = fmt.format(dateToCal.getTime());

		String ret = "";
		ret += String.format("%-13s %13s %n", "Id: ", id);
		ret += String.format("%-13s %13s %n", "Client id: ", idClient);
		ret += String.format("%-13s %13s %n", "Vehicle id: ", idVehicle);
		ret += String.format("%-13s %13s %n", "Km rented: ", numKmRented);
		ret += String.format("%-13s %13s %n", "From: ", dateFromS);
		ret += String.format("%-13s %13s %n", "To: ", dateToS);
		ret += String.format("%-13s %13s %n", "Active: ", active);


		return ret;
	}
}
