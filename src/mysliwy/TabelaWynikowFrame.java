package mysliwy;

        import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.io.File;
        import java.util.Scanner;


public class TabelaWynikowFrame  extends JFrame implements ActionListener {
    public TabelaWynikowFrame(String leaderboardTXT) {
        super("Tabela Wynikow");
        try
        {
            File leaderboard = new File(leaderboardTXT);
            Scanner myLine = new Scanner(leaderboard);
            String tmp;
            JPanel panel = new JPanel();
            panel.setLayout(null);
            add(panel);

            JLabel wstep = new JLabel("Tabela najlepszych wynikow i sygnatury graczy");
            wstep.setBounds(40, 10, 240, 20);
            panel.add(wstep);
            int integer=1;

            for(int i=1; i<Global.iloscRekordowTabWynikow+1; i++) //while(tmp!="koniec")
            {
                tmp = myLine.nextLine();
                JLabel wynik = new JLabel("Miejsce " + i + ": " + tmp);
                wynik.setBounds(40, 10+integer*400/Global.iloscRekordowTabWynikow, 240, 20);
                panel.add(wynik);

                integer++;
            }
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setVisible(true);
            setSize(300, 500);
            setResizable(false);


        }
        catch (java.io.FileNotFoundException e)
        {
            System.out.println("An error occurred. TabelaWynikowFrame.java");
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
    public void actionPerformed(ActionEvent evt) {
    }


}

