package main.java.org.mainapp;



import main.java.org.commands.*;
import main.java.org.helperclass.MyReaderWriter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;

public class AssemblyLineApp {

    private static VehicleContainer memory = new VehicleContainer();
    private static Collection<ICommand> COMMANDS;

    static {
        // Arrays.asList returns a fixed-size list backed by the specified array
        // The method acts as a bridge between array-based and collection-based APIs
        COMMANDS = Arrays.asList(new ICommand[] {new CreateVehicle(memory), new DisassembleVehicle(memory),
                                    new Find(memory), new Print(memory), new Read(memory), new Update(memory)});
    }

    public AssemblyLineApp(){}

    public static void main(String[] args) {
        File f = new File("vehicles.ser");
        if (f.exists()) {
            MyReaderWriter reader = new MyReaderWriter();
            try {
                reader.readFromFile(memory);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No vehicles have been stored to persistent storage yet (vehicles.ser)");
        }

        System.out.println("Please, write down your command");
        InputStream stream = System.in;
        AssemblyLineLogic inter = new AssemblyLineLogic(COMMANDS);
        inter.processInput(stream);
    }
}
