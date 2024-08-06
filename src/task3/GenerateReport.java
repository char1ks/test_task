package task3;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;

public class GenerateReport {

    public static void main(String[] args) {
        // Проверяем, что передано три аргумента
        if (args.length < 3) {
            System.out.println("Необходимо передать три пути к файлам: values.json, tests.json, report.json");
            return;
        }

        String valuesFilePath = args[0];
        String testsFilePath = args[1];
        String reportFilePath = args[2];

        try {
            // Чтение содержимого values.json
            JSONParser parser = new JSONParser();
            JSONObject valuesObject = (JSONObject) parser.parse(new FileReader(valuesFilePath));
            // Чтение и обновление структуры tests.json
            JSONObject testsObject = (JSONObject) parser.parse(new FileReader(testsFilePath));
            updateTestsObject(testsObject, valuesObject);
            // Запись результата в report.json
            try (FileWriter file = new FileWriter(reportFilePath)) {
                file.write(testsObject.toJSONString());
            }
            System.out.println("Файл " + reportFilePath + " успешно сформирован.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateTestsObject(JSONObject testsObject, JSONObject valuesObject) {
        JSONArray tests = (JSONArray) testsObject.get("tests");
        JSONArray values = (JSONArray) valuesObject.get("values");

        for (Object test : tests) {
            JSONObject testObj = (JSONObject) test;
            Long testId = (Long) testObj.get("id");

            for (Object value : values) {
                JSONObject valueObj = (JSONObject) value;
                Long valueId = (Long) valueObj.get("id");

                if (testId.equals(valueId)) {
                    testObj.put("value", valueObj.get("value"));
                    break;
                }
            }
        }
    }
}
