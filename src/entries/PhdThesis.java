package entries;

import java.util.ArrayList;
import java.util.Arrays;

public class PhdThesis extends Entry {
    public PhdThesis() {
        super();
        requiredFieldsTypes = new ArrayList<FieldType>(Arrays.asList(
                FieldType.AUTHOR,
                FieldType.TITLE,
                FieldType.SCHOOL,
                FieldType.YEAR));
        type = EntryType.PHDTHESIS;
    }
}