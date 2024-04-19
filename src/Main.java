import javax.swing.*;
public class Main {
    private static JavaGUI display;

    public static void main(String[] args) {
        display = new JavaGUI();
    }

    public static void openInstanceDisplay(){
        display.initializeGUI();
    }
    /*
    public static void openInstanceScores(String player, int score){
        display.openScores(player, score);
    }

     */
}