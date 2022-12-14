import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class coachControl
{
    private ArrayList<JLabel> badgeLabels;
    private Player playing = new Player("a", "a", "a", 2);
    private boolean isValidLogIn;
    public int subLevel,level;

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
        loadAchivementTitles();
        loadPlayerDataFrom( );

        badgeLabels = new ArrayList<JLabel>( );
        allPlayers = new ArrayList<Player>( );
        createTips( );
        
        theTreePanel =  new TreePanel(this);
        theProfile = new Profile(this);
        theNews = new News(this);
        frameStart( );
        System.out.println(playing.getUsername( ));
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
    
    public void manageBadges( )
    {
        playing.changeBadge(0, true);
        playing.changeBadge(1, true);
        playing.changeBadge(5, true);
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
    
    public void addProfileImage(JLabel profileLabel, String imageName)
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
                 File outputfile = new File(imageName + ".jpg");
                 ImageIO.write(bi, "jpg", outputfile);
             }
             catch(IOException e) 
             {
                e.printStackTrace(); // todo: implement proper error handeling
             }
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
        Random rand = new Random();
        coachTip.setText(tips.get(rand.nextInt(this.tips.size())));
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
        message.setText("Time stamped your habit!");
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
                    manageBadges();
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
            newPlayer.populateBages(badgeLabels);
            info.setText("Successfully registered!");
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
    
    public void updateProfile(JComboBox gender,JTextField name)
    {
        playing.setGender(gender.getSelectedIndex());
        playing.setName_Surname(name.getText());
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
