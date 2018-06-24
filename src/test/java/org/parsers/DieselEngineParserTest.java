package test.java.org.parsers;

import main.java.org.parsers.DieselEngineParser;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DieselEngineParserTest {

    @Test
    public void invalidInputInformation1L(){
        String engineInput = "engine=D-1L-euro3";
        DieselEngineParser dep = new DieselEngineParser();
        assertEquals(null,dep.validateEngineInput(engineInput));
    }

    @Test
    public void invalidInputInformation7L(){
        String engineInput = "engine=D-7L-euro3";
        DieselEngineParser dep = new DieselEngineParser();
        assertEquals(null,dep.validateEngineInput(engineInput));
    }


    @Test
    public void invalidInputInformation8L(){
        String engineInput = "engine=D-8L-euro3";
        DieselEngineParser dep = new DieselEngineParser();
        assertEquals(null,dep.validateEngineInput(engineInput));
    }

    @Test
    public void validInputInformation6L(){
        String engineInput = "engine=D-6L-euro3";
        List output = new ArrayList();
        output.add("D");
        output.add("6L");
        output.add("euro3");
        DieselEngineParser dep = new DieselEngineParser();
        assertEquals(output,dep.validateEngineInput(engineInput));
    }

    @Test
    public void validInputInformation196hpBorderCase(){
        String engineInput = "engine=D-196hp-euro3";
        List output = new ArrayList();
        output.add("D");
        output.add("196hp");
        output.add("euro3");
        DieselEngineParser dep = new DieselEngineParser();
        assertEquals(output,dep.validateEngineInput(engineInput));
    }

    @Test
    public void invalidInputInformation441hpT(){
        String engineInput = "engine=D-441hpT-euro3";
        DieselEngineParser dep = new DieselEngineParser();
        assertEquals(null,dep.validateEngineInput(engineInput));
    }

    @Test
    public void validInputInformation339hpT(){
        String engineInput = "engine=D-339hpT-euro3";
        List output = new ArrayList();
        output.add("D");
        output.add("339hpT");
        output.add("euro3");
        DieselEngineParser dep = new DieselEngineParser();
        assertEquals(output,dep.validateEngineInput(engineInput));
    }

    @Test
    public void validInputInformation684hpBorderCase(){
        String engineInput = "engine=D-684hp-euro6";
        List output = new ArrayList();
        output.add("D");
        output.add("684hp");
        output.add("euro6");
        DieselEngineParser dep = new DieselEngineParser();
        assertEquals(output,dep.validateEngineInput(engineInput));
    }

    @Test
    public void invalidInputInformation981hp(){
        String engineInput = "engine=D-981hp-euro6";
        DieselEngineParser dep = new DieselEngineParser();
        assertEquals(null,dep.validateEngineInput(engineInput));
    }

    @Test
    public void invalidInputInformation0hp(){
        String engineInput = "engine=D-0hp-euro6";
        DieselEngineParser dep = new DieselEngineParser();
        assertEquals(null,dep.validateEngineInput(engineInput));
    }

    @Test
    public void invalidInputInformationNegativeNumberHP(){
        String engineInput = "engine=D--12hp-euro6";
        DieselEngineParser dep = new DieselEngineParser();
        assertEquals(null,dep.validateEngineInput(engineInput));
    }


    @Test
    public void validInputInformation443hpT(){
        String engineInput = "engine=D-443hpT-euro3";
        List output = new ArrayList();
        output.add("D");
        output.add("443hpT");
        output.add("euro3");
        DieselEngineParser dep = new DieselEngineParser();
        assertEquals(output,dep.validateEngineInput(engineInput));
    }
}
