package task1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите n: ");
        int n = scanner.nextInt();
        System.out.print("Введите m: ");
        int m = scanner.nextInt();
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