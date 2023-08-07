import javax.swing.*;

public class WindowSnake extends JFrame {

    public WindowSnake(){
        setTitle("Змейка");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(320,345);
        setLocation(400,400);
        add(new GameField());
        setVisible(true);
    }

    public static void main(String[] args) {
        WindowSnake mb = new WindowSnake();
    }
}
