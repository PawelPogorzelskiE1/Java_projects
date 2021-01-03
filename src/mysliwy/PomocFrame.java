package mysliwy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PomocFrame extends JFrame {
    public PomocFrame() {
        super("Pomoc");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setSize(300, 500);
        setBackground(Color.blue);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);


        //Można to zrobić labelami, lub żeby było wygodniej napisać gdzieś ładnie sformatowane zasady i wkleić je jako zdjęcie co sugeruję
        JLabel tekst = new JLabel("Tutaj opis zasad gry");
        tekst.setBounds(40, 10, 240, 20);
        panel.add(tekst);
    }
}
