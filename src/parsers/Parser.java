package parsers;

import model.Entry;
import model.Name;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Parser is a wrapper class for all parsers in <code>parsers</code> package.
 * It allow to use functionality of these parsers with just one instance of
 * this class.
 */
public class Parser {
    private String filePath;
    private NameParser nameParser;
    private Lexer lexer;
    private FileToString reader;

    /**
     * Constructs an instance of <code>Parser</code>
     * and initializes all parsers.
     * @param filePath is a path to .bib file.
     */
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

    /**
     * Gets an ArrayList of last names present in <code>fieldValue</code>.
     * @param fieldValue is a value of author or editor fields.
     * @return list of last names.
     */
    public ArrayList<String> getLastNames(String fieldValue) {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<Name> names = (ArrayList<Name>) nameParser.getNames(fieldValue);

        for (Name name : names) {
            result.add(name.getLastName());
        }
        return result;
    }

    /**
     * Gets entries that were parsed by Lexer from
     * filePath.
     * @see Lexer
     * @return an ArrayList of entries.
     */
    public ArrayList<Entry> getEntries() {
        return lexer.getEntries();
    }
}
