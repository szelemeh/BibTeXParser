package entries;

import java.util.ArrayList;
import java.util.Arrays;

public class Proceedings extends Entry {
    public Proceedings() {
        super();
        requiredFieldsTypes = new ArrayList<FieldType>(Arrays.asList(
                FieldType.TITLE,
                FieldType.YEAR));
        type = EntryType.PROCEEDINGS;
    }
}