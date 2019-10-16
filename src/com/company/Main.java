package com.company;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    static Random rand = new Random();
    static Scanner scan = new Scanner(System.in);
    static ArrayList<GameResult> leaderBoard = new ArrayList<>();

    public static void main(String[] args) {
        loadResults();
        do {


            String userName = askString("Enter your name:");
            {
                String space = " ";

                int count = 0;

                //Counts each character except space
                for (int i = 0; i < userName.length(); i++) {
                    if (userName.charAt(i) != ' ')
                        count++;
                }

                int myNum = rand.nextInt(100) + 1;
                System.out.println(myNum); // TODO remove this line once testing is finished

                boolean userWon = false;

                for (int i = 0; i < 10; i++) {

                    long t1 = System.currentTimeMillis();

                    int userNum = askInt("Please, enter your guess:", 1, 100);

                    if (myNum > userNum) {
                        System.out.println("My number is greater than yours");
                    } else if (myNum < userNum) {
                        System.out.println("My number is less than yours");
                    } else {
                        GameResult r = new GameResult();
                        long t2 = System.currentTimeMillis();
                        r.setName(userName);
                        r.setTriesCount(i + 1);
                        r.setTime(t2 - t1);
                        leaderBoard.add(r);
                        System.out.println("Yeah! You won!");
                        userWon = true;
                        break;
                    }
                }


                if (!userWon) {
                    System.out.println("Loser!");
                }
            }

        } while (askYesNo("Do you want to play again? (y/n)"));

        showResults2();

        System.out.println("Goodbye!");

        saveScores();


    }

    private static void loadResults() {
        File file = new File("scores.txt");
        try (Scanner in = new Scanner(file)) {
            while (in.hasNext()) {
                GameResult r = new GameResult();

                String name = in.next();
                int triesCount = in.nextInt();
                long time = in.nextLong();

                r.setName(name);
                r.setTriesCount(triesCount);
                r.setTime(time);

                leaderBoard.add(r);
            }
        } catch (IOException e) {
            System.out.println("Cannot read scoreboard");
        }
    }


    //   private static void showResults() {
    //      leaderboard.sort(Comparator.comparing(GameResult::getTriesCount).thenComparing(GameResult::getTime));

    //      for (GameResult result : leaderboard) {
    //           System.out.printf("%s \t\t\t %d \t\t\t %.2f seconds\n", result.getName(), result.getTriesCount(), result.getTime());


    private static void showResults2() {
        leaderBoard.stream()
                .sorted(Comparator
                        .comparing(GameResult::getTriesCount)
                        .thenComparing(GameResult::getTime))
                .limit(10)
                .forEach(r -> System.out.printf("%s \t\t\t %d \t\t\t %.2f seconds\n",
                        r.getName(),
                        r.getTriesCount(),
                        r.getTime() / 1000.0));
    }

    private static void saveScores() {
        File file = new File("scores.txt");
        try (PrintWriter out = new PrintWriter(file)) {
            for (GameResult result : leaderBoard) {
                out.print(result.getName());
                out.print(" ");
                out.print(result.getTriesCount());
                out.print(" ");
                out.print(result.getTime());
                out.println();
            }
        } catch (IOException e) {
            System.out.println("Cannot read scoreboard");
        }
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
