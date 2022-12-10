import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class coachControl
{

    private Player playing;
    private ArrayList<Player> allPlayers;
    private ArrayList<String> tips;
    private ArrayList<String> forumNews;
    
    public coachControl()
    {

    }

    public void loadProfile(JTextField nameSurname, JTextField score, JTextField penalty, JTextField consistency, JTextField status)
    {
        nameSurname.setText(playing.getName() + playing.getSurname());
        score.setText(playing.getScore());
        penalty.setText(playing.getPenalty());
        consistency.setText(playing.getConsistency());
        status.setText(playing.getStatus());
    }

    public void updatePlayerProfile(JTextField nameSurname, JCheckBox)
    {
        playing.setPlayerSur_Name(nameSurname.getText());
    }

    public void giveRandomTip(JTextArea coachTip)
    {
        coachTip.
    }

    public void loadFirstFiveChampions(JTextField r1,JTextField r2,JTextField r3,JTextField r4,JTextField r5)
    {
        r1.setText(this.allPlayers.get(0).getName());
        r2.setText(this.allPlayers.get(1).getName());
        r3.setText(this.allPlayers.get(2).getName());
        r4.setText(this.allPlayers.get(3).getName());
        r5.setText(this.allPlayers.get(4).getName());
    }

    public void loadLastFiveChampions(JTextField r6,JTextField r7,JTextField r8,JTextField r9,JTextField r10)
    {
        r6.setText(this.allPlayers.get(5).getName());
        r7.setText(this.allPlayers.get(6).getName());
        r8.setText(this.allPlayers.get(7).getName());
        r9.setText(this.allPlayers.get(8).getName());
        r10.setText(this.allPlayers.get(9).getName());
    }

    public void stampInput(String input,double inputNumeric,String achiving)
    {
        Achievement stamping = playing.getAchievement(achiving);
        stamping.addInteraction(new Stamp(input, inputNumeric));
    }

    public void CalculateAllScores()
    {
        for(Player p: this.allPlayers)
        {
            Statistics.calculateAllScore(p);
        }
    }



}
