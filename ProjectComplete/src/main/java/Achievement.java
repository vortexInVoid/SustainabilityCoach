import java.util.ArrayList;


public class Achievement{

   private String name;
   private String playerNote;
   private String type;

   private double InterractionNumber;

   private boolean isCompleted;
   private double achivementNumericConstant;
   
   private ArrayList<Stamp> allInputs = new ArrayList<Stamp>();

   public Achievement(String name, String playerNote, String type, boolean isCompleted){
    setName(name);
    setPlayerNote(playerNote);
    setIsCompleted(false);
    setType(type);
   }

      public void setName(String name){
       this.name = name;
    }

    public  void setPlayerNote(String playerNote){
      this.playerNote = playerNote;
     }

     public  void setType(String type){
      this.type=type;
     }

     public void setInterractionNumber(double InterractionNumber){
      this.InterractionNumber = InterractionNumber;
     }

     public void setIsCompleted(boolean isCompleted){
      this.isCompleted = isCompleted;
     }

     public String getName(){
      return this.name ;
   }

   public  String getPlayerNote(){
     return this.playerNote;
    }

    public  String getType(String type){
     return this.type;
    }

    public double getName(double InterractionNumber){
     return this.InterractionNumber;
    }

    public boolean getName(boolean isCompleted){
     return this.isCompleted ;
    }

    public void addInteraction(Stamp x){
      allInputs.add(x);
    }

    public void deleteInteraction(Stamp x){
      allInputs.remove(x);
    }

    public void calculatePlayerInteractionNUmber(){
      this.InterractionNumber = allInputs.size();
    }

    public ArrayList<Stamp> returnAllStamps()
    {
      return this.allInputs;
    }

    public void setAchievementNumericCons(double cons)
    {
      this.achivementNumericConstant = cons;
    }

    public double getNumericCons()
    {
      return this.achivementNumericConstant;
    }
}