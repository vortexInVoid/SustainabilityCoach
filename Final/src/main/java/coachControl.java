import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.EOFException;
import java.io.File;
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
import javax.imageio.ImageIO;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.*;
import java.util.Scanner;

public class coachControl
{
    private ArrayList<JLabel> badgeLabels;
    private Player playing = new Player("Default", "Default", "0", 0);
    private boolean isValidLogIn;

    private ArrayList<Player> allPlayers;

    private ArrayList<String> tips;
    private ArrayList<String> news;
    private JFrame signFrame, frame;
    
    
    public coachControl()
    {
        badgeLabels = new ArrayList<JLabel>( );
        allPlayers = new ArrayList<Player>( );
        loadPlayerDataFrom();
        createTips();
        
    }
    
    public void addBadgeLabels(ArrayList<JLabel> badgeLabels)
    {
        this.badgeLabels = badgeLabels;
    }
    
    public void manageBadges( )
    {
        playing.changeBadge(0, true);
        playing.changeBadge(1, true);
        playing.changeBadge(5, true);
    }
    
    public void utilizeLogIn( )
    {
        if(getIsValidLogIn())
        {
            signFrame.setVisible(false);
            frame.setVisible(true);
        }
        else
        {
            signFrame.setVisible(true);
            frame.setVisible(false);        
        }
    }
    
    public void utilizeLogOut( )
    {
        signFrame.setVisible(true);
        frame.setVisible(false);       
    }
    
    public void frameStart( )
    {
      frame = new JFrame ("Layout Manager Demo");
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

      JTabbedPane tp = new JTabbedPane();
      
      tp.addTab ("Achivements", new TreePanel(this));
      tp.addTab ("Profile", new Profile(this));
      tp.addTab("News", new News(this));
      
      frame.setVisible(false);
      frame.getContentPane().add(tp);
      frame.pack();
      
      signFrame = new JFrame( "Sign in/Sign up" );
      
      signFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
      
      JTabbedPane tpTwo = new JTabbedPane();
      tpTwo.addTab("Sign In", new SignIn(this));
      tpTwo.addTab("Sign Up", new SignUp(this));
      
      signFrame.setVisible(true);
      signFrame.getContentPane().add(tpTwo);
      signFrame.pack();
    }
    
    public static BufferedImage scale(BufferedImage src, int w, int h)
    {
        BufferedImage img = 
                new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        int x, y;
        int ww = src.getWidth();
        int hh = src.getHeight();
        int[] ys = new int[h];
        for (y = 0; y < h; y++)
            ys[y] = y * hh / h;
        for (x = 0; x < w; x++) {
            int newX = x * ww / w;
            for (y = 0; y < h; y++) {
                int col = src.getRGB(newX, ys[y]);
                img.setRGB(x, y, col);
            }
        }
        return img;
    }
    
    public void addProfileImage(JLabel profileLabel)
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose Your File");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnval = fileChooser.showOpenDialog(null);
        if (returnval == JFileChooser.APPROVE_OPTION)
        {
            File file = fileChooser.getSelectedFile();
            BufferedImage bi;
             try 
             {
                 bi = ImageIO.read(file);
                 bi = scale(bi, 186, 209);
                 profileLabel.setIcon(new ImageIcon(bi));
                 File outputfile = new File("image.jpg");
                 ImageIO.write(bi, "jpg", outputfile);
             }
             catch(IOException e) 
             {
                e.printStackTrace(); // todo: implement proper error handeling
             }
        }
    }
    
    public boolean getIsValidLogIn( )
    {
        return isValidLogIn;
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
        return null;
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

    public void updatePlayerProfile(JTextField nameSurname)
    {
        playing.setName_Surname(nameSurname.getText());
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
    
    public void validateLogin(JTextField name, JPasswordField password)
    {
        isValidLogIn = false;
        if( !(allPlayers.size( ) == 0) )
        {
            for(Player aPlayer : this.allPlayers)
            {
                if(name.getText().equals(aPlayer.getUsername()) 
                && password.getText().equals(aPlayer.getPassword()))
                {
                    isValidLogIn = true;
                    playing = aPlayer;
                    manageBadges( );
                    break;
                }
            }             
        } 
    }

    public boolean searchPlayer(String nickName)
    {
        for(Player p: this.allPlayers)
        {
            if(nickName.equals(p.getUsername()))
            {
                return true;
            }       
        }
        return false;
    }

    
    public void registerPlayer(JTextField nameSurname, JTextField userName, JPasswordField pas1, JPasswordField pas2, JComboBox comBox, JLabel info)
    {
        if( (allPlayers.size() == 0) || (pas1.getText().equals(pas2.getText()) && !searchPlayer(userName.getText())))
        {
            Player newPlayer =   new Player(nameSurname.getText( ),userName.getText( ), 
                    pas1.getText(), comBox.getSelectedIndex());
            allPlayers.add( newPlayer );
            newPlayer.populateBages(badgeLabels);
        }
        else
        {
            info.setText( "Invalid. Try again.");
        }
    }
    
    public void loadAchievement(String AchiName,JTextArea des, JTextArea input, JTextField numeric, JLabel name)
    {
        Achievement p  = getAchievement(AchiName);
        name.setText(p.getName());
        des.setText(p.getPlayerNote());
    }

    protected void createTips( )
    {
        try
        {
            File myObj = new File("Coachtips.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              this.tips.add(data);
            }
        }
        catch(FileNotFoundException e)
        {
        }
    }

    public void loadNews()
    {

    }

    public void loadPlayerDataFrom()
    {
        boolean endOfFile = false;
        try
        {
            FileInputStream file = new FileInputStream("Players.dat");
            ObjectInputStream inputFile = new ObjectInputStream(file);



            while(!endOfFile)
            {

                    this.allPlayers.add((Player) inputFile.readObject());
            }

            inputFile.close();

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
    
    /*
    public void changePopUpInfo(JComboBox box,)
    {
        
*/

}
