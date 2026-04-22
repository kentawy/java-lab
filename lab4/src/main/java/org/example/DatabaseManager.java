package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DatabaseManager {
    private String url;
    private String user;
    private String password;

    public DatabaseManager(String configFilePath) {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(configFilePath)) {
            props.load(fis);
            this.url = props.getProperty("db.url");
            this.user = props.getProperty("db.user");
            this.password = props.getProperty("db.password");
            
            if (this.url == null || this.user == null || this.password == null) {
                throw new IllegalArgumentException("Неповна конфігурація БД в файлі " + configFilePath);
            }
        } catch (IOException e) {
            System.err.println("Помилка читання конфігураційного файлу: " + e.getMessage());
            throw new RuntimeException("Не вдалося завантажити конфігурацію БД", e);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void saveStoreItem(StoreItem item) {
        String sql = "INSERT INTO store_items (type, brand, model, price, storage_capacity, os, camera_megapixels, has_flashlight, cooling_system, number_of_screens, quantity) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            Phone phone = item.getPhone();
            
            String type = phone.getClass().getSimpleName();
            pstmt.setString(1, type);
            pstmt.setString(2, phone.getBrand());
            pstmt.setString(3, phone.getModel());
            pstmt.setDouble(4, phone.getPrice());
            pstmt.setInt(5, phone.getStorageCapacity());
            pstmt.setString(6, phone.getOs().name());
            
            // За замовчуванням NULL для специфічних полів
            pstmt.setNull(7, java.sql.Types.NUMERIC);
            pstmt.setNull(8, java.sql.Types.BOOLEAN);
            pstmt.setNull(9, java.sql.Types.VARCHAR);
            pstmt.setNull(10, java.sql.Types.INTEGER);
            
            if (phone instanceof SmartPhone) {
                pstmt.setDouble(7, ((SmartPhone) phone).getCameraMegapixels());
            } else if (phone instanceof KeypadPhone) {
                pstmt.setBoolean(8, ((KeypadPhone) phone).isHasFlashlight());
            } else if (phone instanceof GamingPhone) {
                GamingPhone gp = (GamingPhone) phone;
                pstmt.setDouble(7, gp.getCameraMegapixels());
                pstmt.setString(9, gp.getCoolingSystem());
            } else if (phone instanceof FoldablePhone) {
                FoldablePhone fp = (FoldablePhone) phone;
                pstmt.setDouble(7, fp.getCameraMegapixels());
                pstmt.setInt(10, fp.getNumberOfScreens());
            }
            
            pstmt.setInt(11, item.getQuantity());

            pstmt.executeUpdate();
            System.out.println("Товар успішно збережено в БД.");
        } catch (SQLException e) {
            System.err.println("Помилка збереження товару в БД: " + e.getMessage());
        }
    }
}
