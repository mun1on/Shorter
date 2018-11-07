package menu.file_Reader_Writer;

import menu.MenuBar;
import menu.MyGame;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MyFileReader
{
    public long fileLines = 0;

    public List<String> readFile(String path, String fileName, List<String> listOfStrings) throws IOException {
        Map<Integer, List<String>> map = new HashMap<>();
        FileReader fr = null;
        try {
            fr = new FileReader(path+fileName);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        BufferedReader textReader = new BufferedReader(fr);

        File file = new File(path+fileName);
        Scanner sc = new Scanner(file);

        int i=0;
        while (sc.hasNextLine())
        {
            listOfStrings.add((int)fileLines++,sc.nextLine());
        }
        return listOfStrings;
    }
}
