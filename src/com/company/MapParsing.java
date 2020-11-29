import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
package com.company;

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
        try {
            File myMap = new File(filename);
            Scanner myLine = new Scanner(myMap);
            myLine.useDelimiter(" ");
            int x = Integer.valueOf(myLine);
            myLine.useDelimiter("\n");
            int y = Integer.valueOf(myLine);
            int j = 0;
            for (int i = 0; i < y; i++) {
                j = 0;
                char tmp = myLine.next().charAt(0);
                while (tmp != "\n") {
                    if (tmp != " ") {
                        LevelRead.matrix[i][j];
                    } else {
                        tmp = myLine.next().charAt(0);
                    }
                    j++;
                }
            }
            for (int i = 0; i < 3; i++) {
                myLine.useDelimiter(" ");
                params[i] = Integer.valueOf(myLine);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return matrix;
    }
