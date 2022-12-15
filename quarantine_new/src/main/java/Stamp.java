import java.time.LocalDateTime;

public class Stamp implements java.io.Serializable{
    
    private String interreactionDate;
    private String playerReponse;
    private double playerResponseDegree;

    public Stamp(String playerResponse, double playerResponseDegree)
    {
        LocalDateTime date = LocalDateTime.now();
        this.interreactionDate = date.toString();
        
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
    
    public LocalDateTime getDate()
    {
        return LocalDateTime.parse(interreactionDate);
    }

}
