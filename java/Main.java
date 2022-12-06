import javax.swing.*;
//import javafx.scene.layout.BorderPane;

public class Main
{
    public static void main( String[] args )
    {
      JFrame frame = new JFrame ("Layout Manager Demo");
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

      JTabbedPane tp = new JTabbedPane();
      tp.addTab ("Achivements", new TreePanel());
      tp.addTab ("Profile", new NewJPanel());
      tp.addTab("News", new News());

      frame.getContentPane().add(tp);
      frame.pack();
      frame.setVisible(true);
    }
}
