
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
/**
 * playerNumericStats
 */
public class playerNumericStats {

    //For identification
    private String id;

    //Forum Scoring
    private double forumScore;
    private ArrayList<String> forumContent = new java.util.ArrayList<String>();
    private ArrayList<LocalDate> forumContentDate = new java.util.ArrayList<LocalDate>();

    //Game Scoring
    private double gameScore;
    //Penalty for Anomaly Detection
    private double penaltyNumber;
    //The number that means how consistent is the player
    private double consistency;

    public playerNumericStats(String id)
    {
        this.id = id;
    }

    public void reCalculateStats()
    {
        calculateForumScore();
    }

    private void calculateForumScore()
    {
        double dummy = 0;
        int i = 1;

        long epochRange = ChronoUnit.DAYS.between(forumContentDate.get(0), forumContentDate.get(forumContentDate.size()-1));
        long Dy;
        long Dx;

        // Assuming forumContent date is sorted with oldest starting from index 0
        // Neglects initial content as initial may be too noisy

        while(i < this.forumContentDate.size() - 1) // -2 as Dy/dx = (dy1-dy2)/(dx1-dx2)
        {
            Dx = ChronoUnit.DAYS.between(forumContentDate.get(i), forumContentDate.get(i+1));
            Dy = forumContent.get(i).length();

            dummy += Dy*(Dx/epochRange);
        }
        this.forumScore = dummy;
    }

    public double getForumScore()
    {
        return this.forumScore;
    }
}