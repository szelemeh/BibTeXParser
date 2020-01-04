package main;

import parsers.CommandParser;

import java.util.ArrayList;

/**
 * Main class that receives program arguments.
 */
public class Main {

    /**
     * Receives commands from an user and acts on them.
     * @param args are arguments to the program.
     */
    public static void main(String[] args) {
        CommandParser parser = new CommandParser(args);

        ArrayList<Command> commands = parser.getOptions();

        Executor ex = Executor.getExecutor();

        ex.execute(commands);
    }
}
