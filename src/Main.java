import model.*;
import parsers.Parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

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

//        Parser parser = new Parser("//");
//        String test = "Stas von Shelemekh";
//        Name one  = parser.parseRawNameWithoutCommas(test);
//        Name another = new Name("Stas", "Shelemekh", "von", null);
//        System.out.println(one.equals(another));

//        System.out.println("ja. \nv_,a".replaceAll("[^a-zA-Z ,]", ""));
//
//        String test = "This is a test";
//        StringTokenizer tokenizer = new StringTokenizer(test, " ");
//
//        String word = tokenizer.nextToken();
//        if(tokenizer.nextToken().contains("is")) System.out.println(tokenizer.nextToken());
        ArrayList<String> arr = new ArrayList<>();
        arr.add("Something");
        arr.add("went");
        arr.add("wrong");
        arr.add("sorry");

        Iterator<String>  i = arr.iterator();
        i.remove();
//        System.out.println(i.next());


    }
}
