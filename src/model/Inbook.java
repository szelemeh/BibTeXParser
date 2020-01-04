package model;

//Required fields: author/editor, title, chapter/pages, publisher, year
//Optional fields: volume/number, series, type, address, edition, month, note, key
/**
 * @see Entry
 */
public class Inbook extends Entry{
    public Inbook() {
        super(EntryType.INBOOK);
        fieldTypeList.defineSingleRequiredFields(
                FieldType.TITLE,
                FieldType.PUBLISHER,
                FieldType.YEAR);

        fieldTypeList.defineSingleOptionalFields(
                FieldType.SERIES,
                FieldType.TYPE,
                FieldType.ADDRESS,
                FieldType.EDITION,
                FieldType.MONTH,
                FieldType.NOTE,
                FieldType.KEY);

        fieldTypeList.defineDuplexRequiredFields(
                new FieldPair(FieldType.AUTHOR, FieldType.EDITOR),
                new FieldPair(FieldType.CHAPTER, FieldType.PAGES)
        );

        fieldTypeList.defineDuplexOptionalFields(
                new FieldPair(FieldType.VOLUME, FieldType.NUMBER)
        );
    }

}
