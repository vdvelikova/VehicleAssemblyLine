package main.java.org.vehicle;


import main.java.org.mainapp.VehicleContainer;
import main.java.org.vehiclecomponents.Engine;
import main.java.org.vehiclecomponents.Model;
import main.java.org.vehiclecomponents.Transmission;

import java.io.Serializable;

public class Vehicle implements Serializable{
    private VehicleContainer vehicleContainer;
    private Model model;
    private Engine engine;
    private Transmission transmission;
    private String vehicleType;
    private VIN vin;
    String countryISO = "BG";
    String factoryIdentification = "1";
    String possibleSymbols = "ABCDEFGHJKLMNPRSTUVWXYZ1234567890";

    public Vehicle(String vehicleType, Model model, Engine engine, Transmission transmission, VehicleContainer vehicleContainer) {
        this.vin = new VIN(countryISO, factoryIdentification, possibleSymbols, vehicleContainer);
        this.vehicleType = vehicleType;
        this.model = model;
        this.engine = engine;
        this.transmission = transmission;
        this.vehicleContainer = vehicleContainer;
    }

    public void saveToAssembeledVehicles(){
        this.vehicleContainer.saveToAssembledVehicles(this.vin.toString(), this);
    }


    /**
     * The method's purpose is to be used for the output of create command.
     * @return a String representing the information for a vehicle object
     */
    public String getOutputInformation() {
        return  "VIN = " + this.vin.toString() +
                " model = " + this.model.getModel() +
                " type = " + this.model.getModelType() +
                " engine = " + this.engine.engineInformationForOutput() +
                " transmission = " + this.transmission.getTransmissionDescription();
    }

    public String getEmissionStandard(){
        String emissionStandard = this.engine.getEmissionStandard();
        return emissionStandard;
    }

    public void setNewEmissionStandard(String newValue){
        this.engine.changeEmissionStandard(newValue);
    }

    public void addTurboToEngine(){
        this.engine.addTurbo();
    }

    public String getVIN(){
        return this.vin.toString();
    }

    public String retrieveEngineType() {
        String engineType = this.engine.getEngineType();
        return engineType;
    }

    public String retrieveDisplacementOrPower(){
        String displacementOrPower = this.engine.getDisplacementPower();
        return displacementOrPower;
    }

    public void setNewTransmission(String transmission){
        this.transmission.changeTransmission(transmission);
    }

}
