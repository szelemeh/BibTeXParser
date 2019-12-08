import model.Article;
import model.Entry;
import model.EntryType;
import org.junit.Test;

import static org.junit.Assert.*;

public class EntryFactoryTest {

    @Test
    public void createEntry() {
        Entry entry = EntryFactory.create(EntryType.ARTICLE);
        assertEquals(entry, new Article());
    }
}