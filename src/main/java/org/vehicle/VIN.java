package main.java.org.vehicle;


import main.java.org.mainapp.VehicleContainer;

import java.io.Serializable;
import java.util.Map;
import java.util.Random;

public class VIN implements Serializable{
    private VehicleContainer vehicleContainer;
    final String generatedVIN;

    public VIN(String countryISO, String factoryIdentification, String possibleSymbols, VehicleContainer vcontainer){
        this.vehicleContainer = vcontainer;

        /** Check uniqueness of VIN. */
        String genVIN;
        while (true) {
           // System.out.println("in while");
            genVIN = countryISO + factoryIdentification + generateRandomSymbols(possibleSymbols, 14);
            boolean uniqueFlag = true;
            /**
             * Loop through vehicleContainer's assembledVehicles map
             * and check if such a VIN already exists.
             * If so - generate new genVin and check again for uniqueness.
             */
            Map<String, Vehicle> assembledVehicles = vehicleContainer.getAssembledVehicles();
            if(!(assembledVehicles.isEmpty())){
                for(String key : assembledVehicles.keySet()){
                  //  System.out.println("In for loop");
                    if(key.equals(genVIN)){
                        uniqueFlag = false;
                        break;
                    }
                }
            }
            if (uniqueFlag){
                break;
            }
        }
        this.generatedVIN = genVIN;
    }


    private static String generateRandomSymbols(String posibleSymbols, int numberOfSymbols){
        StringBuilder strBuilder = new StringBuilder();
        Random random = new Random();
        for(int k = 0; k < numberOfSymbols; k ++){
            strBuilder.append(posibleSymbols.charAt(random.nextInt(posibleSymbols.length())));
        }
        return strBuilder.toString();
    }

    @Override
    public String toString() {
        return generatedVIN;
    }


}


