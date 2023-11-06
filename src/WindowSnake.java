import javax.swing.*;
import java.awt.*;

public class WindowSnake extends JFrame {

    public WindowSnake(){
        setTitle("Snake");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new GameField());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int w = this.getSize().width;
        int h = this.getSize().height;
        int x = (screenSize.width-w)/2;
        int y = (screenSize.height-h)/2;
        this.setLocation(x, y);
        setVisible(true);
    }

    public static void main(String[] args) {

        WindowSnake mb = new WindowSnake();
    }
}
