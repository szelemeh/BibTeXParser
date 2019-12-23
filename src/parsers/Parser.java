package parsers;

import model.Entry;
import model.Name;

import java.util.ArrayList;

public class Parser {
    private String filePath;
    private NameParser nameParser = new NameParser();

    public Parser(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<String> getLastNames(String fieldValue) {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<Name> names = (ArrayList<Name>) nameParser.getNames(fieldValue);

        for (Name name : names) {
            result.add(name.getLastName());
        }
        return result;
    }

    public ArrayList<Entry> getEntries() {

        return null;
    }


}
