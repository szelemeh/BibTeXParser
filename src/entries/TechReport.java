package entries;

import java.util.ArrayList;
import java.util.Arrays;

public class TechReport extends Entry {
    public TechReport() {
        super();
        requiredFieldsTypes = new ArrayList<FieldType>(Arrays.asList(
                FieldType.AUTHOR,
                FieldType.TITLE,
                FieldType.INSTITUTION,
                FieldType.YEAR));
        type = EntryType.TECHREPORT;
    }
}