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
        article.addField(FieldType.ADDRES, "");
    }

    @Test
    public void addField_ExistingField_Passes() {
        article.addField(FieldType.AUTHOR, "");
    }
}