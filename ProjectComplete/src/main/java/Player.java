import java.util.ArrayList;

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

    private double stringScore;
    private double numericScore;
    private double consistencyScore;

    private double TotalScore;


    public Player(String nameSurname, String userName, String password, int gender)
    {
        //Name-Surname
        setName_Surname(nameSurname);
        setUsername(userName);
        setPassword(password);
        setGender(gender);

        this.stringScore = 0;
        this.consistencyScore = 0;
        this.numericScore = 0;
        computeTotalScore();
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
        //this.consistencyScore = Statistics.calculateHabitScore(this);
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
    /*
    public void initializeAchievement()
    {


        for(int i = 0; i < 27;i++)
        {
            this.allAchieved.add(new Achievement(nickyName, name, id, false));
        }
    }
    */

}


