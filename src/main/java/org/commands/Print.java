package main.java.org.commands;


import main.java.org.mainapp.VehicleContainer;
import main.java.org.vehicle.Vehicle;

import java.util.Iterator;
import java.util.Map;

public class Print implements ICommand {

    private final String commandName = "print";
    private VehicleContainer vehicleContainer;

    public Print(VehicleContainer dataContainer){
        this.vehicleContainer = dataContainer;
    }

    @Override
    public String getNameOfCommand() {
        return this.commandName;
    }


    /**
     * Check the exact type of Print that is desired by the user (<vin> OR all).
     * If input is "all" - iteration through assembledVehicles is conducted,
     * calling method getOutputInformation (from Vehicle class) for each entry.
     * If an exact VIN is specified - use Iterator to loop through assembledVehicles
     * and compare each key ( using vin.toString) with the String input provided.
     * If a match is found - prints the related Vehicle object to the vin/input.
     * @param input string representation of VIN object
     * @return String value informing the user whether and how the command executed (successfully or not).     *
     */
    @Override
    public String executeCommand(String input) {
        try{
            if(input.equalsIgnoreCase("all")){
                Map<String, Vehicle> assembledVehicles = vehicleContainer.getAssembledVehicles();
                Iterator entries = assembledVehicles.entrySet().iterator();
                while (entries.hasNext()){
                    Map.Entry entry = (Map.Entry) entries.next();

                    Vehicle vehicle = (Vehicle) entry.getValue();
                    System.out.println(vehicle.getOutputInformation());
                }
                return "End of list of vehicles";
            }else if(!input.matches("BG1(.*)")){
                throw new IllegalArgumentException("The VIN entered is not of valid format!");
            }else {
                Map<String, Vehicle> assembledVehicles = vehicleContainer.getAssembledVehicles();
                Iterator entries = assembledVehicles.entrySet().iterator();
                while (entries.hasNext()){
                    Map.Entry entry = (Map.Entry) entries.next();
                    String key = (String) entry.getKey();
                    if(key.toString().equals(input)){
                        Vehicle vehicle = (Vehicle) entry.getValue();
                        return vehicle.getOutputInformation();
                    }
                }
            }
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return null;
        }
        return "No vehicle found with the given VIN";
    }
}
