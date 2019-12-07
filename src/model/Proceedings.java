package model;

import java.util.ArrayList;
import java.util.Arrays;

//Required fields: title, year
//Optional fields: editor, volume/number, series, address, month, publisher, organization, note, key
public class Proceedings extends Entry {
    public Proceedings() {
        super(EntryType.PROCEEDINGS);
        fieldTypeList.defineSingleRequiredFields(
                FieldType.TITLE,
                FieldType.YEAR);

        fieldTypeList.defineSingleOptionalFields(
                FieldType.EDITOR,
                FieldType.SERIES,
                FieldType.ADDRES,
                FieldType.MONTH,
                FieldType.PUBLISHER,
                FieldType.ORGANIZATION,
                FieldType.NOTE,
                FieldType.KEY);

        fieldTypeList.defineDuplexOptionalFields(
                new FieldPair(FieldType.VOLUME, FieldType.NUMBER)
        );
    }
}