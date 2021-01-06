package mysliwy;

import javax.swing.JFrame;

public class Test_game extends JFrame{

    public Test_game() {
        add(new Game());
    }


    public static void main(String[] args) {

        Test_game pac = new Test_game();
        pac.setVisible(true);
        pac.setTitle("Pacman");
        pac.setSize(1600,1200);
        pac.setDefaultCloseOperation(EXIT_ON_CLOSE);
        pac.setLocationRelativeTo(null);

    }

}
