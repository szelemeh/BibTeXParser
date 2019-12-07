import model.Article;
import model.Book;
import model.FieldType;

public class Main {
    public static void main(String[] args) {
        Book book = new Book();
        book.setKey("kitty");
        book.addField(FieldType.AUTHOR, "Stas and Kuba and Nazar");
        book.addField(FieldType.TITLE, "Monastyrski Drama");
        book.addField(FieldType.PUBLISHER, "Dutka");
        book.addField(FieldType.YEAR, "2019");
        book.addField(FieldType.NOTE, "Samuel is also in there");

        Printer printer = new Printer();
        printer.printEntry(book);

    }
}
