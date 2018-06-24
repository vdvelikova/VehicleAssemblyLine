package test.java.org.parsers;

import main.java.org.parsers.BenzineEngineParser;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BenzineEngineParserTest {

    @Test
    public void validInputInformation1L(){
        String engineInput = "engine=B-1L-euro3";
        List expectedOutput = new ArrayList();
        expectedOutput.add("B");
        expectedOutput.add("1L");
        expectedOutput.add("euro3");
        BenzineEngineParser bep = new BenzineEngineParser();
        assertEquals(expectedOutput,bep.validateEngineInput(engineInput));
    }

    @Test
    public void validInputInformation6L(){
        String engineInput = "engine=B-6L-euro3";
        List expectedOutput = new ArrayList();
        expectedOutput.add("B");
        expectedOutput.add("6L");
        expectedOutput.add("euro3");
        BenzineEngineParser bep = new BenzineEngineParser();
        assertEquals(expectedOutput,bep.validateEngineInput(engineInput));
    }


    @Test
    public void validInputInformation8LEuro6(){
        String engineInput = "engine=B-8L-euro6";
        List expectedOutput = new ArrayList();
        expectedOutput.add("B");
        expectedOutput.add("8L");
        expectedOutput.add("euro6");
        BenzineEngineParser bep = new BenzineEngineParser();
        assertEquals(expectedOutput,bep.validateEngineInput(engineInput));
    }

    @Test
    public void validInputInformation987hpTBorderCase(){
        String engineInput = "engine=B-987hpT-euro3";
        List expectedOutput = new ArrayList();
        expectedOutput.add("B");
        expectedOutput.add("987hpT");
        expectedOutput.add("euro3");
        BenzineEngineParser bep = new BenzineEngineParser();
        assertEquals(expectedOutput,bep.validateEngineInput(engineInput));
    }

    @Test
    public void invalidInputInformation0L(){
        String engineInput = "engine=B-0L-euro6";
        BenzineEngineParser bep = new BenzineEngineParser();
        assertEquals(null,bep.validateEngineInput(engineInput));
    }

    @Test
    public void invalidInputInformation71hpBorderCase(){
        String engineInput = "engine=B-71hp-euro3";
        BenzineEngineParser bep = new BenzineEngineParser();
        assertEquals(null,bep.validateEngineInput(engineInput));
    }

    @Test
    public void validInputInformation197hpBorderCase(){
        String engineInput = "engine=B-197hp-euro3";
        List expectedOutput = new ArrayList();
        expectedOutput.add("B");
        expectedOutput.add("197hp");
        expectedOutput.add("euro3");
        BenzineEngineParser bep = new BenzineEngineParser();
        assertEquals(expectedOutput,bep.validateEngineInput(engineInput));
    }

    @Test
    public void validInputInformation73hpBorderCase(){
        String engineInput = "engine=B-73hp-euro3";
        List expectedOutput = new ArrayList();
        expectedOutput.add("B");
        expectedOutput.add("73hp");
        expectedOutput.add("euro3");
        BenzineEngineParser bep = new BenzineEngineParser();
        assertEquals(expectedOutput,bep.validateEngineInput(engineInput));
    }

    @Test
    public void validInputInformation74hpBorderCase(){
        String engineInput = "engine=B-74hp-euro3";
        List expectedOutput = new ArrayList();
        expectedOutput.add("B");
        expectedOutput.add("74hp");
        expectedOutput.add("euro3");
        BenzineEngineParser bep = new BenzineEngineParser();
        assertEquals(expectedOutput,bep.validateEngineInput(engineInput));
    }

    @Test
    public void validInputInformation987hpBorderCase(){
        String engineInput = "engine=B-987hp-euro3";
        List expectedOutput = new ArrayList();
        expectedOutput.add("B");
        expectedOutput.add("987hp");
        expectedOutput.add("euro3");
        BenzineEngineParser bep = new BenzineEngineParser();
        assertEquals(expectedOutput,bep.validateEngineInput(engineInput));
    }

    @Test
    public void validInputInformation986hpBorderCase(){
        String engineInput = "engine=B-986hp-euro3";
        List expectedOutput = new ArrayList();
        expectedOutput.add("B");
        expectedOutput.add("986hp");
        expectedOutput.add("euro3");
        BenzineEngineParser bep = new BenzineEngineParser();
        assertEquals(expectedOutput,bep.validateEngineInput(engineInput));
    }

    @Test
    public void invalidInputInformation983hpBorderCase(){
        String engineInput = "engine=B-983hp-euro3";
        BenzineEngineParser bep = new BenzineEngineParser();
        assertEquals(null,bep.validateEngineInput(engineInput));
    }

    @Test
    public void invalidInputInformation438hp(){
        String engineInput = "engine=D-438hp-euro6";
        BenzineEngineParser dep = new BenzineEngineParser();
        assertEquals(null,dep.validateEngineInput(engineInput));
    }

    @Test
    public void invalidInputInformation666hp(){
        String engineInput = "engine=D-666hp-euro6";
        BenzineEngineParser dep = new BenzineEngineParser();
        assertEquals(null,dep.validateEngineInput(engineInput));
    }

    @Test
    public void invalidInputInformationNegativeNumberHP(){
        String engineInput = "engine=D--12hp-euro6";
        BenzineEngineParser dep = new BenzineEngineParser();
        assertEquals(null,dep.validateEngineInput(engineInput));
    }
}
