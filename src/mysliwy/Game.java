/**
 * @auth Kacper Przydatek Pawel Pogorzelski
 */

package mysliwy;
/**
 * tu umieszczone potrzebne biblioteki
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.stream.Stream;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener {


    private Dimension m;
    private final Font smallFont = new Font("Arial", Font.BOLD, 14);
    private boolean wGrze = false;
    private boolean smierc = false;


    int BLOCK_SIZE = 16*Global.scalar;
    int SCREEN_SIZE_Y = Global.iloscWierszy;
    int SCREEN_SIZE_X = Global.iloscKolumn;
    int MAX_GHOSTS = 8;
    int PACMAN_SPEED = 8;

    private int N_GHOSTS = 2*Global.poziomTrudnosci;
    private int zycia, score;
    private int[] dx, dy;
    private int[] ghost_x, ghost_y, ghost_dx, ghost_dy, ghostSpeed;

    private Image heart,ghost;
    //private ImageIcon ghost;
    private Image up, down, left, right;
    private Image porzeczka, borowka;

    private int pacman_x, pacman_y, pacmand_x, pacmand_y;
    private int req_dx, req_dy;

    private final int validSpeeds[] = {1, 2, 2, 1, 2, 1, 2, 1};
    private final int maxSpeed = 4;

    private int currentSpeed = 3;
    private String screenData[];
    private Timer timer;
    private final Test_game game;
    public String[] levelData = new String[Global.iloscWierszy * Global.iloscKolumn];
    public int[] iloscbip = new int[3];
    Test_game pac;
    /**
     * klasa Game() opisuje sposob tlumaczenia danych z tablicy otrzymanej przy parsowaniu na tablice jednowymiarowa wymagana
     * do poprawnego ladowania poziomow. Odpowiada rowniez za wywolanie wielu kluczowych elementow gry inicjalizacja zmiennych oraz zaladowanie
     * obrazow.
     */

    public Game(String configfilename, Test_game Game) {
        game=Game;
       // getSize(Game);
        MapConfig tmp = MapParsing.LevelRead(configfilename);

        for(int i =0; i < 3; i++ ){
            iloscbip[i]=tmp.params[i];
        }
        for(int i =0; i < Global.iloscKolumn; i++ ){
            for (int j=0; j < Global.iloscWierszy; j++ ){

                levelData[i+Global.iloscKolumn*j]=Global.aktualnyPoziom.matrix[i][j];

            }
        }

        loadImages();
        initVariables();
        addKeyListener(new TAdapter());
        setFocusable(true);
        initGame();
    }

    /**
     * klasa loadImages() opisuje sposob ladowania grafik dla poszczegolnych elementow wyswietlanych na ekranie.
     */
    private void loadImages() {
        down = new ImageIcon("./ImageFolder/125242585_2858133591135898_532305311143949365_n.png").getImage();
        up = new ImageIcon("./ImageFolder/125242585_2858133591135898_532305311143949365_n.png").getImage();
        left = new ImageIcon("./ImageFolder/125242585_2858133591135898_532305311143949365_n.png").getImage();
        right = new ImageIcon("./ImageFolder/125242585_2858133591135898_532305311143949365_n.png").getImage();
        //ghost = new ImageIcon("./ImageFolder/125370900_393514468496811_6055932180037584593_n.png").getImage();
        ghost = new ImageIcon("./ImageFolder/125370900_393514468496811_6055932180037584593_n.png").getImage();
        borowka = new ImageIcon("./ImageFolder/125345269_445091456646538_8868544866404905378_n.png").getImage();
        porzeczka = new ImageIcon("./ImageFolder/125325145_359204815372327_7130634727906490634_n.png").getImage();
        heart = new ImageIcon("./ImageFolder/pixel-heart-2779422_960_720.png").getImage();

    }
    /**
     * klasa getScaledImage() opisuje skalowanie obrazow by miescily sie w blokach wyswietlanych w labiryncie.
     * przyjmuje obrazy zaladowane przez loadImages oraz wartosci h- wysokosci i w- szerokosci zadanej dla przeskalowanego
     * obrazu.
     */
    private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }

    /**
     * klasa initVariables() opisuje poczatkowe ustawienie waznych zmiennych.
     */

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
    /**
     * klasa playGame() opisuje zasady dzialania gry (sprawdzanie czy moze sie ona toczyc, inicjalizowanie)
     */
    private void playGame(Graphics2D g2d) {

        if (smierc) {

            death();

        } else {
            drawPacman(g2d);

            if(Global.czyPauza==false) {
                movePacman();
                moveGhosts(g2d);
                checkMaze();
            }
        }
    }
    /**
     * klasa showIntroScreen() opisuje w ktorym miejscu i w jakim formacie wyswietla sie na ekranie warunek rozpoczecia gry
     * "press SPACE to start"
     */
    private void showIntroScreen(Graphics2D g2d) {

        String start = "Press SPACE to start";
        g2d.setColor(Color.black);
        g2d.drawString(start, (SCREEN_SIZE_X)/3, 400);
    }
    /**
     * klasa drawScore() opisuje w ktorym miejscu i w jakim formacie wyswietlac na ekranie aktualna ilosc punktow
     */
    private void drawScore(Graphics2D g) {
        g.setFont(smallFont);
        g.setColor(new Color(5, 181, 79));
        String s = "Score: " + score;
        g.drawString(s, SCREEN_SIZE_X / 2 + 96, SCREEN_SIZE_Y + 16);

        for (int i = 0; i < zycia; i++) {
            g.drawImage(getScaledImage(heart,BLOCK_SIZE,BLOCK_SIZE), i * 28 + 8, SCREEN_SIZE_Y + 1, this);
        }
    }
    /**
     * klasa checkMaze() opisuje czy gra juz sie skonczyla jesli tak to dodaje punkty (nie do konca dziala)
     */
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
            initLevel();
        }
    }
    /**
     * klasa death() opisuje moment smierci w momencie gdy skoncza sie zycia pacmanowi (niedzwiedziowi)
    */
    private void death() {

        zycia--;

        if (zycia == 0) {
            wGrze = false;
            String tmp = Global.wynikKoncowy+"   "+Global.imie;
            new SortowanieWynikow(tmp, "leaderboard.txt");
            new KomunikatKoniecGry("leaderboard.txt");
        }
        else {
            continueLevel();
        }


    }
    /**
     * klasa moveGhosts(Graphics2D g2d) opisuje poruszanie sie duchow, warunki okreslajace gdzie moga sie ruszac.
     */
    private void moveGhosts(Graphics2D g2d) {

        int pos;
        int count;

        for (int i = 0; i < N_GHOSTS; i++) {
            if (ghost_x[i] % BLOCK_SIZE == 0 && ghost_y[i] % BLOCK_SIZE == 0) {
                pos = ghost_x[i] / BLOCK_SIZE + Global.iloscKolumn * (int) (ghost_y[i] / BLOCK_SIZE);

                count = 0;
                    if ((!screenData[pos-1].equals("s")) && ghost_dx[i] != 1) {
                        dx[count] = -1;
                        dy[count] = 0;
                        count++;
                    }

                if ((!screenData[pos-Global.iloscKolumn].equals("s")) && ghost_dy[i] != 1) {
                    dx[count] = 0;
                    dy[count] = -1;
                    count++;
                }

                if ((!screenData[pos+1].equals("s")) && ghost_dx[i] != -1) {
                    dx[count] = 1;
                    dy[count] = 0;
                    count++;
                }

                if ((!screenData[pos+Global.iloscKolumn].equals("s")) && ghost_dy[i] != -1) {
                    dx[count] = 0;
                    dy[count] = 1;
                    count++;
                }
                if (count == 0) {
                        if ((screenData[pos-1].equals("s") && screenData[pos+1].equals("s") && screenData[pos-Global.iloscKolumn].equals("s") && screenData[pos+Global.iloscKolumn].equals("s"))) {
                            ghost_dx[i] = 0;
                            ghost_dy[i] = 0;
                        } else {
                           ghost_dx[i] = -ghost_dx[i];
                           ghost_dy[i] = -ghost_dy[i];

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
            drawGhost(g2d, (ghost_x[i] + 1), ghost_y[i] + 1);

            if (pacman_x > (ghost_x[i] - 8) && pacman_x < (ghost_x[i] + 8)
                    && pacman_y > (ghost_y[i] - 8) && pacman_y < (ghost_y[i] + 8)
                    && wGrze) {

                smierc = true;
            }
        }
    }
    /**
     * klasa drawGhost() opisuje rysowanie ducha (mysliwego) na mapie w momencie gdy jest na danej pozycji.
     */
    private void drawGhost(Graphics2D g2d, int x, int y) {
        int minBokProstokatuGry=Math.min((int)(game.getHeight()/Global.iloscWierszy),(int)(game.getWidth()/Global.iloscKolumn));
        g2d.drawImage(getScaledImage(ghost,BLOCK_SIZE,BLOCK_SIZE), x*minBokProstokatuGry/BLOCK_SIZE, y*minBokProstokatuGry/BLOCK_SIZE,minBokProstokatuGry, minBokProstokatuGry, this);
    }
    /**
     * klasa movePacman() opisuje sposob poruszania sie pacmana (niedzwiedzia) na mapie oraz warunki gdzie moze a gdzie nie sie ruszyc.
     * Sprawdza ona rowniez, czy poziom zostal skonczony oraz, jesli tak, to laduje nastepny poziom.
     */
    private void movePacman() {

        int pos;


        if (pacman_x % BLOCK_SIZE == 0 && pacman_y % BLOCK_SIZE == 0) {
            pos = pacman_x / BLOCK_SIZE + Global.iloscKolumn * (pacman_y / BLOCK_SIZE);


            if (screenData[pos].equals("b")) {
                screenData[pos] = "o";
                score++;
                iloscbip[2]=iloscbip[2]-1;

            }

            if (screenData[pos].equals("p")) {
                screenData[pos] = "o";
                score+=20;
                //TUTAJ WSTAW DZWIEK PORZECZKI.MP3
                iloscbip[2]=iloscbip[2]-1;

            }
            if (req_dx != 0 || req_dy != 0) {
                if ((req_dx == -1 && req_dy == 0 && !(screenData[pos-1].equals("s")))
                        || (req_dx == 1 && req_dy == 0 && !(screenData[pos+1].equals("s")))
                        || (req_dx == 0 && req_dy == -1 && !(screenData[pos-Global.iloscKolumn].equals("s")))
                        || (req_dx == 0 && req_dy == 1 && !(screenData[pos+Global.iloscKolumn].equals("s")))) {
                    pacmand_x = req_dx;
                    pacmand_y = req_dy;
                }

            }

            // Sprawdzanie czy stoi
            if ((pacmand_x == -1 && pacmand_y == 0 && (screenData[pos-1].equals("s")))
                    || (pacmand_x == 1 && pacmand_y == 0 && (screenData[pos+1].equals("s")))
                    || (pacmand_x == 0 && pacmand_y == -1 && (screenData[pos-Global.iloscKolumn].equals("s")))
                    || (pacmand_x == 0 && pacmand_y == 1 && (screenData[pos+Global.iloscKolumn].equals("s")))) {
                pacmand_x = 0;
                pacmand_y = 0;
            }
        }
        pacman_x = pacman_x + PACMAN_SPEED * pacmand_x;
        pacman_y = pacman_y + PACMAN_SPEED * pacmand_y;
        //PROBA LADOWANIA NASTEPNYCH POZIOMOW
        if (iloscbip[2] == 0){
            wGrze=false;
            Global.wynikKoncowy=Global.wynikKoncowy+score;
            if(Global.Dany_Poziom<Global.ilePoziomow) {
                System.out.println(Global.Dany_Poziom);
                Global.Dany_Poziom++;
                System.out.println(Global.Dany_Poziom);
                Global.aktualnyPoziom = MapParsing.LevelRead("./MapsFolder/Config"+Global.Dany_Poziom+".txt");
                Global.iloscWierszy = Global.aktualnyPoziom.matrix[0].length;
                Global.iloscKolumn = Global.aktualnyPoziom.matrix.length;
//                pac.setVisible(false);
                pac = new Test_game("./MapsFolder/Config"+Global.Dany_Poziom+".txt");
                pac.setVisible(true);
                pac.setTitle("Pacman");
                pac.setSize(1600,1200);
                pac.setLocationRelativeTo(null);
            }
            else {
                Global.wynikKoncowy=(Global.wynikKoncowy+zycia*250)*Global.poziomTrudnosci;
                String tmp = Global.wynikKoncowy+"   "+Global.imie;
                new SortowanieWynikow(tmp, "leaderboard.txt");
                new KomunikatKoniecGry("leaderboard.txt");
                }
            }
        }

    /**
     * klasa drawPacman() opisuje rysowanie pacmana (niedzwiedzia) w zaleznosci od tego w ktorym kierunku sie porusza
     * (jeszcze brak obrazkow niedzwiedzia odwroconego w danym kierunku)
     */
    private void drawPacman(Graphics2D g2d) {
        int minBokProstokatuGry=Math.min((int)(game.getHeight()/Global.iloscWierszy),(int)(game.getWidth()/Global.iloscKolumn));
        if (req_dx == -1) {
            g2d.drawImage(getScaledImage(left,BLOCK_SIZE,BLOCK_SIZE), (pacman_x + 1)*minBokProstokatuGry/BLOCK_SIZE, (pacman_y + 1)*minBokProstokatuGry/BLOCK_SIZE,minBokProstokatuGry,minBokProstokatuGry, this);
        } else if (req_dx == 1) {
            g2d.drawImage(getScaledImage(right,BLOCK_SIZE,BLOCK_SIZE), (pacman_x + 1)*minBokProstokatuGry/BLOCK_SIZE, (pacman_y + 1)*minBokProstokatuGry/BLOCK_SIZE,minBokProstokatuGry,minBokProstokatuGry, this);
        } else if (req_dy == -1) {
            g2d.drawImage(getScaledImage(up,BLOCK_SIZE,BLOCK_SIZE), (pacman_x + 1)*minBokProstokatuGry/BLOCK_SIZE, (pacman_y + 1)*minBokProstokatuGry/BLOCK_SIZE,minBokProstokatuGry,minBokProstokatuGry, this);
        } else {
            g2d.drawImage(getScaledImage(down,BLOCK_SIZE,BLOCK_SIZE), (pacman_x + 1)*minBokProstokatuGry/BLOCK_SIZE, (pacman_y + 1)*minBokProstokatuGry/BLOCK_SIZE,minBokProstokatuGry,minBokProstokatuGry, this);
        }
    }
    /**
     * klasa drawMaze() opisuje sposob wyrysowywania labiryntu w ktorym uzytkownik sie porusza.
     */
    private void drawMaze(Graphics2D g2d, Dimension GameWindow) {
        int i = 0;
        int x, y;
        int minBokProstokatuGry=Math.min((int)(game.getHeight()/Global.iloscWierszy),(int)(game.getWidth()/Global.iloscKolumn));
        for (y = 0; y < SCREEN_SIZE_Y; y += 1) {
            for (x = 0; x < SCREEN_SIZE_X; x += 1) {



                g2d.setColor(new Color(0,250,70));
                g2d.setStroke(new BasicStroke(5));

                if (levelData[i].equals("s")) {
                    g2d.fillRect(x*minBokProstokatuGry, y*minBokProstokatuGry, minBokProstokatuGry, minBokProstokatuGry);

                }
                if ((screenData[i].equals("s"))) {
                    g2d.fillRect(x*minBokProstokatuGry, y*minBokProstokatuGry, minBokProstokatuGry, minBokProstokatuGry);
                }

                if ((screenData[i].equals("b"))) {
                    //g2d.setColor(new Color(210, 20, 20));
                    //g2d.fillOval(x + 10, y + 10, (int)(BLOCK_SIZE/2), (int)(BLOCK_SIZE/2));
                    g2d.drawImage(getScaledImage(borowka,(int)(minBokProstokatuGry),(int)(minBokProstokatuGry)), x*minBokProstokatuGry, y*minBokProstokatuGry,minBokProstokatuGry,minBokProstokatuGry, this);
                }
                if ((screenData[i].equals("p"))) {
                    //g2d.setColor(new Color(255,255,255));
                    //g2d.fillOval(x + 10, y + 10, 6, 6);
                    g2d.drawImage(getScaledImage(porzeczka,minBokProstokatuGry,minBokProstokatuGry), x*minBokProstokatuGry, y*minBokProstokatuGry,minBokProstokatuGry,minBokProstokatuGry, this);
                }
                i++;
            }
        }
    }
    /**
     * klasa initGame() opisuje startowe wartosci zycia, wyniku, ilosci duchow (mysliwych) oraz predkosc.
     */
    private void initGame() {

        zycia = 3;
        score = 0;
        initLevel();
        //N_GHOSTS = 5;
        currentSpeed = 3;
    }
    /**
     * klasa initLevel() opisuje uruchamianie danego poziomu wykorzystujac dane ze zmiennych globalnych w ktorych sa informacje
     * o wielkosci mapy oraz jej konstrukcji (gdzie sciany, borowki i porzeczki)
     */
    private void initLevel() {
        int i;

            for (i = 0; i < Global.iloscWierszy*Global.iloscKolumn; i++) {
                screenData[i] = levelData[i];
            }
            continueLevel();
    }
    /**
     * klasa continueLevel() opisuje warunki kontynuowania poziomu. W innej klasie jest wywolywany po sprawdzeniu
     * czy gra nie jest skonczona (smierc lub koniec poziomu) pozwala na dalsze poruszanie sie duchow (mysliwych) oraz
     * pacmana (niedzwiedzia) na poczatku rozgrywki ustawia rowniez ich pozycje.
     */
    private void continueLevel() {

        int dx = 1;
        int random;

        for (int i = 0; i < N_GHOSTS; i++) {

            ghost_y[i] = 10 * BLOCK_SIZE; //start position
            ghost_x[i] = 6 * BLOCK_SIZE;
            ghost_dy[i] = 0;
            ghost_dx[i] = dx;
            dx = -dx;
            random = (int) (Math.random() * (currentSpeed + 1));

            if (random > currentSpeed) {
                random = currentSpeed;
            }

            //ghostSpeed[i] = validSpeeds[random];
            ghostSpeed[i] = validSpeeds[random];
        }

        pacman_x = 3 * BLOCK_SIZE;  //start position
        pacman_y = 3 * BLOCK_SIZE;
        pacmand_x = 0;	//reset direction move
        pacmand_y = 0;
        req_dx = 0;		// reset direction controls
        req_dy = 0;
        smierc = false;
    }
    /**
     * klasa paintComponent() opisuje sposob rysowania labiryntu oraz wyniku na ekranie
     */

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;

        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, m.width, m.height);


        drawMaze(g2d, Global.WindowSize);
        drawScore(g2d);

        if (wGrze) {
            playGame(g2d);
        } else {
            showIntroScreen(g2d);
        }

        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }

    /**
     * klasa TAdapter() opisuje nasluchiwanie na sygnal z klawiatury by program wiedzial jak ma sie poruszyc pacman (niedzwiedz)
     */

    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if (wGrze) {

                if (key == KeyEvent.VK_P) {
                    if(Global.czyPauza==false)
                    Global.czyPauza = true;
                    else
                        Global.czyPauza=false;
            }else if (key == KeyEvent.VK_LEFT) {
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
                    }}
                else {
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
