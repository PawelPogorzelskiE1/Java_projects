package mysliwy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * klasa NowaGraFrame opisuje implementacje obsluge interaktywnego menu wprowadzania zadanych ustawien nastepnej rozgrywki.
 */
public class UstawieniaFrame extends JFrame implements ActionListener {
    JButton LatwyUstawieniaButton = new JButton("Maly mis");
    JButton SredniUstawieniaButton = new JButton("Niedzwiadek");
    JButton TrudnyUstawieniaButton = new JButton("Stary Wyga");
    JButton IlosclvlButton = new JButton("Zatwierdz");
    JTextArea fieldIlosclvlUstawienia = new JTextArea();
    JButton IloscRekordowWTablicyWynikowButton = new JButton("Zatwierdz");
    JTextArea fieldIloscRekordowWTablicyWynikow = new JTextArea();

    public UstawieniaFrame() {
        super("Ustawienia");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setSize(300, 500);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);


        //Chyba trzeba bedzie zrobic zmienne globalne, lub zmienne wewnatrz klasy StartFrame, ktore tu bylyby modyfikowane
        //Mialyby one wplywac na parametry startowe samego Pacmana przy kliknieciu pierwszego przycisku
        JLabel tekstTrudnoscUstawienia = new JLabel("Wybierz poziom trudnosci");
        tekstTrudnoscUstawienia.setBounds(40, 10, 240, 20);
        panel.add(tekstTrudnoscUstawienia);

        LatwyUstawieniaButton.setBounds(50, 50, 200, 40);
        LatwyUstawieniaButton.addActionListener(this);
        panel.add(LatwyUstawieniaButton);

        SredniUstawieniaButton.setBounds(50, 100, 200, 40);
        SredniUstawieniaButton.addActionListener( this);
        panel.add(SredniUstawieniaButton);

        TrudnyUstawieniaButton.setBounds(50, 150, 200, 40);
        TrudnyUstawieniaButton.addActionListener(this);
        panel.add(TrudnyUstawieniaButton);

        JLabel tekstIloslvlUstawienia = new JLabel("Wpisz ilosc poziomow jaka chcesz rozegrac");
        tekstIloslvlUstawienia.setBounds(40, 200, 240, 20);
        panel.add(tekstIloslvlUstawienia);

        fieldIlosclvlUstawienia.setBounds(40, 240, 240, 20);
        panel.add(fieldIlosclvlUstawienia);

        IlosclvlButton.setBounds(50, 290, 200, 40);
        IlosclvlButton.addActionListener( this);
        panel.add(IlosclvlButton);

        JLabel tekstIloscRekordowWTablicyWynikow = new JLabel("Wpisz ilosc rekordow do wyswietlenia");
        tekstIloscRekordowWTablicyWynikow.setBounds(40, 340, 240, 20);
        panel.add(tekstIloscRekordowWTablicyWynikow);

        fieldIloscRekordowWTablicyWynikow.setBounds(40, 370, 240, 20);
        panel.add(fieldIloscRekordowWTablicyWynikow);

        IloscRekordowWTablicyWynikowButton.setBounds(50, 410, 200, 40);
        IloscRekordowWTablicyWynikowButton.addActionListener( this);
        panel.add(IloscRekordowWTablicyWynikowButton);
    }
    public void actionPerformed(ActionEvent evt)
    {

        if (evt.getSource()==LatwyUstawieniaButton)
        {
            System.out.println("Witaj na polowaniu Maly Misiu");
            Global.poziomTrudnosci=1;
        }

        else if (evt.getSource()==SredniUstawieniaButton)
        {
            System.out.println("Witaj na polowaniu Niedzwiadku");
            Global.poziomTrudnosci=2;
        }
        else if (evt.getSource()==TrudnyUstawieniaButton)
        {
            System.out.println("Witaj na polowaniu Stary Wygo");
            Global.poziomTrudnosci=3;
        }
        else if(evt.getSource()==IlosclvlButton) {
            try {
                String tmp = fieldIlosclvlUstawienia.getText();
                    Global.ilePoziomow = Integer.valueOf(tmp);
                    if (Global.ilePoziomow > Global.iloscDostepnychPoziomow) {
                        System.out.println("Wybrano wiecej poziomow niz jest dostepne.\n Wybierz mniejsza liczbe poziomow.");
                        Global.ilePoziomow = 1;
                    } else if (Global.ilePoziomow < 1) {
                        System.out.println("Wybierz co najmniej jeden poziom.");
                        Global.ilePoziomow = 1;
                    } else {
                        System.out.println("Wybrano " + tmp + " poziomow.\n Rozpocznij lowy!");
                    }
            }
            catch (java.lang.NumberFormatException e)
            {
                System.out.println("Nie wpisano liczby poziomow.");
            }
        }
        else if(evt.getSource()==IloscRekordowWTablicyWynikowButton)
        {
            try {
                String tmp = fieldIloscRekordowWTablicyWynikow.getText();
                Global.iloscRekordowTabWynikow = Integer.valueOf(tmp);
                if (Global.iloscRekordowTabWynikow < 1) {
                    System.out.println("Wybierz co najmniej jeden rekord do wyswietlenia.");
                    Global.ilePoziomow = 1;
                } else {
                    System.out.println("Wybrano " + tmp + " rekordow.");
                }
            }
            catch (java.lang.NumberFormatException e)
            {
                System.out.println("Nie wpisano liczby rekordow.");
            }
        }
    }
}

