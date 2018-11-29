/**
 * 
 */
package business.employee;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


@Entity
@NamedQuery(name = "Business.employee.as.imp.Temporary.findByid", query = "select obj from Temporary obj where :id = obj.id ")
public class Temporary extends Employee implements Serializable {

	private static final long serialVersionUID = 0;


	@Id
	private Integer id;

	private Integer numWorkedHours;

	public Temporary() {
	}

	public Temporary(TTemporaryEmployee tt) {

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
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}
}