package business.contract;



import javax.persistence.*;
import java.io.Serializable;

import business.mainoffice.MainOffice;
import business.service.Service;

@Entity
@NamedQueries({
		@NamedQuery(name = "Contract.findByid",
                query = "select obj from Contract obj where :id = obj.id "),
		@NamedQuery(name = "Contract.findByservice",
                query = "select obj from Contract obj where :service = obj.service "),
		@NamedQuery(name = "Contract.findBymain_Office",
                query = "select obj from Contract obj where :mainOffice = obj.mainOffice "),
		@NamedQuery(name = "Contract.findByservice_level",
                query = "select obj from Contract obj where :serviceLevel = obj.serviceLevel"),
		@NamedQuery(name = "Contract.findByActive",
                query = "select obj from Contract obj where :active = obj.active "),
        @NamedQuery(name = "Contract.findAllContracts",
                query = "select obj from Contract obj")})
public class Contract implements Serializable {

	private static final long serialVersionUID = 0;

	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;

	@Version
	private Integer version;

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

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}