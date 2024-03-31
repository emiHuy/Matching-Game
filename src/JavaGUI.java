import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JavaGUI extends JFrame implements ActionListener{
    private JPanel mainPanel;
    private JPanel startScreen;
    private JButton playButton;
    private JButton aboutButton;
    private JButton instructionsButton;
    private JButton quitButton;
    private JButton scoresButton;
    private JLabel highScoreDisplay;
    private JLabel highScoreValue;
    private JPanel aboutScreen;
    private JButton backButtonAbout;
    private JPanel scoresList;
    private JButton backButtonScores;
    private JFrame displayStart;

    public JavaGUI() {
        addComponents();
    }

    private void addComponents(){
        displayStart = new JFrame();
        displayStart.add(mainPanel);
        displayStart.setTitle("Card Matching Game");
        displayStart.setSize(1900, 1000);
        displayStart.setVisible(true);
        displayStart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        displayStart.add(aboutScreen);
        aboutScreen.setVisible(false);

        displayStart.add(scoresList);
        scoresList.setVisible(false);

        playButton.addActionListener(this);
        aboutButton.addActionListener(this);
        instructionsButton.addActionListener(this);
        scoresButton.addActionListener(this);
        scoresButton.addActionListener(this);
        quitButton.addActionListener(this);
        backButtonAbout.addActionListener(this);
        backButtonScores.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == playButton) {
            new gameScreen();
            displayStart.dispose();
        }
        else if(e.getSource() == aboutButton) {
            mainPanel.setVisible(false);
            aboutScreen.setVisible(true);
        }
        else if(e.getSource() == instructionsButton) {
            JFrame instructions = new instructionsPage();
        }
        else if(e.getSource() == scoresButton) {
            mainPanel.setVisible(false);
            scoresList.setVisible(true);
        }
        else if(e.getSource() == backButtonAbout || e.getSource() == backButtonScores) {
            mainPanel.setVisible(true);
            aboutScreen.setVisible(false);
            scoresList.setVisible(false);
        }
        // if quit button is clicked
        else {
            // ask user for confirmation to quit
            int quitResponse = JOptionPane.showConfirmDialog(instructionsButton,"Are you sure you want to quit?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(quitResponse == JOptionPane.YES_OPTION) {
                // exit program if user confirms
                displayStart.dispose();
            }
        }
    }
}