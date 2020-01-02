package main;

import model.Entry;
import model.FieldType;
import model.Name;
import parsers.NameParser;
import parsers.Parser;

import java.util.ArrayList;


public class Printer {
    private String leftAlignFormat = "| %-22s | %-60s |%n";
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

    private String getPrintableName(FieldType key, String value) {
        StringBuilder printableName = new StringBuilder();
        ArrayList<Name> names = (ArrayList<Name>) new NameParser().getNames(value);

        Name initialName = names.get(0);
        removeTildas(initialName);

        printableName.append(String.format(
                leftAlignFormat, key.toString(), "- "+initialName.getFirstName()+" "+initialName.getLastName()
        ));
        names.remove(initialName);


        for (Name name: names) {

            removeTildas(name);

            printableName.append(String.format(
                    leftAlignFormat, "", "- "+name.getFirstName()+" "+name.getLastName()
            ));
        }

        return printableName.toString();
    }

    private void removeTildas(Name name) {
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

    public void printAll(ArrayList<Entry> entries){
        for (Entry entry: entries) {
            printEntry(entry);
        }
    }
}