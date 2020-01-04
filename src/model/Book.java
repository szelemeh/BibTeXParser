package model;

//Required fields: author/editor, title, publisher, year
//Optional fields: volume/number, series, address, edition, month, note, key, url
/**
 * @see Entry
 */
public class Book extends Entry {
    public Book() {
        super(EntryType.BOOK);
        fieldTypeList.defineSingleRequiredFields(
                FieldType.TITLE,
                FieldType.PUBLISHER,
                FieldType.YEAR);

        fieldTypeList.defineSingleOptionalFields(
                FieldType.SERIES,
                FieldType.ADDRESS,
                FieldType.EDITION,
                FieldType.MONTH,
                FieldType.NOTE,
                FieldType.KEY,
                FieldType.URL);

        fieldTypeList.defineDuplexRequiredFields(
                new FieldPair(FieldType.AUTHOR, FieldType.EDITOR)
        );

        fieldTypeList.defineDuplexOptionalFields(
                new FieldPair(FieldType.VOLUME, FieldType.NUMBER)
        );
    }

}