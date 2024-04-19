import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ImageIcon;

public class JavaGUI extends JFrame implements ActionListener{
    private JFrame displayStart;
    private JPanel mainPanel;
    private JPanel startScreen;
    private JPanel aboutScreen;
    private JPanel scoresList;
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
        mainPanel.add(scoresList);
        scoresList.setVisible(false);
    }
    private void setupComponents(){
        // set up buttons
        playButton.addActionListener(this);
        aboutButton.addActionListener(this);
        instructionsButton.addActionListener(this);
        scoresButton.addActionListener(this);
        scoresButton.addActionListener(this);
        quitButton.addActionListener(this);
        backButtonAbout.addActionListener(this);
        backButtonScores.addActionListener(this);

        scoreDisplay.setText("Score: " + 0);

        // set up about screen
        aboutText.setText("Welcome to the Card Matching Game created by Emily and Keenan in 2024.\n\n" +
                "With our Card Matching Game, we bring you endless fun to help you unwind after a long day.\n\n"+
                "Play our game to test your cognitive abilities and enhance your memory.\n\n" +
                "It's time to start matching! Good luck and have fun!");
        aboutText.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
        gameCharRight.setIcon(new ImageIcon("game Character.png"));
    }

    public void openScores(String newPlayer, int newScore){
        initialize();
        startScreen.setVisible(false);
        scoresList.setVisible(true);
        playerList.add(newPlayer);
        playerScoreList.add(newScore);
    }

    public void initializeGUI(){
        initialize();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == playButton) {
            new gameScreen();
            displayStart.dispose();
        }
        else if(e.getSource() == aboutButton) {
            startScreen.setVisible(false);
            aboutScreen.setVisible(true);
        }
        else if(e.getSource() == instructionsButton) {
            new instructionsPage();
        }
        else if(e.getSource() == scoresButton) {
            startScreen.setVisible(false);
            scoresList.setVisible(true);
        }
        else if(e.getSource() == backButtonAbout || e.getSource() == backButtonScores) {
            startScreen.setVisible(true);
            aboutScreen.setVisible(false);
            scoresList.setVisible(false);
        }
        // if quit button is clicked
        else {
            // ask user for confirmation to quit
            UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 22));
            UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.BOLD, 20));
            int quitResponse = JOptionPane.showConfirmDialog(instructionsButton,
                    "Are you sure you want to quit?", "Confirm Quit", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if(quitResponse == JOptionPane.YES_OPTION) {
                // exit program if user confirms
                displayStart.dispose();
            }
        }
    }
}