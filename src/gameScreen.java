import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;
import java.util.Collections;

public class gameScreen extends javax.swing.JFrame implements ActionListener{
    private JButton backButton;
    private JFrame gameFrame;
    private JMenuItem openInstructions;
    private JPanel gamePanel;
    private JButton circle1;
    private JButton circle2;
    private JButton club1;
    private JButton club2;
    private JButton diamond1;
    private JButton diamond2;
    private JButton spade1;
    private JButton spade2;
    private JButton square1;
    private JButton square2;
    private JButton star1;
    private JButton star2;
    private final ImageIcon cardBack = new ImageIcon("cardback.png");
    private final ImageIcon circleCard = new ImageIcon("circle card.png");
    private final ImageIcon clubCard = new ImageIcon("club card.png");
    private final ImageIcon diamondCard = new ImageIcon("diamond card.png");
    private final ImageIcon spadeCard = new ImageIcon("spade card.png");
    private final ImageIcon squareCard = new ImageIcon("square card.png");
    private final ImageIcon starCard = new ImageIcon("star card.png");
    private final ArrayList<JButton> cards = new ArrayList<>();
    private final ArrayList<ImageIcon> cardIcons = new ArrayList<>();
    private JButton openedBttn1 = null;
    private ImageIcon openedIcon1 = null;
    private JButton openedBttn2 = null;
    private ImageIcon openedIcon2 = null;
    private String matchMsg;
    private boolean match;
    private int matchResult;
    private int matchedPairs = 0;
    private final int totalPairs = 6;
    public int trackScore = 0;
    private JLabel scoreDisplay;
    private String player;

    public gameScreen(){
        gameSetup();
    }

    private void gameSetup(){
        gameFrame = new JFrame();
        gameFrame.setSize(1900, 1000);
        gameFrame.setVisible(true);
        gameFrame.setTitle("Card Matching Game");
        gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gameFrame.setLocationRelativeTo(null);

        JPanel borderPanel = new JPanel(new BorderLayout());
        gameFrame.add(borderPanel);

        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(3,4,10,10));

        menuSetup(gameFrame);

        // add cards to array list
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

        // shuffle card order, match cards to JButtons, and display card backs to screen
        Collections.shuffle(cardIcons);
        for(ImageIcon icon: cardIcons){
            JButton button = new JButton(cardBack);
            button.addActionListener(this);
            cards.add(button);
            gamePanel.add(button);
        }

        borderPanel.add(gamePanel, BorderLayout.CENTER);

        // add back button
        JPanel buttonPanel = new JPanel(new BorderLayout());
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        backButton.setFont(new Font("Arial", Font.PLAIN, 26));
        buttonPanel.add(backButton, BorderLayout.EAST);
        borderPanel.add(buttonPanel,BorderLayout.SOUTH);

        scoreDisplay = new JLabel("Score: " + 0);
        scoreDisplay.setFont(new Font("Arial", Font.PLAIN, 26));
        buttonPanel.add(scoreDisplay, BorderLayout.WEST);
        buttonPanel.setBorder(new EmptyBorder(30,30,30,30));
    }

    private void menuSetup(JFrame frame) {
        JMenu instructionMenu = new JMenu("Menu");
        JMenuBar menuBar = new JMenuBar();
        openInstructions = new JMenuItem("Instructions");
        openInstructions.addActionListener(this);
        instructionMenu.setFont(new Font("Arial", Font.PLAIN, 25));
        openInstructions.setFont(new Font("Arial", Font.PLAIN, 20));
        instructionMenu.add(openInstructions);
        menuBar.add(instructionMenu);
        frame.setJMenuBar(menuBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton) {
            // ask for user confirmation
            UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 20));
            UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.BOLD, 18));
            int confirmBack = JOptionPane.showConfirmDialog(null, "Are you sure you want to go back? Game will NOT save.", "Confirm Back", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (confirmBack == JOptionPane.YES_OPTION){
                // go back to starting menu
                gameFrame.dispose();
                new JavaGUI();
            }
        }
        else if(e.getSource() == openInstructions) {
            new instructionsPage();
        }
        else {
            JButton clickedButton = (JButton) e.getSource();
            int index = cards.indexOf(clickedButton);
            ImageIcon icon = cardIcons.get(index);

            // flips over first card when first card is clicked
            if(openedBttn1 == null){
                openedBttn1 = clickedButton;
                openedIcon1 = icon;
                clickedButton.setIcon(icon);
                matchResult = 1;
            }
            // flips over second card when second card is clicked
            else if(openedBttn2 == null && clickedButton != openedBttn1){
                openedBttn2 = clickedButton;
                openedIcon2 = icon;
                clickedButton.setIcon(icon);

                if(openedIcon1.equals(openedIcon2)){
                    match = true;
                    matchMsg = "It's a MATCH!";
                }
                else{
                    match = false;
                    matchMsg = "Unlucky... try again.";
                }
                while(matchResult != 0) {
                    Object[] options = {"Continue"};
                    UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 20));
                    UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.BOLD, 18));
                    matchResult = JOptionPane.showOptionDialog(null, matchMsg, "Is it a match?", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
                    if (matchResult == 0 && match) {
                        openedBttn1.setVisible(false);
                        openedBttn2.setVisible(false);
                        openedBttn1 = null;
                        openedIcon1 = null;
                        openedBttn2 = null;
                        openedIcon2 = null;
                        trackScore += 100;
                        matchedPairs++;
                        if(matchedPairs == totalPairs){
                            scoreDisplay.setText("Score: " + trackScore);
                            player = JOptionPane.showInputDialog(null, "Enter player name:", "Enter Name", JOptionPane.QUESTION_MESSAGE);
                        }
                    } else if (matchResult == 0 && !match) {
                        openedBttn1.setIcon(cardBack);
                        openedBttn2.setIcon(cardBack);
                        openedBttn1 = null;
                        openedIcon1 = null;
                        openedBttn2 = null;
                        openedIcon2 = null;
                        trackScore -= 10;
                    }
                    scoreDisplay.setText("Score: " + trackScore);
                }
            }
        }
    }
}
