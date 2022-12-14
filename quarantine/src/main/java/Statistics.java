import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.time.LocalDate;

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
        double re;
        
        for(Achievement achi : p.getAllAchievements() )
        {
            for(int i = 0;i < achi.returnAllStamps().size()-1;i++ )
            {
                if(achi.returnAllStamps().size()>1)
                {
                dummy = ChronoUnit.SECONDS.between(achi.returnAllStamps().get(i).getDate(),achi.returnAllStamps().get(i+1).getDate());
                diff.add(dummy);
                System.out.println("\nDUMMY " + dummy);
                }
            }
        }
        
        double sum = 0.0, standardDeviation = 0.0;
        int length = diff.size();

        if(length > 0)
        {
        for(double num : diff) {
            sum += num;
        }

        double mean = sum/length;

        for(double num: diff) {
            standardDeviation += Math.pow(num - mean, 2);
        }
        
        double expression = (double)(1+Math.sqrt(standardDeviation/length));
        re = (100/Math.sqrt(expression));
        return (double) Math.round(re);
        }
        return 100;  
    }

    
}
