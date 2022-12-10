
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

    public static double getPlayerScore(Player thing)
    {
        double score = 0;
        for(Achievement elem : thing.getAllAchievements())
        {
            for(Stamp elem0 : elem.getAllStamps())
            {
                score += elem0.getNumeric();
            }
        }
    }

    public static double calculateStringScore(Player thing)
    {
        double score = 0;

        for(Achievement elem : thing.getAllAchievements())
        {
            for(Stamp elem0 : elem.returnAllStamps())
            {
                score += elem0.getString().length();
            }
        }
        return score;
    }

}
