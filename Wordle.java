import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import static java.awt.event.KeyEvent.VK_BACK_SPACE;
import static java.awt.event.KeyEvent.VK_ENTER;

/**
 * {@code Wordle} is the game window. It includes 6*5 blocks for users to guess words, and also displays some prompt message to players.
 * Top Message TextField:
 * <ul>
 *     <li>"Input is Not a Letter!" means you can't input a non-letter character.</li>
 *     <li>"Input Must be 5 Letters!" means you can input no less than 5 letters per guess.</li>
 *     <li>"Enter Up to 5 Letters!" means you can input no more than 5 letters per guess.</li>
 *     <li>"Spelling Mistake!" means you can't input invalid words.</li>
 *     <li>"Congratulations!" means you win this game!</li>
 * </ul>
 * Bottom Message TextField:
 * <ul>
 *     <li>"Press Enter to Check" means you need to press enter key to check.</li>
 *     <li>"You win! Answer is *****" means you win this game!</li>
 * </ul>
 *
 * @author Tianwei Jiang
 */
public class Wordle extends JFrame {
    /**
     * A {@code TxtReader} object to read words in words.txt.
     */
    private static TxtReader tr;
    /**
     * An arraylist of letterBlock to modifies style and content of blocks.
     */
    private static ArrayList<LetterBlock> tFList;
    /**
     * Number of letters in a row.
     */
    private static final int LEN_COLUMN=5;
    /**
     * Number of attempts in a round
     */
    private static final int LEN_ROW=6;
    /**
     * The width of the entire window.
     */
    private final int WINDOW_WIDTH=550;
    /**
     * The height of the entire window.
     */
    private static final int WINDOW_HEIGHT=750;
    /**
     * The height of the message textField.
     */
    private static final int MESSAGE_HEIGHT=40;
    /**
     * The distance between topMessageField and top border.
     */
    private final int TOP_FIELD_TOP_MARGIN=20;
    /**
     * A textField at the top of window, displays specifications of input to players.
     */
    private MessageTextField topMessageField=new MessageTextField("Press Letter to Input",0,TOP_FIELD_TOP_MARGIN,WINDOW_WIDTH,MESSAGE_HEIGHT);
    /**
     * A textField at the bottom of window, displays game status to players.
     */
    private MessageTextField bottomMessageField=new MessageTextField("Press Enter to Check",0, Wordle.BLOCKS_TOP_MARGIN+ Wordle.CONTENT_HEIGHT+TOP_FIELD_TOP_MARGIN,WINDOW_WIDTH,MESSAGE_HEIGHT);
    /**
     * The distance between blocks and top border.
     */
    private static final int BLOCKS_TOP_MARGIN=70;
    /**
     * The distance between blocks and left border.
     */
    private final int LEFT_MARGIN=50;
    /**
     * The width of 6*5 blocks.
     */
    private final int CONTENT_WIDTH=WINDOW_WIDTH-LEFT_MARGIN*2;
    /**
     * The height of 6*5 blocks.
     */
    private static final int CONTENT_HEIGHT=WINDOW_HEIGHT-BLOCKS_TOP_MARGIN*2-MESSAGE_HEIGHT*2;
    /**
     * The width and height of each letter block.
     */
    private final int BLOCK_SIZE=70;
    /**
     * The padding between 2 adjacent letter blocks in message textField in x direction.
     */
    private final int BLOCK_X_PADDING=(CONTENT_WIDTH-BLOCK_SIZE*LEN_COLUMN)/4;
    /**
     * The padding between 2 adjacent letter blocks in message textField in y direction.
     */
    private final int BLOCK_Y_PADDING=(CONTENT_HEIGHT-BLOCK_SIZE*LEN_ROW)/5;
    /**
     * {@code status==0} means this letter is not checked.
     */
    private final int LETTER_NOT_CHECK=0;
    /**
     * A 2-dimensional array that stores input of players.
     */
    private static char[][] content=new char[LEN_ROW][LEN_COLUMN];
    /**
     * A 2-dimensional array that stores status of each letter blocks. (0: Not checked, 1: yellow, 2: green, -1: grey)
     */
    public static int[][] status=new int[LEN_ROW][LEN_COLUMN];
    /**
     * A pointer indicates current input position of players in x direction.
     */
    public static int pointer_R=0;
    /**
     * A pointer indicates current input position of players in y direction.
     */
    public static int pointer_C=0;
    /**
     * A regex expression to judge whether input character is a letter.
     */
    private final String REGEX="[A-Z]+";
    /**
     * The time the game started.
     */
    public final static long START_TIME=System.currentTimeMillis();

