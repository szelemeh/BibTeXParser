package main;

import org.junit.Test;

import java.util.ArrayList;

public class ExecutorTest {
    Executor ex = Executor.getExecutor();

    @Test
    public void execute_help() {
        ArrayList<Command> commands = new ArrayList<>();
        commands.add(new Command(CommandType.HELP, null));
        ex.execute(commands);
    }
    @Test
    public void execute_file() {
        ArrayList<Command> commands = new ArrayList<>();
        ArrayList<String> path = new ArrayList<>();
        path.add("tests/resourses/main_example.bib");
        commands.add(new Command(CommandType.FILE, path));
        ex.execute(commands);
    }
    @Test
    public void execute_author() {
        ArrayList<Command> commands = new ArrayList<>();

        ArrayList<String> path = new ArrayList<>();
        path.add("tests/resourses/main_example.bib");

        commands.add(new Command(CommandType.FILE, path));


        ArrayList<String> lastNames = new ArrayList<>();

        lastNames.add("Knuth");
        lastNames.add("Aamport");

        commands.add(new Command(CommandType.AUTHOR, lastNames));


        ex.execute(commands);
    }
    @Test
    public void execute_category() {
        ArrayList<Command> commands = new ArrayList<>();

        ArrayList<String> path = new ArrayList<>();
        path.add("tests/resourses/main_example.bib");

        commands.add(new Command(CommandType.FILE, path));

        ArrayList<String> categories = new ArrayList<>();

        categories.add("inbook");
        categories.add("article");

        commands.add(new Command(CommandType.CATEGORY, categories));

        ex.execute(commands);
    }
    @Test
    public void execute_all() {
        ArrayList<Command> commands = new ArrayList<>();

        ArrayList<String> path = new ArrayList<>();
        path.add("tests/resourses/main_example.bib");

        commands.add(new Command(CommandType.FILE, path));

        commands.add(new Command(CommandType.ALL, null));

        ex.execute(commands);
    }




}