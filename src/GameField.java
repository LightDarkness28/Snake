import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;


public class GameField extends JPanel implements ActionListener{

    private Image dot;
    private Image food;
    private int foodX;
    private int foodY;
    private int dots;
    private Timer timer;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame = true;
    private final int SIZE = 320;
    private final int DOT_SIZE = 16;
    private final int ALL_DOTS = 400;
    private int[] x = new int[ALL_DOTS];
    private int[] y = new int[ALL_DOTS];


    public GameField(){ //вызывает само поле
        Color sand= new Color(211,147,33);
        setBackground(sand);
        loadImages();
        initGame();
        addKeyListener(new FieldKeyListener());
        setFocusable(true);

    }

    public void initGame(){
        dots = 3;
        for (int u = 0; u < dots; u++) {
            x[u] = 48 - u*DOT_SIZE;
            y[u] = 48;
        }
        timer = new Timer(250,this);
        timer.start();
        createFood();
    }

    public void createFood(){
        foodX = new Random().nextInt(20)*DOT_SIZE;
        foodY = new Random().nextInt(20)*DOT_SIZE;
    }

    public void loadImages(){
        ImageIcon iif = new ImageIcon("food.png");
        food = iif.getImage();
        ImageIcon iis = new ImageIcon("snakedot.png");
        dot = iis.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(inGame){
            g.drawImage(food,foodX,foodY,this);
            for (int u = 0; u < dots; u++) {
                g.drawImage(dot,x[u],y[u],this);
            }
        } else{
            String str = "Game Over";
           g.setColor(Color.BLACK);
           ;

           g.drawString(str,125,SIZE/2);

        }
    }
   
    public void move(){
        for (int u = dots; u > 0; u--) {
            x[u] = x[u-1];
            y[u] = y[u-1];
        }
        if(left){
            x[0] -= DOT_SIZE;
        }
        if(right){
            x[0] += DOT_SIZE;
        } if(up){
            y[0] -= DOT_SIZE;
        } if(down){
            y[0] += DOT_SIZE;
        }
    }

    public void checkFood(){
        if(x[0] == foodX && y[0] == foodY){
            dots++;
            createFood();
        }
    }

    public void checkCollisions(){
        for (int u = dots; u >0 ; u--) {
            if(u>4 && x[0] == x[u] && y[0] == y[u]){
                inGame = false;
            }
        }

        if(x[0]>SIZE){
            inGame = false;
        }
        if(x[0]<0){
            inGame = false;
        }
        if(y[0]>SIZE){
            inGame = false;
        }
        if(y[0]<0){
            inGame = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(inGame){
            checkFood();
            checkCollisions();
            move();

        }
        repaint();
    }

    class FieldKeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_LEFT && !right){
                left = true;
                up = false;
                down = false;
            }
            if(key == KeyEvent.VK_RIGHT && !left){
                right = true;
                up = false;
                down = false;
            }

            if(key == KeyEvent.VK_UP && !down){
                right = false;
                up = true;
                left = false;
            }
            if(key == KeyEvent.VK_DOWN && !up){
                right = false;
                down = true;
                left = false;
            }
        }
    }


}
