/**
 * 
 */
package business.employee;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@NamedQuery(name = "Permanent.findByid", query = "select obj from Permanent obj where :id = obj.id ")
public class Permanent extends Employee implements Serializable {

	private static final long serialVersionUID = 0;

	private Float apportionment;

	public Permanent() {
	}

	public Permanent(TPermanentEmployee tp) {

	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Float getApportionment() {
		return apportionment;
	}

	public void setApportionment(Float apportionment) {
		this.apportionment = apportionment;
	}

}