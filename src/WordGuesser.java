import java.io.File;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.String.format;
import static java.lang.System.*;
import static java.util.Arrays.*;
import static java.util.Objects.*;

public class WordGuesser {
    public static void main(String [] args) throws Exception{
        String word = getword();
        boolean guessed = false;
        String[] letters = removeDuplicates(requireNonNull(word).split(""));
        ArrayList<String> rguessedletters = new ArrayList<>();
        ArrayList<String> wguessedletters = new ArrayList<>();
        Scanner scanner = new Scanner(in);
        int tries = 0;
        while (wguessedletters.size() < 10) {
            String ask = "You are now guessing:";
            for (String letter : requireNonNull(word).split(""))
                if (!(rguessedletters.contains(letter))) ask = format("%s_", ask);
                else ask = format("%s%s", ask, letter);
            String guessedletters = "";
            for (String w : wguessedletters) guessedletters = format("%s%s, ", guessedletters, w);
            for (String r : rguessedletters) guessedletters = format("%s%s, ", guessedletters, r);
            if (guessedletters.length()>0) guessedletters = guessedletters.substring(0, guessedletters.length()-2);
            out.printf("%s\nYou have guessed these letter(s):\n%s\nYou have %s more chance(s).\nGuess a letter or the word:", ask, guessedletters, 10 - wguessedletters.size());
            String guess = scanner.next();
            if (guess.equals(word)){
                guessed = true;
                break;
            }
            if (guess.length()!=1 || !("abcdefghijklmnopqrstuvwxyz").contains(guess)){
                out.println(guess+" isn't a letter.\n");
                continue;
            }
            tries++;
            if (wguessedletters.contains(guess) || rguessedletters.contains(guess)) out.println("You already guessed " + guess + "!\n");
            else if (asList(letters).contains(guess)) {
                rguessedletters.add(guess);
                out.println(guess+" was a letter!\n");
                if (rguessedletters.size() == letters.length) {
                    guessed = true;
                    break;
                }
            } else {
                out.println("Nope, "+guess+" isn't in the word!\n");
                wguessedletters.add(guess);
            }
        }
        if (guessed) out.println("You did it in " + tries + " tries!\nThe word was " + word + ".");
        else out.println("Better luck next time!\nThe word was " + word + ".");
    }
    private static String getword() throws Exception{
        File file = new File("words.txt");
        Scanner scanner = new Scanner(file);
        int word_index = (int) (random()*1000);
        String word = null;
        while (word_index>0){
            word = scanner.next();
            word_index--;
        }
        return word;
    }
    private static String[] removeDuplicates(String[] arr){
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
        arraycopy(arr, 0, whitelist, 0, end);
        return whitelist;
    }
}
