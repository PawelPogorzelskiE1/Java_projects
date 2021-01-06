package mysliwy;

import javax.swing.JFrame;

public class Test_game extends JFrame{

    public Test_game(String configfilename) {
        add(new Game(configfilename));
    }


    /*public static void main(String[] args) {

        //Test_game pac = new Test_game(configfilename);
        pac.setVisible(true);
        pac.setTitle("Pacman");
        pac.setSize(1600,1200);
        pac.setDefaultCloseOperation(EXIT_ON_CLOSE);
        pac.setLocationRelativeTo(null);

    }*/

}
