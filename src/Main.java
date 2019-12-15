import model.*;
import parsers.Parser;

import java.util.Map;

public class Main {

    public static void main(String[] args) {
//        Document doc = new Document("//");
//
//        for(EntryType type: EntryType.values()) {
//            Entry entry = EntryFactory.create(type);
//            entry.fillItself();
//            doc.put(entry);
//        }
//        //doc.printAll();
//        doc.printEntriesByEntryType(EntryType.ARTICLE);

        Parser parser = new Parser("//");
        String test = "Stas von Shelemekh";
        Name one  = parser.parseRawNameWithoutCommas(test);
        Name another = new Name("Stas", "Shelemekh", "von", null);
        System.out.println(one.equals(another));

    }
}
