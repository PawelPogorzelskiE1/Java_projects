package mysliwy;

import java.awt.*;

/**
 * klasa Global() opisuje implementacje obslugi zmiennych Globalnych.
 */
public class Global {

    // stale
    //public static final int COLS = 4; formula na otrzymanie przykladowej stalej, pewnie sie do czegos przyda
    public static int Dany_Poziom = 1;
    // zmienne
    public static String imie;
    public static int wynikKoncowy =0;
    public static int ilePoziomow=1; //liczba poziomow wybranych przez uzytkownika
    public static int poziomTrudnosci=1; //(przyjm wartosci 1,2,3)
    public static int iloscDostepnychPoziomow = 4; //(poziomy musza byc w oddzielnym folderze, program sprawdzi ilosc plikow by zadecydowac ile bedzie maxs poziomow)
    public static MapConfig aktualnyPoziom = MapParsing.LevelRead("./MapsFolder/Config"+Dany_Poziom+".txt");
    public static int iloscWierszy = aktualnyPoziom.matrix[0].length;
    public static int iloscKolumn = aktualnyPoziom.matrix.length;
    public static int iloscRekordowTabWynikow = 10;
    public static int scalar=3;
    public static Dimension WindowSize;
    public static boolean czyPauza=false;
}