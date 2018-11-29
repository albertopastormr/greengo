/**
 * 
 */
package business.employee;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@NamedQuery(name = "Business.employee.as.imp.Permanent.findByid", query = "select obj from Permanent obj where :id = obj.id ")
public class Permanent extends Employee implements Serializable {

	private static final long serialVersionUID = 0;

	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;

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

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}
}