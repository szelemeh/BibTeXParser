package parsers;

import Exceptions.ParsingException;
import Exceptions.StringNotDefinedException;
import model.Entry;
import model.EntryType;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;


public class Lexer {
    private String fileContent;
    private HashMap <String, String> strings;
    private User user;
    private ArrayList<Entry> entries;

    public Lexer(String fileContent) {
        this.fileContent = fileContent;
        strings = new HashMap<>();
        user = new User();
        entries = new ArrayList<>();
    }

    public void buildEntries() {
        fileContent = fileContent.substring(fileContent.indexOf("@"));
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
                 user.sendMessage("There is no entry type for "+entryTypeName);
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

    private void buildString(String rawString) {
        StringBuilder newStringBuilder = new StringBuilder();
        int start = fromLeftFirstIndexOf(rawString, '{');
        int end = fromRightFirstIndexOf(rawString, '}');

        if (start == -1 || end == -1) throw new IllegalArgumentException("The String must by enclosed in curly braces {}");

        rawString = rawString.substring(start+1, end); //getting rid of entry name and outer braces

        StringTokenizer tokenizer = new StringTokenizer(rawString, "=");
        String name = tokenizer.nextToken().replaceAll("\\s", "");
        String rawValue = tokenizer.nextToken();

        if(name.isBlank() || rawValue.isEmpty()) throw new IllegalArgumentException("Name and value of String must not be blank!");


        String value = null;
        try {
            value = concatenate(rawValue);
        } catch (ParsingException | StringNotDefinedException e) {
            e.printStackTrace();
        }
        if(value != null)strings.put(name, value);


        System.out.println(name+" "+value);
    }

    /**
     * Method concatenates a BibTeX expression, that uses '#' to concatenate strings
     * with values of fields or strings with strings.
     * String variables are substituted with their values.
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
                String key = part;
                String valueOfStrVar = strings.get(key);

                if(valueOfStrVar == null)
                    throw new StringNotDefinedException("String that is being used in concatenation is not defined!");

                valueBuilder.append(valueOfStrVar);
            }
        }
        return valueBuilder.toString();
    }

    private void buildEntry(EntryType type, String rawEntry) {
        System.out.println("building entry");
    }

    /**
     * Method that returns index of the first occurrence of character in source string 
     * starting from beginning of source.
     * @param source is string in which we look for the pattern.
     * @param c is char which we are looking for in source.
     * @return index of first character of pattern found in source, -1 if index was not found.
     */
    private int fromLeftFirstIndexOf(String source, char c) {
        return source.indexOf(c);
    }

    /**
     * Method that returns index of the first occurrence of c in source string.
     * starting from end of source.
     * @param source is string in which we look for the pattern.
     * @param c is string index of which the method returns.
     * @return index of last character of pattern found in source, -1 if index was not found.
     */
    private int fromRightFirstIndexOf(String source, char c) {
        int index;
        for (index=source.length()-1; index>=0 && source.charAt(index) != c; index--);
        if(source.charAt(index) == c)return index;
        else return -1;
    }

    private String clearSidesOfString(String s) {
        int i,j;
        for (i=0; i<s.length() && s.charAt(i) == ' '; i++);
        for (j=s.length()-1; j>=0 && s.charAt(j) == ' '; j--);
        return s.substring(i,j+1);
    }





}
