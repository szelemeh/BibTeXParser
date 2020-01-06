package parsers;

import exceptions.ParsingException;
import exceptions.StringNotDefinedException;
import main.EntryFactory;
import main.User;
import model.Entry;
import model.EntryType;
import model.FieldType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Lexer is one of the parsing classes.
 * Its job is to build entries from the content of <i>.bib</i> file.
 * Also this class substitutes all the string variables with
 * their values in these entries
 */
public class Lexer {
    private String fileContent;
    private HashMap <String, String> strings;
    private ArrayList<Entry> entries;

    /**
     * Gets entries.
     * @return list of entries built from fileContent
     */
    public ArrayList<Entry> getEntries() {
        return entries;
    }

    /**
     * Constructor that takes one argument
     * @param fileContent is content of the file to be parsed
     */
    public Lexer(String fileContent) {
        this.fileContent = fileContent;
        strings = new HashMap<>();
        entries = new ArrayList<>();
        buildEntries();
    }

    /**
     * Method that is called on creation of instance of this class.
     * It parses fileContent to fill entries.
     */
    public void buildEntries() {
        if (fileContent == null)return;

        int indexOfStartOfFirstEntry = fileContent.indexOf("@");
        if (indexOfStartOfFirstEntry == -1) {
            User.getUser().printMessage(
                    "The file you specified has no entries!"
            );
            System.exit(1);
        }
        fileContent = fileContent.substring(indexOfStartOfFirstEntry);
        StringTokenizer mainTokenizer = new StringTokenizer(fileContent, "@");
        while (mainTokenizer.hasMoreTokens()) {
            StringBuilder entryTypeNameBuilder = new StringBuilder();
            String token = mainTokenizer.nextToken();
            int i = 0;
            while(token.charAt(i) != '{') {
                entryTypeNameBuilder.append(token.charAt(i));
                i++;
            }
            String entryTypeName = entryTypeNameBuilder
                    .toString()
                    .replaceAll("[\n ]", "")
                    .toUpperCase();

            EntryType entryType = null;
             try {
                 entryType = EntryType.valueOf(entryTypeName);
             }
             catch (IllegalArgumentException ex) {
                 ex.printStackTrace();
             }
             if(entryType != null) {
                switch (entryType) {
                    case PREAMBLE:
                    case COMMENT:
                        break;
                    case STRING:
                        buildString(token);
                        break;
                    default:
                        buildEntry(entryType, token);
                        break;
                }
             }
        }
    }

    /**
     * Method that builds a BibTeX string and puts it in strings hash map.
     * @param rawString is text with such format: [nameOfStringVariable = stringValue].
     *                  String value may consist of several
     *                  parts(expressions in quotes or nameOfStringVariable of other strings)
     *                  that are concatenated using # symbol.
     */
    private void buildString(String rawString) {
        StringBuilder newStringBuilder = new StringBuilder();
        int start = rawString.indexOf('{');
        int end = rawString.lastIndexOf('}');

        if (start == -1 || end == -1) throw new IllegalArgumentException("A String must by enclosed in curly braces {}");

        rawString = rawString.substring(start+1, end); //getting rid of entry name and outer braces

        StringTokenizer tokenizer = new StringTokenizer(rawString, "=");
        String name = tokenizer.nextToken()
                .replaceAll("\\s", "")
                .toLowerCase();
        String rawValue = tokenizer.nextToken();

        if(name.isBlank() || rawValue.isEmpty()) throw new IllegalArgumentException("Name and value of String must not be blank!");


        String value = null;
        try {
            value = concatenate(rawValue);
        } catch (ParsingException | StringNotDefinedException e) {
            e.printStackTrace();
        }
        if(value != null)strings.put(name, value);


    }

