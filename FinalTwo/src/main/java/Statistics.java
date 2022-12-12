import java.time.temporal.ChronoUnit;

public class Statistics {
    
    static double pi;
    static double degreeOfFreedom;
    static double variance;
    static double fuzinessConstant;

    public Statistics(double a0,double a1,double a2,double a3)
    {
        setPi(a0);
        setDegreeOfFreedom(a1);
        setVariance(a2);
        setFuzinessCons(a3);
    }

    public void setPi(double piCos)
    {
        pi = piCos;
    }

    public void setDegreeOfFreedom(double freedom)
    {
        degreeOfFreedom = freedom;
    }

    public void setVariance(double var)
    {
        variance = var;
    }

    public void setFuzinessCons(double fuzy)
    {
        fuzinessConstant = fuzy;
    }

    public static double calculateStringScore(Player p)
    {
        double score = 0;

        for(Achievement elem : p.getAllAchievements())
        {
            for(Stamp elem0 : elem.returnAllStamps())
            {
                score += elem0.getString().length();
            }
        }
        return score;
    }

    public static double calculateNumericScore(Player p)
    {
        double score = 0;

        for(Achievement elem : p.getAllAchievements())
        {
            for(Stamp elem0 : elem.returnAllStamps())
            {
                score += elem0.getNumeric()*elem.getNumericCons();
            }
        }
        return score;
    }
    /*
    public static double calculateHabitScore(Player p)
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
    }
        */
}
