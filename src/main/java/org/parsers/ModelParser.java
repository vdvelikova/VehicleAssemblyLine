package main.java.org.parsers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModelParser {

      public ModelParser(){}


    /**
     * The method's aim is to take the user's input concerning  the model of the vehicle and
     * using regular expression to validates the input, according to the following rules:
     * - the input should begin with "model";
     * - if the vehicle type is CAR - allowed models are A1 to A8; additional information for model type (sedan, kombi,hatchback) is optional;
     * - if the vehicle type is SUV - allowed models are Q1 to Q8; additional information for model type (sedan, kombi,hatchback) is NOT allowed;
     * If the type of the vehicle is car AND the input from the user doesn't specify the type (sedan, kombi,hatchback) - default SEDAN is set.
     * @param vehicleType
     * @param modelInput
     * @return  a list of model components which are used in the construction of Model objects.
     */
    public List validateModelInput(String vehicleType, String modelInput){
        String model;
        String modelType; // there should not be modelType for SUV vehicles
        List modelConstructorParameters = new ArrayList();
        String regex = "^model=((?:A|Q)[1-8])(?:-(hatchback|sedan|kombi))?$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(modelInput);
        try {
            if(matcher.find()){
                model = matcher.group(1);
                if((vehicleType.equals("car")) && ((model.equals("Q1") | model.equals("Q2") | (model.equals("Q3")) | (model.equals("Q4")) | (model.equals("Q5")) | (model.equals("Q6")) | (model.equals("Q7")) | (model.equals("Q8"))))){
                    throw new IllegalArgumentException("Wrong CAR vehicle model entered. Possible input:A1-A8");
                }else if((vehicleType.equals("suv")) && ((model.equals("A1") | model.equals("A2") | (model.equals("A3")) | (model.equals("A4")) | (model.equals("A5")) | (model.equals("A6")) | (model.equals("A7")) | (model.equals("A8"))))){
                    throw new IllegalArgumentException("Wrong SUV model entered. Possible input:Q1-Q8");
                }
                modelType = matcher.group(2);
                if(vehicleType.equals("suv") && modelType!= null){
                    throw new IllegalArgumentException("The SUV vehicles do not take information for type(example: sedan, hatchback, kombi)");
                }
                if(vehicleType.equals("car") && modelType == null){
                    modelType = "sedan";
                }
                modelConstructorParameters.add(model); // A1-A8; Q1-Q8
                modelConstructorParameters.add(modelType); // hatchback, sedan, kombi
            }else {
                throw new IllegalArgumentException("The MODEL information input is not correct!");
            }
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return null;
        }
        return modelConstructorParameters;
    }

}
