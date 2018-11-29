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

<<<<<<< HEAD
	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;

=======
>>>>>>> 603d92817707b9545096e6ae0f7cb5264d8469f3
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