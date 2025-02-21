package main.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;

public class GameWord {
    private final String word;

    public GameWord() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("wordsForTheGallows.txt")) {
            if (inputStream == null) {
                throw new RuntimeException("wordsForTheGallows.txt not found");
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            List<String> words = bufferedReader.lines().toList();
            if (words.isEmpty()) {
                throw new RuntimeException("wordsForTheGallows.txt is empty");
            }

            Random random = new Random();
            word = words.get(random.nextInt(words.size())).toLowerCase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getWord() {
        return word;
    }

    public String getHiddenWord(List<String> letters) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            String substring = word.substring(i, i + 1);

            if (letters.contains(substring)) {
                sb.append(substring);
            } else {
                sb.append("_");
            }
        }
        return sb.toString();
    }
}
