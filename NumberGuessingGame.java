import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();
    private static int minNumber = 1; // Minimum number in the range
    private static int maxNumber = 100; // Maximum number in the range
    private static int maxAttempts = 5; // Maximum number of attempts allowed
    private static int rounds = 0;
    private static int totalAttempts = 0;
    private static int totalScore = 0;

    public static void main(String[] args) {
        boolean playAgain = true;

        System.out.println("Welcome to the Number Guessing Game!");

        while (playAgain) {
            rounds++;
            System.out.println("\nRound " + rounds + ":");
            int score = playRound();

            totalAttempts += maxAttempts;
            totalScore += score;

            System.out.println("\nRound " + rounds + " Summary:");
            System.out.println("Attempts: " + maxAttempts);
            System.out.println("Score: " + score);
            System.out.println("Total Attempts: " + totalAttempts);
            System.out.println("Total Score: " + totalScore);

            System.out.print("\nDo you want to play another round? (yes/no): ");
            String playAgainChoice = scanner.nextLine();

            if (!playAgainChoice.equalsIgnoreCase("yes")) {
                playAgain = false;
            }
        }

        System.out.println("\nFinal Score:");
        System.out.println("Total Rounds: " + rounds);
        System.out.println("Total Attempts: " + totalAttempts);
        System.out.println("Total Score: " + totalScore);
        System.out.println("Average Score per Round: " + (double) totalScore / rounds);

        System.out.println("\nThank you for playing the Number Guessing Game!");
    }

    private static int playRound() {
        int targetNumber = getRandomNumber(minNumber, maxNumber);
        System.out.println("I have generated a number between " + minNumber + " and " + maxNumber + ". Guess the number!");

        int attempts = 0;
        int score = 0;
        boolean guessedCorrectly = false;

        while (!guessedCorrectly && attempts < maxAttempts) {
            System.out.print("Enter your guess: ");
            int guess = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            attempts++;

            if (guess == targetNumber) {
                guessedCorrectly = true;
                score = calculateScore(attempts);
                System.out.println("Congratulations! You guessed the correct number!");
            } else if (guess < targetNumber) {
                System.out.println("Too low! Try again.");
            } else {
                System.out.println("Too high! Try again.");
            }
        }

        return score;
    }

    private static int getRandomNumber(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    private static int calculateScore(int attempts) {
        if (attempts == 1) {
            return 3; // Guessed correctly on the first attempt (3 points)
        } else if (attempts == 2) {
            return 2; // Guessed correctly on the second attempt (2 points)
        } else {
            return 1; // Guessed correctly on the third attempt or more (1 point)
        }
    }
}
