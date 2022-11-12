import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.ArrayList;
public class generateTree
{
    ShapePane newShapePane;
    public generateTree( ShapePane newShapePane )
    {
        this.newShapePane = newShapePane;
    }

    public void generateMainBranch( int numOfDisplayedLeaves, int statingLevel, ArrayList<String> messages )
    {
        for( int i = 0; i < numOfDisplayedLeaves; i++ )
        {
            newShapePane.addCircle( new Ellipse2D.Double( i * 150, 500, 100, 100 ), messages.get( i ), true );
            newShapePane.addLine( new Line2D.Double( 100 + i * 150, 550, 150 + i * 150, 550 ) );
        }
    }
    
    public void generateSubBranch( int level, int numOfDisplayedLeaves )
    {
        int levelX;
        
        for( int i = 0; i < numOfDisplayedLeaves; i++ )
        {
            levelX = (level - 1) * 150;

            newShapePane.addCircle( new Ellipse2D.Double( levelX + ( i + 1 ) * 25, 500 - 150 * (i + 1) , 100, 100 ), "sublevel " + i, false );
            newShapePane.addLine( new Line2D.Double( levelX + i * 25 + 50 + 8.2200, 550 - i * 150 - 49.3197, levelX + i * 25 + 50 + 16.78, 550 - i * 150 - 100.6803 ) );                
        }
    }
}
