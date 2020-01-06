package main;

import model.EntryType;
import model.FieldType;

import java.util.ArrayList;

/**
 * Executor is singleton class that performs the tasks of the program.
 */
public class Executor {
    private Document doc;

    static private Executor executor = null;
    static public Executor getExecutor() {
        if(executor == null) executor = new Executor();
        return executor;
    }
    private Executor() {}

    /**
     * Method that go through each command and performs
     * the task given in it.
     * @param commands is a list of things the program needs to do.
     */
    public void execute(ArrayList<Command> commands) {
        for (Command command : commands) {
            switch(command.getType()) {
                case HELP:
                    printHelp();
                    break;
                case FILE:
                    putFileToDoc(command.getArgs().get(0));
                    break;
                case AUTHOR:
                    printEntriesByAuthorNames(command.getArgs());
                    break;
                case CATEGORY:
                    printEntriesByEntryTypes(command.getArgs());
                    break;
                case ALL:
                    printAllEntries();
            }
        }
    }

    /**
     * Prints entries by last names of authors.
     * @param lastNames
     */
    private void printEntriesByAuthorNames(ArrayList<String> lastNames) {
        if(doc == null) throw new NullPointerException("Document is null!");

        for (String lastName: lastNames) {
            doc.printEntriesByField(FieldType.AUTHOR, lastName);
        }


    }

    /**
     * Prints entries by entry types (entry categories).
     * @param types
     */
    private void printEntriesByEntryTypes(ArrayList<String> types) {
        for (String t: types) {
            EntryType type = null;

            try {

                type = EntryType.valueOf(t.toUpperCase());

            } catch (IllegalArgumentException e) {

                User.getUser().printMessage(
                        User.MessageType.DANGER, "Category name " +
                        t + " does not exist!"
                );

            }

            if (type == null) continue;

            if(doc == null) throw new NullPointerException("Document is null!");
            else {
                doc.printEntriesByEntryType(type);
            }


        }
    }

    /**
     * Prints all entries.
     */
    private void printAllEntries() {
        if(doc == null) throw new NullPointerException("Document is null!");
        else {
            doc.printAll();
        }
    }

    /**
     * Creates document which will read all the entries from file
     * path to which is given in filePath parameter
     * @param filePath is a path to .bib file.
     */
    private void putFileToDoc(String filePath) {
        doc = new Document(filePath);
    }

    /**
     * Prints help of how to use the program.
     */
    private void printHelp() {
        User.getUser().printHelp();
    }
}
