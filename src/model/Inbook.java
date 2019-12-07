package model;

import java.util.ArrayList;
import java.util.Arrays;

//Required fields: author/editor, title, chapter/pages, publisher, year
//Optional fields: volume/number, series, type, address, edition, month, note, key
public class Inbook extends Entry{
    public Inbook() {
        super(EntryType.INBOOK);
        fieldTypeList.defineSingleRequiredFields(
                FieldType.TITLE,
                FieldType.PUBLISHER,
                FieldType.YEAR);

        fieldTypeList.defineDuplexRequiredFields(
                new FieldPair(FieldType.AUTHOR, FieldType.EDITOR),
                new FieldPair(FieldType.CHAPTER, FieldType.PAGES)
        );

        fieldTypeList.defineDuplexOptionalFields(
                new FieldPair(FieldType.VOLUME, FieldType.NUMBER)
        );
    }

}
