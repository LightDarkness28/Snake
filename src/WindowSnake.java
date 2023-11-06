import javax.swing.*;
import java.awt.*;

public class WindowSnake extends JFrame {

    public WindowSnake(){
        //вызывает окно самой игры
        setTitle("Snake");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


       setLocationRelativeTo(null);
        //setUndecorated(true);
        add(new GameField());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int w = this.getSize().width;
        int h = this.getSize().height;
        int x = (screenSize.width-w)/2;
        int y = (screenSize.height-h)/2;

        // Move the window
        this.setLocation(x, y);

        setVisible(true);
    }

    public static void main(String[] args) {

        WindowSnake mb = new WindowSnake();
    }
}
