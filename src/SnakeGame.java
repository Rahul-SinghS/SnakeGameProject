import javax.swing.*;

public class SnakeGame extends JFrame {//1.1 extending the JFrame class
    GameBoard gameBoard;//2.4
    SnakeGame(){ //1.2 Creating constructor and calling setVisible
        gameBoard=new GameBoard();//2.5
        add(gameBoard);//2.6 add gameboard to the jframe
        setResizable(false);//2.7 frame cannot be resized
        pack();//2.8 packing the jframe around the gameboard
        setVisible(true);
    }
    public static void main(String[] args) {
        SnakeGame snakeGame=new SnakeGame(); //1.3 Making the object of the SnakeGame class
    }
}