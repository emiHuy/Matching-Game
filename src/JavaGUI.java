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

    public JavaGUI(){
        JFrame displayStart = new JFrame();
        displayStart.add(mainPanel);
        displayStart.setTitle("Card Matching Game");
        displayStart.setSize(1200, 800);
        displayStart.setVisible(true);
        displayStart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // when play button is clicked
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        // when about button is clicked
        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        // when instructions button is clicked
        instructionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        // when scores button is clicked
        scoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
    }


}
