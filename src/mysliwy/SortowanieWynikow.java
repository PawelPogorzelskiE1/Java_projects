package mysliwy;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
/**
 * klasa SortowanieWynikow() obsluguje wczytywanie nowego wyniku do pliku z tabela wynikow oraz sortuje ja wzgledem najlepszego wyniku.
 */
public class SortowanieWynikow {
    public SortowanieWynikow(String nowyWynik, String leaderboardTXT)
    {
        int tymczasowailoscrekordow=Global.iloscRekordowTabWynikow;
        try {
            String ZbiorWynikow[] = new String[Global.iloscRekordowTabWynikow+1];
            File leaderboard = new File(leaderboardTXT);
            Scanner myLine = new Scanner(leaderboard);
            String tmp;
            for(int i=0; i<Global.iloscRekordowTabWynikow; i++)
            {
                tmp = myLine.nextLine();
                ZbiorWynikow[i]=tmp;
            }
            for(int i=0; i<Global.iloscRekordowTabWynikow; i++)
            {
                if(ZbiorWynikow[i].compareTo(nowyWynik)<0)
                {
                    tmp=ZbiorWynikow[i];
                    ZbiorWynikow[i]=nowyWynik;
                    nowyWynik=tmp;
                }
            }
            ZbiorWynikow[Global.iloscRekordowTabWynikow]=nowyWynik;

            PrintWriter zapis = new PrintWriter(leaderboardTXT);
            for(int i=0; i<Global.iloscRekordowTabWynikow+1;i++)
            {
//                zapis.println(ZbiorWynikow[i]+"\n");
                zapis.println(ZbiorWynikow[i]);
            }
            zapis.close();
 //           new TabelaWynikowFrame(leaderboardTXT);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        catch(java.util.NoSuchElementException e)
        {
            System.out.println("ZmniejszenieIlosciSortowanychWynikow w SortowanieWynikow");
            //e.printStackTrace();
            Global.iloscRekordowTabWynikow--;
//            new TabelaWynikowFrame(leaderboardTXT);
            new SortowanieWynikow(nowyWynik, leaderboardTXT);
        }

    }
}
