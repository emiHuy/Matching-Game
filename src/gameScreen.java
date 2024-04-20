import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;
import java.util.Collections;

public class gameScreen extends javax.swing.JFrame implements ActionListener{
    // Tracks if instruction window is already opened.
    public static boolean isInstructionsOpened = JavaGUI.isInstructionsOpened;

    // GUI components.
    private JFrame gameFrame;
    private JPanel gamePanel;
    private JLabel scoreDisplay;
    private JMenuItem openInstructions;
    private JButton backButton;
    private JButton openedCard1;
    private JButton openedCard2;
    private ImageIcon openedIcon1;
    private ImageIcon openedIcon2;
    private String player;
    private instructionsPage instructions;
    private int matchResult;
    private int matchedPairs = 0;
    public int trackScore = 0;
    private final int totalPairs = 6;
    private final ArrayList<JButton> cards = new ArrayList<>();
    private final ArrayList<ImageIcon> cardIcons = new ArrayList<>();
    private final ImageIcon cardBack = new ImageIcon("cardback.png");
    private final ImageIcon circleCard = new ImageIcon("circle card.png");
    private final ImageIcon clubCard = new ImageIcon("club card.png");
    private final ImageIcon diamondCard = new ImageIcon("diamond card.png");
    private final ImageIcon spadeCard = new ImageIcon("spade card.png");
    private final ImageIcon squareCard = new ImageIcon("square card.png");
    private final ImageIcon starCard = new ImageIcon("star card.png");

    public gameScreen(){
        initialize();
    }

    private void initialize(){
        gameFrame = new JFrame();
        gameFrame.setSize(1900, 1000);
        gameFrame.setVisible(true);
        gameFrame.setTitle("Card Matching Game");
        gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gameFrame.setLocationRelativeTo(null);

        if(isInstructionsOpened){
            // Assign existing instruction window to game screen attribute.
            instructions = Main.getInstructionWindow();
        }

        setupUI();
    }

    private void setupUI(){
        JPanel borderPanel = new JPanel(new BorderLayout());
        gameFrame.add(borderPanel);

        // Set up game panel.
        gamePanel = new JPanel();
        gamePanel.setBackground(new Color(43, 53, 75));
        gamePanel.setLayout(new GridLayout(3,4,10,10));
        borderPanel.add(gamePanel, BorderLayout.CENTER);

        // Add back button.
        JPanel buttonPanel = new JPanel(new BorderLayout());
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        backButton.setFont(new Font("Arial", Font.PLAIN, 28));
        buttonPanel.add(backButton, BorderLayout.EAST);
        borderPanel.add(buttonPanel,BorderLayout.SOUTH);

        // Add score display.
        scoreDisplay = new JLabel("Score: " + 0);
        scoreDisplay.setFont(new Font("Arial", Font.PLAIN, 28));
        buttonPanel.add(scoreDisplay, BorderLayout.WEST);
        buttonPanel.setBorder(new EmptyBorder(30,30,30,30));

        setupMenu();
        setupCards();
    }

    private void setupMenu() {
        JMenu instructionMenu = new JMenu("Menu");
        JMenuBar menuBar = new JMenuBar();
        openInstructions = new JMenuItem("Open Instructions");
        openInstructions.addActionListener(this);
        instructionMenu.setFont(new Font("Arial", Font.PLAIN, 25));
        openInstructions.setFont(new Font("Arial", Font.PLAIN, 24));
        instructionMenu.add(openInstructions);
        menuBar.add(instructionMenu);
        gameFrame.setJMenuBar(menuBar);
    }

    private void setupCards(){
        // Add card image icons to array.
        cardIcons.add(circleCard);
        cardIcons.add(circleCard);
        cardIcons.add(clubCard);
        cardIcons.add(clubCard);
        cardIcons.add(diamondCard);
        cardIcons.add(diamondCard);
        cardIcons.add(spadeCard);
        cardIcons.add(spadeCard);
        cardIcons.add(squareCard);
        cardIcons.add(squareCard);
        cardIcons.add(starCard);
        cardIcons.add(starCard);

        // Shuffle card order.
        Collections.shuffle(cardIcons);

        // Match cards to JButtons and display card backs to screen.
        for(ImageIcon icon: cardIcons){
            JButton button = new JButton(cardBack);
            button.addActionListener(this);
            cards.add(button);
            gamePanel.add(button);
        }
    }

    private void backButtonClicked(){
        // Ask for user confirmation to leave game screen.
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 22));
        UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.BOLD, 20));
        int confirmBack = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to go back? Game will NOT save.", "Confirm Back",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (confirmBack == JOptionPane.YES_OPTION){
            // Go back to starting menu if user confirms.
            gameFrame.dispose();
            Main.openInstanceDisplay();
        }
    }

    private void cardClicked(JButton clickedButton){
        int index = cards.indexOf(clickedButton);
        ImageIcon icon = cardIcons.get(index);

        // Flips over first card when first card is clicked.
        if(openedCard1 == null){
            openedCard1 = clickedButton;
            openedIcon1 = icon;
            clickedButton.setIcon(icon);
            matchResult = 1;
        }
        // Flips over second card when second card is clicked.
        else if(openedCard2 == null && clickedButton != openedCard1){
            openedCard2 = clickedButton;
            openedIcon2 = icon;
            clickedButton.setIcon(icon);

            if(openedIcon1.equals(openedIcon2)){
                continueGame(true, "It's a MATCH!");}
            else{
                continueGame(false, "Unlucky... try again.");}
        }
    }

    // After card is clicked,
    private void continueGame(boolean isMatch, String matchMsg){
        Object[] options = {"Continue"};
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 24));
        UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.BOLD, 22));

        while(matchResult != 0) {
            // Display match result message
            matchResult = JOptionPane.showOptionDialog(null, matchMsg, "Is it a match?",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);

            // When user confirms to continue game and cards match,
            if (matchResult == 0 && isMatch) {
                // Remove card icons from screen and add to score.
                openedCard1.setVisible(false);
                openedCard2.setVisible(false);
                openedCard1 = null;
                openedIcon1 = null;
                openedCard2 = null;
                openedIcon2 = null;
                trackScore += 100;
                matchedPairs++;

                // When all cards are matched.
                if(matchedPairs == totalPairs){
                    collectUserInfo();
                }
            }
            // When user confirms to continue game and cards don't match,
            else if (matchResult == 0 && !isMatch) {
                // Flip over cards to reveal back side/reset cards and subtract from score
                openedCard1.setIcon(cardBack);
                openedCard2.setIcon(cardBack);
                openedCard1 = null;
                openedIcon1 = null;
                openedCard2 = null;
                openedIcon2 = null;
                if(trackScore > 0) {
                    trackScore -= 10;
                }
            }
            // Updates score display.
            scoreDisplay.setText("Score: " + trackScore);
        }
    }

    private void collectUserInfo(){
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 24));
        UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.BOLD, 22));
        UIManager.put("TextField.font", new Font("Arial", Font.PLAIN, 24));

        do {
            // Collect user's name.
            player = JOptionPane.showInputDialog(null, "Player Name: ", "End", JOptionPane.PLAIN_MESSAGE);
        }while(player == null || player.isEmpty());
        Main.openInstanceScores(player.trim(), trackScore);
        gameFrame.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton) {
            backButtonClicked();
        }
        else if(e.getSource() == openInstructions) {
            if(!isInstructionsOpened){
                instructions = new instructionsPage();
                isInstructionsOpened = true;
            }
            else{
                instructions.frameToFront();
            }
        }
        else {
            cardClicked((JButton)e.getSource());
        }
    }
}