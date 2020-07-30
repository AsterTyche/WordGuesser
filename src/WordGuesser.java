import java.io.File;
import java.util.*;
import static java.lang.System.*;

public class WordGuesser {
    public static void main(String [] args_) throws Exception{
        File file = new File("words.txt");
        Scanner fscanner = new Scanner(file);
        int word_index = (int) (Math.random()*1000);
        String word = null;
        while (word_index>0){
            word = fscanner.next();
            word_index--;
        }
        boolean guessed = false;
        String[] letters = removeDuplicates(Objects.requireNonNull(word).split(""));
        List<String> rguessedletters = new ArrayList<>();
        List<String> wguessedletters = new ArrayList<>();
        Scanner ascanner = new Scanner(in);
        while (!guessed) {
            String ask = "You are now guessing:";
            for (String letter : letters) {
                if (!(rguessedletters.contains(letter))) ask += "_";
                else ask = String.format("%s%s", ask, letter);
            }
            out.println(ask);
            out.println("You have guessed "+wguessedletters.size()+" wrong letter(s).");
            out.println("Guess a letter:");
            String guess = ascanner.next();
            if (Arrays.asList(letters).contains(guess)) {
                rguessedletters.add(guess);
                out.println("That was a letter!");
                if (letters.length == rguessedletters.size()) guessed = true;
            } else if (wguessedletters.contains(guess) || rguessedletters.contains(guess)) {
                out.println("You already guessed that!");
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
