package business.contract;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ContractId implements Serializable {

    @Column(name = "MAINOFFICE_ID")
    private Integer mainOfficeId;

    @Column(name = "SERVICE_ID")
    private Integer serviceId;

    public ContractId(){}
    public ContractId(Integer moId, Integer serId){
        this.mainOfficeId = moId;
        this.serviceId = serId;
    }

    public int hashCode() {
        return (mainOfficeId.hashCode() + serviceId);
    }

    public boolean equals(Object object) {
        if (object instanceof ContractId) {
            ContractId otherId = (ContractId) object;
            return (otherId.mainOfficeId.equals(this.mainOfficeId)) && (otherId.serviceId.equals(this.serviceId));
        }
        return false;
    }

    @Override
    public String toString() {
        return "ContractId{" +
                "mainOfficeId=" + mainOfficeId +
                ", serviceId=" + serviceId +
                '}';
    }

    public Integer getMainOfficeId() {
        return mainOfficeId;
    }

    public void setMainOfficeId(Integer mainOfficeId) {
        this.mainOfficeId = mainOfficeId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }
}
