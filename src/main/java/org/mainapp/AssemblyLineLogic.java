package main.java.org.mainapp;


import main.java.org.commands.*;

import java.io.InputStream;
import java.util.*;

public class AssemblyLineLogic {

    private static VehicleContainer memo = new VehicleContainer();
    private Map<String, ICommand> COMMAND_MAP;

    public AssemblyLineLogic(){
        this(Arrays.asList(new ICommand[]{new CreateVehicle(memo), new DisassembleVehicle(memo), new Find(memo),
                            new Print(memo), new Read(memo), new Update(memo)}));
    }

    public AssemblyLineLogic(Collection<ICommand> commands){
        this.COMMAND_MAP = new HashMap<>();
        Iterator iter = commands.iterator();

        while (iter.hasNext()){
            ICommand command = (ICommand) iter.next();
            this.COMMAND_MAP.put(command.getNameOfCommand(), command);
        }
    }

    public void processInput(InputStream str){
        Scanner scan = new Scanner(str);
        try {
            while (scan.hasNextLine()){
                String line = scan.nextLine().trim();
                this.processLine(line);
            }
        }finally {
            scan.close();
        }
    }

    private void processLine(String line) {
        Scanner lineScan = new Scanner(line);
        try {
            String command = lineScan.next();
            String args = lineScan.nextLine().trim();
            ICommand com = this.COMMAND_MAP.get(command);
            String result = null;
            if (com == null) {
                System.out.println("There is NO command with that name");
                return;
            }else {
                result = com.executeCommand(args);
            }
            System.out.println(result);
        } catch (NoSuchElementException exc) {
            System.out.println("Cannot parse command");
        } finally {
            lineScan.close();
        }
    }
}
