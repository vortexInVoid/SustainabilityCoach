import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

/**
 * Player
 */
public class Player implements java.io.Serializable, Comparable<Player> {

    private String name_surname;
    private String userName;
    private String password;
    private transient BufferedImage profilePic;
    private boolean[] badgeInfo;
    

    // 0-man 1-female 2-other
    private int gender;
   

    // Bilkent unsatisfactory-satis-honour-highhonour
    private String status;

    private ArrayList<Achievement> allAchieved;
    //private ArrayList<Badge> badges;
    private final int NUMBER_OF_BADGES = 10;

    private double stringScore;
    private double numericScore;
    private double consistencyScore;
    private double penalty;

    private double TotalScore;

    
    public Player(String nameSurname, String userName, String password, int gender)
    {
        setProfilePic( );
        badgeInfo = new boolean[10];
        allAchieved = new ArrayList<Achievement>( );
        //Name-Surname
        setName_Surname(nameSurname);
        setUsername(userName);
        setPassword(password);
        setGender(gender);

        this.stringScore = 0;
        this.consistencyScore = 100;
        this.numericScore = 0;
        this.numericScore = 0;
        this.status = "Sappling";
        
        computeTotalScore();
        loadAchivements();
    }
    
    public boolean[] getBadgeInfo( )
    {
        return badgeInfo;
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
    
    public void addProfileImage(JLabel profileLabel)
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose Your File");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnval = fileChooser.showOpenDialog(null);
        if (returnval == JFileChooser.APPROVE_OPTION)
        {
            File file = fileChooser.getSelectedFile();
            BufferedImage bi;
             try 
             {    
                bi = ImageIO.read(file);
                bi = scale(bi, 186, 209);
                profileLabel.setIcon(new ImageIcon(bi));
                profilePic = bi;
                File outputfile = new File(userName + ".jpg");
                ImageIO.write(bi, "jpg", outputfile);
             }
             catch(IOException e) 
             {
                e.printStackTrace(); // todo: implement proper error handeling
             }
        }
    }
    
    public void setProfilePic( )
    {
        try
        {
            BufferedImage bi = ImageIO.read(new File( userName + ".jpg"));
                profilePic =  scale(bi, 186, 209);
        }
        catch(IOException e) 
        {    
        }
        try{
            if(profilePic == null)
            {
                BufferedImage bi = ImageIO.read(new File( "default_name_dont_use_this_name_as_a_user_name.jpg"));
                profilePic =  scale(bi, 186, 209);
            }
        }
        catch(IOException e) 
        {
            System.out.println("Default picture is missing.");
        }
    }
    
    public BufferedImage getProfilePic( )
    {
        return profilePic;
    }
    
    public void populateBages( )
    {
        for(int i = 0; i < NUMBER_OF_BADGES; i++)
        {
            badgeInfo[i] = false;
        }
    }
    
    public void changeBadge(int index, boolean isComplete)
    {
        badgeInfo[index] = isComplete;
        
        ///badges.get(index).refreshVisiblity( );
    }
    
    public boolean checkBadge(int index)
    {
        return badgeInfo[index];
    }
    
    //Controller
    public void setName_Surname(String nameSurname)
    {
        this.name_surname = nameSurname;
    }

    public String getName_Surname()
    {
        return this.name_surname;
    }

    public void setUsername(String user)
    {
        this.userName = user;
    }

    public String getUsername()
    {
        return this.userName;
    }

    public void setPlayerStatus(String status)
    {
        this.status = status;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getStatus()
    {
        return this.status;
    }

    protected String getPassword()
    {
        return this.password;
    }

    protected ArrayList<Achievement> getAllAchievements()
    {
        return this.allAchieved;
    }

    protected void setGender(int gender)
    {
        this.gender = gender;
    }
    
    protected int getGender( )
    {
        return gender;
    }


    protected void deleteAchievement(String name)
    {
        for(Achievement elem : this.allAchieved)
        {
            if(elem.getName().equals(name))
            {
                this.allAchieved.remove(elem);
            }
        }
    }

    protected Achievement getAchievement(String name)
    {
        for(Achievement elem : this.allAchieved)
        {
            if(elem.getName().equals(name))
            {
                return elem;
            }
        }
        return null;
    }

    protected void calculateAllScores()
    {
        this.stringScore = Statistics.calculateStringScore(this);
        this.numericScore = Statistics.calculateNumericScore(this);
        this.consistencyScore = Statistics.calculateHabitScore(this);
        this.penalty = Statistics.calculatePenalty(this);
        computeTotalScore();
    }

    protected void computeTotalScore()
    {
        this.TotalScore = this.stringScore+this.consistencyScore+this.numericScore;
    }

    public double returnTotalScore()
    {
        return this.TotalScore;
    }

    public double returnHabitScore()
    {
        return this.consistencyScore;
    }
    
    public double returnPenalty()
    {
        return this.penalty;
    }

    public int compareTo(Player p)
    {
        if(this.TotalScore > p.TotalScore)
        {
            return 1;
        }
        else if (this.TotalScore == p.TotalScore)
        {
            return 0;
        }
        else 
        {
            return -1;
        }
    }
    
    public void loadAchivements()
    {
        int i = 0;
        try {
            File myObj = new File("project_achivements.txt");
            Scanner myReader = new Scanner(myObj);
            StringTokenizer tok;
            String x0,x1,x2,cat,d0;
            
            x0 = "";
            x1 = "";
            x2 = "";
            cat = "";
            d0 = "";
            
            while(myReader.hasNextLine())
            {
                tok=new StringTokenizer(myReader.nextLine(),";");
                x0 = tok.nextToken();
                x1 = tok.nextToken();
                x2 = tok.nextToken();
                cat = tok.nextToken();
                d0 = tok.nextToken();
                this.allAchieved.add(new Achievement(x0,x1,x2,cat,d0));
                i++;
            }
            
            myReader.close();
        }       
        catch (Exception e) {
            System.out.println("Line location"+i);
         }  
    }

}


