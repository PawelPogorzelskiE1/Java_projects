package mysliwy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UstawieniaFrame extends JFrame implements ActionListener {
    JButton LatwyUstawieniaButton = new JButton("Mały miś");
    JButton SredniUstawieniaButton = new JButton("Niedźwiadek");
    JButton TrudnyUstawieniaButton = new JButton("Stary Wyga");
    JButton IlosclvlButton = new JButton("Zatwierdz");
    JTextArea fieldIlosclvlUstawienia = new JTextArea();

    public UstawieniaFrame() {
        super("Pomoc");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setSize(300, 500);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);


        //Chyba trzeba będzie zrobić zmienne globalne, lub zmienne wewnątrz klasy StartFrame, które tu byłyby modyfikowane
        //Miałyby one wpływać na parametry startowe samego Pacmana przy kliknieciu pierwszego przycisku
        JLabel tekstTrudnoscUstawienia = new JLabel("Wybierz poziom trudności");
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

        JLabel tekstIloslvlUstawienia = new JLabel("Wpisz ilość poziomów jaką chcesz rozegrać");
        tekstIloslvlUstawienia.setBounds(40, 200, 240, 20);
        panel.add(tekstIloslvlUstawienia);

        fieldIlosclvlUstawienia.setBounds(40, 240, 240, 20);
        panel.add(fieldIlosclvlUstawienia);

        IlosclvlButton.setBounds(50, 290, 200, 40);
        IlosclvlButton.addActionListener( this);
        panel.add(IlosclvlButton);
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
        else if(evt.getSource()==IlosclvlButton)
        {
            String tmp=fieldIlosclvlUstawienia.getText();
            Global.ilePoziomow=Integer.valueOf(tmp);
            if(Global.ilePoziomow > Global.iloscDostepnychPoziomow)
            {
                System.out.println("Wybrano wiecej poziomow niz jest dostepne.\n Wybierz mniejsza liczbe poziomow.");
                Global.ilePoziomow=1;
            }
            else if(Global.ilePoziomow < 1)
            {
                System.out.println("Wybierz co najmniej jeden poziom.");
                Global.ilePoziomow=1;
            }
            else
            {
                System.out.println("Wybrano " + tmp + " poziomow.\n Rozpocznij lowy!");
            }
        }
    }
}

