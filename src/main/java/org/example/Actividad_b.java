package org.example;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileReader;

public class Actividad_b {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Datos de nuestro archivo car_sales.json");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        model.addColumn("ID");
        model.addColumn("First Name");
        model.addColumn("Last Name");
        model.addColumn("Car");
        model.addColumn("Price");
        model.addColumn("State");

        try (FileReader reader = new FileReader("car_sales.json")) {
            JsonArray saleData = JsonParser.parseReader(reader).getAsJsonArray();

            for (int i = 0; i < saleData.size(); i++) {
                JsonObject car = saleData.get(i).getAsJsonObject();
                model.addRow(new Object[]{
                        car.get("id").getAsInt(),
                        car.get("first_name").getAsString(),
                        car.get("last_name").getAsString(),
                        car.get("car").getAsString(),
                        car.get("price").getAsString(),
                        car.get("state").getAsString()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setSize(800, 400);
        frame.setVisible(true);
    }
}
