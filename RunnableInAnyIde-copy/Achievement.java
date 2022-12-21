import java.util.ArrayList;


public class Achievement implements java.io.Serializable,Comparable<Achievement>{

   private String name;
   private String playerNote;
   private String task;
   private String type;

   private boolean isCompleted;
   private double achivementNumericConstant;
   
   private ArrayList<Stamp> allInputs = new ArrayList<Stamp>();

   public Achievement(String name, String playerNote,String task, String type,String coef){
    setName(name);
    setPlayerNote(playerNote);
    setIsCompleted(false);
    setType(type);
    
    this.task = task;
    this.achivementNumericConstant = Double.parseDouble(coef);
   }

   public int compareTo(Achievement a)
   {
    if((this.allInputs.size() > 0) && (a.allInputs.size() > 0))
    {
      return this.allInputs.get(0).getDate().compareTo(a.allInputs.get(0).getDate());  
    }
    else
    {
        return 0;
    }
    
   }

      public void setName(String name){
       this.name = name;
    }
      
    public String getTask()
    {
        return this.task;
    }
      
    public boolean getIsComplete( )
    {
        return isCompleted;
    }
    

    public  void setPlayerNote(String playerNote){
      this.playerNote = playerNote;
     }

     public  void setType(String type){
      this.type=type;
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

    public boolean getName(boolean isCompleted){
     return this.isCompleted ;
    }

    public void addInteraction(Stamp x){
      allInputs.add(x);
    }

    public void deleteInteraction(Stamp x){
      allInputs.remove(x);
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