    /**
     * Method concatenates a BibTeX expression, that uses '#' to concatenate strings
     * with values of fields or strings with strings.
     * String variables are substituted with their values.
     *
     * @param rawValue is expression with strings, #'s and text in quotes("")
     * @return concatenated string.
     */
    private String concatenate(String rawValue) throws ParsingException, StringNotDefinedException {
        StringBuilder valueBuilder = new StringBuilder();
        StringTokenizer partsOfConcatenation = new StringTokenizer(rawValue, "#");

        while (partsOfConcatenation.hasMoreTokens()) {
            String part = partsOfConcatenation.nextToken();
            part = clearSidesOfString(part);

            if(part.charAt(0) == '\"' && part.charAt(part.length()-1) == '\"') {
                part = part.substring(1, part.length()-1); // getting rid of quotes("")
                valueBuilder.append(part);
            }
            else if (part.charAt(0) == '\"' || part.charAt(part.length()-1) == '\"') {
                throw new ParsingException("Quotes are not closed at: "+part.charAt(0)+" and "+part.charAt(part.length()-1));
            }
            else {
                part = part.replaceAll("\\s", ""); //getting rid of whitespaces
                String key = part.toLowerCase();
                String valueOfStrVar = strings.get(key);

                if(valueOfStrVar == null)
                    throw new StringNotDefinedException("String "+part+" that is being used in concatenation is not defined!");

                valueBuilder.append(valueOfStrVar);
            }
        }
        return valueBuilder.toString();
    }

    /**
     * Method builds an entry from rawEntry input string
     * and adds it to entries ArrayList
     * @param type is an instance of enum EntryType
     * @see EntryType
     * @param rawEntry is text of BibTeX entry in format: [NameOfEntry{[key and fields, separated by commas]}]
     */
    private void buildEntry(EntryType type, String rawEntry) {

        Entry entry = EntryFactory.create(type);
        int start = rawEntry.indexOf('{');
        int end = rawEntry.lastIndexOf('}');

        if (start == -1 || end == -1) throw new IllegalArgumentException("An Entry must by enclosed in curly braces {}");

        rawEntry = rawEntry.substring(start+1, end); //getting rid of entry name and outer braces

        int i = rawEntry.indexOf(',');

        assert entry != null;
        entry.setKey(rawEntry.substring(0, i)
                .replaceAll("\\s", ""));

        rawEntry = rawEntry.substring(i+1);

        StringTokenizer fields = new StringTokenizer(rawEntry, ",");

        while (fields.hasMoreTokens()) {
            String field = fields.nextToken();

            if(field.isBlank())break;

            StringTokenizer typeAndValue = new StringTokenizer(field, "=");
            String fieldTypeName = typeAndValue.nextToken()
                    .replaceAll("\\s", "")
                    .toUpperCase();

            FieldType fieldType = null;
            try {
                fieldType = FieldType.valueOf(fieldTypeName);
            }
            catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            }

            if(fieldType != null) {
                String rawValue = typeAndValue.nextToken();

                if (rawValue.contains("#") || !rawValue.contains("\"")) {
                    try {
                        rawValue = concatenate(rawValue);
                    } catch (ParsingException | StringNotDefinedException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    int l = rawValue.length();

                    // getting rid of quotes("")
                    if(rawValue.contains("\"")) {
                        if(l>2) rawValue = rawValue.substring(2, rawValue.length()-1);
                        else rawValue = rawValue.substring(1, rawValue.length()-1);
                    }
                }
                String value = rawValue;
                if(fieldType == FieldType.CROSSREF)entry.setCrossRef(value.toLowerCase());
                else {
                    try {
                        entry.addField(fieldType, value);
                    }
                    catch (IllegalArgumentException ex) {
                        User.getUser().printMessage(ex.getMessage());
                    }
                }

            }
        }
        entries.add(entry);

    }

    /**
     * Method that removes all whitespaces at the
     * beginning and end of a <code>String</code>
     * @return Same <code>String</code> without whitespaces
     * at the beginning and end.
     */
    private String clearSidesOfString(String s) {
        int i,j;
        for (i=0; i<s.length() && s.charAt(i) == ' '; i++);
        for (j=s.length()-1; j>=0 && s.charAt(j) == ' '; j--);
        return s.substring(i,j+1);
    }

}
