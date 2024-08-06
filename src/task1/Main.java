package task1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
         if (args.length < 2) {
            System.out.println("Необходимо ввести два параметра: n и m.");
            return;
        }

        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        System.out.println(findCircularArrayPath(n, m));
    }
    public static String findCircularArrayPath(int n, int m) {
        StringBuilder strToReturn = new StringBuilder();
        int lastIndex = 0;
        int currentIndex = 0;

        while (true) {
            StringBuilder substring = new StringBuilder();
            for (int i = 0; i < m; i++) {
                currentIndex = (lastIndex + i) % n;
                substring.append((currentIndex + 1));
            }
            strToReturn.append(substring.charAt(0));

            if (currentIndex == 0) {
                break;
            }

            lastIndex = currentIndex;
        }

        return strToReturn.toString();
    }

}
