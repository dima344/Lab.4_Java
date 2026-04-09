package ua.edu.sumdu.j2se.pr4;

import com.google.gson.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileStorage {
    private static final String FILE_NAME = "input.json";

    public static void saveToJson(ArrayList<StoreItem> inventory) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            gson.toJson(inventory, writer);
            System.out.println("💾 Збережено у файл.");
        } catch (IOException e) {
            System.out.println("❌ Помилка запису.");
        }
    }

    public static ArrayList<StoreItem> loadFromJson() {
        ArrayList<StoreItem> inventory = new ArrayList<>();
        try (FileReader reader = new FileReader(FILE_NAME)) {
            JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();
            Gson gson = new Gson();

            for (JsonElement element : jsonArray) {
                JsonObject itemObj = element.getAsJsonObject();
                int quantity = itemObj.get("quantity").getAsInt();
                JsonObject phoneObj = itemObj.getAsJsonObject("phone");
                String type = phoneObj.get("type").getAsString();

                Phone parsedPhone = null;
                switch (type) {
                    case "SmartPhone": parsedPhone = gson.fromJson(phoneObj, SmartPhone.class); break;
                    case "KeypadPhone": parsedPhone = gson.fromJson(phoneObj, KeypadPhone.class); break;
                    case "GamingPhone": parsedPhone = gson.fromJson(phoneObj, GamingPhone.class); break;
                    case "SatellitePhone": parsedPhone = gson.fromJson(phoneObj, SatellitePhone.class); break;
                    default: parsedPhone = gson.fromJson(phoneObj, BasicPhone.class); break;
                }
                inventory.add(new StoreItem(parsedPhone, quantity));
            }
        } catch (Exception e) {
            System.out.println("⚠️ Нова база ініціалізована.");
        }
        return inventory;
    }
}