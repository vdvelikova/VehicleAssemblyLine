package main.java.org.parsers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TransmissionParser {

    public TransmissionParser(){}


    /**
     * The method sets default value (Manual-4) to transmission if no input is specified
     * AND if the vehicle's engine is not Electric.
     * If the engine is Electric the method will return "none" as a value for the transmission.
     * If there is an input - checks if the input is correct, using regular expression.
     * @param transmissionInput
     * @param engineType
     * @return a String holding information about the type of transmission. The value will be used in Transmission constructor.
     */
    public String validateTransmissionInput (String transmissionInput, String engineType){
        try {
            String transmissionComponents;

            if(engineType.equals("E")) {
                if(!(transmissionInput.equals(null)) && !(transmissionInput.equals("none"))){
                    throw new IllegalArgumentException("Electric vehicles have NO transmission.");
                } else {
                    return transmissionInput;
                }
            }
            if(transmissionInput.equals("none") && (!engineType.equals("E"))){
                return "Manual-4";
            }

            String regex = "^transmission=(Auto-[4|5|6|8]|Manual-[4|5|6])$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(transmissionInput);
            if(matcher.find()){
                transmissionComponents = matcher.group(1);
            }else {
                throw new IllegalArgumentException("The syntax for Transmission is not valid.");
            }
            return transmissionComponents;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }
}
