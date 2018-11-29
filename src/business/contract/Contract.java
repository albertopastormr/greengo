package business.contract;

<<<<<<< HEAD
import javax.annotation.Generated;
=======
>>>>>>> 603d92817707b9545096e6ae0f7cb5264d8469f3
import javax.persistence.*;
import java.io.Serializable;

import business.mainoffice.MainOffice;
import business.service.Service;

@Entity
@NamedQueries({
		@NamedQuery(name = "Contract.findByid",
                query = "select obj from contract obj where :id = obj.id "),
		@NamedQuery(name = "Contract.findByservice",
                query = "select obj from contract obj where :service = obj.service "),
		@NamedQuery(name = "Contract.findBymain_Office",
                query = "select obj from contract obj where :main_Office = obj.main_Office "),
		@NamedQuery(name = "Contract.findByservice_level",
                query = "select obj from contract obj where :service_level = obj.service_level "),
		@NamedQuery(name = "Contract.findByactive",
                query = "select obj from contract obj where :active = obj.active ") })
public class Contract implements Serializable {

	private static final long serialVersionUID = 0;

	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private Service service;

	@ManyToOne
	private MainOffice mainOffice;

	private Integer serviceLevel;

	private Boolean active;

	public Contract() {
	}

    public Contract(TContract tc) {
        this.id = tc.getId();
        this.service = getService();
        this.mainOffice = getMainOffice();
        this.serviceLevel = tc.getServiceLevel();
        this.active = tc.isActive();
    }

    public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public MainOffice getMainOffice() {
		return mainOffice;
	}

	public void setMainOffice(MainOffice mainOffice) {
		this.mainOffice = mainOffice;
	}

	public Integer getServiceLevel() {
		return serviceLevel;
	}

	public void setServiceLevel(Integer serviceLevel) {
		this.serviceLevel = serviceLevel;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}