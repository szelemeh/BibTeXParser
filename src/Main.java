import model.*;

import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Document doc = new Document("//");
        for(EntryType type: EntryType.values()) {
            Entry entry = EntryFactory.create(type);
            entry.fillItself();
            doc.put(entry);
        }
        doc.printAll();
    }
}
