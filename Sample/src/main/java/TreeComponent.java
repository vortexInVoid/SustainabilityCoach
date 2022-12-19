import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.geom.*;

public class TreeComponent extends JComponent 
{
    ArrayList<Circle> circles;
    ArrayList<Line2D.Double> lines;
    char[][] progressChart;
    public TreeComponent( )
    {
        circles = new ArrayList<Circle>( );
        lines = new ArrayList<Line2D.Double>( );
        
        progressChart = new char[8][5];
        for( int i = 0; i < progressChart.length; i++ )
        {
            for( int j = 0; j < progressChart[0].length; j++ )
            {
                progressChart[i][j] = 'e'; // e means empty
            }
        }
    }
    
    public void addCircle( Circle newCircle )
    {
        circles.add( newCircle );
        repaint( );
    }
    
    public void addLine( Line2D.Double newLine )
    {
        lines.add( newLine );
        repaint( );
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        Circle theCircle;
        Color myColor = new Color(0, 255, 0);
        for( int i = 0; i < circles.size( ); i++ )
        {
            theCircle = circles.get( i );

            if( progressChart[theCircle.getLevel( )][theCircle.getSublevel( )] == 'c' )
            {
                if( theCircle.getIsMain( ) )
                    g2.setColor(myColor);
                else
                    g2.setColor(Color.RED);
                g2.fill( theCircle );
                g2.setColor(Color.BLACK);
                g2.draw( theCircle );
            }
            else
            {
                g2.setColor(Color.BLACK);
                g2.draw( theCircle );
            }
            
            g2.setColor(Color.BLACK);
            g.drawString(theCircle.getMessage( ), (int) theCircle.getX( ) + 5 , (int) theCircle.getY( ) + 50 );
        }
        
        for( Line2D.Double line : lines )
        {
            g2.setColor(Color.BLACK);
            g2.draw( line );
        }
    }
    
    public void changeProgressChart( int level, int sublevel, char status )
    {
        progressChart[level][sublevel] = status;
        repaint( );
    }
    
    public void changeMessages( String[][] messages )
    {
        for( Circle aCircle : circles )
        {
            if( !aCircle.getIsMain( ) )
                aCircle.setMessage(messages[aCircle.getLevel( )][aCircle.getSublevel()-1]);
            //System.out.println(aCircle.getMessage());
        }
        repaint( );
    }
    /*
    public Circle findCircle(int level, int sublevel)
    {
        for(Circle aCircle : circles)
        {
            if(!aCircle.getIsMain( ))
            {
                if( (aCircle.getLevel( ) == level) && (aCircle.getSublevel( ) == sublevel) )
                    return aCircle; 
            }
        }
        return null;
    }
    */
}

