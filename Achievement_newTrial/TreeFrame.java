import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.ArrayList;
public class TreeFrame extends JFrame
{
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 400;
    
    private static final int TREE_WIDTH = 200;
    private static final int TREE_HEIGHT = 200;
    
    private JButton buttonOne, buttonTwo, buttonThree, buttonFour,
        buttonFive, buttonSix, buttonSeven, buttonEight;
    private TreeComponent theTree;
    
    public TreeFrame( )
    {
        createTree( );
        createButton( );
        createPanel( );
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }
    
    private void createTree( )
    {
        theTree = new TreeComponent( );
        theTree.setPreferredSize( new Dimension( 1300, 600 ) );
        
        ArrayList<String> messages = new ArrayList<String>( );
        messages.add( "Coffee" );
        messages.add( "Clothes" );
        messages.add( "Transportation" );
        messages.add( "Home" );
        messages.add( "Food" );
        messages.add( "Power" );
        messages.add( "Cleaning" );
        messages.add( "Plastic" );
        
        generateTree treeGenerator = new generateTree( theTree );
        treeGenerator.generateMainBranch( 8, 1,  messages);
        treeGenerator.generateSubBranch( 0,  3 );
        treeGenerator.generateSubBranch( 1,  3 );
        treeGenerator.generateSubBranch( 2,  1 );
        treeGenerator.generateSubBranch( 3,  2 );
        treeGenerator.generateSubBranch( 4,  3 );     
        treeGenerator.generateSubBranch( 5,  1 );
        treeGenerator.generateSubBranch( 6,  1 );
        treeGenerator.generateSubBranch( 7,  2 );
        
        theTree.changeProgressChart( 0, 0, 'c' );
        theTree.changeProgressChart( 0, 1, 'c' );
        theTree.changeProgressChart( 1, 0, 'c' );
        theTree.changeProgressChart( 3, 0, 'c' );
        theTree.changeProgressChart( 3, 1, 'c' );
        theTree.changeProgressChart( 3, 2, 'c' );
    }
    
    private void createButton( )
    {
        buttonOne = new JButton( "Coffee" );
        buttonTwo = new JButton( "Clothes" );
        buttonThree = new JButton( "Transportation" );
        buttonFour = new JButton( "Home" );
        buttonFive = new JButton( "Food" );
        buttonSix = new JButton( "Power" );
        buttonSeven = new JButton( "Cleaning" );
        buttonEight = new JButton( "Plastic" );
    }
    
    private void createPanel( )
    {
        JPanel panelOne = new JPanel( );
        JPanel panelTwo = new JPanel( );
        JPanel panelThree = new JPanel( );
        
        panelThree.setLayout(new BorderLayout( ) );
        
        panelOne.add(buttonOne);
        panelOne.add(buttonTwo);
        panelOne.add(buttonThree);
        panelOne.add(buttonFour);
        panelOne.add(buttonFive);
        panelOne.add(buttonSix);
        panelOne.add(buttonSeven);
        panelOne.add(buttonEight);
        
        panelTwo.add( theTree );
        JScrollPane scrollablePanel = new JScrollPane(panelTwo);
        
        panelThree.add( scrollablePanel );
        panelThree.add( panelOne, BorderLayout.SOUTH);
        
        add( panelThree );
    }
}
