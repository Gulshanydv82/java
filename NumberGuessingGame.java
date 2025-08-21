import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        final int MIN = 1;
        final int MAX = 100;
        final int MAX_ATTEMPTS = 7;

        int roundsPlayed = 0;
        int roundsWon = 0;

        boolean playAgain = true;

        System.out.println("🎯 Welcome to the Number Guessing Game!");
        System.out.println("Guess the number between " + MIN + " and " + MAX);
        System.out.println("You have " + MAX_ATTEMPTS + " attempts per round.");

        while (playAgain) {
            roundsPlayed++;
            int secretNumber = random.nextInt(MAX - MIN + 1) + MIN;
            int attemptsLeft = MAX_ATTEMPTS;
            boolean guessedCorrectly = false;

            while (attemptsLeft > 0) {
                System.out.print("Enter your guess (" + attemptsLeft + " attempts left): ");

                int guess;
                try {
                    guess = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("❌ Please enter a valid number.");
                    continue;
                }

                if (guess < MIN || guess > MAX) {
                    System.out.println("⚠️ Your guess must be between " + MIN + " and " + MAX);
                    continue;
                }

                if (guess == secretNumber) {
                    System.out.println("✅ Correct! You guessed the number!");
                    guessedCorrectly = true;
                    roundsWon++;
                    break;
                } else if (guess < secretNumber) {
                    System.out.println("🔻 Too low!");
                } else {
                    System.out.println("🔺 Too high!");
                }

                attemptsLeft--;
            }

            if (!guessedCorrectly) {
                System.out.println("❌ You've run out of attempts. The number was: " + secretNumber);
            }

            // Ask to play again
            System.out.print("🎮 Do you want to play another round? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();
            playAgain = response.equals("yes") || response.equals("y");
        }

        // Final score
        System.out.println("\n📊 Game Over!");
        System.out.println("Rounds Played: " + roundsPlayed);
        System.out.println("Rounds Won: " + roundsWon);
        System.out.println("🎉 Thank you for playing!");

        scanner.close();
    }
}
