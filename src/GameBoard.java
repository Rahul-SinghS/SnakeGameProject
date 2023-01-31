import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameBoard extends JPanel implements ActionListener { //2.1 create GameBoard class which behaves like a Jpanel
    int height=400;//2.2
    int width=400;//2.2
    int dots=3;//3.1 no of dots in snake including head
    int dot_size=10;//3.1size of dot which is already 10 pixels
    int x_apple=100;//3.1 initial x coordinate of apple
    int y_apple=100;//3.1 initial y coordinate of apple
    int[] x=new int[height*width];//3.2 array for x positions of each dot of snake
    int[] y=new int[height*width];//3.2 array for y positions of each dot of snake
    public void initGame(){ // 3.3 setting up the initial position of dots of snake

        for(int i=0;i<dots;i++){
            x[i]=150+i*dot_size;
            y[i]=150;
        }
    }
    Image apple;//4.1 create an image object that will store the image of apple
    Image head;//4.1 create an image object that will store the image of head
    Image body;//4.1 create an image object that will store the image of body
    boolean leftDirection=true;//5.1 boolean variable to check whether the snake head is in left direction or not
    boolean rightDirection=false;//5.1 boolean variable to check whether the snake head is in right direction or not
    boolean upDirection=false;//5.1 boolean variable to check whether the snake head is in up direction or not
    boolean downDirection=false; //5.1 boolean variable to check whether the snake head is in down direction or not
    Timer timer;//5.3.1 Timer class to generate actionEvent after every 'delay' seconds which will call the move()

    public void loadImages(){ //4.2 function to assign images to objects created in 4.1
        ImageIcon appleIcon=new ImageIcon("src/Resources/apple.png"); // the ImageIcon constructor takes the path of the image to be loaded
        apple=appleIcon.getImage();
        ImageIcon headIcon=new ImageIcon("src/Resources/head.png");
        head=headIcon.getImage();
        ImageIcon bodyIcon=new ImageIcon("src/Resources/dot.png");
        body=bodyIcon.getImage();

    }
    @Override // paintComponent is the method of the JPanel class used to paint the components on the panel
            public void paintComponent(Graphics graphics){ //4.4 painting the images on the JPanel at the specifed coordinates
           super.paintComponent(graphics);
           graphics.drawImage(apple,x_apple,y_apple,this);
           for(int i=0;i<dots;i++){
               if(i==0){
                   graphics.drawImage(head,x[i],y[i],this);
               }else{
                   graphics.drawImage(body,x[i],y[i],this);
               }
           }
           //Toolkit.getDefaultToolkit().sync();
    } // paintComponent method does not need to be called. It is called "when it needs to be".
    public void move(){//5.2 make the move function ** it will move the snake by one step only
        for(int i=dots-1;i>0;i--){ //5.2.1 moving all the dots to their previous position except head
            x[i]=x[i-1];
            y[i]=y[i-1];
        }
        // 5.2.2 moving the head of the snake according to the direction.
        if(leftDirection) x[0]-=10;
        else if(rightDirection) x[0]+=10;
        else if(upDirection) y[0]-=10;
        else y[0]+=10;
    }

    GameBoard(){
        addKeyListener(new TAdapter());
        setFocusable(true);
        setPreferredSize(new Dimension(width,height));//2.2 set dimensions of the gameboard jpanel
        setBackground(Color.BLACK);//2.3 set background color of the gameboard jpanel
        loadImages();//4.3 calling the loadImages to assign the images to objects created in 4.1
        initGame();//3.4 calling the initGame to set up coordinates of dots of snake
        timer=new Timer(200,this);// 5.3.2 initializing the timer
        timer.start();//5.3.3 starting the timer




    }

    @Override
    //5.4 implementing the ActionListener interface and overriding the actionPerformed method
    public void actionPerformed(ActionEvent actionEvent) {
        checkCollision();
        eatApple();
        move(); //5.5.1 will move the snake by one position
        repaint();// 5.5.2 will call the paintComponent() to repaint the new position of the snake
    }
    public void eatApple(){
        if(x[0]==x_apple&&y[0]==y_apple){
            dots++;
            generateNewApple();
        }
    }
    public void generateNewApple(){
        x_apple=((int)(Math.random()*39))*10;
        y_apple=((int)(Math.random()*39))*10;
    }
    public void checkCollision(){
        boolean inGame=true;
        if(x[0]<0||x[0]>=400||y[0]<0||y[0]>=400) inGame=false;
        for(int i=1;i<dots;i++){
            if(x[0]==x[i]&&y[i]==y[0]){
                inGame=false;
                break;
            }
        }
        if(!inGame) System.exit(0);
    }
    class TAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent keyEvent){
         int keyCode=keyEvent.getKeyCode();
         if((keyCode==KeyEvent.VK_LEFT)&&rightDirection==false){
             leftDirection=true;
             upDirection=false;
             downDirection=false;
         }
            if((keyCode==KeyEvent.VK_RIGHT)&&leftDirection==false){
                rightDirection=true;
                upDirection=false;
                downDirection=false;
            }
            if((keyCode==KeyEvent.VK_UP)&&downDirection==false){
                leftDirection=false;
                upDirection=true;
                rightDirection=false;
            }
            if((keyCode==KeyEvent.VK_DOWN)&&upDirection==false){
                leftDirection=false;
                downDirection=true;
                rightDirection=false;
            }
        }
    }
}


