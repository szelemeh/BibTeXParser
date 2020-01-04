package parsers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class transforms a path to file
 * to a <code>String</code>
 */
public class FileToString {
    String filePath;

    /**
     * Constructs a FileToString instance.
     * @param filePath is a valid path to file.
     */
    public FileToString(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Method that gets content of the file.
     * @return content of the file as <code>String</code>
     * @throws IOException in case of some input error.
     */
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
