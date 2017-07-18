package mHaley_B31_A03_WAR;

import java.util.Scanner;

public class WarGame {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner keyboard = new Scanner(System.in);
        War game = new War();
        String results;
        boolean gameOver = false;

        printWAR();
        System.out.println("\nWelcome to WAR!");
        System.out.println(
                "The goal of the game is to force the other players to run out of cards.");
        System.out.println(
                "All cards are shuffled and dealt at the beginning of the game."
                        + "\nDuring negotiations, each player lays the top card of their pile face up. \nThe player with the highest value takes both cards and places them into their deck.");
        System.out.println(
                "When the cards have the same value, it's time for WAR!"
                        + "\nEach player adds their current card, and the top three cards from their deck, and places them into the trench.");
        System.out.println(
                "The victor of WAR! gets all the cards inside the trench. \nNow, what are the combatents names?");
        System.out.print("Enter the name of Player 1: ");
        game.setPlayer1(keyboard.nextLine());
        System.out.print("Enter the name of Player 2: ");
        game.setPlayer2(keyboard.nextLine());

        while (!gameOver) {
            results = game.play();
            System.out.println(game.getOutput());
            game.clearOutput();

            if (results.equals("WAR")) {
                results = game.WAR();
                System.out.println(game.getOutput());
                game.clearOutput();
            }
            if (results.equals(game.getPlayer1())) {
                System.out.print(game.getPlayer1()
                        + " is the victor! Thank you for playing WAR!");
                keyboard.close();
                System.exit(0);
            } else if (results.equals(game.getPlayer2())) {
                System.out.print(game.getPlayer2()
                        + " is the victor! Thank you for playing WAR!");
                keyboard.close();
                System.exit(0);
            }
            System.out.print(
                    "Press any key to keep playing, or 'Q' to call a truce.");

            if (keyboard.next().equalsIgnoreCase("q")) {
                gameOver = true;
                System.out.println(
                        "A truce has been called! Thank you for playing WAR!");
                keyboard.close();
            }
        }
    }

    private static void printWAR() {
        String[] warASCII = { " _    _  ___  ______ ",
                "| |  | |/ _ \\ | ___ \\", "| |  | / /_\\ \\| |_/ /",
                "| |/\\| |  _  ||    /", "\\  /\\  / | | || |\\ \\ ",
                " \\/  \\/\\_| |_/\\_| \\_|" };
        for (int i = 0; i < warASCII.length; i++) {
            System.out.println(warASCII[i]);
        }
    }

}
