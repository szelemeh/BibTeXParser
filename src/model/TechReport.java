package model;

//Required fields: author, title, institution, year
//Optional fields: type, number, address, month, note, key
public class TechReport extends Entry {
    public TechReport() {
        super(EntryType.TECHREPORT);
        fieldTypeList.defineSingleRequiredFields(
                FieldType.AUTHOR,
                FieldType.TITLE,
                FieldType.INSTITUTION,
                FieldType.YEAR);

        fieldTypeList.defineSingleOptionalFields(
                FieldType.TYPE,
                FieldType.NUMBER,
                FieldType.ADDRESS,
                FieldType.MONTH,
                FieldType.NOTE,
                FieldType.KEY);
    }
}