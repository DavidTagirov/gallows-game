package main.java;

import java.util.*;

public class Game {
    private static final Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("Welcome to the gallows!\n");
        System.out.println("""
                The rules of the game:
                    The program writes a random existing noun word.
                    The player enters a letter that can be included in this word. If there is such a letter in a word,
                    then the program writes it over the corresponding lines of this letter — as many times as it occurs
                    in the word. If there is no such letter, then a circle in a loop representing the head is added to
                    the gallows.
                    The player continues to guess the letters until he guesses the whole word. For each incorrect answer,
                    one part of the torso is added to the gallows (There are 6 of them in total: the head, torso, 2 arms and 2 legs).
                    If the torso in the gallows is completely drawn, then the guessing player loses, and is considered hanged.
                    If the player manages to guess the word, he wins.
                """);
        while (true) {
            System.out.println("Press '1' if you want to start the new game and '0' if you want to finish it.");

            try {
                if (Integer.parseInt(scanner.nextLine()) == 0) {
                    System.out.println("A new game has not been started, you can restart the game if you want to play again.");
                    return;
                }
            } catch (NumberFormatException e) {
                throw new RuntimeException(e + "Enter a number.");
            }

            startCycle();
        }
    }

    private void startCycle() {
        GameWord gameWord = new GameWord();
        String word = gameWord.getWord();
        String hiddenWord;

        Gallows gallows = new Gallows();
        int mistakes = 0;

        List<String> correctLetters = new LinkedList<>();
        StringBuilder mistakeLetters = new StringBuilder();
        String letter;

        while (true) {
            hiddenWord = gameWord.getHiddenWord(correctLetters);
            System.out.println("The hidden word: " + hiddenWord);
            System.out.println("Mistakes(" + mistakes + "): " + mistakeLetters);
            System.out.println(gallows.createGallows(mistakes));
            System.out.print("Enter the letter: ");
            letter = scanner.nextLine();
            letter = letter.toLowerCase().trim();

            if (letter.length() != 1) {
                System.out.println("Error! You have entered several characters, enter one letter.");
                continue;
            } else if (!Character.isLetter(letter.charAt(0))) {
                System.out.println("Error! You have entered an incorrect character, enter the letter.");
                continue;
            } else if ((letter.indexOf(0) == 0)) {
                System.out.println("You are out of the game.");
                break;
            } else if (mistakeLetters.substring(0, mistakeLetters.length()).contains(letter)
                    || correctLetters.contains(letter)) {
                System.out.println("You have already entered this letter, try to enter another one.");
                continue;
            }

            if (word.contains(letter)) {
                correctLetters.add(letter);
                hiddenWord = gameWord.getHiddenWord(correctLetters);
            } else {
                mistakeLetters.append(letter).append(", ");
                mistakes++;
            }

            if (isGameWon(word, hiddenWord)) {
                System.out.println("The game is over! You've won!");
                System.out.println("The word was: " + word);
                break;
            } else if (isGameOver(mistakes)) {
                System.out.println("\nThe game is over! You've lost!");
                System.out.println("The word was: " + word);
                System.out.println("Mistakes(" + mistakes + "): " + mistakeLetters);
                gallows.createGallows(mistakes);
                System.out.println(gallows.createGallows(mistakes));
                break;
            }
        }
    }

    private boolean isGameWon(String word, String hiddenWord) {
        return word.equals(hiddenWord);
    }

    private boolean isGameOver(int mistakes) {
        return mistakes == 6;
    }
}