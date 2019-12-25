package parsers;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class FileReaderTest {

    @Test
    public void getContent() {
        String path = "tests/resourses/inputTestFile.txt";
        FileToString reader = new FileToString(path);
        try {
            assertEquals("hello world\nwow, it works\n", reader.getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}