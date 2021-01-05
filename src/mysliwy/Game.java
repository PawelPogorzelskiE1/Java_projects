
package mysliwy;

import java.util.Arrays;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class Game extends JPanel implements ActionListener {

    private Dimension m;
    private final Font smallFont = new Font("Arial", Font.BOLD, 14);
    private boolean wGrze = false;
    private boolean smierc = false;

    public Game() {

        MapConfig tmp = MapParsing.LevelRead("Config1.txt");
        String levelData[][] = Global.aktualnyPoziom.matrix;
        int N_BLOCKS = levelData.length;
        System.out.println(Arrays.deepToString(levelData));
        System.out.println(N_BLOCKS);

    }

     int BLOCK_SIZE = 24;
     int SCREEN_SIZE = N_BLOCKS * BLOCK_SIZE;
     int MAX_GHOSTS = 12;
     int PACMAN_SPEED = 6;

    private int N_GHOSTS = 6;
    private int lives, score;
    private int[] dx, dy;
    private int[] ghost_x, ghost_y, ghost_dx, ghost_dy, ghostSpeed;

    private Image heart, ghost;
    private Image up, down, left, right;

    private int pacman_x, pacman_y, pacmand_x, pacmand_y;
    private int req_dx, req_dy;

        public void actionPerformed (ActionEvent e){
            //code that reacts to the action...
        }

    }
