package main.java.org.vehiclecomponents;

import java.io.Serializable;
import java.util.List;

public class Engine implements Serializable{

    private List engineComponents;

    public Engine(List engineComponents){
        this.engineComponents = engineComponents;
    }

    /**
     * use the method for Find command
     * @return emission standard type
     */
    public String getEmissionStandard(){
        String emissionStandard = (String) this.engineComponents.get(engineComponents.size() - 1);
        return emissionStandard;
    }

    public String getEngineType(){
        String engineType = (String) this.engineComponents.get(0);
        return engineType;
    }

    public String getDisplacementPower(){
        String displacementOrPower = (String) this.engineComponents.get(1);
        return displacementOrPower;
    }

    public String engineInformationForOutput(){
        String listContentConcatenate = String.join("-", this.engineComponents);
        return listContentConcatenate;
    }

    /**
     * The method will be used in Update command to verify if a
     * vehicle could be mounted a Turbo.
     * @return false true
     */

    public void changeEmissionStandard(String newStandard){
        this.engineComponents.set(2, newStandard);
    }

    public void addTurbo(){
        String withoutTurbo = (String) this.engineComponents.get(1);
        String withTurbo = withoutTurbo + "T";
        this.engineComponents.set(1,withTurbo);
    }
}
