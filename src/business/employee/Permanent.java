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

	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;

	private Float apportionment;

	public Permanent() { }

	public Permanent(TPermanentEmployee tp) {
		super(tp);
		apportionment = tp.getApportionment();
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


	@Override
	public Float getDetailedSalary() {
		Float result;
		result = getSalary() + apportionment;
		return result;
	}

}