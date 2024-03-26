import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JavaGUI extends JFrame{
    private JPanel mainPanel;
    private JPanel startScreen;
    private JButton playButton;
    private JButton aboutButton;
    private JButton instructionsButton;
    private JButton quitButton;
    private JButton scoresButton;
    private JLabel highscoreDisplay;
    private JLabel highScoreValue;
    private JLabel gameTitle;
    private JPanel aboutScreen;
    private JTextPane HEYYTextPane;
    private JButton backButtonAbout;
    private JPanel scoresList;
    private JLabel scoresHead;
    private JButton backButtonScores;

    public JavaGUI(){
        JFrame displayStart = new JFrame();
        displayStart.add(mainPanel);
        displayStart.setTitle("Card Matching Game");
        displayStart.setSize(2000, 1100);
        displayStart.setVisible(true);
        displayStart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        displayStart.add(aboutScreen);
        aboutScreen.setVisible(false);

        displayStart.add(scoresList);
        scoresList.setVisible(false);

        // when play button is clicked
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new gameScreen();
                displayStart.dispose();
            }
        });
        // when about button is clicked
        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.setVisible(false);
                aboutScreen.setVisible(true);
            }
        });
        // when instructions button is clicked
        instructionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new instructionsPage();
            }
        });
        // when scores button is clicked
        scoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.setVisible(false);
                scoresList.setVisible(true);
            }
        });
        // when quit button is clicked
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ask user to confirm quit
                int quitResponse = JOptionPane.showConfirmDialog(instructionsButton,"Are you sure you want to quit?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(quitResponse == JOptionPane.YES_OPTION){
                    displayStart.dispose();
                }
            }
        });
        // goes to starting screen when back button is clicked
        backButtonAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.setVisible(true);
                aboutScreen.setVisible(false);
            }
        });
        // goes to starting screen when back button is clicked
        backButtonScores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.setVisible(true);
                scoresList.setVisible(false);
            }
        });
    }
}