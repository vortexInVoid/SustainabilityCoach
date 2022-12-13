import java.time.LocalDate;

public class Stamp {
    
    private LocalDate interreactionDate;
    private String playerReponse;
    private double playerResponseDegree;

    public Stamp(String playerResponse, double playerResponseDegree)
    {
        LocalDate date = LocalDate.now();
        this.interreactionDate = date;
        
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
    
    public LocalDate getDate()
    {
        return this.interreactionDate;
    }

}
