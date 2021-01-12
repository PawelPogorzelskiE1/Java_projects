package mysliwy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class KomunikatKoniecGry extends JFrame implements ActionListener {
    JButton WyswietlTabButton = new JButton("WyswietlTabeleWynikow");
    JButton KoniecGryButton = new JButton("Zamknij program");
    String leaderboardTXT;
    public KomunikatKoniecGry(String leaderboard) {
        super("Koniec Rozgrywki");
        leaderboardTXT=leaderboard;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setSize(300, 500);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);


        //Chyba trzeba będzie zrobić zmienne globalne, lub zmienne wewnątrz klasy StartFrame, które tu byłyby modyfikowane
        //Miałyby one wpływać na parametry startowe samego Pacmana przy kliknieciu pierwszego przycisku
        JLabel tekstTrudnoscUstawienia = new JLabel("Otrzymales "+Global.wynikKoncowy+" punktow.");
        tekstTrudnoscUstawienia.setBounds(40, 10, 240, 20);
        panel.add(tekstTrudnoscUstawienia);

        WyswietlTabButton.setBounds(50, 50, 200, 40);
        WyswietlTabButton.addActionListener(this);
        panel.add(WyswietlTabButton);

        KoniecGryButton.setBounds(50, 100, 200, 40);
        KoniecGryButton.addActionListener( this);
        panel.add(KoniecGryButton);
    }
    public void actionPerformed(ActionEvent evt)
    {

        if (evt.getSource()==WyswietlTabButton)
        {
            new TabelaWynikowFrame(leaderboardTXT);
        }
        else if (evt.getSource()==KoniecGryButton)
        {
            System.out.println("Papa");
            System.exit(0);
        }
    }
}

