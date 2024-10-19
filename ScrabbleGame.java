// Jasmin Cervantes
// Date: 10.17.2024
// I was not able to record a meeting because my group did not respond, I included the screenshots in Canvas
// I will turn in my response to the questions in a document that will be submitted to Canvas


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ScrabbleGame {
    public static void main (String[] args) {
        List<Word> words = new ArrayList<>(); //creates new array list that stores objects of the Word class

        //Read words from the text file "CollingScrabbleWords_2019" and populates the ArrayList
        try (BufferedReader br = new BufferedReader(new FileReader("CollinsScrabbleWords_2019.txt"))) {
            String line;
            while ((line = br.readLine()) != null) { //reads the file line by line until there is no more lines
                words.add(new Word(line.trim())); //the ".trim" helps trim any whitespace before or after the word
            }
        } catch (IOException e) { //catches if there was an error reading the file
            System.err.println("Error reading file: " + e.getMessage());
            return; 
        }

        //Sort the words
        Collections.sort(words);

        //Choose 4 random characters
        String randomLetters = generateRandomLetters();
        System.out.println("Use the letters: " + randomLetters);

        //Allows user to exchange one letter
        randomLetters = exchangeLetter(randomLetters);

        //Ask user for input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a word made from these letters: ");
        String userInput = scanner.nextLine().trim();

        //Validate the user input word
        if (canFormWord(userInput, randomLetters) && binarySearch(words, new Word(userInput)) != -1) {
            System.out.println(userInput + " is a valid word!");
        } else {
            System.out.println(userInput + " is not a valid word.");
        }

        scanner.close();
    }

    //Method to generate 4 random letters
    private static String generateRandomLetters() {
        Random random = new Random();
        StringBuilder letters = new StringBuilder();
        for (int i = 0; i < 4; i++) { 
            letters.append((char) ('a' + random.nextInt(26))); //Generates random lowercase letters
        } //this for loop iterates 4 times from index 0 to 25 where the 4 random letters will be chosen from
        return letters.toString(); //returns letters in a string
    }

    //Method to allow one use to exchange one letter
    private static String exchangeLetter(String letters) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Would you like to exchange a letter? (yes/no): "); //asks if the player would like to exchange the letter
        String  response = scanner.nextLine().trim();

        if (response.equals("yes")) {
            System.out.print("Enter the letter you want to exchange: ");
            char letterToExchange = scanner.nextLine().trim().charAt(0);

            if (letters.indexOf(letterToExchange) == -1) {
                System.out.println("You do not have that letter.");
                return letters; //Return unchanged letters
            }

            //Generate a new random letter
            char newLetter = (char) ('a' + new Random().nextInt(26));
            System.out.println("Exchanging " + letterToExchange + " with " + newLetter);

            //Replace the Letter
            StringBuilder updatedLetters = new StringBuilder(letters);
            int index = updatedLetters.indexOf(Character.toString(letterToExchange));
            if (index != -1) {
                updatedLetters.setCharAt(index, newLetter); //Replace the Letter
            }

            return updatedLetters.toString(); // Return updated Letters
        }

        return letters;
    }

    //Check if the word can be formed with the given Letters
    private static boolean canFormWord(String word, String letters) {
        for (char c : word.toCharArray()) {
            if (letters.indexOf(c) == -1) {
                return false; // Character not found in available Letters
            }
        }
        return true; //All characters are found in the available letters
    }

    //Binary search method
    private static int binarySearch(List<Word> words, Word target) {
        int low = 0;
        int high = words.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int comparison = words.get(mid).compareTo(target);
            if (comparison == 0) {
                return mid; //Found
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1; //Not found
    }
}