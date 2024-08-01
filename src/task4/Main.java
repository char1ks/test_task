package task4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Необходимо передать путь к файлу в качестве аргумента командной строки.");
            return;
        }
        String filePath = args[0];
        int[] nums = readNumbersFromFile(filePath);
        int minMoves = minMoves2(nums);
        System.out.println("Минимальное количество ходов: " + minMoves);
    }

    private static int[] readNumbersFromFile(String filePath) {
        int[] nums;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            int count = 0;
            while (reader.readLine() != null) {
                count++;
            }
            nums = new int[count];

            reader.close(); // Закрываем BufferedReader после использования
        } catch (IOException e) {
            e.printStackTrace();
            return new int[0]; // Возвращаем пустой массив в случае ошибки
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                nums[index++] = Integer.parseInt(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new int[0]; // Возвращаем пустой массив в случае ошибки
        }

        return nums;
    }
    public static int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int mid = nums[nums.length / 2];
        int count = 0;
        for (int num : nums) {
            count += Math.abs(mid - num);
        }
        return count;
    }
}