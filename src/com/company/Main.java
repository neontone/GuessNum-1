package com.company;

import java.util.*;

public class Main {

    static Random rand = new Random();
    static Scanner scan = new Scanner(System.in);
    static ArrayList<GameResult> users = new ArrayList<>();

    public static void main(String[] args) {
        do {
            String userName = askString("Enter your name:");

            int myNum = rand.nextInt(100) + 1;
            System.out.println(myNum); // TODO remove this line once testing is finished

            boolean userWon = false;

            for (int i = 0; i < 10; i++) {
                double t1 = System.currentTimeMillis();
                int userNum = askInt("Please, enter your guess:", 1, 100);

                if (myNum > userNum) {
                    System.out.println("My number is greater than yours");
                } else if (myNum < userNum) {
                    System.out.println("My number is less than yours");
                } else {
                    GameResult r = new GameResult();
                    double t2 = System.currentTimeMillis();
                    r.name = userName;
                    r.triesCount = i +1;
                    r.time = (t2 - t1) /1000;
                    users.add(r);
                    System.out.println("Yeah! You won!");
                    userWon = true;
                    break;
                }
            }

            if (!userWon) {
                System.out.println("Looser!");
            }

        } while (askYesNo("Do you want to play again? (y/n)"));

        users.sort(Comparator.comparing(r -> r.triesCount).thenComparing()
        }


        for (GameResult result : users) {
            System.out.printf("%s \t\t\t %d \t\t\t %.2f seconds\n", result.name, result.triesCount, result.time);

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
