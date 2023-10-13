package org.example;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Actividad_a {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("car_sales.json")) {
            JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();

            Map<String, Double> brandTotalPrice = new HashMap<>();
            Map<String, Integer> brandCarCount = new HashMap<>();

            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject sale = jsonArray.get(i).getAsJsonObject();
                String carBrand = sale.get("car").getAsString();
                String priceStr = sale.get("price").getAsString();

                if (priceStr.startsWith("$")) {
                    priceStr = priceStr.substring(1);
                }

                try {
                    double carPrice = Double.parseDouble(priceStr);
                    brandTotalPrice.put(carBrand, brandTotalPrice.getOrDefault(carBrand, 0.0) + carPrice);
                    brandCarCount.put(carBrand, brandCarCount.getOrDefault(carBrand, 0) + 1);
                } catch (NumberFormatException e) {
                    System.err.println("Error al convertir precio para la marca: " + carBrand);
                }
            }

            System.out.println("Precio promedio por marca de carro:");
            Set<String> brands = brandTotalPrice.keySet();
            for (String brand : brands) {
                double total = brandTotalPrice.get(brand);
                int count = brandCarCount.get(brand);
                double average = total / count;
                System.out.println(brand + ": $" + average);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
