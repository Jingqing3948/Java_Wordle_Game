import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 * {@code TxtReader} reads words.txt file, chooses a word as answer to guess, and compares players input with word list.
 * @author      Tianwei Jiang
 */
public class TxtReader {
    /**
     * A String choose from words.txt as answer.
     */
    public static String answer;
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
      * read words dictionary file, and choose a word as answer.
      */
    public TxtReader() throws IOException {
        Random r=new Random();
        int line=r.nextInt(2315);
        System.out.print("line "+line+": ");
        BufferedReader br=new BufferedReader(new FileReader("words.txt"));
        int cnt=0;
        while(cnt<=line){
            answer=br.readLine();
            cnt++;
        }
        br.close();
        System.out.println(answer);
    }

    /**
      * Compare input with the answer.
      * @param guess    a String words entered by the player.
      * @return boolean    true if guess is the answer, false otherwise.
      */
    public boolean Compare(String guess){
        if(guess.equals(answer)){
            //game clear!
            for(int i=0;i<5;i++) Wordle.status[Wordle.pointer_R][i]=2;
            Wordle.pointer_R=6;
            Wordle.pointer_C=0;
            return true;
        }
        else{
            for(int i=0;i<5;i++) {
                if(answer.charAt(i)==guess.charAt(i)){
                    Wordle.status[Wordle.pointer_R][i]=LETTER_GREEN;
                }

            }
            for(int i=0;i<5;i++){
                if(Wordle.status[Wordle.pointer_R][i]==LETTER_GREEN)continue;
                else if(Wordle.status[Wordle.pointer_R][0]!=LETTER_GREEN&&answer.charAt(0)==guess.charAt(i)||
                        Wordle.status[Wordle.pointer_R][1]!=LETTER_GREEN&&answer.charAt(1)==guess.charAt(i)||
                        Wordle.status[Wordle.pointer_R][2]!=LETTER_GREEN&&answer.charAt(2)==guess.charAt(i)||
                        Wordle.status[Wordle.pointer_R][3]!=LETTER_GREEN&&answer.charAt(3)==guess.charAt(i)||
                        Wordle.status[Wordle.pointer_R][4]!=LETTER_GREEN&&answer.charAt(4)==guess.charAt(i)){
                    Wordle.status[Wordle.pointer_R][i]=LETTER_YELLOW;
                }
                else {
                    Wordle.status[Wordle.pointer_R][i]=LETTER_GREY;
                }
            }
            Wordle.pointer_C=0;
            Wordle.pointer_R+=1;
            return false;
        }
    }

    /**
      * check whether guess is a valid word.
      * @param guess        a String words entered by the player.
      * @return boolean     true if guess is in word dictionary, false otherwise.
      */
    public boolean spellCheck(String guess) throws IOException {
        BufferedReader br=new BufferedReader(new FileReader("words.txt"));
        String line=br.readLine();
        while(line!=null){
            line=br.readLine();
            if(guess.equals(line)){
                return true;
            }
        }
        br.close();
        return false;
    }
}