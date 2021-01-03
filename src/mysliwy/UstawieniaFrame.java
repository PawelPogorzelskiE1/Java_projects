package mysliwy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UstawieniaFrame extends JFrame {
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
        JLabel tekst = new JLabel("Dodaj ustawienia w jakichs swtich/case (opis w klasie UstawieniaFrame)");
        tekst.setBounds(40, 10, 240, 20);
        panel.add(tekst);
    }
}
