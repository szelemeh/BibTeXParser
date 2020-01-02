package main;

import parsers.OptionsParser;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        OptionsParser parser = new OptionsParser(args);

        ArrayList<Option> options = parser.getOptions();

        Executor ex = new Executor();

        ex.execute(options);
    }
}
