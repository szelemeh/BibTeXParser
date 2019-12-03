package entries;

import java.util.ArrayList;
import java.util.Arrays;

public class Inbook extends Entry{
    public Inbook() {
        super();
        requiredFieldsTypes = new ArrayList<FieldType>(Arrays.asList(
                FieldType.TITLE,
                FieldType.PUBLISHER,
                FieldType.YEAR));
        type = EntryType.INBOOK;
    }

    @Override
    protected boolean verifyFields() {
        return super.verifyFields()
                &&(fields.containsKey(FieldType.AUTHOR)|| fields.containsKey(FieldType.EDITOR))
                &&(fields.containsKey(FieldType.CHAPTER)|| fields.containsKey(FieldType.PAGES));
    }
}
