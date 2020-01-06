package model;

import org.junit.Test;

import static org.junit.Assert.*;

//Required fields: author, title, journal, year, volume
//Optional fields: number, pages, month, doi, note, key
public class ArticleTest {
    Article article;
    public ArticleTest(){
        article = new Article();
    }

    @Test(expected = IllegalArgumentException.class)
    public void addField_NotExistingField_ExceptionIsThrown() {
        article.addField(FieldType.ADDRESS, "");
    }

    @Test
    public void addField_ExistingField_Passes() {
        article.addField(FieldType.AUTHOR, "");
    }

    @Test
    public void areRequiredFieldPresent_NotPresent_False() {
        article.addField(FieldType.AUTHOR, "");
        assertEquals(0, article.getMissingFieldTypes(null).size());
    }
}