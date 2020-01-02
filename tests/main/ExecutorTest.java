package main;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ExecutorTest {
    Executor ex = new Executor();

    @Test
    public void execute_help() {
        ArrayList<Option> options = new ArrayList<>();
        options.add(new Option(OptionType.HELP, null));
        ex.execute(options);
    }
    @Test
    public void execute_file() {
        ArrayList<Option> options = new ArrayList<>();
        ArrayList<String> path = new ArrayList<>();
        path.add("tests/resourses/main_example.bib");
        options.add(new Option(OptionType.FILE, path));
        ex.execute(options);
    }
    @Test
    public void execute_author() {
        ArrayList<Option> options = new ArrayList<>();

        ArrayList<String> path = new ArrayList<>();
        path.add("tests/resourses/main_example.bib");

        options.add(new Option(OptionType.FILE, path));


        ArrayList<String> lastNames = new ArrayList<>();

        lastNames.add("Knuth");
        lastNames.add("Aamport");

        options.add(new Option(OptionType.AUTHOR, lastNames));


        ex.execute(options);
    }
    @Test
    public void execute_category() {
        ArrayList<Option> options = new ArrayList<>();

        ArrayList<String> path = new ArrayList<>();
        path.add("tests/resourses/main_example.bib");

        options.add(new Option(OptionType.FILE, path));

        ArrayList<String> categories = new ArrayList<>();

        categories.add("inbook");
        categories.add("article");

        options.add(new Option(OptionType.CATEGORY, categories));

        ex.execute(options);
    }
    @Test
    public void execute_all() {
        ArrayList<Option> options = new ArrayList<>();

        ArrayList<String> path = new ArrayList<>();
        path.add("tests/resourses/main_example.bib");

        options.add(new Option(OptionType.FILE, path));

        options.add(new Option(OptionType.ALL, null));

        ex.execute(options);
    }




}