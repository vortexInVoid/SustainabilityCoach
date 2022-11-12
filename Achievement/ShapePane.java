import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.ArrayList;
public class ShapePane extends JPanel
{
    private ArrayList<Ellipse2D.Double> circles;
    private ArrayList<Line2D.Double> lines;
    private ArrayList<String> messages;
    private ArrayList<Boolean> circlesIsMain;
    private ArrayList<Boolean> circlesIsComplete;
    
    public ShapePane( )
    {
        circles = new ArrayList<Ellipse2D.Double>( );
        lines = new ArrayList<Line2D.Double>( );
        messages = new ArrayList<String>( );
        circlesIsMain = new ArrayList<Boolean>( );
    }
    
    public void addCircle( Ellipse2D.Double circle, String message, boolean isMain )
    {
        circles.add( circle );
        messages.add( message );
        circlesIsMain.add( isMain );
    }
    
    public void addLine( Line2D.Double line )
    {
        lines.add( line );
    }
    
    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent( g );
        Graphics2D g2 = (Graphics2D) g;
        Ellipse2D.Double theCircle;
        for( int i = 0; i < circles.size( ); i++ )
        {
            theCircle = circles.get( i );
            if( circlesIsMain.get( i ) )
                g2.setColor(Color.GREEN);
            else
                g2.setColor(Color.RED);
            g2.draw( theCircle );
            g2.setColor(Color.BLACK);
            g.drawString(messages.get(i), (int) theCircle.getX( ) + 5 , (int) theCircle.getY( ) + 50 );
        }
        
        for( Line2D.Double line : lines )
        {
            g2.setColor(Color.RED);
            g2.draw( line );
        }
    }
}
