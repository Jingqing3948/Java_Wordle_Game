import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.io.IOException;

/**
 * {@code HomePage} is the home page window of this wordle game, includes:
 * <ul>
 *     <li>{@code TITLE_1}: "WORDLE"</li>
 *     <li>{@code TITLE_2}: "GAME"</li>
 *     <li>{@code start} Button: start game</li>
 *     <li>{@code help} Button: view help</li>
 * </ul>
 *
 * @author         Tianwei Jiang
 */
public class HomePage extends JFrame {
    /**
     * The width of the entire window.
     */
    private final int WINDOW_WIDTH=500;
    /**
     * The height of the entire window.
     */
    private final int WINDOW_HEIGHT=700;
    /**
     * The content of first line header.
     */
    private final String TITLE_1="WORDLE";
    /**
     * The content of second line header.
     */
    private final String TITLE_2="GAME";
    /**
     * the style of first line header.
     */
    private final int STYLE_GREEN=2;
    /**
     *the style of second line header.
     */
    private final int STYLE_YELLOW=1;
    /**
     * The width and height of each letter in title.
     */
    private final int BLOCK_SIZE=60;
    /**
     * The padding between 2 adjacent letter blocks in title.
     */
    private final int TITLE_BLOCK_PADDING=8;
    /**
     * The margin between {@code TITLE_1} and upper border.
     */
    private final int TITLE_1_TOP_MARGIN=80;
    /**
     * The margin between {@code TITLE_1} and upper border.
     */
    private final int TITLE_2_TOP_MARGIN=TITLE_1_TOP_MARGIN+BLOCK_SIZE+20;
    /**
     * The margin between {@code TITLE_1} "WORDLE" and left border.
     */
    private final int TITLE_1_LEFT_MARGIN=(WINDOW_WIDTH-BLOCK_SIZE*6-TITLE_BLOCK_PADDING*5)/2;
    /**
     * The margin between {@code TITLE_2} "GAME" and left border.
     */
    private final int TITLE_2_LEFT_MARGIN=(WINDOW_WIDTH-BLOCK_SIZE*4-TITLE_BLOCK_PADDING*5)/2;
    /**
     * start game button
     */
    private final JButton start=new JButton("START");
    /**
     * game help button
     */
    private final JButton help=new JButton("HELP");
    /**
     * The width of "START" and "HELP" button.
     */
    private final int BUTTON_WIDTH=150;
    /**
     * The height of "START" and "HELP" button.
     */
    private final int BUTTON_HEIGHT=50;
    /**
     * The font size of button.
     */
    private final int BUTTON_FONT_SIZE=30;
    /**
     * The margin between button and left border.
     */
    private final int BUTTON_LEFT_MARGIN=(WINDOW_WIDTH-BUTTON_WIDTH)/2;

    /**
      * This method initializes a {@code HomePage} Window.
      */
    public HomePage() {
        //Assemble the letters in the first row of headers (W,O,R,D,L,E) into the window
        for(int i=0;i<6;i++){
            LetterBlock textField=new LetterBlock(1,
                    ""+TITLE_1.charAt(i),
                    TITLE_1_LEFT_MARGIN+i*(TITLE_BLOCK_PADDING+BLOCK_SIZE),
                    TITLE_1_TOP_MARGIN,
                    BLOCK_SIZE,BLOCK_SIZE,
                    STYLE_GREEN);
            this.add(textField);
        }

        //Assemble the letters in the second row of headers (G,A,M,E) into the window
        for(int i=0;i<4;i++){
            LetterBlock textField=new LetterBlock(1,
                    ""+TITLE_2.charAt(i),
                    TITLE_2_LEFT_MARGIN+i*(TITLE_BLOCK_PADDING+BLOCK_SIZE),
                    TITLE_2_TOP_MARGIN,
                    BLOCK_SIZE,
                    BLOCK_SIZE,
                    STYLE_YELLOW);
            this.add(textField);
        }

        //Add an event to open the wordle game window when the start button is clicked
        start.addActionListener(e -> {
            try {
                new Wordle();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            setVisible(false);
        });

        start.setFont(new Font("New Times Roman", Font.BOLD,BUTTON_FONT_SIZE));
        start.setBounds(BUTTON_LEFT_MARGIN,400,BUTTON_WIDTH,BUTTON_HEIGHT);

        //Add an event to open the game help window when the help button is clicked
        help.addActionListener(e -> {
            try {
                new Help();
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            }
        });

        help.setFont(new Font("New Times Roman", Font.BOLD,BUTTON_FONT_SIZE));
        help.setBounds(BUTTON_LEFT_MARGIN,500,BUTTON_WIDTH,BUTTON_HEIGHT);

        this.add(start);
        this.add(help);

        this.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setTitle("Wordle Game");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
