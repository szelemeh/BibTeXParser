import main.Document;
import model.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class DocumentTest {
    Document document;
    public DocumentTest() {
        document = new Document();
        //Article:
        //  Required fields: author, title, journal, year, volume
        //  Optional fields: number, pages, month, doi, note, key
        Article article = new Article();
        article.addField(FieldType.AUTHOR, "First von Last");
        article.addField(FieldType.TITLE, "Best article ever");
        article.addField(FieldType.JOURNAL, "Observer");
        article.addField(FieldType.YEAR, "1995");
        article.addField(FieldType.VOLUME, "Big");
        article.addField(FieldType.NOTE, "a good article");

        //Booklet
        //  Required fields: title
        //  Optional fields: author, howpublished, address, month, year, note, key
        Booklet booklet = new Booklet();
        booklet.addField(FieldType.TITLE, "Best booklet ever");
        booklet.addField(FieldType.AUTHOR, "Kaka foo Beka");

        //Inbook
        //  Required fields: author/editor, title, chapter/pages, publisher, year
        //  Optional fields: volume/number, series, type, address, edition, month, note, key
        Inbook inbook = new Inbook();
        inbook.addField(FieldType.AUTHOR, "James bmw Bond");
        inbook.addField(FieldType.TITLE, "Best inbook ever");
        inbook.addField(FieldType.CHAPTER, "56");
        inbook.addField(FieldType.PUBLISHER, "a good publisher");
        inbook.addField(FieldType.YEAR, "2045");

        document.put(article);
        document.put(booklet);
        document.put(inbook);
    }
    @Test
    public void printEntriesByField() {
        System.out.println("printEntriesByField(): Bond");
        document.printEntriesByField(FieldType.AUTHOR, "Bonddfg");
    }

    @Test
    public void printEntriesByEntryType() {
        System.out.println("printEntriesByEntryType(): EntryType.Article");
        document.printEntriesByEntryType(EntryType.ARTICLE);
    }

    @Test
    public void getCrossReferencedEntry() {
        Document doc = new Document();
        Article articleWhole = new Article();
        articleWhole.addField(FieldType.AUTHOR, "First von Last");
        articleWhole.addField(FieldType.TITLE, "Best article ever");
        articleWhole.addField(FieldType.JOURNAL, "Observer");
        articleWhole.addField(FieldType.YEAR, "1995");
        articleWhole.addField(FieldType.VOLUME, "Big");
        articleWhole.addField(FieldType.NOTE, "a good article");
        articleWhole.setKey("article-whole");

        Article articleCross = new Article();
        articleCross.addField(FieldType.AUTHOR, "First von Last");
        articleCross.addField(FieldType.TITLE, "Best article ever");
        articleCross.addField(FieldType.JOURNAL, "Observer");
        articleCross.setCrossRef("article-whole");

        doc.put(articleWhole);
        doc.put(articleCross);

        assertEquals(articleWhole, doc.getCrossreferencedEntry(articleCross.getCrossRef()));
    }
}