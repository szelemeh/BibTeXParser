package parsers;

import main.Option;
import main.OptionType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class OptionsParserTest {
    private final String pathToFile = "C:\\Users\\stass\\IdeaProjects\\BibTexParser\\tests\\resourses\\ex.bib";
    private OptionsParser parser;

    @Test
    public void getOptions() {
        ArrayList<String> args;

        args = new ArrayList<>();
        args.add(pathToFile);
        Option opt1 = new Option(OptionType.FILE, args);

        args = new ArrayList<>();
        args.add("inbook");
        Option opt2 = new Option(OptionType.CATEGORY, args);

        args = new ArrayList<>();
        args.add("Knuth");
        Option opt3 = new Option(OptionType.AUTHOR, args);

        String[] programArgs = new String[6];
        programArgs[0] = "--category";
        programArgs[1] = "inbook";
        programArgs[2] = "--file";
        programArgs[3] = pathToFile;
        programArgs[4] = "--author";
        programArgs[5] = "Knuth";

        parser = new OptionsParser(programArgs);
        ArrayList<Option> parsedOptions = parser.getOptions();

        assertEquals(opt1, parsedOptions.get(0));
        assertEquals(opt2, parsedOptions.get(1));
        assertEquals(opt3, parsedOptions.get(2));
    }
}