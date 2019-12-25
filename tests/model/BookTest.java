package model;

import org.junit.Test;

import static org.junit.Assert.*;

//Required fields: author/editor, title, publisher, year
//Optional fields: volume/number, series, address, edition, month, note, key, url
public class BookTest {
    Book book;
    public BookTest(){
        book = new Book();
    }

    @Test(expected = IllegalArgumentException.class)
    public void addField_NotExistingField_ExceptionIsThrown() {
        book.addField(FieldType.SCHOOL, "");
    }

    @Test
    public void addField_ExistingField_Passes() {
        book.addField(FieldType.AUTHOR, "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void addField_TwoArgumentsOfPair_ExceptionIsThrown() {
        book.addField(FieldType.AUTHOR, "");
        book.addField(FieldType.EDITOR, "");
    }

    @Test
    public void areRequiredFieldPresent_NotPresent_False() {
        book.addField(FieldType.AUTHOR, "");
        assertFalse(book.areRequiredFieldsPresent(null));
    }
}