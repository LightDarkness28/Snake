import javax.swing.*;
import java.awt.*;

public class WindowSnake extends JFrame {

    public WindowSnake(){//вызывает окно самой игры
        setTitle("Змейка");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(sSize);
        setLocationRelativeTo(null);

        add(new GameField());
        setVisible(true);
    }

    public static void main(String[] args) {
        WindowSnake mb = new WindowSnake();
    }
}
