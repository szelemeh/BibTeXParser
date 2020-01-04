package model;

////Required fields: author, title, booktitle, year
////Optional fields: editor, volume/number, series, pages, address, month, organization, publisher, note, key
/**
 * @see Entry
 */
public class Inproceedings extends Entry {
    public Inproceedings() {
        super(EntryType.INPROCEEDINGS);
        fieldTypeList.defineSingleRequiredFields(
                FieldType.AUTHOR,
                FieldType.TITLE,
                FieldType.BOOKTITLE,
                FieldType.YEAR);
        fieldTypeList.defineSingleOptionalFields(
                FieldType.EDITOR,
                FieldType.SERIES,
                FieldType.PAGES,
                FieldType.ADDRESS,
                FieldType.MONTH,
                FieldType.ORGANIZATION,
                FieldType.PUBLISHER,
                FieldType.NOTE,
                FieldType.KEY);

        fieldTypeList.defineDuplexOptionalFields(
                new FieldPair(FieldType.VOLUME, FieldType.NUMBER)
        );
    }
}