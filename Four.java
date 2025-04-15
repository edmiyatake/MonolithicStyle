import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Four {
    /*
    MONOLITHIC STYLE CONSTRAINTS
    1. No named abstractions
    2. No use of libraries (except to read in a file I assume)
     */
    public static void main(String[] args) {
        String[] word_freqs;
        String[] stopWords = new String[0];
        String textFileName = args[0];
        String stopWordsFileName = args[1];

        // read in the stop words text file
        // problem is I can't use ArrayList, so I have to swap with a new array everytime (array is fixed size)
        try (BufferedReader stopList = new BufferedReader(new FileReader(stopWordsFileName))) {
            String line;
            while ((line = stopList.readLine()) != null) {
                String[] wordsInLine = line.split(",");
                for (String stopWord : wordsInLine) {
                    // Resize the array by one and copy existing elements
                    String[] newStopWords = new String[stopWords.length + 1];
                    System.arraycopy(stopWords, 0, newStopWords, 0, stopWords.length);
                    newStopWords[stopWords.length] = stopWord; // Add new word
                    stopWords = newStopWords; // Update reference
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        // now we have to do the same thing with the actual input file and keep track of the frequencies without hashmap

        String[] words = new String[0];
        int[] counts = new int[0];

        // Read input and track frequency
        try (BufferedReader inputReader = new BufferedReader(new FileReader(textFileName))) {
            String line;
            while ((line = inputReader.readLine()) != null) {
                String[] inputWords = line.split("\\s+");

                for (String word : inputWords) {
                    word = word.replaceAll("\\W", "").toLowerCase(); // clean and normalize
                    if (word.isEmpty() || isStopWord(word, stopWords)) continue;

                    int index = findIndex(word, words);
                    if (index != -1) {
                        counts[index]++;
                    } else {
                        // Add new word and count
                        String[] newWords = new String[words.length + 1];
                        int[] newCounts = new int[counts.length + 1];
                        System.arraycopy(words, 0, newWords, 0, words.length);
                        System.arraycopy(counts, 0, newCounts, 0, counts.length);
                        newWords[words.length] = word;
                        newCounts[counts.length] = 1;
                        words = newWords;
                        counts = newCounts;
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        sortByFrequency(words, counts); // sort arrays by frequency

        int N = 25; // Top N words to print
        System.out.println("Top " + N + " words:");
        for (int i = 0; i < N && i < words.length; i++) {
            System.out.println(words[i] + ": " + counts[i]);
        }

    }
    // To spare my eyeballs, I will use helper functions just for organization
    private static boolean isStopWord(String word, String[] stopWords) {
        for (String stop : stopWords) {
            if (word.equals(stop)) {
                return true;
            }
        }
        return false;
    }

    // Helper: find index of word in words array
    private static int findIndex(String word, String[] words) {
        for (int i = 0; i < words.length; i++) {
            if (word.equals(words[i])) {
                return i;
            }
        }
        return -1;
    }

    private static void sortByFrequency(String[] words, int[] counts) {
        for (int i = 0; i < counts.length - 1; i++) {
            int maxIdx = i;
            for (int j = i + 1; j < counts.length; j++) {
                if (counts[j] > counts[maxIdx]) {
                    maxIdx = j;
                }
            }
            // Swap counts
            int tempCount = counts[i];
            counts[i] = counts[maxIdx];
            counts[maxIdx] = tempCount;

            // Swap corresponding words
            String tempWord = words[i];
            words[i] = words[maxIdx];
            words[maxIdx] = tempWord;
        }
    }
}
