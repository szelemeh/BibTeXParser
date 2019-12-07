package model;

import java.util.ArrayList;
import java.util.Arrays;

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
                FieldType.ADDRES,
                FieldType.EDITION,
                FieldType.MONTH,
                FieldType.YEAR,
                FieldType.NOTE,
                FieldType.KEY);
    }
}
