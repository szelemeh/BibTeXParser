package model;

//Required fields: author, title, booktitle, publisher, year
//Optional fields: editor, volume/number, series, type, chapter, pages, address, edition, month, note, key
public class Incollection extends Entry{
    public Incollection() {
        super(EntryType.INCOLLECTION);
        fieldTypeList.defineSingleRequiredFields(
                FieldType.AUTHOR,
                FieldType.TITLE,
                FieldType.BOOKTITLE,
                FieldType.PUBLISHER,
                FieldType.YEAR);

        fieldTypeList.defineSingleOptionalFields(
                FieldType.EDITOR,
                FieldType.SERIES,
                FieldType.TYPE,
                FieldType.CHAPTER,
                FieldType.PAGES,
                FieldType.ADDRESS,
                FieldType.EDITION,
                FieldType.MONTH,
                FieldType.NOTE,
                FieldType.KEY);

        fieldTypeList.defineDuplexOptionalFields(
                new FieldPair(FieldType.VOLUME, FieldType.NUMBER)
        );
    }
}
