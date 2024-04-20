import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class JavaGUI extends JFrame implements ActionListener{
    // Tracks if instructions window is already opened
    public static boolean isInstructionsOpened = false;

    // GUI components
    private JFrame displayStart;
    private JPanel mainPanel;
    private JPanel startScreen;
    private JPanel aboutScreen;
    private JPanel scoresScreen;
    private JButton playButton;
    private JButton aboutButton;
    private JButton instructionsButton;
    private JButton quitButton;
    private JButton scoresButton;
    private JButton backButtonAbout;
    private JButton backButtonScores;
    private JLabel scoreDisplay;
    private JLabel gameCharRight;
    private JTextPane aboutText;
    private instructionsPage instructions;

    // Data for tracking players and scores.
    private ArrayList<String> playerList = new ArrayList<>();
    private ArrayList<Integer> playerScoreList = new ArrayList<>();

    public JavaGUI() {
        initialize();
        setupComponents();
    }

    private void initialize(){
        displayStart = new JFrame();
        displayStart.setSize(1900, 1000);
        displayStart.add(mainPanel);
        displayStart.setContentPane(mainPanel);
        mainPanel.setVisible(true);
        displayStart.setTitle("Card Matching Game");
        displayStart.setVisible(true);
        displayStart.setLocationRelativeTo(null);
        displayStart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel.add(startScreen);
        startScreen.setVisible(true);
        mainPanel.add(aboutScreen);
        aboutScreen.setVisible(false);
        mainPanel.add(scoresScreen);
        scoresScreen.setVisible(false);
    }
    private void setupComponents(){
        // Set up buttons
        playButton.addActionListener(this);
        aboutButton.addActionListener(this);
        instructionsButton.addActionListener(this);
        scoresButton.addActionListener(this);
        scoresButton.addActionListener(this);
        quitButton.addActionListener(this);
        backButtonAbout.addActionListener(this);
        backButtonScores.addActionListener(this);

        scoreDisplay.setText("Score: " + 0);

        // Set up about screen
        aboutText.setText("Welcome to the Card Matching Game created by Emily and Keenan in 2024.\n\n" +
                "With our Card Matching Game, we bring you endless fun to help you unwind after a long day.\n\n"+
                "Play our game to test your cognitive abilities and enhance your memory.\n\n" +
                "It's time to start matching! Good luck and have fun!");
        gameCharRight.setIcon(new ImageIcon("game Character.png"));
    }

    public void openScores(String newPlayer, int newScore){
        // Go to scores screen.
        initialize();
        startScreen.setVisible(false);
        scoresScreen.setVisible(true);

        // Update player and score data.
        playerList.add(newPlayer);
        playerScoreList.add(newScore);

        System.out.println("Player: Score");
        for(int i = 0 ; i < playerList.toArray().length; i++) {
            System.out.println(playerList.get(i) + ": " + playerScoreList.get(i));
        }
        System.out.println("");
    }

    public void initializeGUI(){
        initialize();
    }

    public instructionsPage getInstructionWindow(){
        return instructions;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Go to game screen if play button is clicked.
        if(e.getSource() == playButton) {
            new gameScreen();
            displayStart.dispose();
        }

        // Go to about screen if score button is clicked.
        else if(e.getSource() == aboutButton) {
            startScreen.setVisible(false);
            aboutScreen.setVisible(true);
        }

        // If instructions button is clicked,
        else if(e.getSource() == instructionsButton) {
            // Open instruction window if not already opened.
            if(!isInstructionsOpened){
                instructions = new instructionsPage();
                isInstructionsOpened = true;
            }
            // If already opened, bring existing instruction window to the front center of screen.
            else{
                instructions.frameToFront();
            }
        }

        // Go to scores screen if scores button is clicked.
        else if(e.getSource() == scoresButton) {
            startScreen.setVisible(false);
            scoresScreen.setVisible(true);
        }

        // Go to start screen if back button is clicked.
        else if(e.getSource() == backButtonAbout || e.getSource() == backButtonScores) {
            startScreen.setVisible(true);
            aboutScreen.setVisible(false);
            scoresScreen.setVisible(false);
        }

        // If quit button is clicked,
        else {
            // ask user for confirmation to quit.
            UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 22));
            UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.BOLD, 20));
            int quitResponse = JOptionPane.showConfirmDialog(instructionsButton,
                    "Are you sure you want to quit?", "Confirm Quit", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if(quitResponse == JOptionPane.YES_OPTION) {
                // Exit program if user confirms.
                instructions.dispose();
                displayStart.dispose();
            }
        }
    }
}