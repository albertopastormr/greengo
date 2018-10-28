package business.contract;

public class TContract {

    private Integer id;
    private Integer serviceLevel;
    private Integer idMainOffice;
    private Integer idService;
    private boolean active;

    public TContract() {
    }

    public TContract(Integer id, Integer serviceLevel, Integer idMainOffice, Integer idService, boolean active) {
        this.id = id;
        this.serviceLevel = serviceLevel;
        this.idMainOffice = idMainOffice;
        this.idService = idService;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getServiceLevel() {
        return serviceLevel;
    }

    public void setServiceLevel(Integer serviceLevel) {
        this.serviceLevel = serviceLevel;
    }

    public Integer getIdMainOffice() {
        return idMainOffice;
    }

    public void setIdMainOffice(Integer idMainOffice) {
        this.idMainOffice = idMainOffice;
    }

    public Integer getIdService() {
        return idService;
    }

    public void setIdService(Integer idService) {
        this.idService = idService;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
