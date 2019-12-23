package model;

import java.util.ArrayList;
import java.util.Arrays;

//Required fields: author, title, school, year
//Optional fields: type, address, month, note, key
public class MastersThesis extends Entry {
    public MastersThesis() {
        super(EntryType.MASTERSTHESIS);
        fieldTypeList.defineSingleRequiredFields(
                FieldType.AUTHOR,
                FieldType.TITLE,
                FieldType.SCHOOL,
                FieldType.YEAR);

        fieldTypeList.defineSingleOptionalFields(
                FieldType.TYPE,
                FieldType.ADDRES,
                FieldType.MONTH,
                FieldType.NOTE,
                FieldType.KEY);
    }
}