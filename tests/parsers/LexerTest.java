package parsers;

import org.junit.Test;

import java.io.IOException;

public class LexerTest {
    Lexer lexer;
    String content;

    public LexerTest() {
        FileToString reader = new FileToString("resourses/main_example.bib");
        try {
            content = reader.getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void buildEntries() {
        lexer = new Lexer(content);
    }
}