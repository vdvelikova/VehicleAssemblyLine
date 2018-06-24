package test.java.org.parsers;

import main.java.org.parsers.ElectricEngineParser;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ElectricEngineParserTest {

    @Test
    public void validInputInformation(){
        String engineInput = "engine=E";
        List expectedOutput = new ArrayList();
        expectedOutput.add("E");
        expectedOutput.add("718hp");
        expectedOutput.add("euro6");
        ElectricEngineParser eep = new ElectricEngineParser();
        assertEquals(expectedOutput,eep.validateEngineInput(engineInput));
    }

    @Test
    public void validInputInformationFull(){
        String engineInput = "engine=E-718hp-euro6";
        List expectedOutput = new ArrayList();
        expectedOutput.add("E");
        expectedOutput.add("718hp");
        expectedOutput.add("euro6");
        ElectricEngineParser eep = new ElectricEngineParser();
        assertEquals(expectedOutput,eep.validateEngineInput(engineInput));
    }

    @Test
    public void invalidEngineTypeInput(){
        String engineInput = "engine=B-0L-euro6";
        ElectricEngineParser eep = new ElectricEngineParser();
        assertEquals(null,eep.validateEngineInput(engineInput));
    }

    @Test
    public void invalidInputDisplacementInfoGiven(){
        String engineInput = "engine=E-5L-euro6";
        ElectricEngineParser eep = new ElectricEngineParser();
        assertEquals(null,eep.validateEngineInput(engineInput));
    }

    @Test
    public void invalidInputEmissionStandardEURO4(){
        String engineInput = "engine=E-718hp-euro4";
        ElectricEngineParser eep = new ElectricEngineParser();
        assertEquals(null,eep.validateEngineInput(engineInput));
    }

    @Test
    public void invalidInputEmissionStandardMissing(){
        String engineInput = "engine=E-718hp";
        ElectricEngineParser eep = new ElectricEngineParser();
        assertEquals(null,eep.validateEngineInput(engineInput));
    }
}
