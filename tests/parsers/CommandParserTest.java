package parsers;

import main.Command;
import main.CommandType;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CommandParserTest {
    private final String pathToFile = "C:\\Users\\stass\\IdeaProjects\\BibTexParser\\tests\\resourses\\ex.bib";
    private CommandParser parser;

    @Test
    public void getOptions() {
        ArrayList<String> args;

        args = new ArrayList<>();
        args.add(pathToFile);
        Command opt1 = new Command(CommandType.FILE, args);

        args = new ArrayList<>();
        args.add("inbook");
        Command opt2 = new Command(CommandType.CATEGORY, args);

        args = new ArrayList<>();
        args.add("Knuth");
        Command opt3 = new Command(CommandType.AUTHOR, args);

        String[] programArgs = new String[6];
        programArgs[0] = "--category";
        programArgs[1] = "inbook";
        programArgs[2] = "--file";
        programArgs[3] = pathToFile;
        programArgs[4] = "--author";
        programArgs[5] = "Knuth";

        parser = new CommandParser(programArgs);
        ArrayList<Command> parsedCommands = parser.getOptions();

        assertEquals(opt1, parsedCommands.get(0));
        assertEquals(opt2, parsedCommands.get(1));
        assertEquals(opt3, parsedCommands.get(2));
    }
}