package model;

import java.util.ArrayList;
import java.util.Arrays;

//Required fields: title
//Optional fields: author, howpublished, address, month, year, note, key
public class Booklet extends Entry{
    public Booklet() {
        super(EntryType.BOOKLET);
        fieldTypeList.defineSingleRequiredFields(
                FieldType.TITLE);

        fieldTypeList.defineSingleOptionalFields(
                FieldType.AUTHOR,
                FieldType.HOWPUBLISHED,
                FieldType.ADDRES,
                FieldType.MONTH,
                FieldType.YEAR,
                FieldType.NOTE,
                FieldType.KEY);
    }
}