package main.java.org.mainapp;


import main.java.org.vehicle.Vehicle;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class VehicleContainer implements Serializable{

    // the key(string) is the VIN
    private Map<String, Vehicle> assembledVehicles = new LinkedHashMap<>();
    //private Map<String, Vehicle> disassembledVehicles = new LinkedHashMap<>();

    public VehicleContainer(){}

    public Map<String, Vehicle> getAssembledVehicles(){
        return this.assembledVehicles;
    }

    public void saveToAssembledVehicles(String vin, Vehicle vehicle){
        assembledVehicles.put(vin,vehicle);
    }


}
