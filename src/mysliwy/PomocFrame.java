package mysliwy;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.image.*;
import java.io.File;
import java.io.FileNotFoundException;


public class PomocFrame extends JFrame implements ActionListener{
    public PomocFrame(){
        super("Pomoc");
        try {
            JPanel panel = new JPanel();

            String imagePath="C:/Users/Kacper/Desktop/EL4/PROZE/proze20z_pogorzelski_przydatek/src/mysliwy/PomocOpis.png";
            BufferedImage myPicture = ImageIO.read(new File(imagePath));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            add(picLabel);

            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setVisible(true);
            setSize(300, 500);
            setBackground(Color.blue);
            setResizable(false);

            /*panel.setLayout(null);
            add(panel);
            //Można to zrobić labelami, lub żeby było wygodniej napisać gdzieś ładnie sformatowane zasady i wkleić je jako zdjęcie co sugeruję
            JLabel tekst = new JLabel("Tutaj opis zasad gry");
            tekst.setBounds(40, 10, 240, 20);
            panel.add(tekst);*/


        }
        catch (java.io.IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public void actionPerformed(ActionEvent evt)
    {}
}









/*import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.awt.Graphics2D;
import java.io.IOException;

public class PomocFrame extends JFrame {
    public PomocFrame() {
        super("Pomoc");

        BufferedImage image;
        public PomocFrame() {
            try {
                image = ImageIO.read(new File("PomocOpis.jpg"));
            } catch (IOException ex) {
                // handle exception...
            }
        }

            super.paintComponent(g);
            g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters


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
*/