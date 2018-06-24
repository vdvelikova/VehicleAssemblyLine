package test.java.org.parsers;


import main.java.org.parsers.TransmissionParser;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TransmissionParserTest {

    @Test
    public void validTransmissionInputInformationNaN(){
        String transmissionInput = "none";
        String expectedOutput = "Manual-4";
        String engineType = "D";
        TransmissionParser tp = new TransmissionParser();
        assertEquals(expectedOutput, tp.validateTransmissionInput(transmissionInput, engineType));
    }

    @Test
    public void validTransmissionInputInformationAuto4(){
        String transmissionInput = "transmission=Auto-4";
        String expectedOutput = "Auto-4";
        String engineType = "D";
        TransmissionParser tp = new TransmissionParser();
        assertEquals(expectedOutput, tp.validateTransmissionInput(transmissionInput, engineType));
    }

    @Test
    public void validTransmissionInputInformationAuto6(){
        String transmissionInput = "transmission=Auto-6";
        String expectedOutput = "Auto-6";
        String engineType = "B";
        TransmissionParser tp = new TransmissionParser();
        assertEquals(expectedOutput, tp.validateTransmissionInput(transmissionInput, engineType));
    }

    @Test
    public void validTransmissionInputInformationAuto8(){
        String transmissionInput = "transmission=Auto-8";
        String expectedOutput = "Auto-8";
        String engineType = "B";
        TransmissionParser tp = new TransmissionParser();
        assertEquals(expectedOutput, tp.validateTransmissionInput(transmissionInput, engineType));
    }

    @Test
    public void validTransmissionInputInformationManual4(){
        String transmissionInput = "transmission=Manual-4";
        String expectedOutput = "Manual-4";
        String engineType = "B";
        TransmissionParser tp = new TransmissionParser();
        assertEquals(expectedOutput, tp.validateTransmissionInput(transmissionInput,engineType));
    }

    @Test
    public void validTransmissionInputInformationManual6(){
        String transmissionInput = "transmission=Manual-6";
        String expectedOutput = "Manual-6";
        String engineType = "D";
        TransmissionParser tp = new TransmissionParser();
        assertEquals(expectedOutput, tp.validateTransmissionInput(transmissionInput, engineType));
    }

    @Test
    public void invalidTransmissionInputInformationElectricVehicle(){
        String transmissionInput = "transmission=Manual-6";
        String engineType = "E";
        TransmissionParser tp = new TransmissionParser();
        assertEquals(null, tp.validateTransmissionInput(transmissionInput,engineType));
    }

    @Test
    public void invalidTransmissionInputInformation(){
        String transmissionInput = "Transmission=Manual-6";
        String engineType = "B";
        TransmissionParser tp = new TransmissionParser();
        assertEquals(null, tp.validateTransmissionInput(transmissionInput,engineType));
    }

    @Test
    public void invalidTransmissionInputInformationManual(){
        String transmissionInput = "transmission=Manu-6";
        String engineType = "D";
        TransmissionParser tp = new TransmissionParser();
        assertEquals(null, tp.validateTransmissionInput(transmissionInput,engineType));
    }


    @Test
    public void invalidTransmissionInputInformationAuto7(){
        String transmissionInput = "transmission=Auto-7";
        String engineType = "B";
        TransmissionParser tp = new TransmissionParser();
        assertEquals(null, tp.validateTransmissionInput(transmissionInput,engineType));
    }

    @Test
    public void invalidTransmissionInputInformationAuto3(){
        String transmissionInput = "transmission=Auto-3";
        String engineType = "D";
        TransmissionParser tp = new TransmissionParser();
        assertEquals(null, tp.validateTransmissionInput(transmissionInput,engineType));
    }

    @Test
    public void invalidTransmissionInputInformationManual3(){
        String transmissionInput = "transmission=Manual-3";
        String engineType = "B";
        TransmissionParser tp = new TransmissionParser();
        assertEquals(null, tp.validateTransmissionInput(transmissionInput,engineType));
    }

    @Test
    public void invalidTransmissionInputInformationManual7(){
        String transmissionInput = "transmission=Manual-7";
        String engineType = "D";
        TransmissionParser tp = new TransmissionParser();
        assertEquals(null, tp.validateTransmissionInput(transmissionInput,engineType));
    }

    @Test
    public void invalidTransmissionInputInformationManual8(){
        String transmissionInput = "transmission=Manual-8";
        String engineType = "B";
        TransmissionParser tp = new TransmissionParser();
        assertEquals(null, tp.validateTransmissionInput(transmissionInput,engineType));
    }



}
