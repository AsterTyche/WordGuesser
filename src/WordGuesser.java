import java.io.File;
import java.util.*;
import static java.lang.System.*;

public class WordGuesser {
    public static void main(String [] args) throws Exception{
        File file = new File("words.txt");
        Scanner fscanner = new Scanner(file);
        int word_index = (int) (Math.random()*1000);
        String word = null;
        while (word_index>0){
            word = fscanner.next();
            word_index--;
        }
        boolean guessed = false;
        out.println(Objects.requireNonNull(word).split("").length);
        String[] letters = removeDuplicates(Objects.requireNonNull(word).split(""));
        out.println(letters.length);
        List<String> rguessedletters = new ArrayList<>();
        List<String> wguessedletters = new ArrayList<>();
        Scanner ascanner = new Scanner(in);
        while (!guessed) {
            String ask = "You are now guessing:";
            for (String letter : Objects.requireNonNull(word).split("")) {
                if (!(rguessedletters.contains(letter))) ask += "_";
                else ask = String.format("%s%s", ask, letter);
            }
            out.println(ask);
            out.println("You have guessed "+wguessedletters.size()+" wrong letter(s).");
            out.println("Guess a letter:");
            String guess = ascanner.next();
            if (wguessedletters.contains(guess) || rguessedletters.contains(guess)) {
                out.println("You already guessed "+guess+"!");
            } else if (Arrays.asList(letters).contains(guess)) {
                rguessedletters.add(guess);
                out.println(guess+" was a letter!");
                if (letters.length == rguessedletters.size()) {
                    guessed = true;
                }
            } else {
                out.println("Sorry, you got it wrong.");
                wguessedletters.add(guess);
            }
        }
        out.println("You did it!");
        out.println("The word was "+word+".");
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
