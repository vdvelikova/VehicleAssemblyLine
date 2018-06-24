package main.java.org.commands;

import main.java.org.mainapp.VehicleContainer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Read implements ICommand {

    private final String commandName = "read";
    private VehicleContainer vehicleContainer;

    public Read(VehicleContainer vehicleContainer){
        this.vehicleContainer = vehicleContainer;
    }

    @Override
    public String getNameOfCommand() {
        return this.commandName;
    }

    /**
     * The method reads a file from the computer, which the user has provided as an input.
     * The file is read line by line. When a line is being read:
     * Each line should begin with "create", if a line does not begin with create then
     * a message shows up to the user. No vehicle object is created and the method goes on to reading the next line.
     * When a vehicle is created its information is printed to the console.
     * @param input
     * @return
     */
    @Override
    public String executeCommand(String input) {
        String file = input;
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            for(String line; (line = br.readLine()) != null; ) {
                System.out.println(line);
                String currentLine[] = line.split(" ", 2);
                String command = currentLine[0];
                if(!command.equals("create")){
                    System.out.println("The commands in the file can be only for creating vehicles!\nEach line in the text file must begin with create.");
                    continue;
                }
                String args = currentLine[1];
                CreateVehicle create = new CreateVehicle(vehicleContainer);
                System.out.println(create.executeCommand(args));
            }

        } catch (FileNotFoundException e) {
            return "The file cannot be found!";
        } catch (IOException e) {
            return "There is I/O exception problem!";
        }
        return "End of file reached.";
    }
}
