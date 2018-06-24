package main.java.org.helperclass;


import main.java.org.mainapp.VehicleContainer;
import main.java.org.vehicle.Vehicle;

import java.io.*;
import java.util.Iterator;
import java.util.Map;

public class MyReaderWriter implements Serializable{

    public MyReaderWriter(){}


    /**
     * Each time a vehicle object is created it must be saved to a persistent storage.
     * The method takes care of that, creating and using file "vehicles.ser" in which
     * vehicle objects are stored right after being created.
     * @param vehicle an object of type Vehicle
     */
    public void saveObjectToFile(Vehicle vehicle){
        FileOutputStream fos;
        try {
            boolean exists = new File("vehicles.ser").exists();
            fos = new FileOutputStream("vehicles.ser", true);

            ObjectOutputStream oos = exists ? new ObjectOutputStream(fos){
                @Override
                protected void writeStreamHeader() throws IOException {
                    reset();
                }
            }:new ObjectOutputStream(fos);
            oos.writeObject(vehicle);
            oos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * The method is used when starting the program. Its purpose is to fill the map with assembled vehicles
     * with the objects that were saved during the previous execution of the program.
     * That way information won't be lost every time the program is terminated and then started again.
     * The method uses "vehicles.ser" which is the persistent storage to fill the map with data.
     * @param vehicleContainer
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void readFromFile(VehicleContainer vehicleContainer) throws IOException, ClassNotFoundException {
        FileInputStream fis;

        ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("vehicles.ser")));
        while (true){
            try {
                Vehicle vehicle = (Vehicle) ois.readObject();
                String keyForMap = vehicle.getVIN();
                vehicleContainer.saveToAssembledVehicles(keyForMap,vehicle);

            }catch (EOFException e){
                System.out.println("Assembled vehicles are loaded in memory.");
                break;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                break;
            }
        }
        ois.close();
    }


    /**
     * The method is used in Disassemble command. When a vehicle is deleted the method is called.
     * Its purpose is to:
     * First, delete the already existing "vehicles.ser" file.
     * Second, saving the changed map's (assembledVehicles) content to a new "vehicles.ser" file,
     * using saveObjectToFile().
     * @param vehicleContainer
     */
    public void writeMapToFile(VehicleContainer vehicleContainer){
        deleteFile();
        Map<String, Vehicle> assembledVehicles = vehicleContainer.getAssembledVehicles();
        Iterator entries = assembledVehicles.entrySet().iterator();
        while (entries.hasNext()){
            Map.Entry entry = (Map.Entry) entries.next();
            Vehicle vehicle = (Vehicle) entry.getValue();
            saveObjectToFile(vehicle);
        }
    }

    public void deleteFile(){
        boolean result = new File("vehicles.ser").delete();
    }


}
