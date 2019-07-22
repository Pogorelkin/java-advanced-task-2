package com.epam.regexps;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class IOUtil {

    public List<String> readFileByPath(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        List<String> stringList = new ArrayList<>();
        stringList = Files.readAllLines(path);
        return stringList;
    }

    public void writeToFileByPath(List<String> list, String filePath) throws IOException {
        Path path = Paths.get(filePath);
        Files.write(path, list);
    }
}
