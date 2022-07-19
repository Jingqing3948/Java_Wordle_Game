import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * {@code Result} is a final window of each round of game. It displays the result of this round.
 * @author      Tianwei Jiang
 */
public class Result extends JDialog {
    /**
     * A button to start another round.
     */
    JButton tryAgain=new JButton("Try Again!");
    /**
     * A button to back to home.
     */
    JButton home=new JButton("Back to Home");
    /**
     * A textField that shows result of this round.
     */
    JTextField textField=new JTextField();
    /**
     * The time the game ended.
     */
    public final static long END_TIME=System.currentTimeMillis();

    /**
     * This method initializes a {@code Result} dialog.
     * @param owner     result's parent component.
     * @param title     set title of this dialog.
     * @param modal     whether this dialog is a modal dialog.
     * @param isWon     whether player wins this round.
     */
    public Result(Frame owner, String title, boolean modal, boolean isWon){
        super(owner,title,modal);
        if(isWon)textField.setText("You Win! Spend "+(END_TIME-Wordle.START_TIME)/1000.0+" secs.");
        else textField.setText("Pity! You Didn't Guess the Answer");
        textField.setFocusable(false);
        textField.setOpaque(false);
        textField.setBorder(null);
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setBounds(0,0,350,90);
        textField.setFont(new Font("Times New Roman",Font.BOLD,20));

        tryAgain.addActionListener(e -> {
            try {
                new Wordle();
                owner.setVisible(false);
                Result.this.setVisible(false);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        home.addActionListener(e -> {
            new HomePage();
            owner.setVisible(false);
            Result.this.setVisible(false);
        });

        home.setBounds(20,90,130,25);
        tryAgain.setBounds(170,90,130,25);

        this.setSize(350,160);
        this.setLayout(null);
        this.add(textField);
        this.add(tryAgain);
        this.add(home);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}