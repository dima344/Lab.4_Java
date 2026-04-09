package ua.edu.sumdu.j2se.pr4;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Клас для роботи з базою даних через JDBC.
 */
public class DatabaseManager {
    private Connection connection;

    public DatabaseManager(String propertiesFilePath) {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(propertiesFilePath)) {
            props.load(fis);
            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String pass = props.getProperty("db.password");

            connection = DriverManager.getConnection(url, user, pass);
            System.out.println("🗄️ Успішно підключено до бази даних PostgreSQL!");
        } catch (IOException e) {
            System.out.println("❌ Помилка читання файлу конфігурації БД: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("❌ Помилка підключення до БД (перевірте, чи запущений сервер PostgreSQL).");
        }
    }

    public void insertPhone(Phone phone) {
        if (connection == null) return;

        String sql = "INSERT INTO phones_db (type, brand, model, price, memory_gb, battery_capacity, os_type, " +
                     "screen_size, has_nfc, has_flashlight, has_cooling, trigger_count, satellite_network, is_waterproof) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            // Базові поля (є у всіх)
            pstmt.setString(1, phone.getType());
            pstmt.setString(2, phone.getBrand());
            pstmt.setString(3, phone.getModel());
            pstmt.setDouble(4, phone.getPrice());
            pstmt.setInt(5, phone.getMemoryGB());
            pstmt.setInt(6, phone.getBatteryCapacity());
            pstmt.setString(7, phone.getOsType().name());

            // Заповнюємо Null для специфічних полів за замовчуванням
            pstmt.setNull(8, Types.NUMERIC);
            pstmt.setNull(9, Types.BOOLEAN);
            pstmt.setNull(10, Types.BOOLEAN);
            pstmt.setNull(11, Types.BOOLEAN);
            pstmt.setNull(12, Types.INTEGER);
            pstmt.setNull(13, Types.VARCHAR);
            pstmt.setNull(14, Types.BOOLEAN);

            // Перевіряємо тип і додаємо специфічні поля
            if (phone instanceof SmartPhone) {
                SmartPhone sp = (SmartPhone) phone;
                pstmt.setDouble(8, sp.getScreenSize());
                pstmt.setBoolean(9, sp.isHasNFC());
                
                if (phone instanceof GamingPhone) {
                    GamingPhone gp = (GamingPhone) phone;
                    pstmt.setBoolean(11, gp.isHasCoolingSystem());
                    pstmt.setInt(12, gp.getTriggerButtonsCount());
                }
            } else if (phone instanceof KeypadPhone) {
                KeypadPhone kp = (KeypadPhone) phone;
                pstmt.setBoolean(10, kp.isHasFlashlight());
            } else if (phone instanceof SatellitePhone) {
                SatellitePhone sat = (SatellitePhone) phone;
                pstmt.setString(13, sat.getSatelliteNetwork());
                pstmt.setBoolean(14, sat.isWaterproof());
            }

            pstmt.executeUpdate();
            System.out.println("💾 [БД] Об'єкт " + phone.getType() + " успішно збережено в таблицю!");

        } catch (SQLException e) {
            System.out.println("❌ Помилка при виконанні INSERT: " + e.getMessage());
        }
    }
}