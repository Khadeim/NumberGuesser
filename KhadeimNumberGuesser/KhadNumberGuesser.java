import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class KhadNumberGuesser {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;
        int totalScore = 0;
        ArrayList<Integer> highScores = new ArrayList<>();
        
        System.out.println("Welcome to Khad's Number Guessing Game with Scoring and Leaderboard!");
        
        while (playAgain) {
            // Ask for difficulty
            System.out.println("\nChoose difficulty level:");
            System.out.println("1 - Easy (1 to 50)");
            System.out.println("2 - Medium (1 to 100)");
            System.out.println("3 - Hard (1 to 200)");
            System.out.print("Enter 1, 2, or 3: ");
            int difficulty = scanner.nextInt();
            
            int min = 1;
            int max;
            int basePoints;
            switch (difficulty) {
                case 1:
                    max = 50;
                    basePoints = 50;
                    break;
                case 3:
                    max = 200;
                    basePoints = 200;
                    break;
                default:
                    max = 100;
                    basePoints = 100;
                    break;
            }
            
            int numberToGuess = random.nextInt(max - min + 1) + min;
            int numberOfTries = 0;
            boolean hasGuessed = false;
            
            System.out.println("\nI have selected a number between " + min + " and " + max + ". Can you guess it?");
            
            while (!hasGuessed) {
                System.out.print("Enter your guess: ");
                int guess = scanner.nextInt();
                numberOfTries++;
                
                if (guess < numberToGuess) {
                    System.out.println("Too low! Try again.");
                } else if (guess > numberToGuess) {
                    System.out.println("Too high! Try again.");
                } else {
                    hasGuessed = true;
                    int roundScore = Math.max(basePoints - (numberOfTries - 1) * 10, 10);
                    totalScore += roundScore;
                    System.out.println("Congratulations! You guessed the number in " + numberOfTries + " tries.");
                    System.out.println("You earned " + roundScore + " points this round.");
                    System.out.println("Your total score is: " + totalScore);
                    
                    // Update leaderboard
                    highScores.add(totalScore);
                    Collections.sort(highScores, Collections.reverseOrder());
                    if (highScores.size() > 3) {
                        highScores.remove(highScores.size() - 1); // keep top 3
                    }
                    
                    System.out.println("\n--- Leaderboard (Top Scores) ---");
                    for (int i = 0; i < highScores.size(); i++) {
                        System.out.println((i + 1) + ". " + highScores.get(i) + " points");
                    }
                }
            }
            
            System.out.print("\nDo you want to play again? (yes/no): ");
            String response = scanner.next().trim().toLowerCase();
            if (!response.equals("yes") && !response.equals("y")) {
                playAgain = false;
                System.out.println("\nThanks for playing! Your final score is: " + totalScore);
                System.out.println("--- Final Leaderboard ---");
                // iterate through the high scores and print them, i++
                for (int i = 0; i < highScores.size(); i++) {
                    System.out.println((i + 1) + ". " + highScores.get(i) + " points");
                }
                System.out.println("Goodbye!");
            }
        }
        
        scanner.close();
    }
}