package com.company;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files


/* wygląd pliku konfiguracyjnego
        x y
    s s s s s s s
    s p p p p p s
    s p s p s p s
    s p p p s p s
    s p s s s p s
    s p p p p p s
    s s s s s s s
    3 (ilość porzeczek) 5 (ilość borówek) 2 (ilość mysliwych)
 */


public class MapParsing {
    public MapConfig LevelRead(String filename) {
        MapConfig Overall=new MapConfig();
        try {
            File myMap = new File(filename);
            Scanner myLine = new Scanner(myMap);
            String tmp = myLine.nextLine();
            String[] parts = tmp.split(" ");
            int x = Integer.valueOf(parts[0]);
            int y = Integer.valueOf(parts[1]);
            MapConfig result = new MapConfig(x, y);
            for (int i = 0; i < y; i++) {
                tmp = myLine.nextLine();
                parts = tmp.split(" ");
                for (int j = 0; j < x; j++) {
                    result.matrix[j][i] = parts[j];

                }
            }
            tmp = myLine.nextLine();
            parts = tmp.split(" ");
            for (int i = 0; i < 3; i++) {
                result.params[i] = Integer.valueOf(parts[i]);
            }
            Overall=result;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println(Overall);
        return Overall;
    }
}
