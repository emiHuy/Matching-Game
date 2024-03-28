import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;

public class gameScreen extends javax.swing.JFrame implements ActionListener{
    private JButton backButton;
    private JFrame gameFrame;
    private JMenuItem openInstructions;
    private JPanel gamePanel;
    private JLabel grid1;
    private JLabel grid2;
    private JLabel grid3;
    private JLabel grid4;
    private JLabel grid5;
    private JLabel grid6;
    private JLabel grid7;
    private JLabel grid8;
    private JLabel grid9;
    private JLabel grid10;
    private JLabel grid11;
    private JLabel grid12;
    private final ImageIcon cardBack = new ImageIcon("cardback.png");
    private final ImageIcon circleCard = new ImageIcon("circle card.png");
    private final ImageIcon clubCard = new ImageIcon("club card.png");
    private final ImageIcon diamondCard = new ImageIcon("diamond card.png");
    private final ImageIcon spadeCard = new ImageIcon("spade card.png");
    private final ImageIcon squareCard = new ImageIcon("square card.png");
    private final ImageIcon starCard = new ImageIcon("star card.png");
    private final JLabel[] grids = {grid1, grid2, grid3, grid4, grid5, grid6, grid7, grid8, grid9, grid10, grid11, grid12};

    public gameScreen(){
        gameSetup();
    }

    private void gameSetup(){
        gameFrame = new JFrame();
        gameFrame.setTitle("Card Matching Game");
        gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel borderPanel = new JPanel(new BorderLayout());
        gameFrame.add(borderPanel);

        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(3,4,10,10));

        menuSetup(gameFrame);

        // display cards
        for(int i = 0; i<grids.length; i++){
            grids[i] = new JLabel(cardBack);
            gamePanel.add(grids[i]);
        }
        borderPanel.add(gamePanel, BorderLayout.CENTER);

        // add back button
        JPanel buttonPanel = new JPanel(new BorderLayout());
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        backButton.setFont(new Font("Arial", Font.PLAIN, 26));
        buttonPanel.add(backButton, BorderLayout.EAST);
        borderPanel.add(buttonPanel,BorderLayout.SOUTH);

        JLabel scoreDisplay = new JLabel("Score: " + 0);
        scoreDisplay.setFont(new Font("Arial", Font.PLAIN, 26));
        buttonPanel.add(scoreDisplay, BorderLayout.WEST);

        buttonPanel.setBorder(new EmptyBorder(30,30,30,30));
        gameFrame.setSize(2000, 1000);
        gameFrame.setVisible(true);
    }

    private void menuSetup(JFrame frame) {
        JMenu instructionMenu = new JMenu("Menu");
        JMenuBar menuBar = new JMenuBar();
        openInstructions = new JMenuItem("Instructions");
        openInstructions.addActionListener(this);
        instructionMenu.add(openInstructions);
        menuBar.add(instructionMenu);
        frame.setJMenuBar(menuBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton) {
            // ask for user confirmation
            int confirmBack = JOptionPane.showConfirmDialog(null, "Are you sure you want to go back? Game will not save.", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (confirmBack == JOptionPane.YES_OPTION){
                // go back to starting menu
                gameFrame.dispose();
                new JavaGUI();
            }
        }
        else if(e.getSource() == openInstructions) {
            new instructionsPage();
        }
    }
}
