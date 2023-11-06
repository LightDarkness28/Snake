import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;


public class GameField extends JPanel implements ActionListener{
    private Image snakedot;
    private Image food;
    private Image wall;
    private Image corner;
    private Image head;
    private Image a;
    private Image b;
    private Image v;
    private Image g;
    private Image ta;
    private Image tb;
    private Image tv;
    private Image tg;
    private Image tail;

    private int foodX;
    private int foodY;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = (int)screenSize.getWidth();
    int height = (int)screenSize.getHeight();
    int WIDTH = width-width%16;
    int HEIGHT = height - height%16;

    private Timer timer;

    private boolean left = false;
    private boolean right = false;
    private boolean up =  true;
    private boolean down = false;
    boolean inGame = true;

 int ab=0;


    //размер 1 ячейки
    private final int DOT_SIZE = 16;
    //всего ячеек на поле

    //private final int ALL_DOTS = 1000;

    private final int ALL_DOTS = WIDTH/16*HEIGHT/16;
    //положение змейки
    private final int[] x = new int[ALL_DOTS];
    private final int[] y = new int[ALL_DOTS];
    //размер змейки в данное время
    private int dots;


    public GameField(){ //вызывает само поле

        setBackground(new Color(211,147,33));
        loadImages();
        initGame();
        addKeyListener(new FieldKeyListener());
        setFocusable(true);

    }

    public void initGame(){
//        if(width>WIDTH){
//            for(int i;i<width;i=i+16){
//
//            }

     //   }



        dots = 3;
        for (int u = 0; u < dots; u++) {
            x[u] = WIDTH/2 - u*DOT_SIZE;
            y[u] = HEIGHT/2;

head = a;
            tail =tv;



        }
        timer = new Timer(100,this);
        timer.start();
        createFood();
    }

    public void createFood(){


        foodX = new Random().nextInt(WIDTH/16)*DOT_SIZE;
        foodY = new Random().nextInt(HEIGHT/16)*DOT_SIZE;

    }

    public void loadImages(){
        ImageIcon food1 = new ImageIcon("food.png");
        food = food1.getImage();
        ImageIcon snakedot1 = new ImageIcon("snakedot.png");
        snakedot = snakedot1.getImage();
        ImageIcon wall1 = new ImageIcon("wall.png");
        wall = wall1.getImage();
        ImageIcon corner1 = new ImageIcon("corner.png");
        corner = corner1.getImage();
        ImageIcon head0 = new ImageIcon("head.png");
        ImageIcon head1 = new ImageIcon("head1.png");
        ImageIcon head2 = new ImageIcon("head2.png");
        ImageIcon head3 = new ImageIcon("head3.png");
        head = b;
                a=head0.getImage();
                b=head1.getImage();
                v=head2.getImage();
                g=head3.getImage();
        ImageIcon tail0 = new ImageIcon("tail.png");
        ImageIcon tail1 = new ImageIcon("tail1.png");
        ImageIcon tail2 = new ImageIcon("tail2.png");
        ImageIcon tail3 = new ImageIcon("tail3.png");

        tail = tb;
                ta=tail0.getImage();
                tb=tail1.getImage();
                tg=tail3.getImage();
                tv=tail2.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(inGame && ab==0){
            g.drawImage(food,foodX,foodY,this);
            for (int u = 0; u < dots; u++) {

                if(u==0){
                    g.drawImage(head,x[u],y[u],this);
                }
                else if(u==dots-1) {
                    g.drawImage(tail,x[u],y[u],this);

                }
                    else{
                    g.drawImage(snakedot,x[u],y[u],this);
                }
            }

        }

        else if(inGame==false && ab==0){
            ab++;

            if(ab==1){
           endFrame();




                   }
        }
    }
//    public void contur(){
//        if (x>WIDTH){
//
//        }
//        if (x<WIDTH){
//
//        }
//        if(y>HEIGHT){
//
//        }
//        if(y<HEIGHT){
//
//        }


  //  }
        public void endFrame() {
            JFrame frame1 = new JFrame();
            JPanel panel1 = new JPanel();
            frame1.add(panel1);
            panel1.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            panel1.add(new JLabel("     Game Over"), gbc);
            JButton but1=new JButton("New Game");
            but1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    inGame=true;
                    ab=0;
                    setVisible(false);
                    frame1.dispose();
                WindowSnake a = new WindowSnake();

                }
            });
            panel1.add(but1,gbc);
            JButton but2=new JButton("Exit");
            panel1.add(but2, gbc);
            but2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);

                }
            });

            frame1.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame1.setUndecorated(true);
            panel1.setBackground(new Color(211,147,33));
            frame1.setVisible(true);
        }

    public void move() {
        for (int u = dots; u > 0; u--) {
            x[u] = x[u - 1];
            y[u] = y[u - 1];
        }
        if (left) {
            x[0] -= DOT_SIZE;
        }
        if (right) {
            x[0] += DOT_SIZE;
        }
        if (up) {
            y[0] -= DOT_SIZE;
        }
        if (down) {
            y[0] += DOT_SIZE;
        }
//        if (x[dots] < x[dots - 1] ) {
//            g.drawImage(tail, x[dots], y[dots], this);
//        }


    }


        public void checkFood () {
            if (x[0] == foodX && y[0] == foodY) {
                dots++;
                createFood();
            }
        }

        public void checkCollisions () {
            for (int u = dots; u > 0; u--) {
                if (u > 4 && x[0] == x[u] && y[0] == y[u]) {
                    inGame = false;

                }
            }
            if (x[0] > WIDTH) {
                inGame = false;
            }
            if (y[0] > HEIGHT) {
                inGame = false;
            }
            if (x[0] < 0) {
                inGame = false;
            }
            if (y[0] < 0) {
                inGame = false;
            }
        }

        @Override
        public void actionPerformed (ActionEvent e){
            if (inGame) {
                checkFood();
                checkCollisions();
                move();

            }
            repaint();
        }

        class FieldKeyListener extends KeyAdapter {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_LEFT && !right) {

                    head = g;
                    left = true;
                    up = false;
                    down = false;
                }
                if (key == KeyEvent.VK_RIGHT && !left) {
                    head = b;
                    right = true;
                    up = false;
                    down = false;
                }

                if (key == KeyEvent.VK_UP && !down) {
                    head = a;
                    right = false;
                    up = true;
                    left = false;
                }
                if (key == KeyEvent.VK_DOWN && !up) {
                    head = v;

                    right = false;
                    down = true;
                    left = false;
                }
            }
        }


    }