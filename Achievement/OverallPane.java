import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.ArrayList;
public class OverallPane extends JPanel
{
    private JPanel panel;
    private JButton buttonOne, buttonTwo, buttonThree, buttonFour,
        buttonFive, buttonSix, buttonSeven, buttonEight;
    private JLabel label;
    private ShapePane shapePane;
    
    public OverallPane( )
    {       
        setLayout(new BorderLayout( ) );

        shapePane = new ShapePane( );
        ArrayList<String> messages = new ArrayList<String>( );
        
        messages.add( "Coffee" );
        messages.add( "Clothes" );
        messages.add( "Transportation" );
        messages.add( "Home" );
        messages.add( "Food" );
        messages.add( "Power" );
        messages.add( "Cleaning" );
        messages.add( "Plastic" );
        
        buttonOne = new JButton( "Coffee" );
        buttonTwo = new JButton( "Clothes" );
        buttonThree = new JButton( "Transportation" );
        buttonFour = new JButton( "Home" );
        buttonFive = new JButton( "Food" );
        buttonSix = new JButton( "Power" );
        buttonSeven = new JButton( "Cleaning" );
        buttonEight = new JButton( "Plastic" );
        
        
        generateTree theTree = new generateTree( shapePane );
        
        theTree.generateMainBranch( 8, 1, messages );
        theTree.generateSubBranch( 1,  3 );
        theTree.generateSubBranch( 2,  1 );
        theTree.generateSubBranch( 3,  2 );
        theTree.generateSubBranch( 4,  3 );     
        theTree.generateSubBranch( 5,  1 );
        theTree.generateSubBranch( 6,  1 );
        theTree.generateSubBranch( 7,  2 );
        theTree.generateSubBranch( 8,  3 ); 
        
        Scrollbar scroll=new Scrollbar();
        panel = new JPanel( );
        
        buttonOne.setVisible(true);
        buttonTwo.setVisible(true);
        
        panel.add(buttonOne);
        panel.add(buttonTwo);
        panel.add(buttonThree);
        panel.add(buttonFour);
        panel.add(buttonFive);
        panel.add(buttonSix);
        panel.add(buttonSeven);
        panel.add(buttonEight);

        
        add(panel, BorderLayout.SOUTH);
        add(shapePane);
    }
}
