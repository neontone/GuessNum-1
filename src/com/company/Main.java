package com.company;

import java.util.*;

public class Main {

    static Random rand = new Random();
    static Scanner scan = new Scanner(System.in);
    static ArrayList<String> users = new ArrayList<>();

    public static void main(String[] args) {
        int counter = 0;
        do {
            String userName = askString("Enter your name:");

            int myNum = rand.nextInt(100) + 1;
            System.out.println(myNum); // TODO remove this line once testing is finished

            boolean userWon = false;

            for (int i = 0; i < 10; i++) {
                int userNum = askInt("Please, enter your guess:", 1, 100);

                if (myNum > userNum) {
                    counter+=1;
                    System.out.println("My number is greater than yours");
                } else if (myNum < userNum) {
                    counter+=1;
                    System.out.println("My number is less than yours");
                } else {
                    System.out.println("Yeah! You won!");
                    users.add(userName + ". Turns: " + counter);
                    userWon = true;
                    break;
                }
            }

            if (!userWon) {
                System.out.println("Looser!");
            }

        } while (askYesNo("Do you want to play again? (y/n)"));

        Collections.sort(users, Collections.reverseOrder());

        for (String name : users) {
            System.out.println(name);

        }

        System.out.println("Goodbye!");
    }

    private static String askString(String msg) {
        System.out.println(msg);
        return scan.next();
    }

    static int askInt(String msg, int min, int max) {
        while (true) {
            try {
                System.out.println(msg);
                int answer = scan.nextInt();
                if (answer >= min && answer <= max) {
                    return answer;
                }
            } catch (InputMismatchException ex) {
                System.out.println("It isn't a number!");
                scan.next();
            }
            System.out.printf("Please enter number from %d to %d\n", min, max);
        }
    }

    static boolean askYesNo(String msg) {
        while (true) {
            System.out.println(msg);
            String answer = scan.next();
            boolean isY = answer.equalsIgnoreCase("y");
            boolean isN = answer.equalsIgnoreCase("n");
            if (isY || isN) {
                return isY;
            }
            System.out.println("Enter 'y' or 'n'");
        }
    }
}
