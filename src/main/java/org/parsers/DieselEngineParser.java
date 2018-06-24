package main.java.org.parsers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DieselEngineParser implements EngineParser {

    private final List<Double> possibleCC = new ArrayList<>(Arrays.asList(2000.0, 3000.0, 4000.0, 5000.0, 6000.0));
    private final List<Double> possibleKwWithoutTurbo = new ArrayList<>(Arrays.asList(147.0, 245.0, 253.0, 331.0, 510.0));
    private final List<Double> possibleKwWithTurbo = new ArrayList<>(Arrays.asList(191.1,318.5,328.0,430.3,663.0));

    /**
     * Using regular expression the method first checks if the input for engine is following the general
     * syntax rules - first, engine type is mandatory and must be always "B";
     *                second, information for engine's power/displacement and emission standard is optional, but if one is given it obeys certain limitations.
     * If no information is provided for power/displacement and emission standard - default values are set (respectively - 4L and euro3).
     * The method uses another method for further input validation - checkPowerEntered(String engineDisplacementOrPower).
     * @param engineInput the string input that is given as characteristics for engine
     * @return a List of components (engineType, engineDisplacementOrPower, emissionStandard) that will be used for creating Engine objects.
     *          If the validation fails - return "null".
     */
    @Override
    public List validateEngineInput(String engineInput) {
        String engineType;
        String engineDisplacementOrPower;
        String emissionStandard;
        List engineConstructorParameters = new ArrayList();
        String regex = "^engine=(?:(D)(?:-(\\d+(?:hp|L)(?:T?))-(euro[3-6]))?)$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(engineInput);
        try {
            if (matcher.find()) {
                engineType = matcher.group(1); // content: always D
                engineDisplacementOrPower = matcher.group(2); // content example: 6L, 5LT, 100hp
                if (engineDisplacementOrPower != null) {
                    List powerInfo = checkPowerEntered(engineDisplacementOrPower);
                }
                if (engineDisplacementOrPower == null) {
                    engineDisplacementOrPower = "4L"; // setting default value of 4L
                }
                emissionStandard = matcher.group(3); // content example: euro3, euro6
                if (emissionStandard == null) {
                    emissionStandard = "euro3"; //set default value for emissionStandard = euro-3, if no information is provided
                }
                engineConstructorParameters.add(engineType);
                engineConstructorParameters.add(engineDisplacementOrPower);
                engineConstructorParameters.add(emissionStandard);
            } else {
                throw new IllegalArgumentException("The syntax for engine is not correct. \nCorrect examples: engine=D OR engine=D-2L-euro3");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return engineConstructorParameters;
    }

    /**
     * The method uses the information that is provided for engine's displacement/ power.
     * Via regular expression checks validates the input syntax.
     * Regular expression is also used to split the input into two parts - number and string input.
     * The method calls checkValidityOfPowerEntered(String displacementOrPower, String number),
     * which does the final validation of the input for engine.
     * The main purpose of the method is to propagate any exception, that might occur, to validateEngineInput().
     * @param engineDisplacementOrPower
     * @return a List containing information about the engine power/displacement.
     */
    private List checkPowerEntered(String engineDisplacementOrPower) {
        String number;
        String displacementOrPower;
        List enginePowerInfo = new ArrayList(); // consists of number and type of engine power (hp/L{T})
        String regex = "^(\\d+)(hp(?:T?)|L(?:T?))$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(engineDisplacementOrPower);
        if (matcher.find()) {
             number = matcher.group(1);
             displacementOrPower = matcher.group(2); //hp; L or LT
             checkValidityOfPowerEntered(displacementOrPower, number);
             enginePowerInfo.add(number);
             enginePowerInfo.add(displacementOrPower);

        }else{
           throw new IllegalArgumentException("Not valid input for engine power");
        }
        return enginePowerInfo;
    }

    /**
     * Converts the input in cc/kW and checks if the input is correct,
     * according to previously defined possible values.
     * @param displacementOrPower
     * @param number
     * @return true/false
     */
    private boolean checkValidityOfPowerEntered(String displacementOrPower, String number) {
        double num = Integer.parseInt(number);

        if (displacementOrPower.equals("hp")) {
            double kW = num * 0.7457;
            boolean fallsWithinKwRangeFlag = false;
            for (double kWpossible : possibleKwWithoutTurbo) {
                if ((kW <= (kWpossible + 1.0)) && (kW >= (kWpossible - 1.0))) {
                    fallsWithinKwRangeFlag = true;
                    break;
                }
            }
            if (fallsWithinKwRangeFlag){
                return true;
            }else {
                throw new IllegalArgumentException("Not possible input for hp and diesel");
            }
        } else if(displacementOrPower.equals("L") || displacementOrPower.equals("LT")) {
            double cc = num * 1000.0;
            boolean fallsWithinCCRangeFlag = false;
            for (double ccPossible : possibleCC) {
                if ((cc <= (ccPossible+ 1.0)) && (cc >= (ccPossible - 1.0))) {
                    fallsWithinCCRangeFlag = true;
                    break;
                }
            }
            if (fallsWithinCCRangeFlag) {
                return true;
            }else{
                throw new IllegalArgumentException("Not possible input for L and diesel");
            }
        } else if(displacementOrPower.equals("hpT")){
            double kW = num * 0.7457 * 1.3;
            System.out.println(kW + "kW");

            boolean fallsWithinKwRangeFlag = false;
            for (double kWpossible : possibleKwWithTurbo) {
                if ((kW <= (kWpossible + 1.0)) && (kW >= (kWpossible - 1.0))) {
                    fallsWithinKwRangeFlag = true;
                    break;
                }
            }
            if (fallsWithinKwRangeFlag){
                return true;
            }else {
                throw new IllegalArgumentException("Not possible input for hpT and diesel");
            }

        }
        return false;
    }
}

