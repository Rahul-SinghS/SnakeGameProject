import javax.swing.*;
import java.awt.*;

public class Pane extends JPanel {
    static JFrame frame;
    Pane(){
        setPreferredSize(new Dimension(400,400));
        setBackground(Color.RED);
        setVisible(true);
    }
    public static void main(String[] args) {
        Pane pane = new Pane();
        /*frame=new JFrame();
        frame.add(pane);
        frame.pack();
        frame.setVisible(true);*/
    }
}
