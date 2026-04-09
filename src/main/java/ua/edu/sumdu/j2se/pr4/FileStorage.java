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
            System.out.println("💾 Дані успішно збережено у файл " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("❌ Помилка запису: " + e.getMessage());
        }
    }

    public static ArrayList<StoreItem> loadFromJson() {
        ArrayList<StoreItem> inventory = new ArrayList<>();
        try (FileReader reader = new FileReader(FILE_NAME)) {
            JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();
            Gson gson = new Gson();

            for (JsonElement element : jsonArray) {
                JsonObject itemObj = element.getAsJsonObject();
                int quantity = itemObj.has("quantity") ? itemObj.get("quantity").getAsInt() : 1;
                
                JsonObject phoneObj = itemObj.getAsJsonObject("phone");
                String type = phoneObj.has("type") ? phoneObj.get("type").getAsString() : "Phone";

                Phone parsedPhone = null;
                switch (type) {
                    case "SmartPhone": parsedPhone = gson.fromJson(phoneObj, SmartPhone.class); break;
                    case "KeypadPhone": parsedPhone = gson.fromJson(phoneObj, KeypadPhone.class); break;
                    case "GamingPhone": parsedPhone = gson.fromJson(phoneObj, GamingPhone.class); break;
                    case "SatellitePhone": parsedPhone = gson.fromJson(phoneObj, SatellitePhone.class); break;
                    default: parsedPhone = gson.fromJson(phoneObj, Phone.class); break;
                }
                inventory.add(new StoreItem(parsedPhone, quantity));
            }
            System.out.println("📂 Базу даних успішно завантажено. Позицій: " + inventory.size());
        } catch (Exception e) {
            System.out.println("⚠️ Файл бази відсутній або порожній. Створено новий магазин.");
        }
        return inventory;
    }
}