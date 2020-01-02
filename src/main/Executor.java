package main;

import model.EntryType;
import model.FieldType;

import java.util.ArrayList;

public class Executor {
    Document doc;

    public void execute(ArrayList<Option> options) {
        for (Option option: options) {
            switch(option.getType()) {
                case HELP:
                    printHelp();
                    break;
                case FILE:
                    putFileToDoc(option.getArgs().get(0));
                    break;
                case AUTHOR:
                    printEntriesByAuthorNames(option.getArgs());
                    break;
                case CATEGORY:
                    printEntriesByEntryTypes(option.getArgs());
                    break;
                case ALL:
                    printAllEntries();
            }
        }
    }

    private void printEntriesByAuthorNames(ArrayList<String> lastNames) {
        if(doc == null) throw new NullPointerException("Document is null!");

        for (String lastName: lastNames) {
            doc.printEntriesByField(FieldType.AUTHOR, lastName);
        }


    }

    private void printEntriesByEntryTypes(ArrayList<String> types) {
        for (String t: types) {
            EntryType type = null;

            try {

                type = EntryType.valueOf(t.toUpperCase());

            } catch (IllegalArgumentException e) {

                User.getUser().sendMessage(
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

    private void printAllEntries() {
        if(doc == null) throw new NullPointerException("Document is null!");
        else {
            doc.printAll();
        }
    }

    private void putFileToDoc(String filePath) {
        doc = new Document(filePath);
    }

    private void printHelp() {
        User.getUser().printHelp();
    }
}
