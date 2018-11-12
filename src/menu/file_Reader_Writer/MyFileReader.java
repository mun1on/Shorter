package menu.file_Reader_Writer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class MyFileReader
{
    public List<String> readFile(String path, String fileName, List<String> listOfStrings) {

        try (Stream<String> lines = Files.lines (Paths.get(path + fileName), StandardCharsets.UTF_8))
        {
            int i=0;
            for (String line : (Iterable<String>) lines::iterator) {
                System.out.println(line);
                listOfStrings.add(i++, line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfStrings;
    }
}
