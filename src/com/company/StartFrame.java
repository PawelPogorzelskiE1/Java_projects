
import java.awt.*;
import javax.swing.*;


public class StartFrame extends JFrame {
    public StartFrame() {
        super("Menu Wyboru");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(300, 500);

        JPanel panel=new JPanel();
        panel.setLayout(null);
        add(panel);

        JLabel tekst=new JLabel("Symulator Polowania Na Niedzwiedzia");
        tekst.setBounds(40, 10, 240, 20);
        panel.add(tekst);

        JButton NowaGraButton = new JButton("Nowa Gra");
        NowaGraButton.setBounds(50, 50, 200, 70);
        panel.add(NowaGraButton);
        JButton TabelaWynikowButton = new JButton("Tabela Wynikow");
        TabelaWynikowButton.setBounds(50, 150, 200, 70);
        panel.add(TabelaWynikowButton);
        JButton UstawieniaButton = new JButton("Ustawienia");
        UstawieniaButton.setBounds(50, 250, 200, 70);
        panel.add(UstawieniaButton);
        JButton PomocButton = new JButton("Pomoc");
        PomocButton.setBounds(50, 350, 200, 70);
        panel.add(PomocButton);
    }
}
