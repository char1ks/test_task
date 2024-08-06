package task4;

import java.io.BufferedReader;
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
        if (nums.length == 0) {
            System.out.println("Не удалось прочитать числа из файла.");
            return;
        }
        int minMoves = minMoves2(nums);
        System.out.println("Минимальное количество ходов: " + minMoves);
    }

    private static int[] readNumbersFromFile(String filePath) {
        int[] nums = new int[0];
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                nums = Arrays.copyOf(nums, count + 1);
                nums[count] = Integer.parseInt(line);
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new int[0]; // Возвращаем пустой массив в случае ошибки
        } catch (NumberFormatException e) {
            System.err.println("Ошибка формата числа в файле: " + e.getMessage());
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
