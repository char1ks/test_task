package task2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CirclePosition {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Необходимо передать два аргумента: путь к файлу с окружностью и путь к файлу с точками.");
            return;
        }

        try {
            // Чтение центра окружности и радиуса из файла 1
            Scanner scanner1 = new Scanner(new File(args[0]));
            double centerX = scanner1.nextDouble();
            double centerY = scanner1.nextDouble();
            double radius = scanner1.nextDouble();
            scanner1.close();

            // Чтение точек из файла 2 и вычисление их положения относительно окружности
            Scanner scanner2 = new Scanner(new File(args[1]));
            while (scanner2.hasNext()) {
                double pointX = scanner2.nextDouble();
                double pointY = scanner2.nextDouble();
                int position = calculatePosition(centerX, centerY, radius, pointX, pointY);
                System.out.println(position);
            }
            scanner2.close();
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    private static int calculatePosition(double centerX, double centerY, double radius, double pointX, double pointY) {
        double distance = Math.sqrt(Math.pow(pointX - centerX, 2) + Math.pow(pointY - centerY, 2));
        if (distance == radius) {
            return 0; // Point is on the circle
        } else if (distance < radius) {
            return 1; // Point is inside the circle
        } else {
            return 2; // Point is outside the circle
        }
    }
}
