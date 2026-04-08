package ua.edu.sumdu.j2se.pr4;

import com.google.gson.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileStorage {
    private static final String FILE_NAME = "input.json";

    public static void saveToJson(ArrayList<Phone> phones) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            gson.toJson(phones, writer);
            System.out.println("💾 Дані успішно збережено у файл " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("❌ Помилка запису у файл: " + e.getMessage());
        }
    }

    public static ArrayList<Phone> loadFromJson() {
        ArrayList<Phone> phones = new ArrayList<>();
        try (FileReader reader = new FileReader(FILE_NAME)) {
            JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();
            Gson gson = new Gson();

            for (JsonElement element : jsonArray) {
                JsonObject obj = element.getAsJsonObject();
                String type = obj.has("type") ? obj.get("type").getAsString() : "";

                switch (type) {
                    case "SmartPhone":
                        phones.add(gson.fromJson(obj, SmartPhone.class));
                        break;
                    case "KeypadPhone":
                        phones.add(gson.fromJson(obj, KeypadPhone.class));
                        break;
                    case "GamingPhone":
                        phones.add(gson.fromJson(obj, GamingPhone.class));
                        break;
                    case "SatellitePhone":
                        phones.add(gson.fromJson(obj, SatellitePhone.class));
                        break;
                    default:
                        phones.add(gson.fromJson(obj, Phone.class));
                        break;
                }
            }
            System.out.println("📂 Дані успішно завантажено з файлу " + FILE_NAME + " (Знайдено: " + phones.size() + " шт.)");
        } catch (IOException e) {
            System.out.println("⚠️ Файл збереження відсутній або порожній. Створено нову базу.");
        } catch (Exception e) {
            System.out.println("❌ Помилка обробки JSON: " + e.getMessage());
        }
        return phones;
    }
}