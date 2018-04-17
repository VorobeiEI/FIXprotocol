import javafx.fxml.FXML;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class FileReaderService {

    @FXML
    private static final String FILENAME = "fix.txt";

    public static List<String> readFromLogFile() {
        List<String> lines = Collections.emptyList();

        try {
            lines = Files.readAllLines(Paths.get(FILENAME), StandardCharsets.UTF_8);
            lines.replaceAll(s -> s.toString().substring(s.toString().indexOf("8=FIX.4.4"), s.toString().lastIndexOf(" ")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
