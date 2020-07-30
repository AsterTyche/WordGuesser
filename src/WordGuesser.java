import java.io.File;
import java.util.*;
import static java.lang.System.*;

public class WordGuesser {
    public static void main(String [] args) throws Exception{
        String word = getword();
        boolean guessed = false;
        String[] letters = removeDuplicates(Objects.requireNonNull(word).split(""));
        ArrayList<String> rguessedletters = new ArrayList<>();
        ArrayList<String> wguessedletters = new ArrayList<>();
        Scanner ascanner = new Scanner(in);
        int tries = 0;
        for (int x = 0; x < 6; x++) {
            String ask = "You are now guessing:";
            for (String letter : Objects.requireNonNull(word).split("")) {
                if (!(rguessedletters.contains(letter))) ask = String.format("%s_", ask);
                else ask = String.format("%s%s", ask, letter);
            }
            String wrong = "";
            for (String w : wguessedletters) wrong = String.format("%s%s, ", wrong, w);
            if (wrong.length()>0) wrong = wrong.substring(0, wrong.length()-2);
            out.println(ask+"\nYou have guessed "+wguessedletters.size()+" wrong letter(s):\n"+wrong+"\nGuess a letter:");
            String guess = ascanner.next();
            if (guess.length()!=1){
                out.println(guess+" isn't a letter.");
                continue;
            }
            tries++;
            if (wguessedletters.contains(guess) || rguessedletters.contains(guess)) {
                out.println("You already guessed "+guess+"!");
            } else if (Arrays.asList(letters).contains(guess)) {
                rguessedletters.add(guess);
                out.println(guess+" was a letter!");
                if (letters.length == rguessedletters.size()) {
                    guessed = true;
                    break;
                }
            } else {
                out.println("Nope, "+guess+" isn't a letter!");
                wguessedletters.add(guess);
            }
        }
        if (guessed) out.println("\nYou did it in " + tries + " tries!\nThe word was " + word + ".");
        else out.println("\nBetter luck next time!\nThe word was " + word + ".");
    }
    public static String getword() throws Exception{
        File file = new File("words.txt");
        Scanner fscanner = new Scanner(file);
        int word_index = (int) (Math.random()*1000);
        String word = null;
        while (word_index>0){
            word = fscanner.next();
            word_index--;
        }
        return word;
    }
    public static String[] removeDuplicates(String[] arr){
        int end = arr.length;
        for (int i = 0; i < end; i++) {
            for (int j = i + 1; j < end; j++) {
                if (arr[i].equals(arr[j])) {
                    arr[j] = arr[end-1];
                    end--;
                    j--;
                }
            }
        }
        String[] whitelist = new String[end];
        System.arraycopy(arr, 0, whitelist, 0, end);
        return whitelist;
    }
}
