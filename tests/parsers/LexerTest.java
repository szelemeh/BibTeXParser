package parsers;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class LexerTest {
    Lexer lexer;
    String content;

    public LexerTest() {
        FileToString reader = new FileToString("tests/resourses/example.bib");
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