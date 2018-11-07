package menu.file_Reader_Writer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MyFileWriter
{

    private String pathFile, pathName;
    private boolean append_to_file = false;

    public MyFileWriter(String file_path, String pathName)
    {
        this.pathFile = file_path;
        this.pathName = pathName;
    }

    public MyFileWriter(String filePath, String namePath, boolean append_value)
    {
        pathFile = filePath;
        pathName = namePath;
        append_to_file = append_value;
    }

    public void writeToFile(String textLine) {
        FileWriter write = null;
        try {
            write = new FileWriter(pathFile+pathName, append_to_file);

            PrintWriter printLine = new PrintWriter(write);
            printLine.println(textLine+"\n");
            printLine.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
