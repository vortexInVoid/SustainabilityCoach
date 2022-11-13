import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.ArrayList;
public class Circle extends Ellipse2D.Double
{
    String message;
    boolean isMain, isComplete;
    int level, sublevel;
    public Circle( double X, double Y, double diameter, String message,
                boolean isMain, boolean isComplete, int level, int sublevel )
    {
        super( X, Y, diameter, diameter );
        this.message = message;
        this.isMain = isMain;
        this.isComplete = isComplete;
        this.level = level;
        this.sublevel = sublevel;
    }
    
    public String getMessage( )
    {
        return message;
    }
    
    public boolean getIsMain( )
    {
        return isMain;
    }
    
    public boolean getIsComplete( )
    {
        return isComplete;
    }
    
    public void setIsComplete( boolean isComplete )
    {
        this.isComplete = isComplete;
    }
    
    public int getLevel( )
    {
        return level;
    }
    
    public int getSublevel( )
    {
        return sublevel;
    }
}
