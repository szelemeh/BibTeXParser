package parsers;

import Exceptions.NoSuchEntryException;
import model.Entry;
import model.EntryType;

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
        System.out.println("bulding string");
    }

    private void buildEntry(EntryType type, String rawEntry) {
        System.out.println("building entry");
    }






}
