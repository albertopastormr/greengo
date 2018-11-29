package business.contract;

import javax.persistence.*;
import java.io.Serializable;

import business.mainoffice.MainOffice;
import business.service.Service;

@Entity
@NamedQueries({
		@NamedQuery(name = "Business.contract.as.imp.contract.findByid", query = "select obj from contract obj where :id = obj.id "),
		@NamedQuery(name = "Business.contract.as.imp.contract.findByservice", query = "select obj from contract obj where :service = obj.service "),
		@NamedQuery(name = "Business.contract.as.imp.contract.findBymain_Office", query = "select obj from contract obj where :main_Office = obj.main_Office "),
		@NamedQuery(name = "Business.contract.as.imp.contract.findByservice_level", query = "select obj from contract obj where :service_level = obj.service_level "),
		@NamedQuery(name = "Business.contract.as.imp.contract.findByactive", query = "select obj from contract obj where :active = obj.active ") })
public class Contract implements Serializable {

	private static final long serialVersionUID = 0;

	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private Service service;

	@ManyToOne
	private MainOffice main_Office;

	private Integer service_level;

	private Boolean active;

	public Contract() {
	}

    public Contract(TContract tc) {
        this.id =
        this.service = getService();
        this.main_Office = main_Office;
        this.service_level = service_level;
        this.active = active;
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

	public MainOffice getMain_Office() {
		return main_Office;
	}

	public void setMain_Office(MainOffice main_Office) {
		this.main_Office = main_Office;
	}

	public Integer getService_level() {
		return service_level;
	}

	public void setService_level(Integer service_level) {
		this.service_level = service_level;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}