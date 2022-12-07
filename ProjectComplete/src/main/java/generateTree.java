import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.ArrayList;
public class generateTree
{
    TreeComponent treeComponent;
    public generateTree( TreeComponent treeComponent )
    {
        this.treeComponent = treeComponent;
    }
    
    public void generateMainBranch( int numOfDisplayedLeaves, 
                    int statingLevel, ArrayList<String> messages )            
    {
        for( int i = 0; i < numOfDisplayedLeaves; i++ )
        {
            treeComponent.addCircle( new Circle( i * 150, 500, 100, messages.get( i ), true, false, i, 0 ) );
            treeComponent.addLine( new Line2D.Double( 100 + i * 150, 550, 150 + i * 150, 550 ) );
            treeComponent.changeProgressChart(i, 0, 'n'); //n means not complete
        }
    }
    
    public void generateSubBranch( int level, int numOfDisplayedLeaves )
    {
        int levelX;
        for( int i = 0; i < numOfDisplayedLeaves; i++ )
        {
            levelX = level * 150;

            treeComponent.addCircle( new Circle( levelX + ( i + 1 ) * 25, 500 - 150 * (i + 1), 
                                            100, "sublevel " + i, false, false, level, i + 1 ) );
            treeComponent.addLine( new Line2D.Double( levelX + i * 25 + 50 + 8.2200, 550 - i * 150 - 49.3197, 
                                        levelX + i * 25 + 50 + 16.78, 550 - i * 150 - 100.6803  ) );
            treeComponent.changeProgressChart(level, i + 1, 'n');
        }
    }
}
