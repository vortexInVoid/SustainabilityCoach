import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class coachControl
{
    private ArrayList<JLabel> badgeLabels;
    private JLabel pictureLabel;
    private Player playing = new Player("a", "a", "a", 2);
    private boolean isValidLogIn;
    public int subLevel,level;
    private JFrame infoPage;

    private ArrayList<Player> allPlayers;

    private ArrayList<String> tips;
    private ArrayList<String> news;

    private JFrame signFrame, frame;
    public TreePanel theTreePanel;
    private Profile theProfile;
    private News theNews;

    private String[][] treeMessages = new String[8][3];
    
    public coachControl()
    {

        infoPage = new UserInfoPage("user-guide.txt");
        infoPage.setVisible(false);
        
        loadAchivementTitles();

        badgeLabels = new ArrayList<JLabel>( );
        allPlayers = new ArrayList<Player>( );
        tips = new ArrayList<String>();
        
        createTips();
        
        
        theTreePanel =  new TreePanel(this);
        theProfile = new Profile(this);
        theNews = new News(this);
        frameStart( );
        
        //Delete
        //this.allPlayers.add(playing);
        //playing.populateBages( );
        //
        loadPlayerDataFrom( );
        System.out.println();
    }
    
    public JFrame getInfoPage( )
    {
        return infoPage;
    }
    
    
    public Player getPlayer( )
    {
        return playing;
    }
    
    public String[][] returnMessage()
    {
        return this.treeMessages;
    }
    
    public void addBadgeLabels(ArrayList<JLabel> badgeLabels)
    {
        this.badgeLabels = badgeLabels;
    }
    
    public void addPicLabel(JLabel pictureLabel)
    {
        this.pictureLabel = pictureLabel;
    }
    
    public void refreshProfilePicture( )
    {
        pictureLabel.setIcon(new ImageIcon(playing.getProfilePic()));
    }
    
    /*
Healing earth;Finish 3 achievements
Real Adventurer;Finish 6 achievements
Savoiour of the green;Finish 12 achievements
Real warrior;Finish 18 achievements
Plant lover;Finish 24 achievements
Endemic flower;Earn 5.000 points in a week
Surfer of the Ocean;20.000 points in a week
Flying Hero;Do not lose points in a week
Cool Dude;Finish an achievement every day for a week
Noble flamingo;Earn 1.000 points every day for a week
    */
    public void refreshBadges( )
    {
        for(int i = 0; i < 10; i++)
        {
            badgeLabels.get(i).setVisible(playing.getBadgeInfo()[i]);
        }
    }
   
    public void manageBadges( )
    {
        double score = playing.returnTotalScore();
        ArrayList<Achievement> achivements = playing.getAllAchievements();
        int pointer = 0;
        
        //ArrayList<LocalDateTime> stampOntheGround = new ArrayList<LocalDateTime>();
        //ArrayList<Double> scoreTime = new ArrayList<Double>();
        
        
        
        int count = 0;
        for(Achievement anAch : achivements)
        {
            if(anAch.getIsComplete())
            {
                count++;
                //stampOntheGround.add(anAch.returnAllStamps().get(0).getDate());
            }
        }
        
        //Collections.sort(stampOntheGround);
        
        /*
        for(int i = 0; i < stampOntheGround.size()-1; i++)
        {
            //Change minutes to DAYS
            if(ChronoUnit.MINUTES.between(stampOntheGround.get(i),stampOntheGround.get(i+1)) ==1)
            {
                pointer++;
            }
            else
            {
                pointer = 0;
            }
        }
        */
        
    
        
        if(count > 2 && !playing.checkBadge(4))
        {
            playing.changeBadge(4, true);
            JOptionPane.showMessageDialog(frame, "Badge: Healing earth.");
            badgeLabels.get(4).setVisible(true);
            
        }
        if(count > 5 && !playing.checkBadge(5))
        {
            playing.changeBadge(5, true);
            JOptionPane.showMessageDialog(frame, "Badge: Real Adventurer.");
            badgeLabels.get(5).setVisible(true);
        }
        if(count > 11 && !playing.checkBadge(6))
        {
            playing.changeBadge(6, true);
            JOptionPane.showMessageDialog(frame, "Badge: Savoiour of the green.");
            badgeLabels.get(6).setVisible(true);
        }
        if(count > 17 && !playing.checkBadge(7))
        {
            playing.changeBadge(7, true);
            JOptionPane.showMessageDialog(frame, "Badge: Real warrior.");
            badgeLabels.get(7).setVisible(true);
        }        
        if(count > 23 && !playing.checkBadge(8))
        {
            playing.changeBadge(8, true);
            JOptionPane.showMessageDialog(frame, "Badge: Plant lover.");
            badgeLabels.get(8).setVisible(true);
        }
        if(score > 5000 && !playing.checkBadge(9))
        {
            playing.changeBadge(9, true);
            JOptionPane.showMessageDialog(frame, "Badge: Endemic flower.");
            badgeLabels.get(9).setVisible(true);
        }
        if(score > 20000 && !playing.checkBadge(0))
        {
            playing.changeBadge(0, true);
            JOptionPane.showMessageDialog(frame, "Badge: Surfer of the Ocean.");
            badgeLabels.get(0).setVisible(true);
        }
        
        for(boolean badgeInfo : playing.getBadgeInfo() )
        {
            System.out.println(badgeInfo);
        }
        /*
        if( //condition &&
                !playing.checkBadge(1))
        {
            playing.changeBadge(1, true);
            JOptionPane.showMessageDialog(frame, "Badge: Flying Hero.");            
        }
        if( //condition &&
                !playing.checkBadge(2))
        {
            playing.changeBadge(2, true);
            JOptionPane.showMessageDialog(frame, "Badge: Cool Dude.");            
        }       
        if( //condition &&
                !playing.checkBadge(3))
        {
            playing.changeBadge(3, true);
            JOptionPane.showMessageDialog(frame, "Badge: Noble flamingo.");            
        }    
        */
    }
    
    public void loadProfile(JTextField name,JComboBox gender,JTextField score,JTextField pen,JTextField cons,JTextField stat)
    {
        name.setText(playing.getName_Surname());
        gender.setSelectedIndex(playing.getGender());
        score.setText(Double.toString(playing.returnTotalScore()));
        cons.setText(Double.toString(playing.returnHabitScore()));
        pen.setText(Double.toString(playing.returnPenalty()));
        stat.setText(playing.getStatus() );
    }
    
    public void utilizeLogIn( )
    {
        if(getIsValidLogIn())
        {
            signFrame.setVisible(false);
            frame.setVisible(true);
            theProfile.updateInfo();
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
      frame = new JFrame ("Sustainability Coach");
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

      JTabbedPane tp = new JTabbedPane();
      tp.addTab ("Achivements",theTreePanel);
      tp.addTab ("Profile", theProfile);
      tp.addTab("News", theNews);
      
      //Make FALSE!!!!!!!!!!!!!!!
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
      
      loadAchivementTitles( );
      theTreePanel.changeMessageMatrix(treeMessages);
      /*
      theTreePanel.showProgress( 0, 1, 'c' );
      theTreePanel.showProgress( 0, 0, 'c' );
      theTreePanel.showProgress( 0, 1, 'c' );
      theTreePanel.showProgress( 1, 0, 'c' );
      theTreePanel.showProgress( 3, 0, 'c' );
      theTreePanel.showProgress( 3, 1, 'c' );
      theTreePanel.showProgress( 3, 2, 'c' );
      */
      frame.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
            loadPlayerDataTo();
            System.out.println("Upload complete");
        }
    });
      
    }
    
    public void loadAchivementTitles( )
    {
        try {
            File myObj = new File("project_achivements.txt");
            Scanner myReader = new Scanner(myObj);
            for(int i = 0; i < 8; i++) 
            {
                for(int j = 0; j < 3 && myReader.hasNextLine(); j++)
                {
                    String data = myReader.nextLine();
                    StringTokenizer st = new StringTokenizer(data, ";");             
                    treeMessages[i][j] = st.nextToken();
                }
                
            }
            myReader.close();
        }       
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
         }  
    }

    public Achievement getAchievementCurrent(String achievement)
    {
        for(int i = 0; i < 8; i++) 
            {
                for(int j = 0; j < 3; j++)
                {            
                    if(treeMessages[i][j].equals(achievement)) {return playing.getAchievement(achievement);};
                }
                
            }
            return null;
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

    public void updatePlayerProfile(JTextField nameSurname)
    {
        playing.setName_Surname(nameSurname.getText());
    }

    public void giveRandomTip(JTextArea coachTip)
    {
        coachTip.setText("");
        Random rand = new Random();
        
        String n0,n1,n2;
        
        n0 = tips.get(rand.nextInt(this.tips.size()));
        n1 = tips.get(rand.nextInt(this.tips.size()));
        n2 = tips.get(rand.nextInt(this.tips.size()));
        
        if(!(n0.equals(n1)&&n0.equals(n2)&&n1.equals(n2)))
        {

            coachTip.append(n0);
            coachTip.append("\n");
            coachTip.append(n1);
            coachTip.append("\n");
            coachTip.append(n2);
        }
        else
        {
            giveRandomTip(coachTip);
        }
    }

    public void loadFirstFiveChampions(JTextField r1,JTextField r2,JTextField r3,JTextField r4,JTextField r5)
    {
        //this.allPlayers.sort(null);

        if(this.allPlayers.size() >9)
        {
        r1.setText(this.allPlayers.get(0).getUsername());
        r2.setText(this.allPlayers.get(1).getUsername());
        r3.setText(this.allPlayers.get(2).getUsername());
        r4.setText(this.allPlayers.get(3).getUsername());
        r5.setText(this.allPlayers.get(4).getUsername());   
        }
    }

    public void loadLastFiveChampions(JTextField r6,JTextField r7,JTextField r8,JTextField r9,JTextField r10)
    {
        //this.allPlayers.sort(null);

        if(this.allPlayers.size() >9)
        {
        r6.setText(this.allPlayers.get(5).getUsername());
        r7.setText(this.allPlayers.get(6).getUsername());
        r8.setText(this.allPlayers.get(7).getUsername());
        r9.setText(this.allPlayers.get(8).getUsername());
        r10.setText(this.allPlayers.get(9).getUsername());
        }
    }
    
    public boolean isNumeric(String str) {
    if (str == null) {
        return false;
    }
    try {
        Double.parseDouble(str);
        return true;
    } catch (NumberFormatException nfe) {
        return false;
    }
}

    public void stampInput(String AchiName, JTextField numeric,JTextArea input,JLabel message)
    {
        String res = input.getText();
        String num = numeric.getText(); 
        
        if(isNumeric(num))
        {
        Achievement stamping = playing.getAchievement(AchiName);
        playing.getAchievement(AchiName).setIsCompleted(true);
        theTreePanel.showProgress( this.level, this.subLevel+1, 'c' );
        stamping.addInteraction(new Stamp(res,Double.parseDouble(num)));
        playing.calculateAllScores();
        this.theProfile.updateInfo();
        stamping.setIsCompleted(true);
        manageBadges( );
        message.setText("Time stamped your habit!");
        }
    }
    
    public void reloadAchi()
    {
        for(int i = 0; i < 8;i++)
        {
            for(int k = 0; k < 3;k++)
            {
                if(this.getAchievement(this.treeMessages[i][k]).getIsComplete())
                {
                    theTreePanel.showProgress( i, k+1, 'c' );
                }
                else
                {
                    theTreePanel.showProgress( i, k+1, 'e' );
                }
            }
        }
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
        String log = new String(password.getPassword()).trim();
        if( !(allPlayers.size( ) == 0) )
        {
            for(Player aPlayer : this.allPlayers)
            {
                if(name.getText().trim().equals(aPlayer.getUsername()) && log.equals(aPlayer.getPassword().trim()))
                {
                    isValidLogIn = true;
                    this.playing = aPlayer;
                    //this.playing.populateBages( );
                    refreshProfilePicture( );
                    manageBadges();
                    reloadAchi();
                    refreshBadges();
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
        String pass1,pass2,userStr;
        pass1 = new String(pas1.getPassword()).trim();
        pass2 = new String(pas2.getPassword()).trim();

        userStr = (userName.getText());
        int selected = comBox.getSelectedIndex();

        if(!searchPlayer(userStr))
        {
            if((pass1.equals(pass2)))
            {
            Player newPlayer = new Player(nameSurname.getText().trim(),userName.getText().trim(),pass1, selected);
            allPlayers.add(newPlayer);
            newPlayer.populateBages( );
            info.setText("Successfully registered!");
            validateLogin(userName, pas1);
            utilizeLogIn( );
            infoPage.setVisible(true);
            
            }
            else
            {
                info.setText("Passwords do not match! Try again");
                pas1.setText("");
                pas2.setText("");
            }
        }
        else
        {
            info.setText( "A player already exists! Please do MNEMONICS to remember it!");
        }
    }
    
    public void loadAchievement(String AchiName,JTextArea des, JLabel name)
    {
        Achievement p  = playing.getAchievement(AchiName);
        name.setText(p.getName());
        des.setText(p.getPlayerNote());
        des.append("\n");
        des.append("********************************");
        des.append("\n");
        des.append(p.getTask());
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
        /*
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
        */ 
        /*
        InputStream fileIs = null;
        ObjectInputStream objIs = null;
        boolean endOfFile = false;
        
        try {
            fileIs = new FileInputStream("Players.txt");
            objIs = new ObjectInputStream(fileIs);
            
            while(!endOfFile)
            {

                    this.allPlayers.add((Player) objIs.readObject());
            }
        }catch (EOFException e)
        {
            endOfFile = true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        */
        InputStream fileIs = null;
        ObjectInputStream objIs = null;
        try {
            fileIs = new FileInputStream("EmpFile.txt");
            objIs = new ObjectInputStream(fileIs);
            Player emp = (Player) objIs.readObject();
            System.out.println(emp.getName_Surname());
            emp.setProfilePic();
            //emp.populateBages(badgeLabels);
            allPlayers.add(emp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateProfile(JComboBox gender,JTextField name)
    {
        playing.setGender(gender.getSelectedIndex());
        playing.setName_Surname(name.getText().trim());
    }

    public void loadPlayerDataTo()
    {
        /*
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
        */
        /*
        OutputStream ops = null;
        ObjectOutputStream objOps = null;
        
        try
        {
            ops = new FileOutputStream("Players.txt");
            objOps = new ObjectOutputStream(ops);

            for(int i= 0; i < this.allPlayers.size();i++)
            {
                objOps.writeObject(this.allPlayers.get(i));
            }
            objOps.close();
            // show success message
        }
        catch(Exception e)
        {
            
        }
        */
        OutputStream ops = null;
        ObjectOutputStream objOps = null;
        try {
            ops = new FileOutputStream("EmpFile.txt");
            objOps = new ObjectOutputStream(ops);
            objOps.writeObject(playing);
            objOps.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    
}
