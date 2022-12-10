import java.util.ArrayList;

/**
 * Player
 */
public class Player {

    private String id;
    private String name;
    private String surname;
    private String nickyName;

    private String leaguePlayer; // May make as an object
    private String location; // May make as an object
    private String status; // May make as an object

    private String password;
    private ArrayList<Achievement> allAchieved;

    private playerNumericStats numericStats;

    public Player(String nameSurname)
    {
        setPlayerName(nameSurname.substring(0,nameSurname.indexOf(" ")));
        setPlayerSurname(nameSurname.substring(nameSurname.indexOf(" ")+1, nameSurname.length()));
        this.numericStats = new playerNumericStats(id);
        this.allAchieved = new ArrayList<Achievement>();
    }

    public void setPlayerName(String name)
    {
        this.name = name;
    }

    public void setPlayerSurname(String surname)
    {
        this.surname = surname;
    }

    public void setPlayerNickyName(String nickname)
    {
        this.nickyName = nickname;
    }

    public void setPlayerLeague(String league)
    {
        this.leaguePlayer = league;
    }

    public void setPlayerLocation(String geo)
    {
        this.location = geo;
    }

    public void setPlayerStatus(String status)
    {
        this.status = status;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getName()
    {
        return this.name;
    }

    public String getSurname()
    {
        return this.surname;
    }

    public String getNickName()
    {
        return this.nickyName;
    }

    public String getLeaguePlayer()
    {
        return this.leaguePlayer;
    }

    public String getLocation()
    {
        return this.location;
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

    protected void setID()
    {

    }

    protected String getID()
    {
        return this.id;
    }

    protected void deleteAchievement(String name)
    {
        for(Achievement elem : this.allAchieved)
        {
            if(elem.getName.equals(name))
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
    }


}


