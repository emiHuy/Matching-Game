import javax.swing.*;
import java.awt.event.*;

public class instructionsPage extends JFrame implements ActionListener{
    private JFrame instructionsFrame;
    private JPanel instructionPanel;
    private JButton backButton;
    private JTextArea instructionText;

    public instructionsPage(){
        instructionSetup();
    }

    private void instructionSetup(){
        instructionsFrame = new JFrame();
        instructionsFrame.add(instructionPanel);
        instructionsFrame.setTitle("Card Matching Game Instructions");
        instructionsFrame.setSize(1800, 950);
        instructionsFrame.setLocationRelativeTo(null);
        instructionsFrame.setVisible(true);
        instructionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        backButton.addActionListener(this);

        instructionText.setText("Welcome to the Card Matching Game!\n\n" +
                "Objective:\n" +
                "The goal of this game is to match all pairs of cards on the board.\n\n" +
                "How to Play:\n" +
                "1. Click on two cards to reveal their icons.\n" +
                "2. If the icons of the two selected cards match, you will earn 100 points.\n" +
                "   If the icons do not match, you will lose 10 points. However, your score will never go below 0.\n" +
                "3. Click the 'Continue' button in the match results pop-up message to continue the game.\n" +
                "         - matched cards will leave the screen\n" +
                "         -mismatched cards will flip back over\n" +
                "4. Continue matching cards until all cards are matched.\n" +
                "5. Once the game is finished, enter your name\n\n" +
                "Tips:\n" +
                "- Remember the position of the cards to make successful matches\n" +
                "- Keep track of your score - try to maximize it by minimizing failed attempts.\n" +
                "         - The max score achievable is 600 points.\n\n" +
                "Enjoy the game and have fun!");

        instructionsFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                // Reset isInstructionOpened trackers when window is closed.
                JavaGUI.isInstructionsOpened = false;
                gameScreen.isInstructionsOpened = false;
            }
        });
    }

    // Brings existing instruction window to front of screen.
    public void frameToFront(){
        instructionsFrame.setExtendedState(JFrame.NORMAL);
        instructionsFrame.setState(JFrame.NORMAL);
        instructionsFrame.setLocationRelativeTo(null);
        instructionsFrame.toFront();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            // Reset isInstructionOpened trackers when back button is clicked.
            JavaGUI.isInstructionsOpened = false;
            gameScreen.isInstructionsOpened = false;
            instructionsFrame.dispose();
        }
    }
}
