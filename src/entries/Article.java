package entries;

import java.util.ArrayList;
import java.util.Arrays;

public class Article extends Entry{
    public Article() {
        super();
        requiredFieldsTypes = new ArrayList<FieldType>(Arrays.asList(
                FieldType.AUTHOR,
                FieldType.TITLE,
                FieldType.JOURNAL,
                FieldType.YEAR,
                FieldType.VOLUME));
        type = EntryType.ARTICLE;
    }
}
