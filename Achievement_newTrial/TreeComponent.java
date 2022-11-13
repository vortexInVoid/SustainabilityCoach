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
    }
    
    public void addLine( Line2D.Double newLine )
    {
        lines.add( newLine );
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        Circle theCircle;
        for( int i = 0; i < circles.size( ); i++ )
        {
            theCircle = circles.get( i );
            if( theCircle.getIsMain( ) )
                g2.setColor(Color.GREEN);
            else
                g2.setColor(Color.RED);
                
            if( progressChart[theCircle.getLevel( )][theCircle.getSublevel( )] == 'c' )
                g2.fill( theCircle );
            else
                g2.draw( theCircle );
            
            g2.setColor(Color.BLACK);
            g.drawString(theCircle.getMessage( ), (int) theCircle.getX( ) + 5 , (int) theCircle.getY( ) + 50 );
        }
        
        for( Line2D.Double line : lines )
        {
            g2.setColor(Color.RED);
            g2.draw( line );
        }
    }
    
    public void changeProgressChart( int level, int sublevel, char status )
    {
        progressChart[level][sublevel] = status;
    }
}

