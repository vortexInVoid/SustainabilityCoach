import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class coachControl
{

    private Player playing;

    private ArrayList<Player> allPlayers;

    private ArrayList<String> tips;
    private ArrayList<String> news;
    
    public coachControl()
    {
        loadPlayerDataFrom();
    }

    public Achievement getAchievement(String name)
    {
        for(Achievement x: this.playing.getAllAchievements())
        {
            if(x.getName().equalsIgnoreCase(name))
            {
                return x;
            }
        }
    }

    // penalty holds the negative score
    public void loadProfile(JTextField nameSurname,JTextField userName, JTextField score, JTextField penalty, JTextField consistency, JTextField status)
    {
        nameSurname.setText(playing.getName_Surname());
        userName.setText(playing.getUsername());
        score.setText(Double.toString(playing.returnTotalScore()));
        consistency.setText(Double.toString(playing.returnHabitScore()));
        status.setText(playing.getStatus());
    }

    public void updatePlayerProfile(JTextField nameSurname, JCheckBox)
    {
        playing.setPlayerSur_Name(nameSurname.getText());
    }

    public void giveRandomTip(JTextArea coachTip)
    {
        Random rand = new Random();
        coachTip.setText(tips.get(rand.nextInt(this.tips.size())));
    }

    public void loadFirstFiveChampions(JTextField r1,JTextField r2,JTextField r3,JTextField r4,JTextField r5)
    {
        this.allPlayers.sort(null);

        r1.setText(this.allPlayers.get(0).getUsername());
        r2.setText(this.allPlayers.get(1).getUsername());
        r3.setText(this.allPlayers.get(2).getUsername());
        r4.setText(this.allPlayers.get(3).getUsername());
        r5.setText(this.allPlayers.get(4).getUsername());
    }

    public void loadLastFiveChampions(JTextField r6,JTextField r7,JTextField r8,JTextField r9,JTextField r10)
    {
        this.allPlayers.sort(null);

        r6.setText(this.allPlayers.get(5).getUsername());
        r7.setText(this.allPlayers.get(6).getUsername());
        r8.setText(this.allPlayers.get(7).getUsername());
        r9.setText(this.allPlayers.get(8).getUsername());
        r10.setText(this.allPlayers.get(9).getUsername());
    }

    public void stampInput(String input,double inputNumeric,String achiving)
    {
        Achievement stamping = playing.getAchievement(achiving);
        stamping.addInteraction(new Stamp(input, inputNumeric));
    }

    public void calculateAllPlayerScores()
    {
        for(Player p: this.allPlayers)
        {
           p.calculateAllScores();
        }
    }

    public boolean validateLogin()
    {

    }

    public void registerPlayer()
    {

    }

    public void loadAchievement(String AchiName,JTextArea des, JTextArea input, JTextField numeric, JLabel name)
    {
        Achievement p  = getAchievement(AchiName);
        name.setText(p.getName());
        des.setText(p.getPlayerNote());
    }

    public void loadTips()
    {

    }

    public void loadNews()
    {

    }

    public void loadPlayerDataFrom()
    {
        FileInputStream file = new FileInputStream("Players.dat");
        ObjectInputStream inputFile = new ObjectInputStream(file);

        boolean endOfFile = false;

        while(!endOfFile)
        {
            try
            {
                this.allPlayers.add((Player) inputFile.readObject());
            }
            catch (EOFException e)
            {
                endOfFile = true;
            }
            catch (Exception f)
            {
                //fill
            }
        }
        inputFile.close();
    }

    public void loadPlayerDataTo()
    {
        try
        {
            FileOutputStream file = new FileOutputStream("Players.dat");
            ObjectOutputStream outputFile = new ObjectOutputStream(file);

            for(int i= 0; i < this.allPlayers.size();i++)
            {
                outputFile.writeObject(this.allPlayers.get(i));
            }

            outputFile.close();

            // show success message
        }
        catch (IOException e)
        {
            // show message
        }
    }

}
