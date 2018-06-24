package main.java.org.commands;


import main.java.org.mainapp.VehicleContainer;
import main.java.org.vehicle.Vehicle;

import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Find implements ICommand {

    private final String commandName = "find";
    private VehicleContainer vehicleContainer;

    public Find(VehicleContainer dataContainer){
        this.vehicleContainer = dataContainer;
    }

    @Override
    public String getNameOfCommand() {
        return this.commandName;
    }

    /**
     * The method takes the users input for print command and checks it is possible/correct one - euro3,euro4,euro5, euro6.
     * If something other than the possible input is provided - notify the user of invalid input information.
     * Else, the method iterates through the map storing the assembled vehicles and for each checks whether
     * it is of emission standard type as requested (input information).
     * The vehicles whose information matches to the input are printed to the console
     * @param input
     * @return a String notifying that all vehicles who meet the criteria of the search are listed.
     *         OR a String notifying for syntax error in the provided input.
     *         OR a String telling that there are no vehicles who match the search
     */
    @Override
    public String executeCommand(String input) {
        String emissionRequested = input;
        String regex = "^(euro[3-6])$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(emissionRequested);
        try {
            if(matcher.find()) {
                Map<String, Vehicle> assembledVehicles = vehicleContainer.getAssembledVehicles();
                Iterator entries = assembledVehicles.entrySet().iterator();
                int numberOfMatches = 0;
                while (entries.hasNext()) {
                    Map.Entry entry = (Map.Entry) entries.next();
                    Vehicle vehicle = (Vehicle) entry.getValue();
                    String vehicleEmissionStandard = vehicle.getEmissionStandard();
                    if(input.equals(vehicleEmissionStandard)){
                        System.out.println(vehicle.getOutputInformation());
                        numberOfMatches ++;
                    }
                }
                if(numberOfMatches == 0){
                    return "No vehicles with emission standard " + emissionRequested + " in the database.";
                }else {
                    return "All vehicles with emission standard " + emissionRequested + " listed";
                }
            }else {
                throw new IllegalArgumentException("There is no such emission standard.");
            }
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return "";
        }
    }
}
