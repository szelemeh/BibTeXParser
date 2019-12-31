package main;

import model.Entry;
import model.EntryType;
import model.FieldType;
import parsers.Parser;

import java.util.ArrayList;
import java.util.EnumMap;

public class Document {
    private EnumMap<EntryType, ArrayList<Entry>> entries = new EnumMap<EntryType, ArrayList<Entry>>(EntryType.class);
    private String filePath;
    private Parser parser;
    private Printer printer = new Printer();
    private User user = User.getUser();

    public Document() {
    }

    public Document(String filePath) {
        this.filePath = filePath;
        parser = new Parser(filePath);
        fillEntries();
    }

    private void fillEntries() {
        // TODO: 31-Dec-19 check if filePath is valid
        ArrayList<Entry> allEntries = parser.getEntries();

        for (Entry entry: allEntries) {
            put(entry);
        }
    }

    private void checkEntry(Entry entry) {
        ArrayList<FieldType> missingFields = entry.getMissingFieldTypes(getCrossReferencedEntry(entry.getCrossRef()));
        if(missingFields != null) {
            user.sendMessage(entry.type + ":" + entry.getKey() + " is missing these required fields: " + missingFields.toString());
        }
    }

    public void put(Entry entry) {

        if(!entries.containsKey(entry.type)) {
            entries.put(entry.type, new ArrayList<>());
        }
        entries.get(entry.type).add(entry);

    }

    public void printEntriesByField(FieldType fieldType, String value) {
        if(fieldType == FieldType.AUTHOR || fieldType == FieldType.EDITOR) printEntriesByLastName(value);

        //here to implement function for other fields
    }

    private void printEntriesByLastName(String searchLastName) {
        ArrayList<ArrayList<Entry>> allArrayLists = new ArrayList<>(this.entries.values());
        ArrayList<Entry> toPrint = new ArrayList<>();
        for (ArrayList<Entry> array: allArrayLists) {
            for (Entry entry: array) {
                String fieldValue = entry.getNameField();
                if(fieldValue != null) {
                    ArrayList<String> lastNames = parser.getLastNames(fieldValue);
                    for(String lastName: lastNames) {
                        if(lastName.contains(searchLastName))toPrint.add(entry);
                    }
                }
            }
        }
        if(toPrint.isEmpty())user.sendMessage("There are no entries with author or editor last name " + searchLastName);
        else printer.printAll(toPrint);
    }

    public void printEntriesByEntryType(EntryType entryType) {
        for(Entry entry: entries.get(entryType)){
            printer.printEntry(entry);
        }
    }

    public void printAll() {
        for(ArrayList<Entry> list: entries.values()) {
            for(Entry entry: list){
                printer.printEntry(entry);
            }
        }
    }

    public Entry getCrossReferencedEntry(String crossRef) {
        if (crossRef == null) return null;

        ArrayList<ArrayList<Entry>> allArrayLists = new ArrayList<>(this.entries.values());

        for (ArrayList<Entry> array: allArrayLists) {
            for (Entry entry: array) {
                if(crossRef.equals(entry.getKey()))return entry;
            }
        }
        return null;
    }


    public void checkAllEntries() {
        ArrayList<ArrayList<Entry>> allArrayLists = new ArrayList<>(this.entries.values());

        for (ArrayList<Entry> array: allArrayLists) {
            for (Entry entry: array) {
                checkEntry(entry);
            }
        }
    }



}


