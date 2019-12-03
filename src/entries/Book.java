package entries;

import java.util.ArrayList;
import java.util.Arrays;

public class Book extends Entry {
    public Book() {
        super();
        requiredFieldsTypes = new ArrayList<FieldType>(Arrays.asList(
                FieldType.TITLE,
                FieldType.PUBLISHER,
                FieldType.YEAR));
        type = EntryType.BOOK;
    }

    @Override
    protected boolean verifyFields() {
        return super.verifyFields() &&(fields.containsKey(FieldType.AUTHOR)|| fields.containsKey(FieldType.EDITOR)) ;
    }
}