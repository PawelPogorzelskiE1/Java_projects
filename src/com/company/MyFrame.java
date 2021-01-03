import javax.swing.*;

public class MyFrame extends JFrame {

    public MyFrame() {
        super("Hello World");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setExtendedState(MAXIMIZED_BOTH);
        setSize(1920, 500);
    }
}