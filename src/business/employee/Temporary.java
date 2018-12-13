package business.employee;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


@Entity
@NamedQuery(name = "Temporary.findByid", query = "select obj from Temporary obj where :id = obj.id ")
public class Temporary extends Employee implements Serializable {

	private static final long serialVersionUID = 0;

	private Integer numWorkedHours;

	public Temporary() {
	}

	@Override
	public float getDetailedSalary() {
		return 0;
	}

	public Temporary(TTemporaryEmployee tt) {
		super(tt);
		numWorkedHours = tt.getNumWorkedHours();
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getNumWorkedHours() {
		return numWorkedHours;
	}

	public void setNumWorkedHours(Integer numWorkedHours) {
		this.numWorkedHours = numWorkedHours;
	}

	@Override
	public Double getDetailedSalary() {
		//TODO comprobar
		return getSalary() + (numWorkedHours * 4.2d);
	}

}