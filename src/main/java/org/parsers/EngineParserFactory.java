package main.java.org.parsers;

public class EngineParserFactory {

    /**
     * The method is used for dynamically creating EngineParser objects
     * given the vehicle's engine type.
     * @param engineType
     * @return object of type EngineParser
     */
    public EngineParser getAppropriateEngineParser(String engineType){
        if(engineType.equals("B")){
            return new BenzineEngineParser();
        }else if(engineType.equals("D")){
             return new DieselEngineParser();
        }else if(engineType.equals("E")){
            return new ElectricEngineParser();
        }
        return null;
    }
}
