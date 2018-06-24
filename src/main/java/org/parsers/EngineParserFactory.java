package main.java.org.parsers;

public class EngineParserFactory {

    /**
     * The method is used for dynamically creating EngineParser objects
     * given the vehicle's engine type.
     * @param engineType
     * @return object of type EngineParser
     */
    /*public EngineParser getAppropriateEngineParser(String engineType){
        if(engineType.equals("B")){
            return new BenzineEngineParser();
        }else if(engineType.equals("D")){
             return new DieselEngineParser();
        }else if(engineType.equals("E")){
            return new ElectricEngineParser();
        }
        else{
            throw new IllegalArgumentException("There is some problem with your input for engine. Please check and try again.");
        }
        //return null;
    }*/

    public EngineParser getAppropriateEngineParser(String engineType){
        try {
            if(engineType.equals("B")){
                return new BenzineEngineParser();
            }else if(engineType.equals("D")){
                return new DieselEngineParser();
            }else if(engineType.equals("E")){
                return new ElectricEngineParser();
            }
            else{
                throw new IllegalArgumentException("There is some problem with the type of engine you entered. Possible engine types are E, D, B.\nPlease check and try again.");
            }
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return null;
        }
        //return null;
    }
}
