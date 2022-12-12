
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP
 */
public class Badge 
{
    private BufferedImage badgePic;
    private String badgeName;
    private boolean isComplete;
    private JLabel theLabel;
    
    public Badge(String badgeName, boolean isComplete, JLabel theLabel)
    {
        this.badgeName = badgeName;
        this.isComplete = isComplete;
        this.theLabel = theLabel;
        
        loadImage( );
        setLabel( );
        refreshVisiblity( );
    }
    
    public void setIsComplete(boolean isComplete)
    {
        this.isComplete = isComplete;
    }
     
    public void loadImage( )
    {
        try
        {
            badgePic = ImageIO.read(new File("Badges/" + badgeName + ".jpg"));
        }
        catch(IOException e) 
        {
            e.printStackTrace(); // todo: implement proper error handeling
        }
    }
    
    public void setLabel( )
    {
        theLabel.setIcon(new ImageIcon(scale(badgePic, 200, 200)));
    }
    
    public void refreshVisiblity( )
    {
        loadImage( );
        setLabel( );
        theLabel.setVisible(isComplete);
    }
    
    
    public static BufferedImage scale(BufferedImage src, int w, int h)
    {
        BufferedImage img = 
                new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        int x, y;
        int ww = src.getWidth();
        int hh = src.getHeight();
        int[] ys = new int[h];
        for (y = 0; y < h; y++)
            ys[y] = y * hh / h;
        for (x = 0; x < w; x++) {
            int newX = x * ww / w;
            for (y = 0; y < h; y++) {
                int col = src.getRGB(newX, ys[y]);
                img.setRGB(x, y, col);
            }
        }
        return img;
    }
    
}
