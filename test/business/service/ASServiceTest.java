package business.service;

import business.ASException;

import business.IncorrectInputException;
import business.contract.TContract;
import business.contract.as.ASContract;
import business.contract.factory.ASContractFactory;
import business.mainoffice.TMainOffice;
import business.mainoffice.as.ASMainOffice;

import business.mainoffice.factory.ASMainOfficeFactory;
import business.service.factory.ASServiceFactory;

import business.service.as.ASService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class ASServiceTest {

    private ASService as;
    private ASMainOffice asMainOffice;
    private ASContract asContract;

    private TService tService;
    private TMainOffice tMainOffice;
    private TContract tContract;

    @BeforeEach
    private void setUp() throws Exception{

        as = ASServiceFactory.getInstance().generateASService();
        asMainOffice = ASMainOfficeFactory.getInstance().generateASMainOffice();
        asContract = ASContractFactory.getInstance().generateASContract();

        tService = new TService(null, 200,
                true, "Taller","Calle mercado,3",12345);
        tMainOffice = new TMainOffice(null,"Avila","Calle Don Manuel", true);
        tContract = new TContract();
    }

    // --------------------- CREATE --------------------

    @Test
    public void createServiceSuccessful() throws ASException, IncorrectInputException {
        assertTrue(as.create(tService)>0);
    }

    @Test
    public void createIncorrectServiceSameType() throws ASException, IncorrectInputException {
        as.create(tService);

        // all different fields except the type
        TService tService2 = new TService(null, 201, true,
                "Taller","Calle mercado,3 Modificado",54321);
        assertThrows(ASException.class, ()-> as.create(tService2));
    }

    @Test
    public void createIncorrectInputServiceIdNegative(){
        tService.setId(-1);
        assertThrows(IncorrectInputException.class, ()-> as.create(tService));
    }

    @Test
    public void createIncorrectInputServiceTypeNull(){
        tService.setType(null);
        assertThrows(IncorrectInputException.class, ()-> as.create(tService));
    }

    @Test
    public void createIncorrectInputServiceTypeVoid(){
        tService.setType("");
        assertThrows(IncorrectInputException.class, ()-> as.create(tService));
    }

    @Test
    void createIncorrectInputNullAddress(){
        tService.setAddress(null);
        assertThrows(IncorrectInputException.class, ()->as.create(tService));
    }

    @Test
    void createIncorrectInputVoidAddress(){
        tService.setAddress("");
        assertThrows(IncorrectInputException.class, ()->as.create(tService));
    }

    @Test
    void createIncorrectInputNumVehiclesAttendedNegative(){
        tService.setNumVehiclesAttended(-1);
        assertThrows(IncorrectInputException.class, ()->as.create(tService));
    }

    @Test
    void createIncorrectInputNumVehiclesAttendedNull(){
        tService.setNumVehiclesAttended(null);
        assertThrows(IncorrectInputException.class, ()->as.create(tService));
    }

    @Test
    void createIncorrectInputActiveNull(){
        tService.setActive(null);
        assertThrows(IncorrectInputException.class, ()->as.create(tService));
    }

    // --------------------- DROP --------------------

    @Test
    public void dropServiceSuccessful() throws ASException, IncorrectInputException {
        as.drop(as.create(tService));
        assertTrue(!as.show(tService.getId()).isActive());
    }

    @Test
    public void dropIncorrectServiceContractAssociated() throws ASException, IncorrectInputException {

        Integer idService = as.create(tService);
        tService = as.show(idService);

        Integer idMainOffice = asMainOffice.create(tMainOffice);
        tService = as.show(idService);

        tContract.setIdService(idService);
        tContract.setIdMainOffice(idMainOffice);
        tContract.setServiceLevel(3);
        tContract.setActive(true);

        Integer idContract = asContract.create(tContract);
        tContract = asContract.show(idContract);

        assertThrows(ASException.class, ()-> as.drop(idService));
    }

    @Test
    public void dropServiceSuccessfulContractAssociated() throws ASException, IncorrectInputException {

        Integer idService = as.create(tService);
        tService = as.show(idService);

        Integer idMainOffice = asMainOffice.create(tMainOffice);
        tService = as.show(idService);

        tContract.setIdService(idService);
        tContract.setIdMainOffice(idMainOffice);
        tContract.setServiceLevel(3);
        tContract.setActive(false);

        Integer idContract = asContract.create(tContract);
        tContract = asContract.show(idContract);

        assertTrue(!as.show(as.drop(idService)).isActive());
    }

    @Test
    public void dropIncorrectServiceAlreadyInactive() throws ASException, IncorrectInputException {
        Integer idService = as.drop(as.create(tService));

        assertThrows(ASException.class, ()-> as.drop(idService));
    }

    @Test
    public void dropIncorrectServiceNotExist(){
        assertThrows(IncorrectInputException.class, ()-> as.drop(20));
    }

    @Test
    public void dropIncorrectServiceIdNull(){
        assertThrows(IncorrectInputException.class, ()-> as.drop(null));
    }

    @Test
    public void dropIncorrectServiceIdNegative(){
        assertThrows(IncorrectInputException.class, ()-> as.drop(-1));
    }


    // --------------------- UPDATE --------------------

    @Test
    public void updateServiceSuccessful() throws ASException, IncorrectInputException {

        // Change all fields less type
        TService tServiceComparator = new TService(null, 201, true, "Limpieza","Calle Mercado,3 Modificada",54321);
        tServiceComparator = as.show(as.create(tServiceComparator));

        Integer idService = as.create(tService);
        tService = as.show(idService);

        tService.setCapacity(201);
        tService.setType("Taller modificado");
        tService.setAddress("Calle Mercado,3 Modificada");
        tService.setCapacity(54321);

        Integer idServiceSecond = as.update(tService);
        TService tServiceSecond = as.show(idServiceSecond);

        assertEquals(tService.getId(), idServiceSecond);
        assertNotEquals(tServiceComparator.getType(), tServiceSecond.getType());
    }

    @Test
    public void updateIncorrectServiceSameType() throws ASException, IncorrectInputException {

        TService tServiceComparator = new TService(null, 201, true, "Limpieza","Calle Mercado,3 Modificada",54321);
        as.create(tServiceComparator);

        Integer idService = as.create(tService);
        tService = as.show(idService);

        tService.setCapacity(201);
        tService.setType("Limpieza");
        tService.setAddress("Calle Mercado,3 Modificada");
        tService.setCapacity(54321);

        assertThrows(ASException.class, ()-> as.update(tService));
    }

    @Test
    public void updateIncorrectServiceTypeNull() throws ASException, IncorrectInputException {

        // Service con campos igual menos el type
        TService tServiceComparator = new TService(null, 201, true, "Limpieza","Calle Mercado,3 Modificada",54321);
        as.create(tServiceComparator);

        Integer idService = as.create(tService);
        tService = as.show(idService);

        tService.setCapacity(201);
        tService.setType(null);
        tService.setAddress("Calle Mercado,3 Modificada");
        tService.setCapacity(54321);

        assertThrows(ASException.class, ()-> as.update(tService));
    }

    @Test
    public void updateIncorrectServiceNotExist() {
        tService.setId(20);
        assertThrows(ASException.class, ()-> as.update(tService));
    }

    @Test
    public void updateIncorrectServiceZero() {
        tService.setId(0);
        assertThrows(IncorrectInputException.class, ()-> as.update(tService));
    }

    @Test
    public void updateIncorrectServiceNotActive() {
        tService.setActive(false);
        assertThrows(ASException.class, ()-> as.update(tService));
    }

    @Test
    public void updateIncorrectServiceActiveNull() {
        tService.setActive(null);
        assertThrows(IncorrectInputException.class, ()-> as.update(tService));
    }

    @Test
    public void updateIncorrectServiceNotNullActive() {
        tService.setId(null);
        tService.setActive(true);

        assertThrows(IncorrectInputException.class, ()-> as.update(tService));
    }

    @Test
    public void updateIncorrectServiceNotNegativeActive(){
        tService.setId(-1);
        tService.setActive(true);

        assertThrows(IncorrectInputException.class, ()-> as.update(tService));
    }

    // --------------------- SHOW --------------------

    @Test
    public void showServiceSuccessful() throws ASException, IncorrectInputException {
        Integer idService = as.create(tService);
        assertTrue(checkTransfersService(tService, as.show(idService)));
    }

    @Test
    public void showServiceNotExits(){
        assertThrows(ASException.class, ()-> as.show(20));
    }

    @Test
    public void showServiceIdNegative(){
        assertThrows(IncorrectInputException.class, ()-> as.show(-1));
    }

    @Test
    public void showServiceIdNull(){
        assertThrows(IncorrectInputException.class, ()-> as.show(null));
    }

    // ----------------- SHOW ALL -------------------


    @Test
    public void showAllServiceSuccessful() throws ASException, IncorrectInputException {
        Integer idServicePrincipal = as.create(tService);
        tService.setType("Reparaciones");

        as.create(tService);
        Collection<TService> c = as.showAll();

        for(TService tServiceAux : c){
            if(tServiceAux.getId().equals(idServicePrincipal))
                assertTrue(checkTransferValues(tServiceAux,"Limpieza"));
            else
                assertTrue(checkTransferValues(tServiceAux,"Reparaciones"));
        }
    }

    @Test
    public void showAllCitySuccessfulEmpty() throws ASException {
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
    public void showServicesFromLevel() throws ASException, IncorrectInputException {

        // Contract one

        Integer idService = as.create(tService);
        tService = as.show(idService);

        Integer idMainOffice = asMainOffice.create(tMainOffice);
        tService = as.show(idService);

        tContract.setIdService(idService);
        tContract.setIdMainOffice(idMainOffice);
        tContract.setServiceLevel(3);
        tContract.setActive(true);

        asContract.create(tContract);

        // Contract two

        tService = new TService(null, 500, true, "cloud","Calle Alberto Guacamole,3",9876);
        tMainOffice = new TMainOffice(null,"Algete","Calle Don Juan", true);

        idService = as.create(tService);
        tService = as.show(idService);

        idMainOffice = asMainOffice.create(tMainOffice);
        tService = as.show(idService);

        tContract.setIdService(idService);
        tContract.setIdMainOffice(idMainOffice);
        tContract.setServiceLevel(2);
        tContract.setActive(true);

        asContract.create(tContract);

        assertTrue(as.showServicesFromLevel(3).size() == 1);
    }

    @Test
    public void showIncorrectServicesFromLevelNull() {
        assertThrows(IncorrectInputException.class, ()-> as.show(null));
    }

    @Test
    public void showIncorrectServicesFromLevelNegative() {
        assertThrows(IncorrectInputException.class, ()-> as.show(-1));
    }

    @Test
    public void showServicesFromLevelEmpty() throws ASException, IncorrectInputException {
        assertTrue(as.showServicesFromLevel(3).isEmpty());
    }

}
