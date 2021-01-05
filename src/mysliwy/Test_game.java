package mysliwy;

import java.awt.*;

public class Test_game {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StartFrame();
            }
        });
    }
}
