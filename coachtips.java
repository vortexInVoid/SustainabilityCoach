import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class coachtips{
    protected ArrayList<String> CreateTips(String filename,  ArrayList<String> Tips) throws FileNotFoundException{
        File myObj = new File("Coachtips.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
          String data = myReader.nextLine();
          Tips.add(data);
        }
        return Tips;
    }
}