package main;

import model.Entry;
import model.EntryType;
import model.FieldType;
import parsers.Parser;

import java.util.ArrayList;
import java.util.EnumMap;



/**
 * The <code>Document</code> is a container and for BibTeX entries.
 * It performs printing of desired entries.
 */
public class Document {
    /**
     * Container of all entries,
     * an enum map of entries stored in an array list.
     * Such container allows to get entries of specific
     * type(category) in constant time.
     */
    private EnumMap<EntryType, ArrayList<Entry>> entries = new EnumMap<EntryType, ArrayList<Entry>>(EntryType.class);
    private Parser parser;
    private Printer printer = new Printer();
    private User user = User.getUser();

    /**
     * Constructor with no arguments used in tests
     */
    public Document() {
    }

    /**
     * Constructs a document of BibTeX entries based on file,
     * path to which is an argument to the constructor
     * @param filePath is path to a .bib file
     */
    public Document(String filePath) {
        // TODO: 31-Dec-19 check if filePath is valid

        parser = new Parser(filePath);

        ArrayList<Entry> allEntries = parser.getEntries();
        fillEntries(allEntries);

        checkAllEntries();
    }

    /**
     * Method that add entries to document
     * @param allEntries are entries that are
     *                   going to be checked and
     *                   added to the document
     */
    private void fillEntries(ArrayList<Entry> allEntries) {
        for (Entry entry: allEntries) {
            put(entry);
        }
    }

    /**
     * Method that checks missing fields in an entry
     * and notifies user if there are so.
     * @param entry is an entry to check
     */
    private void checkMissingFields(Entry entry) {
        ArrayList<FieldType> missingFields = entry.getMissingFieldTypes(getCrossreferencedEntry(entry.getCrossRef()));
        if(missingFields != null) {
            user.printMessage(entry.type + ":" + entry.getKey() + " is missing these required fields: " + missingFields.toString());
        }
    }

    /**
     * Puts an entry in entries enum map
     * @param entry is an entry to add
     */
    public void put(Entry entry) {

        if(!entries.containsKey(entry.type)) {
            entries.put(entry.type, new ArrayList<>());
        }
        entries.get(entry.type).add(entry);

    }

    /**
     * Method that prints entries which contain fields
     * that contain searchPhrase, given as an argument to the method, in their value.
     * @param fieldType is a type of field
     * @see FieldType
     * @param searchPhrase is a search term
     */
    public void printEntriesByField(FieldType fieldType, String searchPhrase) {
        if(fieldType == FieldType.AUTHOR) {
            ArrayList<Entry> entriesToPrint = getEntriesByLastName(searchPhrase);
            if(entriesToPrint.isEmpty())user.printMessage(
                    "There are no entries with author last name " + searchPhrase + "!");
            else printer.printAll(entriesToPrint);
        }

        //a switch statement here would do the job of
        //printing entries of other fields
    }

    /**
     * Return list of entries that have searched last name
     * @param lastName is searched last name
     * @return an <code>ArrayList</code>, ArrayList is empty if no entries
     * with the searched last name found
     */
    private ArrayList<Entry> getEntriesByLastName(String lastName) {
        ArrayList<ArrayList<Entry>> allArrayLists = new ArrayList<>(this.entries.values());
        ArrayList<Entry> entriesWithSearchLastName = new ArrayList<>();
        for (ArrayList<Entry> array: allArrayLists) {
            for (Entry entry : array) {
                String fieldValue = entry.getNameField();
                if (fieldValue != null) {
                    ArrayList<String> lastNames = parser.getLastNames(fieldValue);
                    for (String ln : lastNames) {
                        if (ln.contains(lastName)) entriesWithSearchLastName.add(entry);
                    }
                }
            }
        }
        return entriesWithSearchLastName;
    }

    /**
     * Prints entries with a given entry type.
     * @param entryType is a given entry type.
     * @see EntryType
     */
    public void printEntriesByEntryType(EntryType entryType) {
        for(Entry entry: entries.get(entryType)){
            printer.printEntry(entry);
        }
    }

    /**
     * Prints all entries present in this document.
     */
    public void printAll() {
        for(ArrayList<Entry> list: entries.values()) {
            for(Entry entry: list){
                printer.printEntry(entry);
            }
        }
    }

    /**
     * Returns entry which is crossreferenced by another entry.
     * Method goes through all entries checking if key of entry is
     * equal to crossRef and returns first entry that satisfies the condition.
     * @param crossRef is the main key of crossreferenced entry.
     * @return Entry of null if not found any.
     */
    public Entry getCrossreferencedEntry(String crossRef) {
        if (crossRef == null) return null;

        ArrayList<ArrayList<Entry>> allArrayLists = new ArrayList<>(this.entries.values());

        for (ArrayList<Entry> array: allArrayLists) {
            for (Entry entry: array) {
                if(crossRef.equals(entry.getKey()))return entry;
            }
        }
        return null;
    }

    /**
     * Runs checkMissingFields() for each entry present in the document.
     */
    public void checkAllEntries() {
        ArrayList<ArrayList<Entry>> allArrayLists = new ArrayList<>(this.entries.values());

        for (ArrayList<Entry> array: allArrayLists) {
            for (Entry entry: array) {
                checkMissingFields(entry);
            }
        }
    }



}


