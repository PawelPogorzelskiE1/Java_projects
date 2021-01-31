package mysliwy;
/**
 * klasa MapConfig opisuje tworzenie tabeli w ktorej zawarta bedzie mapa z pliku konfiguracyjnego.
 */
public class MapConfig {
    public String[][] matrix;
    public int [] params;

    public MapConfig(int x, int y)
    {
        matrix = new String[x][y];
        params = new int[3];
    }
    public MapConfig()
    {

    }

}
