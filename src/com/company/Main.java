package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    static Random rand = new Random();
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        String answer;

        do {
            int myNum = rand.nextInt(100) + 1;
            System.out.println(myNum); // TODO remove this line once testing is finished

            boolean userWon = false;

            for (int i = 0; i < 10; i++) {
                int userNum = askInt("Please, enter your guess:", 1, 100);

                if (myNum > userNum) {
                    System.out.println("My number is greater than yours");
                } else if (myNum < userNum) {
                    System.out.println("My number is less than yours");
                } else {
                    System.out.println("Yeah! You won!");
                    userWon = true;
                    break;
                }
            }

            if (!userWon) {
                System.out.println("Looser!");
            }

            answer = askYesNo("Do you want to play again? (y/n)");

        } while (answer.equalsIgnoreCase("y"));

        System.out.println("Goodbye!");
    }

    static int askInt(String msg, int min, int max) {
        while (true) {
            System.out.println(msg);
            int answer = scan.nextInt();
            if (answer >= min && answer <= max) {
                return answer;
            }
            System.out.printf("Please enter number from %d to %d\n", min, max);
        }
    }

    static String askYesNo(String msg) {
        while (true) {
            System.out.println(msg);
            String answer = scan.next();
            if (answer.equalsIgnoreCase("y")
                    || answer.equalsIgnoreCase("n")) {
                return answer;
            }
            System.out.println("Enter 'y' or 'n'");
        }
    }
}
