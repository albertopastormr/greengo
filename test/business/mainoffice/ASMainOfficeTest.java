package business.mainoffice;

import business.ASException;
import business.IncorrectInputException;
import business.mainoffice.as.ASMain_Office;
import business.mainoffice.factory.ASMain_OfficeFactory;
import business.service.as.ASService;
import business.service.factory.ASServiceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ASMainOfficeTest {
    private static ASMain_Office as = ASMain_OfficeFactory.getInstance().generateASMain_Office();
    private static ASService ass = ASServiceFactory.getInstance().generateASService();
    private static TMainOffice tmo = new TMainOffice(null,"Madrid","C/Villamayor",true);


    @BeforeEach
    private void setUp() throws Exception{


    }

    @Test
    public void createMainOfficeSuccessful(){
        assertTrue(as.create(tmo)>0);
    }

    @Test
    public void createMainOfficeIncorrectInput(){//id must be null
        tmo.setId(0);
        assertThrows(IncorrectInputException.class, () -> {as.create(tmo);});
    }

    @Test
    public void createMainOfficeIncorrectInput2(){//address mustn't be null
        tmo.setAdress(null);
        assertThrows(IncorrectInputException.class, () -> {as.create(tmo);});
    }

    @Test
    public void createMainOfficeIncorrectInput3(){//active mustn't be null
        tmo.setActive(null);
        assertThrows(IncorrectInputException.class, () -> {as.create(tmo);});
    }

    @Test
    public void createMainOfficeErrorAddress(){//couldn´t exit other main_office with the same address
        as.create(tmo);
        assertThrows(ASException.class, () -> {as.create(tmo);});
    }

    @Test
    public void dropMainOfficeSuccessful(){
        as.drop(as.create(tmo));
        assertTrue(!as.show(tmo.getId()).isActive());
    }

    @Test
    public void dropMainOfficeIncorrectInput(){//id mustn`t be null
        tmo.setId(null);
        assertThrows(IncorrectInputException.class, () -> {as.drop(tmo.getId());});
    }

    @Test
    public void dropMainOfficeIncorrectInput2(){//id must be > 0
        tmo.setId(-1);
        assertThrows(IncorrectInputException.class, () -> {as.drop(tmo.getId());});
    }

    @Test
    public void dropMainOfficeErrorNotExists(){//couldn´t drop a main_office which doesn't exists
        tmo.setId(100);
        assertThrows(ASException.class, () -> {as.drop(tmo.getId());});
    }

    @Test
    public void dropMainOfficeErrorNotActive(){//couldn´t drop a disbled main_office
        tmo.setActive(false);
        tmo.setId(as.create(tmo));
        tmo.setAdress("XXX");
        assertThrows(ASException.class, () -> {as.drop(tmo.getId());});
    }
}
