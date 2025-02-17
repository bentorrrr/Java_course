
import java.util.Random;

public class App {

    public static void main(String[] args) throws Exception {
        while (true) {
            Random random = new Random();
            int n = random.nextInt(100);
            System.out.println("Let's play a game of random number guessing. I have chosen a number between 0 and 100. Try to guess it!");
            System.out.println("You have 7 attemps to guess the number.");
            int guess = 0;
            while (guess < 7) {
                System.out.println("Enter your guess: ");
                int userGuess = Integer.parseInt(System.console().readLine());
                if (userGuess == n) {
                    System.out.println("Congratulations! You guessed the number.");
                    break;
                } else if (userGuess < n) {
                    System.out.println("The number is greater than your guess.");
                } else {
                    System.out.println("The number is less than your guess.");
                }
                guess++;
            }
            if (guess == 7) {
                System.out.println("You have exhausted all your attempts. The number was: " + n);
            }
            System.out.println("Do you want to play again? (y/n)");
            String playAgain = System.console().readLine();
            while (!playAgain.equals("y") && !playAgain.equals("n")) {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
                playAgain = System.console().readLine();
            }
            if (playAgain.equals("n")) {
                break;
            }
        }
    }
}
