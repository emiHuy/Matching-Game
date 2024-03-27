import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class instructionsPage extends JFrame{
    private JButton backButton;
    private JPanel instructionPanel;
    private JLabel instructionsHead;
    private JTextPane instructionText;

    public instructionsPage(){
        instructionSetup();
    }
    private void instructionSetup(){
        JFrame instructions = new JFrame();
        instructions.add(instructionPanel);
        instructions.setTitle("Card Matching Game Instructions");
        instructions.setSize(1500, 700);
        instructions.setLocationRelativeTo(null);
        instructions.setVisible(true);
        instructions.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instructions.dispose();
            }
        });
    }
}
