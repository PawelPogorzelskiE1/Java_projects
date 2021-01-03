package mysliwy;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class StartFrame extends JFrame implements ActionListener
{
    JButton NowaGraButton = new JButton("Nowa Gra");
    JButton TabelaWynikowButton = new JButton("Tabela Wynikow");
    JButton UstawieniaButton = new JButton("Ustawienia");
    JButton PomocButton = new JButton("Pomoc");
    public StartFrame() {
        super("Menu Wyboru");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(300, 500);
        setBackground(Color.blue);
        setResizable(false);

        JPanel panel=new JPanel();
        panel.setLayout(null);
        add(panel);

        JLabel tekst=new JLabel("Symulator Polowania Na Niedzwiedzia");
        tekst.setBounds(40, 10, 240, 20);
        panel.add(tekst);

        //JButton NowaGraButton = new JButton("Nowa Gra");
        NowaGraButton.setBounds(50, 50, 200, 70);
        NowaGraButton.addActionListener(this);
        panel.add(NowaGraButton);

        //JButton TabelaWynikowButton = new JButton("Tabela Wynikow");
        TabelaWynikowButton.setBounds(50, 150, 200, 70);
        TabelaWynikowButton.addActionListener(this);
        panel.add(TabelaWynikowButton);

        //JButton UstawieniaButton = new JButton("Ustawienia");
        UstawieniaButton.setBounds(50, 250, 200, 70);
        UstawieniaButton.addActionListener(this);
        panel.add(UstawieniaButton);

        //JButton PomocButton = new JButton("Pomoc");
        PomocButton.setBounds(50, 350, 200, 70);
        PomocButton.addActionListener(this);
        panel.add(PomocButton);
    }
    @Override
    public void actionPerformed(ActionEvent evt)
    {
        if (evt.getSource()==NowaGraButton)
        {
            System.out.println("1 przycisk");
        }
        else if (evt.getSource()==TabelaWynikowButton)
        {
            System.out.println("2 przycisk");
        }
        else if (evt.getSource()==UstawieniaButton)
        {
            new UstawieniaFrame();
            System.out.println("3 przycisk");
        }
        else
        {
            new PomocFrame();
            System.out.println("4 przycisk");
        }
    }

}
