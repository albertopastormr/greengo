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
    void createServiceSuccessful() throws ASException, IncorrectInputException { //-
        assertTrue(as.create(tService)>0);
    }

    @Test
    void createIncorrectServiceSameType() throws ASException, IncorrectInputException { //-
        as.create(tService);

        // all different fields except the type
        TService tService2 = new TService(null, 201, true,
                "Taller","Calle mercado,3 Modificado",54321);
        assertThrows(ASException.class, ()-> as.create(tService2));
    }

    @Test
    void createIncorrectInputServiceIdNegative(){ //-
        tService.setId(-1);
        assertThrows(IncorrectInputException.class, ()-> as.create(tService));
    }

    @Test
    void createIncorrectInputServiceTypeNull(){ //-
        tService.setType(null);
        assertThrows(IncorrectInputException.class, ()-> as.create(tService));
    }

    @Test
    void createIncorrectInputServiceTypeVoid(){ //-
        tService.setType("");
        assertThrows(IncorrectInputException.class, ()-> as.create(tService));
    }

    @Test
    void createIncorrectInputNullAddress(){ //-
        tService.setAddress(null);
        assertThrows(IncorrectInputException.class, ()->as.create(tService));
    }

    @Test
    void createIncorrectInputVoidAddress(){ //-
        tService.setAddress("");
        assertThrows(IncorrectInputException.class, ()->as.create(tService));
    }

    @Test
    void createIncorrectInputNumVehiclesAttendedNegative(){ //-
        tService.setNumVehiclesAttended(-1);
        assertThrows(IncorrectInputException.class, ()->as.create(tService));
    }

    @Test
    void createIncorrectInputNumVehiclesAttendedNull(){ //-
        tService.setNumVehiclesAttended(null);
        assertThrows(IncorrectInputException.class, ()->as.create(tService));
    }

    @Test
    void createIncorrectInputActiveNull(){ //-
        tService.setActive(null);
        assertThrows(IncorrectInputException.class, ()->as.create(tService));
    }


    // --------------------- DROP --------------------
    @Test
    void dropServiceSuccessful() throws ASException, IncorrectInputException { //-
        as.drop(as.create(tService));
        assertTrue(!as.show(tService.getId()).isActive());
    }

    @Test
    void dropServiceWithContractAssociatedSuccessful() throws ASException, IncorrectInputException { //-
        Integer idService = as.create(tService);
        Integer idMainOffice = asMainOffice.create(tMainOffice);

        tContract.setIdService(idService);
        tContract.setIdMainOffice(idMainOffice);
        tContract.setServiceLevel(3);
        tContract.setActive(false);

        asContract.create(tContract);

        assertTrue(!as.show(as.drop(idService)).isActive());
    }

    @Test
    void dropIncorrectServiceWithContractActive() throws ASException, IncorrectInputException { //-
        Integer idService = as.create(tService);
        Integer idMainOffice = asMainOffice.create(tMainOffice);

        tContract.setIdService(idService);
        tContract.setIdMainOffice(idMainOffice);
        tContract.setServiceLevel(3);
        tContract.setActive(true);

        asContract.create(tContract);

        assertThrows(ASException.class, ()-> as.drop(idService));
    }

    @Test
    void dropIncorrectServiceAlreadyInactive() throws ASException, IncorrectInputException { //-
        Integer idService = as.drop(as.create(tService));

        assertThrows(ASException.class, ()-> as.drop(idService));
    }

    @Test
    void dropIncorrectServiceNotExist(){
        assertThrows(ASException.class, ()-> as.drop(20));
    } //-

    @Test
    void dropIncorrectServiceIdNull(){
        assertThrows(IncorrectInputException.class, ()-> as.drop(null));
    } //-

    @Test
    void dropIncorrectServiceIdNegative(){
        assertThrows(IncorrectInputException.class, ()-> as.drop(-1));
    } //-


    // --------------------- UPDATE --------------------

    @Test
    void updateServiceSuccessful() throws ASException, IncorrectInputException { //-
        // tService = new TService(null, 200, true, "Taller","Calle mercado,3",12345);

        TService oldService = as.show(as.create(tService));

        tService.setId(oldService.getId());
        tService.setCapacity(201);
        tService.setType("Taller modificado");
        tService.setAddress("Calle Mercado,3 Modificada");
        tService.setNumVehiclesAttended(54321);

        TService tServiceUpdated = as.show(as.update(tService));

        assertFalse(checkTransferValues(oldService,tServiceUpdated));
        assertTrue(checkTransferValues(tService, tServiceUpdated));
    }

    @Test
    void updateIncorrectServiceSameType() throws ASException, IncorrectInputException { //-
        // tService = new TService(null, 200, true, "Taller","Calle mercado,3",12345);
        Integer idService = as.create(tService);

        TService service2 = new TService(null,200,true,
                "Lavado","Calle mayor",100);
        as.create(service2);

        tService.setId(idService);

        tService.setType("Lavado");
        assertThrows(ASException.class, ()->as.update(tService));
    }

    @Test
    void updateIncorrectActiveModified() throws ASException, IncorrectInputException {
        // tService = new TService(null, 200, true, "Taller","Calle mercado,3",12345);
        as.create(tService);
        tService.setActive(false); //active field can't be modified

        assertThrows(ASException.class, ()->as.update(tService));
    }

    @Test
    void updateIncorrectServiceNotExist() {
        tService.setId(20);
        assertThrows(ASException.class, ()-> as.update(tService));
    }

    @Test
    void updateIncorrectInputNullID() {
        tService.setId(null);
        assertThrows(IncorrectInputException.class, ()-> as.update(tService));
    }

    @Test
    void updateIncorrectInputId0() {
        tService.setId(0);
        assertThrows(IncorrectInputException.class, ()-> as.update(tService));
    }

    @Test
    void updateIncorrectInputNegativeId() {
        tService.setId(-1);
        assertThrows(IncorrectInputException.class, ()-> as.update(tService));
    }

    @Test
    void updateIncorrectInputNegativeCapacity(){
        tService.setCapacity(-10);
        assertThrows(IncorrectInputException.class, ()->as.update(tService));
    }

    @Test
    void updateIncorrectInputCapacity0(){
        tService.setCapacity(0);
        assertThrows(IncorrectInputException.class, ()->as.update(tService));
    }

    @Test
    void updateIncorrectInputNullActive(){
        tService.setActive(null);
        assertThrows(IncorrectInputException.class, ()->as.update(tService));
    }

    @Test
    void updateIncorrectInputNullType(){
        tService.setType(null);
        assertThrows(IncorrectInputException.class,()->as.update(tService));
    }

    @Test
    void updateIncorrectInputVoidType(){
        tService.setType("");
        assertThrows(IncorrectInputException.class,()->as.update(tService));
    }

    @Test
    void updateIncorrectInputNullAddress(){
        tService.setAddress(null);
        assertThrows(IncorrectInputException.class,()->as.update(tService));
    }

    @Test
    void updateIncorrectInputVoidAddress(){
        tService.setAddress("");
        assertThrows(IncorrectInputException.class,()->as.update(tService));
    }

    @Test
    void updateIncorrectInputNegativeNumVehicleAttended(){
        tService.setNumVehiclesAttended(-1);
        assertThrows(IncorrectInputException.class,()->as.update(tService));
    }

    @Test
    void updateIncorrectInputNullNumVehicleAttended(){
        tService.setNumVehiclesAttended(null);
        assertThrows(IncorrectInputException.class,()->as.update(tService));
    }

    @Test
    void updateIncorrectInputNumVehicleAttended0(){
        tService.setNumVehiclesAttended(0);
        assertThrows(IncorrectInputException.class,()->as.update(tService));
    }

    // --------------------- SHOW --------------------

    @Test
    public void showServiceSuccessful() throws ASException, IncorrectInputException {
        Integer idService = as.create(tService);
        assertTrue(checkTransferValues(tService, as.show(idService)));
    }

    @Test
    void showServiceNotExits(){
        assertThrows(ASException.class, ()-> as.show(20));
    }

    @Test
    void showServiceIdNegative(){
        assertThrows(IncorrectInputException.class, ()-> as.show(-1));
    }

    @Test
    void showServiceIdNull() { assertThrows(IncorrectInputException.class, ()-> as.show(null)); }

    @Test
    void showServiceId0() { assertThrows(IncorrectInputException.class, ()-> as.show(0)); }

    // ----------------- SHOW ALL -------------------


    @Test
    void showAllServiceSuccessful() throws ASException, IncorrectInputException {
        Integer idService1 = as.create(tService);
        tService.setType("Reparaciones");

        as.create(tService);
        Collection<TService> c = as.showAll();

        for(TService tServiceAux : c){
            if(tServiceAux.getId().equals(idService1))
                assertEquals(tServiceAux.getType(),"Limpieza");
            else
                assertEquals(tServiceAux.getType(),"Reparaciones");
        }
    }

    @Test
    void showAllCitySuccessfulEmpty() throws ASException {
        Collection<TService> c = as.showAll();
        assertTrue(c.isEmpty());
    }

    //show service by level
    @Test
    void showServicesFromLevelSuccessful() throws ASException, IncorrectInputException {
        // Contract one

        Integer idService = as.create(tService);
        Integer idMainOffice = asMainOffice.create(tMainOffice);

        tContract.setIdService(idService);
        tContract.setIdMainOffice(idMainOffice);
        tContract.setServiceLevel(3);
        tContract.setActive(true);

        asContract.create(tContract);

        // Contract two

        TService service2 = new TService(null, 500, true,
                "cloud","Calle Alberto Guacamole,3",9876);
        tMainOffice = new TMainOffice(null,"Algete","Calle Don Juan", true);

        idService = as.create(service2);
        idMainOffice = asMainOffice.create(tMainOffice);

        tContract.setIdService(idService);
        tContract.setIdMainOffice(idMainOffice);
        tContract.setServiceLevel(2);
        tContract.setActive(true);

        asContract.create(tContract);

        assertEquals(1,as.showServicesByLevel(3).size());
        assertTrue(checkTransferValues(tService,as.showServicesByLevel(3).iterator().next()));
    }

    @Test
    void showServicesFromLevelEmpty() throws ASException, IncorrectInputException {
        assertTrue(as.showServicesByLevel(3).isEmpty());
    }

    @Test
    void showServicesFromLevelIncorrectInputNullLevel() {
        assertThrows(IncorrectInputException.class, ()-> as.showServicesByLevel(null));
    }

    @Test
    void showServicesFromLevelIncorrectInputLevel0() {
        assertThrows(IncorrectInputException.class, ()-> as.showServicesByLevel(0));
    }

    @Test
    void showServicesFromLevelIncorrectInputNegativeLevel() {
        assertThrows(IncorrectInputException.class, ()-> as.showServicesByLevel(-1));
    }

    //private methods
    private boolean checkTransferValues(TService expected, TService actual) {
        return expected.getId().equals(actual.getId())
                && expected.getAddress().equals(actual.getAddress())
                && expected.getNumVehiclesAttended().equals(actual.getNumVehiclesAttended())
                && expected.getType().equals(actual.getType())
                && expected.isActive().equals(actual.isActive());
    }
}
