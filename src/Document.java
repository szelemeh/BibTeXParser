import model.Entry;
import model.EntryType;
import model.FieldType;
import parsers.Parser;

import java.util.ArrayList;
import java.util.EnumMap;

public class Document {
    private EnumMap<EntryType, ArrayList<Entry>> entries;
    private final String filePath;
    private Parser parser;
    private Printer printer;

    public Document(String filePath) {
        this.filePath = filePath;
        parser = new Parser(filePath);
        printer = new Printer();
        entries = new EnumMap<EntryType, ArrayList<Entry>>(EntryType.class);
    }

    public void put(Entry entry) {
        if(!entries.containsKey(entry.type)) {
            entries.put(entry.type, new ArrayList<>());
        }
        entries.get(entry.type).add(entry);
    }

    public void printEntriesByField(FieldType fieldType, String value) {
        // TODO: 02-Dec-19 write function
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

}


