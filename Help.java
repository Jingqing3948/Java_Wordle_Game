import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;

/**
 * {@code Help} window displays help information.
 * Its content mainly comes from the instruction file of this mini project: java mini project 2022.pdf.
 *
 * @author      Tianwei Jiang
 */
public class Help extends JFrame {
    /**
     * The width of the entire window.
     */
    private final int WINDOW_WIDTH=500;
    /**
     * The height of the entire window.
     */
    private final int WINDOW_HEIGHT=700;
    /**
     * A textPane that displays instruction information to players.
     */
    JTextPane textPane=new JTextPane();
    /**
     * Back to Home button.
     */
    JButton home=new JButton("Back to Home");
    /**
     * This method initializes a {@code Help} Window.
     */
    public Help() throws BadLocationException {
        initTextPane(textPane);
        home.setBounds(140,550,200,50);
        home.setFont(new Font("New Times Roman",Font.BOLD,25));

        //Add an event to close the game help window when the Back to Home button is clicked
        home.addActionListener(e -> Help.this.setVisible(false));

        this.add(textPane);
        this.add(home);

        this.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        this.setTitle("Help");
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
      * This method initializes the TextPane.
      * @param textPane the textPane to be initialized.
      */
    private void initTextPane(JTextPane textPane) throws BadLocationException {
        textPane.setFocusable(false);
        textPane.setFont(new Font("Times New Roman",Font.PLAIN,20));
        textPane.setBounds(10,10,470,520);
        textPane.setOpaque(false);
        textPane.setText(
                "In this game, you need to guess a hidden target 5-letter word within 6 attempts.\n\n"

                +"An English word consisting of five letters is selected at random (by the computer) from a list of words, but kept hidden from you. You need to guess the 5-letter word.\n\n"

                +"You are allowed to make 6 guesses in total to discover the target word.\n\n"

                +"You are given feedback after each attempt you make. The feedback is as follows:\n\n"

                +"There are three colours that the cells containing the letters can be: green, yellow, or grey.\n\n"

                +"1.");
        addColorText(textPane,"Green",Color.decode("#79b851"),true);
        addColorText(textPane," means the letter is contained in the word and is in that position.\n2. ",Color.BLACK,false);
        addColorText(textPane,"Yellow",Color.decode("#f3c237"),true);
        addColorText(textPane," means the letter is contained in the word but not in that position.\n3. ",Color.BLACK,false);
        addColorText(textPane,"Grey",Color.decode("#a4aec4"),true);
        addColorText(textPane," means the letter is not contained in the word.\n",Color.BLACK,false);
    }
    /**
     * This method add  colored text to the TextPane.
     * @param textPane  the textPane to be modified.
     * @param color     the color of the newly added text.
     * @param text      the content of the newly added text.
     * @param isBold    the style of the newly added text.
     */
    public void addColorText(JTextPane textPane,String text,Color color, boolean isBold) throws BadLocationException {
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        StyleConstants.setForeground(attributeSet,color);
        StyleConstants.setBold(attributeSet,isBold);
        Document doc = textPane.getStyledDocument();
        doc.insertString(doc.getLength(), text, attributeSet);
    }
}