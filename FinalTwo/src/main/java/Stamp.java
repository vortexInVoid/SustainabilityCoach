import java.util.Date;

public class Stamp {
    
    private Date interreactionDate;
    private String playerReponse;
    private double playerResponseDegree;

    public Stamp(String playerResponse, double playerResponseDegree)
    {
        this.interreactionDate.getTime();
        this.playerReponse = playerResponse;
        this.playerResponseDegree = playerResponseDegree;
    }
    
    public String getString()
    {
        return this.playerReponse;
    }

    public double getNumeric()
    {
        return this.playerResponseDegree;
    }

}
