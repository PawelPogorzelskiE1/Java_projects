package mysliwy;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files


/** wyglad pliku konfiguracyjnego
        x y
    s s s s s s s
    s p p p p p s
    s p s p s p s
    s p p p s p s
    s p s s s p s
    s p p p p p s
    s s s s s s s
    3 (ilosc porzeczek) 5 (ilosc borowek) 8 (suma porzeczek i borowek)
 */

/**
 * klasa MapParsing() obsluguje wczytywanie danych konfiguracyjnych z plikow konfiguracyjnych
 * wymaga podania nazwy pliku otrzymywanego z innej klasy.
 */
public class MapParsing {
    public static MapConfig LevelRead(String filename) {
        MapConfig Overall = new MapConfig();
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
            Overall = result;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return Overall;
    }
}
