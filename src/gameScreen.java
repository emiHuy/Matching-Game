import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gameScreen extends javax.swing.JFrame{
    private JPanel gamePanel;
    private JButton backButton;
    private JMenu instructionMenu;
    private JMenuItem openInstructions;

    public gameScreen(){
        gameSetup();
    }
    private void gameSetup(){
        JFrame gameFrame = new JFrame();
        gameFrame.add(gamePanel);
        gameFrame.setTitle("Card Matching Game");
        gameFrame.setSize(2000, 1100);
        gameFrame.setVisible(true);
        gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // creates instructions menu that allows user to open instructions mid-game
        instructionMenu = new JMenu("Menu");
        JMenuBar menuBar = new JMenuBar();
        openInstructions = new JMenuItem("Instructions");
        openInstructions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new instructionsPage();
            }
        });
        instructionMenu.add(openInstructions);
        menuBar.add(instructionMenu);
        gameFrame.setJMenuBar(menuBar);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmBack = JOptionPane.showConfirmDialog(null, "Are you sure you want to go back? Game will not save.", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (confirmBack == JOptionPane.YES_OPTION){
                    gameFrame.dispose();
                    new JavaGUI();
                }
            }
        });
    }
}
