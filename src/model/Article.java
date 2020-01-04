package model;

//Required fields: author, title, journal, year, volume
//Optional fields: number, pages, month, doi, note, key

/**
 * @see Entry
 */
public class Article extends Entry{
    public Article() {
        super(EntryType.ARTICLE);
        fieldTypeList.defineSingleRequiredFields(
                FieldType.AUTHOR,
                FieldType.TITLE,
                FieldType.JOURNAL,
                FieldType.YEAR,
                FieldType.VOLUME);
        fieldTypeList.defineSingleOptionalFields(
                FieldType.NUMBER,
                FieldType.PAGES,
                FieldType.MONTH,
                FieldType.DOI,
                FieldType.NOTE,
                FieldType.KEY);
    }

}
