package engine.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utils {

    public static String loadFileAsString (String path) {
        String map = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null)
                map += line + "\n";
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return  map;
    }

}