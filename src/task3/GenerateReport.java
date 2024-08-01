package task3;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;

public class GenerateReport {

    public static void main(String[] args) {
//        if (args.length < 3) {
//            System.out.println("Необходимо передать три пути к файлам: values.json, tests.json, report.json");
//            return;
//        }

        String valuesFilePath = "src/task3/values.json";
        String testsFilePath = "src/task3/tests.json";
        String reportFilePath ="src/task3/report.json";

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

            System.out.println("Файл report.json успешно сформирован.");

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