package main.java.org.commands;

import main.java.org.helperclass.MyReaderWriter;
import main.java.org.mainapp.VehicleContainer;
import main.java.org.parsers.EngineParser;
import main.java.org.parsers.EngineParserFactory;
import main.java.org.parsers.ModelParser;
import main.java.org.parsers.TransmissionParser;
import main.java.org.vehicle.Vehicle;
import main.java.org.vehiclecomponents.Engine;
import main.java.org.vehiclecomponents.Model;
import main.java.org.vehiclecomponents.Transmission;

import java.io.Serializable;
import java.util.List;


public class CreateVehicle implements ICommand, Serializable {

    private final String commandName = "create";
    private VehicleContainer vehicleContainer;

    public CreateVehicle (VehicleContainer dataContainer){
        this.vehicleContainer = dataContainer;
    }

    @Override
    public String getNameOfCommand() {
        return commandName;
    }

    /**
     * Splits the original input into parts, corresponding to the
     * components of Vehicle (type, model, engine, transmission).
     * Saves the information for each component in its own String variable.
     * Creates parser objects for each vehicle component and calls its own version of validateInput method.
     * If any kind of problem occurs (wrong syntax, invalid input, .etc),
     * the parsers terminate the execution of CREATE command by returning empty String
     * and printing the error that was caught.
     * If all parsers receive valid input data - objects of type Model, Engine and Transmission are created.
     * They are passed to Vehicle class constructor. The object created is returned by the CreateVehicle command in the form of String information for it/the vehicle.
     * @param input a String containing all the needed information for creating Vehicle object
     * @return a String presenting the information about the vehicle, organized by components
     */
    @Override
    public String executeCommand(String input) {
        String[] parts = input.split(" ");
        //Check if all information needed is entered
        if(parts.length < 3){
            System.out.println("The input is missing mandatory vehicle components information.");
            return "";
        }
        String vehicleType = parts[0];
        if(!vehicleType.equalsIgnoreCase("car")){
            if(!vehicleType.equalsIgnoreCase("suv")){
                throw new IllegalArgumentException("The type of vehicle entered is not valid.");
            }
        }

        String modelInfo = parts[1];
        String engineInfo = parts[2];
        String engineType;
        if(engineInfo.length() >= 7){
            engineType = String.valueOf(engineInfo.charAt(7)); //need engineType for the EngineParserFactory's 'getAppropriateEngineParser' method
        }else {
            System.out.println("The engine information is not correctly entered!");
            return "";
        }
        String transmissionInfo;
        if(parts.length == 4){
            transmissionInfo = parts[3];
        }else{
            transmissionInfo = "none";
        }


        ModelParser mp = new ModelParser();
        List modelInput = mp.validateModelInput(vehicleType,modelInfo);
        Model model;
        if(modelInput == null){
            return "";
        }else{
            model = new Model(vehicleType,modelInput);
        }

        EngineParserFactory epf = new EngineParserFactory();
        EngineParser ep = epf.getAppropriateEngineParser(engineType);
        List engineInput = ep.validateEngineInput(engineInfo);
        Engine engine;
        if(engineInput == null){
            return "";
        }else{
            engine = new Engine(engineInput);
        }

        TransmissionParser tp = new TransmissionParser();
        String transmissionInput = tp.validateTransmissionInput(transmissionInfo,engineType);
        Transmission transmission;
        if(transmissionInput == null){
            return "";
        }else {
            transmission = new Transmission(transmissionInput);
        }

        Vehicle vehicle = new Vehicle(vehicleType,model,engine,transmission,vehicleContainer);
        vehicle.saveToAssembeledVehicles();
        MyReaderWriter writer = new MyReaderWriter();
        writer.saveObjectToFile(vehicle);

        return vehicle.getOutputInformation();
    }
}
