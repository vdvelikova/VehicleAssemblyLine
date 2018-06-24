package test.java.org.parsers;
import main.java.org.parsers.ModelParser;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ModelParserTest {

    @Test
    public void invalidModelInputInformation(){
        String vehicleType = "suv";
        String modelInput = "test=Q5-hatchback";
        ModelParser mp = new ModelParser();
        assertEquals(null, mp.validateModelInput(vehicleType,modelInput));
    }

    @Test
    public void validCarModelInformation(){
        String vehicleType = "car";
        String modelInput = "model=A5-hatchback";
        List expectedOutput = new ArrayList();
        expectedOutput.add("A5");
        expectedOutput.add("hatchback");
        ModelParser mp = new ModelParser();
        assertEquals(expectedOutput, mp.validateModelInput(vehicleType,modelInput));
    }

    @Test
    public void validCarModelExcludingModelTypeInfo(){
        String vehicleType = "car";
        String modelInput = "model=A5";
        List expectedOutput = new ArrayList();
        expectedOutput.add("A5");
        expectedOutput.add("sedan");
        ModelParser mp = new ModelParser();
        assertEquals(expectedOutput, mp.validateModelInput(vehicleType,modelInput));
    }

    @Test
    public void validSUVModelInformation(){
        String vehicleType = "suv";
        String modelInput = "model=Q1";
        List expectedOutput = new ArrayList();
        expectedOutput.add("Q1");
        expectedOutput.add(null);
        ModelParser mp = new ModelParser();
        assertEquals(expectedOutput, mp.validateModelInput(vehicleType,modelInput));
    }

    @Test
    public void invalidCarModelInput(){
        String vehicleType = "car";
        String modelInput = "model=Q5-hatchback";
        ModelParser mp = new ModelParser();
        assertEquals(null, mp.validateModelInput(vehicleType,modelInput));
    }

    @Test
    public void invalidSUVModelInput(){
        String vehicleType = "suv";
        String modelInput = "model=A5-hatchback";
        ModelParser mp = new ModelParser();
        assertEquals(null, mp.validateModelInput(vehicleType,modelInput));
    }

    @Test
    public void invalidSUVModelInputIncludingModelType(){
        String vehicleType = "suv";
        String modelInput = "model=Q5-hatchback";
        ModelParser mp = new ModelParser();
        assertEquals(null, mp.validateModelInput(vehicleType,modelInput));
    }
}
