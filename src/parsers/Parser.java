package parsers;

import model.Entry;
import model.Name;

import java.io.IOException;
import java.util.ArrayList;

public class Parser {
    private String filePath;
    private NameParser nameParser;
    private Lexer lexer;
    private FileToString reader;

    public Parser(String filePath) {
        this.filePath = filePath;
        nameParser = new NameParser();
        reader = new FileToString(filePath);

        String content = null;

        try {
            content = reader.getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }

        lexer = new Lexer(content);
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
        return lexer.getEntries();
    }
}
