package entries;

import java.util.ArrayList;
import java.util.Arrays;

public class Incollection extends Entry{
    public Incollection() {
        super();
        requiredFieldsTypes = new ArrayList<FieldType>(Arrays.asList(
                FieldType.AUTHOR,
                FieldType.TITLE,
                FieldType.BOOKTITLE,
                FieldType.PUBLISHER,
                FieldType.YEAR));
        type = EntryType.INCOLLECTION;
    }
}
