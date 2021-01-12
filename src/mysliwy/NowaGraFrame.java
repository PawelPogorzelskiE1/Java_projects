package mysliwy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class NowaGraFrame extends JFrame implements ActionListener {
    JTextArea fieldWprowadzImie = new JTextArea();
    JButton rozpocznijGreButton = new JButton("Rozpocznij gre");
    public NowaGraFrame()
    {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setSize(300,500);
        setResizable(false);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);


        JLabel tekstIloscRekordowWTablicyWynikow = new JLabel("Wpisz ilość rekordow do wyswietlenia");
        tekstIloscRekordowWTablicyWynikow.setBounds(40,340,240,20);
        panel.add(tekstIloscRekordowWTablicyWynikow);

        fieldWprowadzImie.setBounds(40,240,240,20);
        panel.add(fieldWprowadzImie);

        rozpocznijGreButton.setBounds(50,290,200,40);
        rozpocznijGreButton.addActionListener(this);
        panel.add(rozpocznijGreButton);
    }

    public void actionPerformed(ActionEvent evt)
    {
            String imie = fieldWprowadzImie.getText();
            Global.imie=imie;
            Test_game pac = new Test_game("./MapsFolder/Config"+Global.Dany_Poziom+".txt");
            pac.setVisible(true);
            pac.setTitle("Pacman");
            pac.setSize(1600,1200);
            pac.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            pac.setLocationRelativeTo(null);

    }
}