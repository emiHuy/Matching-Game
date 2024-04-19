import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class instructionsPage extends JFrame implements ActionListener{
    private JFrame instructionsFrame;
    private JButton backButton;
    private JPanel instructionPanel;

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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            instructionsFrame.dispose();
        }
    }
}
