package model;

/**Required fields: author, title, school, year
 * Optional fields: type, address, month, note, key
 * @see Entry
 */
public class PhdThesis extends Entry {
    public PhdThesis() {
        super(EntryType.PHDTHESIS);
        fieldTypeList.defineSingleRequiredFields(
                FieldType.AUTHOR,
                FieldType.TITLE,
                FieldType.SCHOOL,
                FieldType.YEAR);

        fieldTypeList.defineSingleOptionalFields(
                FieldType.TYPE,
                FieldType.ADDRESS,
                FieldType.MONTH,
                FieldType.NOTE,
                FieldType.KEY);
    }
}