import javax.swing.*;
import java.awt.*;

/**
 * {@code LetterBlock} class defines the style of 4 different letter blocks.
 * <ul>
 *     <li>
 *         not checked: player input letters, with white background and black foreground.
 *     </li>
 *     <li>
 *         grey: checked letters, means the letter is not contained in the word. With grey background and white foreground.
 *     </li>
 *     <li>
 *         green: checked letters, means the letter is contained in the word and is in that position. With green background and white foreground.
 *     </li>
 *     <li>
 *         yellow: checked letters, means the letter is contained in the word but not in that position. With yellow background and white foreground.
 *     </li>
 * </ul>
 * @author      Tianwei Jiang
 */
public class LetterBlock extends JTextField {
    /**
     * {@code status==0} means this letter is not checked.
     */
    private final int LETTER_NOT_CHECK=0;
    /**
     * {@code status==-1} means this letter is checked, and should be grey.
     */
    private final int LETTER_GREY=-1;
    /**
     * {@code status==1} means this letter is checked, and should be yellow.
     */
    private final int LETTER_YELLOW=1;
    /**
     * {@code status==2} means this letter is checked, and should be green.
     */
    private final int LETTER_GREEN=2;
    /**
     * Font size of letters in blocks.
     */
    private final int FONT_SIZE=30;
    /**
     * Hexadecimal color of grey blocks.
     */
    private final String BACKGROUND_GREY="#a4aec4";
    /**
     * Hexadecimal color of yellow blocks.
     */
    private final String BACKGROUND_YELLOW="#f3c237";
    /**
     * Hexadecimal color of green blocks.
     */
    private final String BACKGROUND_GREEN="#79b851";
    /**
     * Hexadecimal color of white letters.
     */
    private final String FONT_COLOR="#fbfcff";

    /**
     * This method initializes a {@code LetterBlock} textField.
     * @param column    number of columns in LetterBlock.
     * @param text      set text of this block.
     * @param x         set x coordinate of this block.
     * @param y         set y coordinate of this block.
     * @param width     set width coordinate of this block.
     * @param height    set height coordinate of this block.
     * @param status    set status of this block. {not checked, grey, green, yellow}
     */
    public LetterBlock(int column, String text, int x, int y, int width, int height, int status){
        super(column);
        this.setHorizontalAlignment(JTextField.CENTER);
        this.setFont(new Font("Arial",Font.BOLD,FONT_SIZE));
        this.setBorder(null);
        this.setFocusable(false);
        this.setText(text);
        this.setBounds(x,y,width,height);
        this.setStatus(status);
    }

    /**
     * This method set status of a {@code LetterBlock} textField.
     * @param status    set status of this block. {not checked, grey, green, yellow}
     */
    public void setStatus(int status){
        switch (status) {
            case LETTER_NOT_CHECK:
                this.setForeground(Color.BLACK);
                this.setBackground(Color.WHITE);
				break;
            case LETTER_GREY:
                this.setBackground(Color.decode(BACKGROUND_GREY));
                this.setForeground(Color.decode(FONT_COLOR));
				break;
            case LETTER_YELLOW:
                this.setBackground(Color.decode(BACKGROUND_YELLOW));
                this.setForeground(Color.decode(FONT_COLOR));
				break;
            case LETTER_GREEN:
                this.setBackground(Color.decode(BACKGROUND_GREEN));
                this.setForeground(Color.decode(FONT_COLOR));
				break;
            default:
				break;
        }
    }
}
