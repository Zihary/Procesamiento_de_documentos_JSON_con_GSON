package org.example;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Actividad_c {
    public static void main(String[] args) {
        try {
            File xmlFile = new File("car_sales.xml");
            String xmlString = new String(Files.readAllBytes(Paths.get("car_sales.xml")));

            JSONObject jsonObject = XML.toJSONObject(xmlString).getJSONObject("car_sales");
            JSONArray salesArray = jsonObject.getJSONArray("sale");

            JSONArray simplifiedArray = new JSONArray();
            for (int i = 0; i < salesArray.length(); i++) {
                JSONObject saleObject = salesArray.getJSONObject(i);
                simplifiedArray.put(saleObject);
            }

            JSONObject finalObject = new JSONObject();
            finalObject.put("sales", simplifiedArray);

            try (FileWriter fileWriter = new FileWriter("car_sales-Act-C.json")) {
                fileWriter.write("[");
                for (int i = 0; i < simplifiedArray.length(); i++) {
                    if (i > 0) {
                        fileWriter.write("," + System.lineSeparator());
                    }
                    fileWriter.write(simplifiedArray.getJSONObject(i).toString());
                }
                fileWriter.write("]");
            } catch (IOException e) {
                e.printStackTrace();
            }

            String jsonContent = new String(Files.readAllBytes(Paths.get("car_sales-Act-C.json")));
            System.out.println("Contenido del archivo JSON:");
            System.out.println(jsonContent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
