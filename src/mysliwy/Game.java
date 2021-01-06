package mysliwy;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.stream.Stream;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener {


    private Dimension m;
    private final Font smallFont = new Font("Arial", Font.BOLD, 14);
    private boolean wGrze = false;
    private boolean smierc = false;


    int BLOCK_SIZE = 16;
    int SCREEN_SIZE_Y = Global.iloscWierszy * BLOCK_SIZE;
    int SCREEN_SIZE_X = Global.iloscKolumn * BLOCK_SIZE;
    int MAX_GHOSTS = 8;
    int PACMAN_SPEED = 6;

    private int N_GHOSTS = 6;
    private int zycia, score;
    private int[] dx, dy;
    private int[] ghost_x, ghost_y, ghost_dx, ghost_dy, ghostSpeed;

    private Image heart, ghost;
    private Image up, down, left, right;

    private int pacman_x, pacman_y, pacmand_x, pacmand_y;
    private int req_dx, req_dy;

    private final int validSpeeds[] = {1, 2, 3, 4, 6, 8};
    private final int maxSpeed = 6;

    private int currentSpeed = 3;
    private String screenData[];
    private Timer timer;

    public String[] levelData = new String[Global.iloscWierszy * Global.iloscKolumn];

    public Game() {

        MapConfig tmp = MapParsing.LevelRead("Config1.txt");
        tmp.matrix = Global.aktualnyPoziom.matrix;

        for(int i =0; i < Global.iloscWierszy; i++ ){
            for (int j=0; j < Global.iloscKolumn; j++ ){

                levelData[j]=Global.aktualnyPoziom.matrix[i][j];

            }
        }

        loadImages();
        initVariables();
        addKeyListener(new TAdapter());
        setFocusable(true);
        initGame();
    }


    private void loadImages() {
        down = new ImageIcon("C:/Users/Paweł/IdeaProjects/proze20z_pogorzelski_przydatek/125242585_2858133591135898_532305311143949365_n.png").getImage();
        up = new ImageIcon("C:/Users/Paweł/IdeaProjects/proze20z_pogorzelski_przydatek/125242585_2858133591135898_532305311143949365_n.png").getImage();
        left = new ImageIcon("C:/Users/Paweł/IdeaProjects/proze20z_pogorzelski_przydatek/125242585_2858133591135898_532305311143949365_n.png").getImage();
        right = new ImageIcon("C:/Users/Paweł/IdeaProjects/proze20z_pogorzelski_przydatek/125242585_2858133591135898_532305311143949365_n.png").getImage();
        ghost = new ImageIcon("C:/Users/Paweł/IdeaProjects/proze20z_pogorzelski_przydatek/125370900_393514468496811_6055932180037584593_n.png").getImage();
        heart = new ImageIcon("C:/Users/Paweł/IdeaProjects/proze20z_pogorzelski_przydatek/pixel-heart-2779422_960_720.png").getImage();

    }
    private void initVariables() {

        screenData = new String[Global.iloscKolumn * Global.iloscWierszy];
        m = new Dimension(SCREEN_SIZE_X, SCREEN_SIZE_Y);
        ghost_x = new int[MAX_GHOSTS];
        ghost_dx = new int[MAX_GHOSTS];
        ghost_y = new int[MAX_GHOSTS];
        ghost_dy = new int[MAX_GHOSTS];
        ghostSpeed = new int[MAX_GHOSTS];
        dx = new int[4];
        dy = new int[4];

        timer = new Timer(40, this);
        timer.start();
    }

    private void playGame(Graphics2D g2d) {

        if (smierc) {

            death();

        } else {

            movePacman();
            drawPacman(g2d);
            moveGhosts(g2d);
            checkMaze();
        }
    }

    private void showIntroScreen(Graphics2D g2d) {

        String start = "Press SPACE to start";
        g2d.setColor(Color.yellow);
        g2d.drawString(start, (SCREEN_SIZE_X)/4, 150);
    }

    private void drawScore(Graphics2D g) {
        g.setFont(smallFont);
        g.setColor(new Color(5, 181, 79));
        String s = "Score: " + score;
        g.drawString(s, SCREEN_SIZE_X / 2 + 96, SCREEN_SIZE_Y + 16);

        for (int i = 0; i < zycia; i++) {
            g.drawImage(heart, i * 28 + 8, SCREEN_SIZE_Y + 1, this);
        }
    }

    private void checkMaze() {

        int i = 0;
        boolean finished = true;

        while (i < Global.iloscWierszy * Global.iloscKolumn && finished) {

            if ((levelData[i]) != "o") {
                finished = false;
            }

            i++;
        }

        if (finished) {

            score += 50;
            //TO DODAĆ DO POZIOMU TRUDNOŚCI - PO PRZEJSCIU POZIOMU POJAWIA SIĘ NASTĘPNY Z TRUDNIEJSZĄ MAPĄ
            /*
            if (N_GHOSTS < MAX_GHOSTS) {
                N_GHOSTS++;
            }

            if (currentSpeed < maxSpeed) {
                currentSpeed++;
            }
*/
            initLevel();
        }
    }

    private void death() {

        zycia--;

        if (zycia == 0) {
            wGrze = false;
        }

        continueLevel();
    }

    private void moveGhosts(Graphics2D g2d) {

        int pos;
        int count;

        for (int i = 0; i < N_GHOSTS; i++) {
            if (ghost_x[i] % BLOCK_SIZE == 0 && ghost_y[i] % BLOCK_SIZE == 0) {
                pos = ghost_x[i] / BLOCK_SIZE + Global.iloscKolumn * (int) (ghost_y[i] / BLOCK_SIZE);

                count = 0;
                if(pos>0)
                if ((screenData[pos-1] !="s") && ghost_dx[i] != 1) {
                    dx[count] = -1;
                    dy[count] = 0;
                    count++;
                }
                if (pos>Global.iloscKolumn)
                if ((screenData[pos-Global.iloscKolumn] != "s") && ghost_dy[i] != 1) {
                    dx[count] = 0;
                    dy[count] = -1;
                    count++;
                }

                if ((screenData[pos+1] != "s") && ghost_dx[i] != -1) {
                    dx[count] = 1;
                    dy[count] = 0;
                    count++;
                }

                if ((screenData[pos+Global.iloscKolumn] != "s") && ghost_dy[i] != -1) {
                    dx[count] = 0;
                    dy[count] = 1;
                    count++;
                }

                if (count == 0) {
                    if(pos>Global.iloscKolumn && pos<(Global.iloscKolumn-1)*Global.iloscWierszy && pos%Global.iloscKolumn!=Global.iloscKolumn-1 && pos%Global.iloscKolumn!=1)
                    {
                        if (screenData[pos-1] == "s" && screenData[pos+1] == "s" && screenData[pos-Global.iloscKolumn] == "s" && screenData[pos-Global.iloscKolumn] == "s") {
                            ghost_dx[i] = 0;
                            ghost_dy[i] = 0;
                        } else {
                            ghost_dx[i] = -ghost_dx[i];
                            ghost_dy[i] = -ghost_dy[i];
                        }
                    }

                } else {

                    count = (int) (Math.random() * count);

                    if (count > 3) {
                        count = 3;
                    }

                    ghost_dx[i] = dx[count];
                    ghost_dy[i] = dy[count];
                }

            }

            ghost_x[i] = ghost_x[i] + (ghost_dx[i] * ghostSpeed[i]);
            ghost_y[i] = ghost_y[i] + (ghost_dy[i] * ghostSpeed[i]);
            drawGhost(g2d, ghost_x[i] + 1, ghost_y[i] + 1);

            if (pacman_x > (ghost_x[i] - 8) && pacman_x < (ghost_x[i] + 8)
                    && pacman_y > (ghost_y[i] - 8) && pacman_y < (ghost_y[i] + 8)
                    && wGrze) {

                smierc = true;
            }
        }
    }

    private void drawGhost(Graphics2D g2d, int x, int y) {
        g2d.drawImage(ghost, x, y, this);
    }

    private void movePacman() {

        int pos;


        if (pacman_x % BLOCK_SIZE == 0 && pacman_y % BLOCK_SIZE == 0) {
            pos = pacman_x / BLOCK_SIZE + Global.iloscKolumn * (pacman_y / BLOCK_SIZE);


            if (screenData[pos] == "b") {
                screenData[pos] = "o";
                score++;
            }

            if (screenData[pos] == "p") {
                screenData[pos] = "o";
                score+=20;
                //TUTAJ WSTAW DZWIEK PORZECZKI.MP3
            }

            if (req_dx != 0 || req_dy != 0) {
                if (!((req_dx == -1 && req_dy == 0 && (screenData[pos--] != "s"))
                        || (req_dx == 1 && req_dy == 0 && (screenData[pos++] != "s"))
                        || (req_dx == 0 && req_dy == -1 && (screenData[pos-Global.iloscKolumn] != "s"))
                        || (req_dx == 0 && req_dy == 1 && (screenData[pos+Global.iloscKolumn] != "s")))) {
                    pacmand_x = req_dx;
                    pacmand_y = req_dy;
                }
            }

            // Sprawdzanie czy stoi ( ͡° ͜ʖ ͡°)
            if ((pacmand_x == -1 && pacmand_y == 0 && (screenData[pos--] != "s"))
                    || (pacmand_x == 1 && pacmand_y == 0 && (screenData[pos++] != "s"))
                    || (pacmand_x == 0 && pacmand_y == -1 && (screenData[pos-Global.iloscKolumn] != "s"))
                    || (pacmand_x == 0 && pacmand_y == 1 && (screenData[pos+Global.iloscKolumn] != "s"))) {
                pacmand_x = 0;
                pacmand_y = 0;
            }
        }
        pacman_x = pacman_x + PACMAN_SPEED * pacmand_x;
        pacman_y = pacman_y + PACMAN_SPEED * pacmand_y;
    }

    private void drawPacman(Graphics2D g2d) {

        if (req_dx == -1) {
            g2d.drawImage(left, pacman_x + 1, pacman_y + 1, this);
        } else if (req_dx == 1) {
            g2d.drawImage(right, pacman_x + 1, pacman_y + 1, this);
        } else if (req_dy == -1) {
            g2d.drawImage(up, pacman_x + 1, pacman_y + 1, this);
        } else {
            g2d.drawImage(down, pacman_x + 1, pacman_y + 1, this);
        }
    }

    private void drawMaze(Graphics2D g2d) {

        int i = 0;
        int x, y;

        for (y = 0; y < SCREEN_SIZE_Y; y += BLOCK_SIZE) {
            for (x = 0; x < SCREEN_SIZE_X; x += BLOCK_SIZE) {

                g2d.setColor(new Color(0,72,251));
                g2d.setStroke(new BasicStroke(5));

                if ((levelData[i] == "s")) {
                    g2d.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
                }
                if(i>1)
                if ((screenData[i-1] == "s")) {
                    g2d.drawLine(x, y, x, y + BLOCK_SIZE - 1);
                }
                if(i<(Global.iloscKolumn-1)*Global.iloscWierszy)
                if ((screenData[i+Global.iloscKolumn] == "s")) {
                    g2d.drawLine(x, y, x + BLOCK_SIZE - 1, y);
                }
                if(i<(Global.iloscWierszy * Global.iloscKolumn)-1)
                if ((screenData[i+1] == "s")) {
                    g2d.drawLine(x + BLOCK_SIZE - 1, y, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }
                if(i>Global.iloscKolumn)
                if ((screenData[i-Global.iloscKolumn] == "s")) {
                    g2d.drawLine(x, y + BLOCK_SIZE - 1, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] == "b")) {
                    g2d.setColor(new Color(255,255,255));
                    g2d.fillOval(x + 10, y + 10, 6, 6);
                }

                i++;
            }
        }
    }

    private void initGame() {

        zycia = 3;
        score = 0;
        initLevel();
        N_GHOSTS = 6;
        currentSpeed = 3;
    }

    private void initLevel() {
        int i;

            for (i = 0; i < levelData.length; i++) {
                screenData[i] = levelData[i];
            }

        continueLevel();
    }

    private void continueLevel() {

        int dx = 1;
        int random;

        for (int i = 0; i < N_GHOSTS; i++) {

            ghost_y[i] = 4 * BLOCK_SIZE; //start position
            ghost_x[i] = 4 * BLOCK_SIZE;
            ghost_dy[i] = 0;
            ghost_dx[i] = dx;
            dx = -dx;
            random = (int) (Math.random() * (currentSpeed + 1));

            if (random > currentSpeed) {
                random = currentSpeed;
            }

            ghostSpeed[i] = validSpeeds[random];
        }

        pacman_x = 7 * BLOCK_SIZE;  //start position
        pacman_y = 11 * BLOCK_SIZE;
        pacmand_x = 0;	//reset direction move
        pacmand_y = 0;
        req_dx = 0;		// reset direction controls
        req_dy = 0;
        smierc = false;
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, m.width, m.height);

        drawMaze(g2d);
        drawScore(g2d);

        if (wGrze) {
            playGame(g2d);
        } else {
            showIntroScreen(g2d);
        }

        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }


    //controls
    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if (wGrze) {
                if (key == KeyEvent.VK_LEFT) {
                    req_dx = -1;
                    req_dy = 0;
                } else if (key == KeyEvent.VK_RIGHT) {
                    req_dx = 1;
                    req_dy = 0;
                } else if (key == KeyEvent.VK_UP) {
                    req_dx = 0;
                    req_dy = -1;
                } else if (key == KeyEvent.VK_DOWN) {
                    req_dx = 0;
                    req_dy = 1;
                } else if (key == KeyEvent.VK_ESCAPE && timer.isRunning()) {
                    wGrze = false;
                }
            } else {
                if (key == KeyEvent.VK_SPACE) {
                    wGrze = true;
                    initGame();
                }
            }
        }
    }

        public void actionPerformed (ActionEvent e){
            repaint();
        }

    }
