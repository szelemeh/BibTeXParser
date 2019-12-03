import entries.Entry;
import entries.EntryType;
import entries.FieldType;
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
    }

    public ArrayList<Entry> printEntriesByField(FieldType fieldType, String value) {
        // TODO: 02-Dec-19 write function 
        return null;
    }

    public ArrayList<Entry> printEntriesByEntryType(EntryType entryType) {
        // TODO: 02-Dec-19 write function
        return null;
    }

}


