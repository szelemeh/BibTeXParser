package main;

import model.Entry;
import model.FieldType;

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
            String key = field.getKey().toString().toLowerCase();
            String value = field.getValue();
            body.append(String.format(leftAlignFormat, key, value));
            if(i<length)body.append(String.format("+------------------------+--------------------------------------------------------------+%n"));
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
    public void printAll(ArrayList<Entry> entries){
        for (Entry entry: entries) {
            printEntry(entry);
        }
    }
}