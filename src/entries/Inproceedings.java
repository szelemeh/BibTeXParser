package entries;

import java.util.ArrayList;
import java.util.Arrays;

public class Inproceedings extends Entry {
    public Inproceedings() {
        super();
        requiredFieldsTypes = new ArrayList<FieldType>(Arrays.asList(
                FieldType.AUTHOR,
                FieldType.TITLE,
                FieldType.BOOKTITLE,
                FieldType.YEAR));
        type = EntryType.INPROCEEDINGS;
    }
}