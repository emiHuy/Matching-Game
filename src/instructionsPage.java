import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class instructionsPage extends JFrame implements ActionListener{
    private JFrame instructions;
    private JButton backButton;
    private JPanel instructionPanel;

    public instructionsPage(){
        instructionSetup();
    }

    private void instructionSetup(){
        instructions = new JFrame();
        instructions.add(instructionPanel);
        instructions.setTitle("Card Matching Game Instructions");
        instructions.setSize(1500, 700);
        instructions.setLocationRelativeTo(null);
        instructions.setVisible(true);
        instructions.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        backButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            instructions.dispose();
        }
    }
}
