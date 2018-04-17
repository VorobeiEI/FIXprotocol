package services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class FileReaderService {

    private String fileName;

    public FileReaderService(String FILENAME) {
        this.fileName = FILENAME;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<String> readFromLogFile() {
        List<String> lines = Collections.emptyList();

        try {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
            lines.replaceAll(s -> s.toString().substring(s.toString().indexOf("8=FIX.4.4"), s.toString().lastIndexOf(" ")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
