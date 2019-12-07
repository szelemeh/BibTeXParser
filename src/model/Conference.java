package model;

import java.util.ArrayList;
import java.util.Arrays;

//Required fields: author, title, booktitle, year
//Optional fields: editor, volume/number, series, pages, address, month, organization, publisher, note, key
public class Conference  extends Entry {
    public Conference() {
        super(EntryType.CONFERENCE);
        fieldTypeList.defineSingleRequiredFields(
                FieldType.AUTHOR,
                FieldType.TITLE,
                FieldType.BOOKTITLE,
                FieldType.YEAR);
        fieldTypeList.defineSingleOptionalFields(
                FieldType.EDITOR,
                FieldType.SERIES,
                FieldType.PAGES,
                FieldType.ADDRES,
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