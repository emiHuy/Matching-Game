import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

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
    private JLabel gameCharRight;
    private JTextPane aboutText;
    private JTable scoresTable;
    private instructionsPage instructions;

    // Data for tracking players and scores.
    private final ArrayList<String> playerList = new ArrayList<>();
    private final ArrayList<Integer> playerScoreList = new ArrayList<>();

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

        displayStart.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // If user escapes window and instructions are opened,
                if (isInstructionsOpened) {
                    // Close instruction window.
                    instructions.closeWindow();
                }
            }
        });
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

        // Set up about screen
        aboutText.setText("Welcome to the Card Matching Game created by Emily and Keenan in 2024.\n\n" +
                "With our Card Matching Game, we bring you endless fun to help you unwind after a long day.\n\n"+
                "Play our game to test your cognitive abilities and enhance your memory.\n\n" +
                "It's time to start matching! Good luck and have fun!");
        gameCharRight.setIcon(new ImageIcon("game Character.png"));

        createScoresTable();
    }
    public void createScoresTable(){
        // Add player data to 2D list.
        Object[][] data = new Object[playerList.size()][2];
        for(int x = 0; x < playerList.size(); x++){
            data[x][0] = playerList.get(x);
            data[x][1] = playerScoreList.get(x);
        }

        // Sort data by score.
        Arrays.sort(data, Comparator.comparing(row -> (int)row[1]));

        // Create new sorted player data list and add rankings.
        int rank = 1;
        Object[][] sortedData = new Object[playerList.size()][3];
        for(int x = 0; x < playerList.size(); x++){
            sortedData[x][0] = rank++;
            sortedData[x][1] = data[playerList.size()-1-x][0];
            sortedData[x][2] = data[playerList.size()-1-x][1];
        }
        // Creates table with headers and data.
        scoresTable.setModel(new DefaultTableModel(sortedData, new String[]{"Rank", "Player", "Score"}));
        JTableHeader header = scoresTable.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 26));
    }

    public void openScores(String newPlayer, int newScore){
        // Go to scores screen.
        initialize();
        startScreen.setVisible(false);
        scoresScreen.setVisible(true);

        // Update player and score data.
        playerList.add(newPlayer);
        playerScoreList.add(newScore);

        createScoresTable();
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
                isInstructionsOpened = true;
                instructions = new instructionsPage();
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
                if(isInstructionsOpened) {
                    instructions.closeWindow();
                }
                displayStart.dispose();
            }
        }
    }
}