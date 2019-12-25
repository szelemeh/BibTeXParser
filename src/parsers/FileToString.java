package parsers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileToString {
    String filePath;
    public FileToString(String filePath) {
        this.filePath = filePath;
    }

    public String getContent() throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        String currentLine = reader.readLine();
        while (currentLine != null) {
            contentBuilder.append(currentLine);
            contentBuilder.append('\n');
            currentLine = reader.readLine();
        }

        reader.close();
        return contentBuilder.toString();
    }
}
