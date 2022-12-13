import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

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

    public static double calculatePenalty(Player p)
    {
        double score = 0;

        for(Achievement elem : p.getAllAchievements())
        {
            for(Stamp elem0 : elem.returnAllStamps())
            {
                if(elem0.getNumeric()*elem.getNumericCons() < 0)
                {
                score += elem0.getNumeric()*elem.getNumericCons();
                }
            }
        }
        return score;
    }

    
    public static double calculateHabitScore(Player p)
    {
        ArrayList<Long> diff = new ArrayList<Long>();
        long dummy;
        
        for(Achievement achi : p.getAllAchievements() )
        {
            for(int i = 0;i < achi.returnAllStamps().size()-1;i++ )
            {
                dummy = ChronoUnit.MINUTES.between(achi.returnAllStamps().get(i+1).getDate(),achi.returnAllStamps().get(i).getDate());
                diff.add(dummy);
            }
        }
        
        double sum = 0.0, standardDeviation = 0.0;
        int length = diff.size();

        for(double num : diff) {
            sum += num;
        }

        double mean = sum/length;

        for(double num: diff) {
            standardDeviation += Math.pow(num - mean, 2);
        }

        return 100/(1+Math.sqrt(standardDeviation/length));
        
    }

    
}
