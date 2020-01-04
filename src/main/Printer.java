package main;

import model.Entry;
import model.FieldType;
import model.Name;
import parsers.NameParser;

import java.util.ArrayList;

/**
 * Class is a printing class that is used to display Entry's data to the console.
 */
public class Printer {
    /**
     * Format to print out entry's fields.
     */
    private String leftAlignFormat = "| %-22s | %-60s |%n";

    /**
     * Method that prints entry to the console in a table format.
     * @param entry is an entry to print.
     */
    public void printEntry(Entry entry) {
        StringBuilder header = new StringBuilder();
        header.append(String.format("+---------------------------------------------------------------------------------------+%n"));
        header.append(String.format("| %-85s |%n",entry.type+"("+entry.getKey()+")"));
        header.append(String.format("+------------------------+--------------------------------------------------------------+%n"));

        if (entry.getCrossRef() != null) {
            header.append(String.format(leftAlignFormat, "CROSSREF", entry.getCrossRef()));
            header.append(String.format("+------------------------+--------------------------------------------------------------+%n"));
        }


        StringBuilder body = new StringBuilder();
        int i = 1;
        int length = entry.getFields().size();
        for (java.util.Map.Entry<FieldType, String> field : entry.getFields().entrySet()) {
            if (field.getKey() == FieldType.AUTHOR || field.getKey() == FieldType.EDITOR) {
                body.append(getPrintableName(field.getKey(), field.getValue()));
            } else {
                String key = field.getKey().toString().toLowerCase();
                String value = field.getValue();
                body.append(String.format(leftAlignFormat, key, value));
            }
            if (i<length) body.append(String.format("+------------------------+--------------------------------------------------------------+%n"));
            i++;
        }

        StringBuilder footer = new StringBuilder();
        footer.append(String.format("+---------------------------------------------------------------------------------------+%n"));

        StringBuilder full = new StringBuilder();
        full.append(header);
        full.append(body);
        full.append(footer);

        System.out.println(full);
    }

    /**
     * Method that transform fieldType and value to Entry table row.
     * @param fieldType is type of field. It can be Author or Editor.
     * @param value is raw value of names separated by 'and'.
     * @return row of Entry table to print.
     */
    private String getPrintableName(FieldType fieldType, String value) {
        StringBuilder printableName = new StringBuilder();

        ArrayList<Name> names = (ArrayList<Name>) new NameParser().getNames(value);

        Name initialName = names.get(0);
        removeTildes(initialName);

        printableName.append(String.format(
                leftAlignFormat, fieldType.toString(), "- "+initialName.getFirstName()+" "+initialName.getLastName()
        ));
        names.remove(initialName);


        for (Name name: names) {

            removeTildes(name);

            printableName.append(String.format(
                    leftAlignFormat, "", "- "+name.getFirstName()+" "+name.getLastName()
            ));
        }

        return printableName.toString();
    }

    /**
     * Removes tildes from all parts of name
     * @param name is an instance of <code>Name</code>
     * @see Name
     */
    private void removeTildes(Name name) {
        name.setLastName(name.getLastName()
                .replaceAll("~ ", "")
                .replaceAll("~\\s", " ")
                .replaceAll("~", " ")
        );
        name.setFirstName(name.getFirstName()
                .replaceAll("~ ", "")
                .replaceAll("~\\s", " ")
                .replaceAll("~", " "));
    }

    /**
     * Prints all entries in entries ArrayList.
     * @param entries is an ArrayList of entries.
     */
    public void printAll(ArrayList<Entry> entries){
        for (Entry entry: entries) {
            printEntry(entry);
        }
    }
}