package model;

//Required fields: title
//Optional fields: author, organization, address, edition, month, year, note, key
public class Manual extends Entry{
    public Manual() {
        super(EntryType.MANUAL);
        fieldTypeList.defineSingleRequiredFields(
                FieldType.TITLE);

        fieldTypeList.defineSingleOptionalFields(
                FieldType.AUTHOR,
                FieldType.ORGANIZATION,
                FieldType.ADDRESS,
                FieldType.EDITION,
                FieldType.MONTH,
                FieldType.YEAR,
                FieldType.NOTE,
                FieldType.KEY);
    }
}
