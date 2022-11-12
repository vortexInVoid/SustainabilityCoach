import java.awt.*;
import javax.swing.*;
public class Tester
{
    public static void main( String[] args )
    {
        JFrame frame = new JFrame("Testing");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new OverallPane());
        frame.setVisible(true);
    }
}
