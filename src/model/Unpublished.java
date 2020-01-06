package model;

import java.util.ArrayList;
import java.util.Arrays;

/**Required fields: author, title, note
 * Optional fields: month, year, key
 * @see Entry
 */
public class Unpublished extends Entry {
    public Unpublished() {
        super(EntryType.UNPUBLISHED);
        fieldTypeList.defineSingleRequiredFields(
                FieldType.AUTHOR,
                FieldType.TITLE,
                FieldType.NOTE);

        fieldTypeList.defineSingleOptionalFields(
                FieldType.MONTH,
                FieldType.YEAR,
                FieldType.KEY);
    }
}