import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TreePanel extends JPanel
{
    
    private static final int TREE_WIDTH = 200;
    private static final int TREE_HEIGHT = 200;
    
    String[][] messageMatrix = new String[8][3];
    
    private JButton buttonOne, buttonTwo, buttonThree, buttonFour,
        buttonFive, buttonSix, buttonSeven, buttonEight;
    private TreeComponent theTree;
    private coachControl control;
    private final int NUMBER_OF_LEVELS = 8;
    private final int NUMBER_OF_SUBLEVELS = 3;
    
    public TreePanel(coachControl theControl)
    {
        createTree( );
        createButton( );
        createPanel( );
        this.control = theControl;
        for( int i = 0; i < NUMBER_OF_LEVELS; i++ )
        {
            theTree.changeProgressChart(i, 0, 'c' );
        }
    }
    
    class ActionButton implements ActionListener
    {
        public void actionPerformed( ActionEvent event )
        {
            JFrame pupUp = new PopUp(control);   
            pupUp.setVisible(true);
            pupUp.setPreferredSize(new Dimension(400, 300));
        }
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
        
        for(int i = 0; i < NUMBER_OF_LEVELS; i++)
        {
            for(int j = 0; j < NUMBER_OF_SUBLEVELS; j++)
            {
                messageMatrix[i][j] = "Empty";
            }
        }
        
        generateTree treeGenerator = new generateTree( theTree );
        treeGenerator.generateMainBranch( 8, 1,  messages);
        treeGenerator.generateSubBranch( 0,  messageMatrix[0] );
        treeGenerator.generateSubBranch( 1,  messageMatrix[1] );
        treeGenerator.generateSubBranch( 2,  messageMatrix[2] );
        treeGenerator.generateSubBranch( 3,  messageMatrix[3] );
        treeGenerator.generateSubBranch( 4,  messageMatrix[4] );     
        treeGenerator.generateSubBranch( 5,  messageMatrix[5] );
        treeGenerator.generateSubBranch( 6,  messageMatrix[6] );
        treeGenerator.generateSubBranch( 7,  messageMatrix[7] );
        
        /*
        theTree.changeProgressChart( 0, 0, 'c' );
        theTree.changeProgressChart( 0, 1, 'c' );
        theTree.changeProgressChart( 1, 0, 'c' );
        theTree.changeProgressChart( 3, 0, 'c' );
        theTree.changeProgressChart( 3, 1, 'c' );
        theTree.changeProgressChart( 3, 2, 'c' );
        */
    }
    
    public void showProgress( int level, int sublevel, char status )
    {
        theTree.changeProgressChart(level, sublevel, status); //e for empty, c for full
    }
    
    public void changeMessageMatrix( String[][] messageMatrix )
    {
        theTree.changeMessages(messageMatrix);
    }
    
    private void createButton( )
    {
        buttonOne = new JButton( "Coffee" );
        buttonOne.addActionListener( new ActionButton( ) );
        buttonTwo = new JButton( "Clothes" );
        buttonTwo.addActionListener( new ActionButton( ) );
        buttonThree = new JButton( "Transportation" );
        buttonThree.addActionListener( new ActionButton( ) );
        buttonFour = new JButton( "Home" );
        buttonFour.addActionListener( new ActionButton( ) );
        buttonFive = new JButton( "Food" );
        buttonFive.addActionListener( new ActionButton( ) );
        buttonSix = new JButton( "Power" );
        buttonSix.addActionListener( new ActionButton( ) );
        buttonSeven = new JButton( "Cleaning" );
        buttonSeven.addActionListener( new ActionButton( ) );
        buttonEight = new JButton( "Plastic" );
        buttonEight.addActionListener( new ActionButton( ) );
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
