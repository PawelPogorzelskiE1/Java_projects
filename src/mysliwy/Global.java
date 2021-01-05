package mysliwy;

public class Global {

    // stałe
    //public static final int COLS = 4; formula na otrzymanie przykladowej stalej, pewnie sie do czegos przyda

    // zmienne
    public static int ilePoziomow; //liczba poziomow wybranych przez uzytkownika
    public static int poziomTrudnosci; //(przyjm wartosci 1,2,3)
    public static int iloscDostepnychPoziomow; //(poziomy musza byc w oddzielnym folderze, program sprawdzi ilosc plikow by zadecydowac ile bedzie maxs poziomow)
    public static MapConfig aktualnyPoziom = MapParsing.LevelRead("Config1.txt");
    public static int iloscWierszy = aktualnyPoziom.matrix.length;
    public static int iloscKolumn = aktualnyPoziom.matrix[0].length;
}