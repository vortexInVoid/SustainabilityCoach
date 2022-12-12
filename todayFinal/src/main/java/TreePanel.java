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
    
    private JButton buttonOne, buttonTwo, buttonThree, buttonFour,
        buttonFive, buttonSix, buttonSeven, buttonEight;
    private TreeComponent theTree;
    
    public TreePanel( )
    {
        createTree( );
        createButton( );
        createPanel( );
    }
    
    class ActionButton implements ActionListener
    {
        int count;
        int level;
        ActionButton( int level )
        {
            count = 0;
            this.level = level;
        }
        
        public void actionPerformed( ActionEvent event )
        {
            theTree.changeProgressChart(level, count, 'c' );
            if( count < 4 )
                count++;
            else
                count = 0;
            JFrame pupUp = new JFrame ("Layout Manager Demo");   
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
        
        /*
        theTree.changeProgressChart( 0, 0, 'c' );
        theTree.changeProgressChart( 0, 1, 'c' );
        theTree.changeProgressChart( 1, 0, 'c' );
        theTree.changeProgressChart( 3, 0, 'c' );
        theTree.changeProgressChart( 3, 1, 'c' );
        theTree.changeProgressChart( 3, 2, 'c' );
        */
    }
    
    private void createButton( )
    {
        buttonOne = new JButton( "Coffee" );
        buttonOne.addActionListener( new ActionButton( 0 ) );
        buttonTwo = new JButton( "Clothes" );
        buttonTwo.addActionListener( new ActionButton( 1 ) );
        buttonThree = new JButton( "Transportation" );
        buttonThree.addActionListener( new ActionButton( 2 ) );
        buttonFour = new JButton( "Home" );
        buttonFour.addActionListener( new ActionButton( 3 ) );
        buttonFive = new JButton( "Food" );
        buttonFive.addActionListener( new ActionButton( 4 ) );
        buttonSix = new JButton( "Power" );
        buttonSix.addActionListener( new ActionButton( 5 ) );
        buttonSeven = new JButton( "Cleaning" );
        buttonSeven.addActionListener( new ActionButton( 6 ) );
        buttonEight = new JButton( "Plastic" );
        buttonEight.addActionListener( new ActionButton( 7 ) );
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
