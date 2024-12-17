package org.example;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileReaderUtil {

    public static List<String> readLinesFromFile(String fileName) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try {
            URL url = classloader.getResource(fileName);
            assert url != null;
            Path path = Paths.get(url.toURI());
            return Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException | URISyntaxException exception) {
            throw new RuntimeException(exception);
        }
    }
}
