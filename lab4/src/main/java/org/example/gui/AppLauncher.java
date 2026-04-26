package org.example.gui;

public class AppLauncher {
    public static void main(String[] args) {
        // Запуск JavaFX Application через сторонній клас вирішує проблему з
        // "JavaFX runtime components are missing" у Java 11+
        MainApp.main(args);
    }
}
