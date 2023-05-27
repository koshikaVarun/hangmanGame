package hangman;

import java.util.Scanner;
import java.util.Random;

public class HangmanGame {
    private String[] wordList = {"apple", "banana", "orange", "grape", "watermelon"};
    private String word;
    private char[] guessedLetters;
    private int tries;

    public HangmanGame() {
        Random random = new Random();  // Create a new Random object for generating random numbers
        word = wordList[random.nextInt(wordList.length)];  // Select a random word from the wordList array
        guessedLetters = new char[word.length()];  // Initialize the guessedLetters array with the length of the selected word
        tries = 6;  // Set the initial number of tries
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);  // Create a new Scanner object for reading user input

        while (tries > 0) {  // Continue the game while there are remaining tries
            String guessedWord = "";  // Initialize an empty string to store the guessed word

            for (int i = 0; i < word.length(); i++) {
                if (guessedLetters[i] != '\u0000') {  // If the letter at index i has been guessed
                    guessedWord += guessedLetters[i];  // Append the guessed letter to the guessedWord string
                } else {
                    guessedWord += "_";  // Append an underscore to represent an unguessed letter
                }
            }

            if (guessedWord.equals(word)) {  // If the guessedWord matches the selected word
                System.out.println("Congratulations! You guessed the word: " + word);
                break;  // Exit the loop and end the game
            }

            System.out.println("Guess the word: " + guessedWord);  // Display the current state of the guessed word
            System.out.println("Tries left: " + tries);  // Display the number of remaining tries
            System.out.print("Enter a letter: ");
            char guess = scanner.nextLine().charAt(0);  // Read the user's letter guess
            guess = Character.toLowerCase(guess);  // Convert the guess to lowercase for consistency

            if (isLetterGuessed(guess)) {  // If the letter has already been guessed
                System.out.println("You already guessed that letter. Try again!");
                continue;  // Skip the rest of the loop and prompt for another guess
            }

            if (!updateGuessedLetters(guess)) {  // If the guess is incorrect
                tries--;  // Decrement the number of remaining tries
                System.out.println("Incorrect guess!");
            }
        }

        if (tries == 0) {  // If the player has used all their tries
            System.out.println("You lost! The word was: " + word);
        }

        scanner.close();  // Close the Scanner object
    }

    private boolean isLetterGuessed(char letter) {
        for (char c : guessedLetters) {
            if (c == letter) {
                return true;  // If the letter is found in the guessedLetters array, return true
            }
        }
        return false;  // If the letter is not found, return false
    }

    private boolean updateGuessedLetters(char letter) {
        boolean isLetterPresent = false;  // Variable to track if the guessed letter is present in the word
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {  // If the letter matches a letter in the word
                guessedLetters[i] = letter;  // Update the guessedLetters array at the corresponding index
                isLetterPresent = true;  // Set the flag to true
            }
        }
        return isLetterPresent;  // Return whether the guessed letter is present in the word or not
    }

    public static void main(String[] args) {
        HangmanGame game = new HangmanGame();  // Create a new instance of the HangmanGame class
        game.play();  // Start the game
    }
}

