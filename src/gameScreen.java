import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

public class gameScreen extends javax.swing.JFrame{
    private JPanel gamePanel;
    private JLabel grid1 = new JLabel("");;
    private JLabel grid2 = new JLabel("");;
    private JLabel grid3 = new JLabel("");;
    private JLabel grid4 = new JLabel("");;
    private JLabel grid5 = new JLabel("");;
    private JLabel grid6 = new JLabel("");;
    private JLabel grid7 = new JLabel("");;
    private JLabel grid8 = new JLabel("");;
    private JLabel grid9 = new JLabel("");;
    private JLabel grid10 = new JLabel("");;
    private JLabel grid11 = new JLabel("");;
    private JLabel grid12 = new JLabel("");;
    private JButton backButton;
    private JMenu instructionMenu;
    private JMenuItem openInstructions;
    private JLabel cardback = new JLabel(new ImageIcon("cardback.png"));;
    private JLabel circleCard = new JLabel(new ImageIcon("circle card.png"));;
    private JLabel clubCard = new JLabel(new ImageIcon("club card.png"));;
    private JLabel diamondCard = new JLabel(new ImageIcon("diamond card.png"));;
    private JLabel spadeCard = new JLabel(new ImageIcon("spade card.png"));;
    private JLabel squareCard = new JLabel(new ImageIcon("square card.png"));;
    private JLabel starCard = new JLabel(new ImageIcon("star card.png"));;
    private JLabel[] grids;

    public gameScreen(){
        gameSetup();
    }
    private void gameSetup(){
        JFrame gameFrame = new JFrame();
        gameFrame.setTitle("Card Matching Game");
        gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        gamePanel.setLayout(new GridLayout(3,4,10,10));

        menuSetup(gameFrame);

        grids = new JLabel[]{grid1, grid2, grid3, grid4, grid5, grid6, grid7, grid8, grid9, grid10, grid11, grid12};
        for(int i = 0; i<grids.length; i++){
            grids[i] = new JLabel(new ImageIcon("cardback.png"));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = (i - 1) % 4; // Set grid position based on column index
            gbc.gridy = (i - 1) / 3; // Set grid position based on row index
            gbc.insets = new Insets(10, 10, 10, 10); // Add insets for spacing
            gamePanel.add(grids[i], gbc);
        }
        gameFrame.add(gamePanel);
        gameFrame.setSize(2000, 1100);
        gameFrame.setVisible(true);

        /*backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmBack = JOptionPane.showConfirmDialog(null, "Are you sure you want to go back? Game will not save.", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (confirmBack == JOptionPane.YES_OPTION){
                    gameFrame.dispose();
                    new JavaGUI();
                }
            }
        });*/
    }
    private void menuSetup(JFrame frame){
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
        frame.setJMenuBar(menuBar);
    }
}
