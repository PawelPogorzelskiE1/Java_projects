package mysliwy;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SortowanieWynikow {
    public SortowanieWynikow(String nowyWynik, String leaderboardTXT)
    {
        try {
            String ZbiorWynikow[];
            File leaderboard = new File(leaderboardTXT);
            Scanner myLine = new Scanner(leaderboard);
            String tmp;
            for(int i=1; i<Global.iloscRekordowTabWynikow+1; i++)
            {
                tmp = myLine.nextLine();
                ZbiorWynikow[i]=tmp;
            }
            for(int i=1; i<Global.iloscRekordowTabWynikow+1; i++)
            {
                if(ZbiorWynikow[i].compareTo(nowyWynik)<0)
                {
                    tmp=ZbiorWynikow[i];
                    ZbiorWynikow[i]=nowyWynik;
                    nowyWynik=tmp;
                }
            }
            ZbiorWynikow[Global.iloscRekordowTabWynikow+1]=nowyWynik;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        catch(java.util.NoSuchElementException e)
        {
            System.out.println("blabla");
            //e.printStackTrace();
            Global.iloscRekordowTabWynikow--;
            new TabelaWynikowFrame(leaderboardTXT);
        }

    }
}
