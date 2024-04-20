import javax.swing.*;
import java.awt.event.*;

public class instructionsPage extends JFrame implements ActionListener{
    private JFrame instructionsFrame;
    private JPanel instructionPanel;
    private JButton backButton;

    public instructionsPage(){
        instructionSetup();
    }

    private void instructionSetup(){
        instructionsFrame = new JFrame();
        instructionsFrame.add(instructionPanel);
        instructionsFrame.setTitle("Card Matching Game Instructions");
        instructionsFrame.setSize(1500, 700);
        instructionsFrame.setLocationRelativeTo(null);
        instructionsFrame.setVisible(true);
        instructionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        backButton.addActionListener(this);

        instructionsFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                // Reset isInstructionOpened trackers when window is closed.
                JavaGUI.isInstructionsOpened = false;
                gameScreen.isInstructionsOpened = false;
            }
        });
    }

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
