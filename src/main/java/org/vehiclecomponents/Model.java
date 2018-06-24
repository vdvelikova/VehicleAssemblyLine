package main.java.org.vehiclecomponents;

import java.io.Serializable;
import java.util.List;

public class Model implements Serializable{

    private List modelComponents;
    private String vehicleType;

    public Model(String vehicleType, List modelComponents){
        this.vehicleType = vehicleType;
        this.modelComponents = modelComponents;
    }


    public String getModel(){
        String model = (String) this.modelComponents.get(0);
        return model; // Q1-8;A1-8
    }

    /**
     * Return the model type entered (sedan, kombi or hatchback).
     * @return If nothing is entered for CAR - default is "sedan".
     *         For vehicleType = suv -> return null
     */
    public String getModelType(){
        String modelType = (String) this.modelComponents.get(1);
        return modelType;
    }
}
