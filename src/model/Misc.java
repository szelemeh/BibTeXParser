package model;

//Required fields: none
//Optional fields: author, title, howpublished, month, year, note, key
/**
 * @see Entry
 */
public class Misc extends Entry {
    public Misc() {
        super(EntryType.MISC);

        fieldTypeList.defineSingleOptionalFields(
                FieldType.AUTHOR,
                FieldType.TITLE,
                FieldType.HOWPUBLISHED,
                FieldType.MONTH,
                FieldType.YEAR,
                FieldType.NOTE,
                FieldType.KEY);
    }
}