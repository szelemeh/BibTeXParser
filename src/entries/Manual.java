package entries;

import java.util.ArrayList;
import java.util.Arrays;

public class Manual extends Entry{
    public Manual() {
        super();
        requiredFieldsTypes = new ArrayList<FieldType>(Arrays.asList(
                FieldType.TITLE));
        type = EntryType.MANUAL;
    }
}
