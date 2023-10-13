package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import org.json.XML;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Actividad_c {
    public static void main(String[] args) {
        try {

            File xmlFile = new File("car_sales.xml");
            String xmlString = new String(java.nio.file.Files.readAllBytes(xmlFile.toPath()));

            JSONObject jsonObject = XML.toJSONObject(xmlString).getJSONObject("car_sales");

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonString = gson.toJson(jsonObject);

            System.out.println(jsonString);

            try (FileWriter fileWriter = new FileWriter("car_sales-Act-C.json")) {
                fileWriter.write(jsonString);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
