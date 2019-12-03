package entries;

import java.util.ArrayList;
import java.util.Arrays;

public class MasterThesis extends Entry {
    public MasterThesis() {
        super();
        requiredFieldsTypes = new ArrayList<FieldType>(Arrays.asList(
                FieldType.AUTHOR,
                FieldType.TITLE,
                FieldType.SCHOOL,
                FieldType.YEAR));
        type = EntryType.MASTERTHESIS;
    }
}