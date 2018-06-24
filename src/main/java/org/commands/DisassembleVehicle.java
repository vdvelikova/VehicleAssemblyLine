package main.java.org.commands;
import main.java.org.helperclass.MyReaderWriter;
import main.java.org.mainapp.VehicleContainer;
import main.java.org.vehicle.Vehicle;

import java.util.Map;



public class DisassembleVehicle implements ICommand {

    private final String commandName = "disassemble";
    private VehicleContainer vehicleContainer;

    public DisassembleVehicle (VehicleContainer dataContainer){
        this.vehicleContainer = dataContainer;
    }

    @Override
    public String getNameOfCommand() {
        return this.commandName;
    }

    /**
     * The method searches for a vehicle with the specified vin (input)
     * in the VehicleContainer's assembledVehicles map.
     * If a match is found - the Vehicle object mapped to the vin is deleted from the Map.
     * After that using MyReaderWriter object the method writeMapToFile is called
     * in order to update the persistent storage (removing the deleted Vehicle object).
     * @param input a VIN number represented as a string
     * @return a String which informs the user of the program what is the outcome of the command -
     *           an object was deleted OR there was no Vehicle object with the specified VIN
     */
    @Override
    public String executeCommand(String input) {
        Map<String, Vehicle> assembledVehicles = vehicleContainer.getAssembledVehicles();
        try {
            if(assembledVehicles.remove(input) == null){
                return "There is no vehicle with that VIN";
            }
            MyReaderWriter mw = new MyReaderWriter();
            mw.writeMapToFile(vehicleContainer);
        }catch (Exception e){
            return "A problem occurred while Disassemble command was being executed.";
        }
        return "Vehicle was deleted.";
    }
}
