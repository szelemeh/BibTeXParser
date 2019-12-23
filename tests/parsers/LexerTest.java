package parsers;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class LexerTest {
    Lexer lexer;
    String content;

    public LexerTest() {
        FileToString reader = new FileToString();
        try {
            content = reader.getContent("tests/resourses/example.bib");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void buildEntries() {
        lexer = new Lexer(content);
        lexer.buildEntries();
    }
}