    /**
     * This method initializes a {@code Wordle} Window.
     */
    public Wordle() throws IOException {
        this.init();
        for(int i=0;i<LEN_ROW;i++){
            for(int j=0;j<LEN_COLUMN;j++){
                int COLUMN_LIMIT = 1;
                LetterBlock textField=new LetterBlock(COLUMN_LIMIT,
                        "",
                        LEFT_MARGIN+j*(BLOCK_X_PADDING+BLOCK_SIZE),
                        BLOCKS_TOP_MARGIN+i*(BLOCK_Y_PADDING+BLOCK_SIZE),
                        BLOCK_SIZE,
                        BLOCK_SIZE,
                        LETTER_NOT_CHECK);
                this.add(textField);
                tFList.add(textField);
            }
        }

        this.add(topMessageField);
        this.add(bottomMessageField);

        //Get players' inputs.
        this.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e) {
                if(pointer_R<6){
                    if(e.getKeyChar()!=KeyEvent.VK_ENTER&&e.getKeyChar()!=KeyEvent.VK_BACK_SPACE){
                        String s=""+Character.toUpperCase(e.getKeyChar());
                        if(!s.matches(REGEX))topMessageField.setText("Input Is not a Letter!");
                        else if(pointer_C>=5)topMessageField.setText("Only 5 Letters Can Be Entered");
                        else{
                            topMessageField.setText("Press Letter to Input");
                            content[pointer_R][pointer_C]=Character.toUpperCase(e.getKeyChar());
                            pointer_C+=1;
                            refreshTextField();
                        }
                    }
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(pointer_R<6){
                    topMessageField.setText("Press Letter to Input");
                    if(e.getKeyCode()==VK_BACK_SPACE){
                        if(pointer_C>0){
                            content[pointer_R][pointer_C-1]='0';
                            pointer_C-=1;
                        }
                    }
                    if(e.getKeyCode()==VK_ENTER){
                        if(pointer_C!=5){
                            //input less than 5 letters
                            topMessageField.setText("Only 5 Letters Can Be Entered");
                        }
                        else {
                            try {
                                String guess=""+content[pointer_R][0]+
                                        content[pointer_R][1]+
                                        content[pointer_R][2]+
                                        content[pointer_R][3]+
                                        content[pointer_R][4];
                                //check spell
                                if(!tr.spellCheck(guess)) topMessageField.setText("Word Not Found!");
                                else{
                                    //compare it with answer
                                    boolean flag=tr.Compare(guess);
                                    refreshTextField();
                                    if(pointer_R==6){
                                        if(flag){
                                            topMessageField.setText("Congratulations!");
                                            bottomMessageField.setText("You win! Answer Is "+guess);
                                            new Result(Wordle.this,"Game Clear!",true,true);
                                        }
                                        else {
                                            topMessageField.setText("Game Over");
                                            bottomMessageField.setText("Oops! Answer Is "+TxtReader.answer);
                                            new Result(Wordle.this,"Game Over!",true,false);
                                        }
                                    }
                                }
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }

                    }
                    refreshTextField();
                }
            }
        });

        this.setTitle("Wordle Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setVisible(true);
    }

    /**
     *  This method is to update the content and style of letter blocks.
     */
    private void refreshTextField(){
        int i=0,j=0;
        for(LetterBlock tf:tFList){
            if(i<6){
                if(content[i][j]!='0'){
                    tf.setText(""+content[i][j]);
                }
                else {
                    tf.setText("");
                }
            }
            tf.setStatus(status[i][j]);
            if(j<4)j+=1;
            else{
                i+=1;
                j=0;
            }
        }
    }

    /**
     * This method is used to init a Wordle game.
     */
    private void init() throws IOException {
        //choose a word as answer
        tr = new TxtReader();
        tFList=new ArrayList<>();
        pointer_R=0;
        pointer_C=0;
        for(int i=0;i<LEN_ROW;i++){
            for(int j=0;j<LEN_COLUMN;j++){
                content[i][j]='0';
                status[i][j]=0;
            }
        }
        refreshTextField();
        topMessageField.setText("Press Letter to Input");
        bottomMessageField.setText("Press Enter to Check");
    }
}