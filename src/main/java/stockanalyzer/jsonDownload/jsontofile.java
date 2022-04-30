package stockanalyzer.jsonDownload;


import java.io.BufferedWriter;
import java.io.*;
import java.io.IOException;

public class jsontofile {

    public static final String download_directory = "./";

    public static void saveJson2File(String json){

        try {
            BufferedWriter writer;
            String fileName = download_directory + "export.json";

            writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(json.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}