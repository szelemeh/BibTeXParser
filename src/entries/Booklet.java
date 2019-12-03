package entries;

import java.util.ArrayList;
import java.util.Arrays;

public class Booklet extends Entry{
    public Booklet() {
        super();
        requiredFieldsTypes = new ArrayList<FieldType>(Arrays.asList(
                FieldType.AUTHOR));
        type = EntryType.BOOKLET;
    }
}