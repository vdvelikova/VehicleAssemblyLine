package main.java.org.parsers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ElectricEngineParser implements EngineParser {
    /**
     * The method's purpose is to validate the information given about the characteristics of an Electric engine.
     * According to the specification of the electric engine,
     * the engine can accept only one value for hp - 718.
     * The emission standard for electric engines is always euro6 (the highest one).
     * @param engineInput
     * @return a List of components (engineType, engineDisplacementOrPower, emissionStandard) that will be used for creating Engine objects.
     *          If the validation fails - return "null".
     */
    @Override
    public List validateEngineInput(String engineInput) {
        String engineType;
        String engineDisplacementOrPower;
        String emissionStandard;
        List engineConstructorParameters = new ArrayList();
        String regex = "^engine=(E)(?:-718hp-euro6)?$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(engineInput);
        try {
            if(matcher.find()) {
                engineType = matcher.group(1); // content: always E
                engineDisplacementOrPower = "718hp";
                emissionStandard = "euro6";

                engineConstructorParameters.add(engineType);
                engineConstructorParameters.add(engineDisplacementOrPower);
                engineConstructorParameters.add(emissionStandard);
            }else {
                throw new IllegalArgumentException("The ENGINE information input is not correct!");
            }
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return null;
        }
        return engineConstructorParameters;
    }
}
