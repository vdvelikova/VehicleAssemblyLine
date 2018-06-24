package main.java.org.commands;

public interface ICommand {

    String getNameOfCommand();
    String executeCommand(String input);

}
