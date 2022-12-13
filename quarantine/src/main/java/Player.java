import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.JLabel;

/**
 * Player
 */
public class Player {

    private String name_surname;
    private String userName;
    private String password;
    

    // 0-man 1-female 2-other
    private int gender;
   

    // Bilkent unsatisfactory-satis-honour-highhonour
    private String status;

    private ArrayList<Achievement> allAchieved;
    private ArrayList<Badge> badges;
    private final int NUMBER_OF_BADGES = 10;

    private double stringScore;
    private double numericScore;
    private double consistencyScore;
    private double penalty;

    private double TotalScore;


    public Player(String nameSurname, String userName, String password, int gender)
    {
        badges = new ArrayList<Badge>( );
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

    public void populateBages(ArrayList<JLabel> labels)
    {
        for(int i = 1; i <= NUMBER_OF_BADGES; i++)
        {
            badges.add(new Badge(""+i, false, labels.get(i-1)));
        }
    }
       
    public void changeBadge(int index, boolean isComplete)
    {
        badges.get(index).setIsComplete(isComplete);
        badges.get(index).refreshVisiblity( );
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


