package main.java.org.commands;


import main.java.org.helperclass.MyReaderWriter;
import main.java.org.mainapp.VehicleContainer;
import main.java.org.vehicle.Vehicle;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Update implements ICommand {

    private final String commandName = "update";
    private VehicleContainer vehicleContainer;

    public Update(VehicleContainer dataContainer){
        this.vehicleContainer = dataContainer;
    }

    @Override
    public String getNameOfCommand() {
        return this.commandName;
    }

    /**
     * The method takes a vin. If there is a vehicle with the specified vin
     * a check is made if the desired update is possible.
     * If so - the information for the specified vehicle is changed.     *
     * @param input
     * @return
     */
    @Override
    public String executeCommand(String input) {
        String[] parts = input.split(" ");
        String vin = parts[0];
        String changes = parts[1];

        String regex = "^engine=-(?:(-euro[3-6])|(?:(\\*T)(?:-transmission=(Auto-[4-6,8]|Manual-[4-6,8])?)?))$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(changes);
        try {
            if(matcher.find()){
                Map<String, Vehicle> assembledVehicles = vehicleContainer.getAssembledVehicles();
                Vehicle vehicle = assembledVehicles.get(vin);
                MyReaderWriter mw = new MyReaderWriter();
                if(matcher.group(1) != null){
                    String emissionStandard = matcher.group(1);
                    vehicle.setNewEmissionStandard(emissionStandard);
                    mw = new MyReaderWriter();
                    mw.writeMapToFile(vehicleContainer);
                    return vehicle.getOutputInformation();
                }
                if (matcher.group(2) != null){
                    String displacementOrPower = vehicle.retrieveDisplacementOrPower();
                    String lastSymbol = displacementOrPower.substring(displacementOrPower.length() - 1);
                    if(lastSymbol.equals("T") || (vehicle.retrieveEngineType().equals("E"))){
                        throw new IllegalArgumentException("The vehicle is already mounted a Turbo or its engine is Electric");
                    }else {
                        vehicle.addTurboToEngine();
                        mw.writeMapToFile(vehicleContainer);
                        if (matcher.group(3) != null){
                            String newTransmission = matcher.group(3);
                            String vehicleEngineType = vehicle.retrieveEngineType();
                            if(vehicleEngineType.equals("E")){
                                throw new IllegalArgumentException("Electric vehicles DO NOT have transmission.");
                            }else {
                                vehicle.setNewTransmission(newTransmission);
                                mw.writeMapToFile(vehicleContainer);
                                return vehicle.getOutputInformation();
                            }
                        }
                        return vehicle.getOutputInformation();
                    }
                }
            }
        }catch (IllegalArgumentException e){
            return e.getMessage();
        }
        return "";
    }
}
