package entries;

import java.util.ArrayList;
import java.util.Arrays;

public class Unpublished extends Entry {
    public Unpublished() {
        super();
        requiredFieldsTypes = new ArrayList<FieldType>(Arrays.asList(
                FieldType.AUTHOR,
                FieldType.TITLE,
                FieldType.NOTE));
        type = EntryType.UNPUBLISHED;
    }
}