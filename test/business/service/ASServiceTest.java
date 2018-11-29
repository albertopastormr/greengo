package business.service;

import business.ASException;

import business.IncorrectInputException;
import business.contract.TContract;
import business.contract.as.ASContract;
import business.contract.factory.ASContractFactory;
import business.mainoffice.TMainOffice;
import business.mainoffice.as.ASMain_Office;

import business.mainoffice.factory.ASMain_OfficeFactory;
import business.service.factory.ASServiceFactory;

import business.service.as.ASService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class ASServiceTest {

    private ASService as;
    private ASMain_Office asMainOffice;
    private ASContract asContract;

    private TService tServicePrincipal;
    private TMainOffice tMainOfficePrincipal;
    private TContract tContractPrincipal;

    @BeforeEach
    private void setUp() throws Exception{

        as = ASServiceFactory.getInstance().generateASService();
        asMainOffice = ASMain_OfficeFactory.getInstance().generateASMain_Office();
        asContract = ASContractFactory.getInstance().generateASContract();

        tServicePrincipal = new TService(null, 200, true, "Taller","Calle mercado,3",12345);
        tMainOfficePrincipal = new TMainOffice(null,"Avila","Calle Don Manuel", true);
        tContractPrincipal = new TContract();
    }

    // --------------------- CREATE --------------------

    @Test
    public void createServiceSuccessful(){
        assertTrue(as.create(tServicePrincipal)>0);
    }

    @Test
    public void createIncorrectServiceSameType(){

        Integer idService = as.create(tServicePrincipal);
        tServicePrincipal = as.show(idService);

        // Create all diferents fields less the type
        TService tServiceSecond = new TService(null, 201, true, "Taller","Calle mercado,3 Modificado",54321);
        assertThrows(ASException.class, ()-> as.create(tServiceSecond));
    }

    @Test
    public void createIncorrectServiceIdNegative(){
        tServicePrincipal.setId(-1);
        assertThrows(IncorrectInputException.class, ()-> as.create(tServicePrincipal));
    }

    @Test
    public void createIncorrectServiceTypeNull(){
        tServicePrincipal.setType(null);
        assertThrows(IncorrectInputException.class, ()-> as.create(tServicePrincipal));
    }


    // --------------------- DROP --------------------


    @Test
    public void dropServiceSuccessful(){
        as.drop(as.create(tServicePrincipal));
        assertTrue(!as.show(tServicePrincipal.getId()).isActive());
    }

    @Test
    public void dropIncorrectServiceContractAssociated(){

        Integer idService = as.create(tServicePrincipal);
        tServicePrincipal = as.show(idService);

        Integer idMainOffice = asMainOffice.create(tMainOfficePrincipal);
        tServicePrincipal = as.show(idService);

        tContractPrincipal.setIdService(idService);
        tContractPrincipal.setIdMainOffice(idMainOffice);
        tContractPrincipal.setServiceLevel(3);
        tContractPrincipal.setActive(true);

        Integer idContract = asContract.create(tContractPrincipal);
        tContractPrincipal = asContract.show(idContract);

        assertThrows(ASException.class, ()-> as.drop(idService));
    }

    @Test
    public void dropServiceSuccessfulContractAssociated(){

        Integer idService = as.create(tServicePrincipal);
        tServicePrincipal = as.show(idService);

        Integer idMainOffice = asMainOffice.create(tMainOfficePrincipal);
        tServicePrincipal = as.show(idService);

        tContractPrincipal.setIdService(idService);
        tContractPrincipal.setIdMainOffice(idMainOffice);
        tContractPrincipal.setServiceLevel(3);
        tContractPrincipal.setActive(false);

        Integer idContract = asContract.create(tContractPrincipal);
        tContractPrincipal = asContract.show(idContract);

        assertTrue(!as.show(as.drop(idService)).isActive());
    }

    @Test
    public void dropIncorrectServiceAlreadyInactive(){
        Integer idService = as.drop(as.create(tServicePrincipal));

        assertThrows(ASException.class, ()-> as.drop(idService));
    }

    @Test
    public void dropIncorrectServiceNotExist(){
        assertThrows(ASException.class, ()-> as.drop(20));
    }

    @Test
    public void dropIncorrectServiceIdNull(){
        assertThrows(ASException.class, ()-> as.drop(null));
    }

    @Test
    public void dropIncorrectServiceIdNegative(){
        assertThrows(ASException.class, ()-> as.drop(-1));
    }


    // --------------------- UPDATE --------------------

    @Test
    public void updateServiceSuccessful(){

        // Change all fields less type
        TService tServiceComparator = new TService(null, 201, true, "Limpieza","Calle Mercado,3 Modificada",54321);
        tServiceComparator = as.show(as.create(tServiceComparator));

        Integer idService = as.create(tServicePrincipal);
        tServicePrincipal = as.show(idService);

        tServicePrincipal.setCapacity(201);
        tServicePrincipal.setType("Taller modificado");
        tServicePrincipal.setAddress("Calle Mercado,3 Modificada");
        tServicePrincipal.setCapacity(54321);

        Integer idServiceSecond = as.update(tServicePrincipal);
        TService tServiceSecond = as.show(idServiceSecond);

        assertEquals(tServicePrincipal.getId(), idServiceSecond);
        assertNotEquals(tServiceComparator.getType(), tServiceSecond.getType());
    }

    @Test
    public void updateIncorrectServiceSameType(){

        TService tServiceComparator = new TService(null, 201, true, "Limpieza","Calle Mercado,3 Modificada",54321);
        as.create(tServiceComparator);

        Integer idService = as.create(tServicePrincipal);
        tServicePrincipal = as.show(idService);

        tServicePrincipal.setCapacity(201);
        tServicePrincipal.setType("Limpieza");
        tServicePrincipal.setAddress("Calle Mercado,3 Modificada");
        tServicePrincipal.setCapacity(54321);

        assertThrows(ASException.class, ()-> as.update(tServicePrincipal));
    }

    @Test
    public void updateIncorrectServiceTypeNull(){

        // Service con campos igual menos el type
        TService tServiceComparator = new TService(null, 201, true, "Limpieza","Calle Mercado,3 Modificada",54321);
        as.create(tServiceComparator);

        Integer idService = as.create(tServicePrincipal);
        tServicePrincipal = as.show(idService);

        tServicePrincipal.setCapacity(201);
        tServicePrincipal.setType(null);
        tServicePrincipal.setAddress("Calle Mercado,3 Modificada");
        tServicePrincipal.setCapacity(54321);

        assertThrows(ASException.class, ()-> as.update(tServicePrincipal));
    }

    @Test
    public void updateIncorrectServiceNotExist(){
        tServicePrincipal.setId(20);
        assertThrows(ASException.class, ()-> as.update(tServicePrincipal));
    }

    @Test
    public void updateIncorrectServiceZero(){
        tServicePrincipal.setId(0);
        assertThrows(ASException.class, ()-> as.update(tServicePrincipal));
    }

    @Test
    public void updateIncorrectServiceNotActive(){
        tServicePrincipal.setActive(false);
        assertThrows(ASException.class, ()-> as.update(tServicePrincipal));
    }

    @Test
    public void updateIncorrectServiceActiveNull(){
        tServicePrincipal.setActive(null);
        assertThrows(ASException.class, ()-> as.update(tServicePrincipal));
    }

    @Test
    public void updateIncorrectServiceNotNullActive(){
        tServicePrincipal.setId(null);
        tServicePrincipal.setActive(true);

        assertThrows(ASException.class, ()-> as.update(tServicePrincipal));
    }

    @Test
    public void updateIncorrectServiceNotNegativeActive(){
        tServicePrincipal.setId(-1);
        tServicePrincipal.setActive(true);

        assertThrows(ASException.class, ()-> as.update(tServicePrincipal));
    }

    // --------------------- SHOW --------------------

    @Test
    public void showServiceSuccessful(){
        Integer idService = as.create(tServicePrincipal);
        assertTrue(checkTransfersService(tServicePrincipal, as.show(idService)));
    }

    @Test
    public void showServiceNotExits(){
        assertThrows(ASException.class, ()-> as.show(20));
    }

    @Test
    public void showServiceIdNegative(){
        assertThrows(ASException.class, ()-> as.show(-1));
    }

    @Test
    public void showServiceIdNull(){
        assertThrows(ASException.class, ()-> as.show(null));
    }

    // ----------------- SHOW ALL -------------------


    @Test
    public void showAllServiceSuccessful(){
        Integer idServicePrincipal = as.create(tServicePrincipal);
        tServicePrincipal.setType("Reparaciones");

        as.create(tServicePrincipal);
        Collection<TService> c = as.showAll();

        for(TService tServiceAux : c){
            if(tServiceAux.getId().equals(idServicePrincipal))
                assertTrue(checkTransferValues(tServiceAux,"Limpieza"));
            else
                assertTrue(checkTransferValues(tServiceAux,"Reparaciones"));
        }
    }

    @Test
    public void showAllCitySuccessfulEmpty() {
        Collection<TService> c = as.showAll();
        assertTrue(c.isEmpty());
    }

    private boolean checkTransferValues(TService out, String type) {
        return out.getType().equals(type) && out.isActive();
    }

    private boolean checkTransfersService(TService first, TService second) {
        return first.getId().equals(second.getId())
                && first.getAddress().equals(second.getAddress())
                && first.getNumVehiclesAttended().equals(second.getNumVehiclesAttended())
                && first.getType().equals(second.getType())
                && first.isActive().equals(second.isActive());
    }

    @Test
    public void showServicesFromLevel() {

        // Contract one

        Integer idService = as.create(tServicePrincipal);
        tServicePrincipal = as.show(idService);

        Integer idMainOffice = asMainOffice.create(tMainOfficePrincipal);
        tServicePrincipal = as.show(idService);

        tContractPrincipal.setIdService(idService);
        tContractPrincipal.setIdMainOffice(idMainOffice);
        tContractPrincipal.setServiceLevel(3);
        tContractPrincipal.setActive(true);

        asContract.create(tContractPrincipal);

        // Contract two

        tServicePrincipal = new TService(null, 500, true, "cloud","Calle Alberto Guacamole,3",9876);
        tMainOfficePrincipal = new TMainOffice(null,"Algete","Calle Don Juan", true);

        idService = as.create(tServicePrincipal);
        tServicePrincipal = as.show(idService);

        idMainOffice = asMainOffice.create(tMainOfficePrincipal);
        tServicePrincipal = as.show(idService);

        tContractPrincipal.setIdService(idService);
        tContractPrincipal.setIdMainOffice(idMainOffice);
        tContractPrincipal.setServiceLevel(2);
        tContractPrincipal.setActive(true);

        asContract.create(tContractPrincipal);

        assertTrue(as.showServicesFromLevel(3).size() == 1);
    }

    @Test
    public void showIncorrectServicesFromLevelNull() {
        assertThrows(ASException.class, ()-> as.show(null));
    }

    @Test
    public void showIncorrectServicesFromLevelNegative() {
        assertThrows(ASException.class, ()-> as.show(-1));
    }

    @Test
    public void showServicesFromLevelEmpty() {
        assertTrue(as.showServicesFromLevel(3).isEmpty());
    }

}
