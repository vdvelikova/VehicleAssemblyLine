package main.java.org.vehiclecomponents;

import java.io.Serializable;

public class Transmission implements Serializable{
    private String transmissionDescription;

    public Transmission(String description){
        this.transmissionDescription =description;
    }

    public String getTransmissionDescription() {
        return this.transmissionDescription;
    }

    public void changeTransmission(String newTransmission){
        this.transmissionDescription = newTransmission;
    }
}
