package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Головний клас (Драйвер) для керування ієрархією пристроїв та файловим вводом/виводом.
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Phone> phones = new ArrayList<>();
    private static final String FILE_NAME = "input.txt";

    public static void main(String[] args) {
        printHeader();
        loadFromFile(); // Зчитуємо дані при запуску

        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== Головне меню ===");
            System.out.println("1. Створити новий об’єкт");
            System.out.println("2. Вивести інформацію про всі об’єкти");
            System.out.println("3. Завершити роботу програми (Зберегти у файл)");
            System.out.print("Виберіть опцію: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    showCreationMenu();
                    break;
                case "2":
                    showAllDevices();
                    break;
                case "3":
                    saveToFile(); // Записуємо дані при завершенні
                    exit = true;
                    System.out.println("Роботу завершено. Дані збережено.");
                    break;
                default:
                    System.out.println("Помилка: невідома опція головного меню.");
                    break;
            }
        }
    }

    private static void printHeader() {
        System.out.println("======================================================");
        System.out.println("Практична робота №9");
        System.out.println("Виконав: студент групи ІН-33-4, Дмитренко Богдан Леонідович");
        System.out.println("Спеціальність: 122 Комп'ютерні науки");
        System.out.println("Тема: Ієрархія успадкування, робота з файлами");
        System.out.println("======================================================");
    }

    // --- БЛОК РОБОТИ З ФАЙЛАМИ ---

    private static void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("Файл " + FILE_NAME + " не знайдено. Буде створено новий під час збереження.");
            return;
        }

        System.out.println("Завантаження даних із файлу " + FILE_NAME + "...");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.trim().isEmpty()) continue; // Пропускаємо порожні рядки

                try {
                    String[] parts = line.split(";");
                    String type = parts[0];
                    String brand = parts[1];
                    String model = parts[2];
                    double price = Double.parseDouble(parts[3]);
                    int storage = Integer.parseInt(parts[4]);
                    OperatingSystem os = OperatingSystem.valueOf(parts[5]);

                    switch (type) {
                        case "Phone":
                            phones.add(new Phone(brand, model, price, storage, os));
                            break;
                        case "SmartPhone":
                            double cameraSp = Double.parseDouble(parts[6]);
                            phones.add(new SmartPhone(brand, model, price, storage, os, cameraSp));
                            break;
                        case "KeypadPhone":
                            boolean flashlight = Boolean.parseBoolean(parts[6]);
                            phones.add(new KeypadPhone(brand, model, price, storage, os, flashlight));
                            break;
                        case "GamingPhone":
                            double cameraGp = Double.parseDouble(parts[6]);
                            String cooling = parts[7];
                            phones.add(new GamingPhone(brand, model, price, storage, os, cameraGp, cooling));
                            break;
                        case "FoldablePhone":
                            double cameraFp = Double.parseDouble(parts[6]);
                            int screens = Integer.parseInt(parts[7]);
                            phones.add(new FoldablePhone(brand, model, price, storage, os, cameraFp, screens));
                            break;
                        default:
                            System.out.println("Помилка на рядку " + lineNumber + ": невідомий тип пристрою '" + type + "'. Рядок пропущено.");
                    }
                } catch (Exception e) {
                    System.out.println("Помилка парсингу на рядку " + lineNumber + ": некоректні дані. Рядок пропущено.");
                }
            }
            System.out.println("Завантаження завершено. Завантажено об'єктів: " + phones.size());
        } catch (IOException e) {
            System.out.println("Помилка при читанні файлу: " + e.getMessage());
        }
    }

    private static void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Phone phone : phones) {
                writer.write(phone.toFileString());
                writer.newLine();
            }
            System.out.println("Успішно записано " + phones.size() + " об'єктів у файл " + FILE_NAME + ".");
        } catch (IOException e) {
            System.out.println("Помилка при записі у файл: " + e.getMessage());
        }
    }

    // --- БЛОК МЕНЮ ТА СТВОРЕННЯ (Залишається без суттєвих змін) ---

    private static void showCreationMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Меню створення об'єкта ---");
            System.out.println("1. Phone (Звичайний телефон)");
            System.out.println("2. SmartPhone (Смартфон)");
            System.out.println("3. KeypadPhone (Кнопковий телефон)");
            System.out.println("4. GamingPhone (Ігровий смартфон)");
            System.out.println("5. FoldablePhone (Складаний смартфон)");
            System.out.println("6. Повернутися до головного меню");
            System.out.print("Оберіть тип пристрою: ");

            String choice = scanner.nextLine();

            if (choice.equals("6")) {
                back = true;
                continue;
            }

            try {
                int type = Integer.parseInt(choice);
                if (type >= 1 && type <= 5) {
                    addDevice(type);
                    back = true;
                } else {
                    System.out.println("Помилка: оберіть число від 1 до 6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Помилка: введіть коректне число.");
            }
        }
    }

    private static void addDevice(int type) {
        try {
            System.out.print("Введіть марку: ");
            String brand = scanner.nextLine();

            System.out.print("Введіть модель: ");
            String model = scanner.nextLine();

            System.out.print("Введіть ціну: ");
            double price = Double.parseDouble(scanner.nextLine());

            System.out.print("Введіть об'єм пам'яті (ГБ): ");
            int storage = Integer.parseInt(scanner.nextLine());

            System.out.print("Виберіть ОС (ANDROID, IOS, HARMONY_OS, OTHER): ");
            OperatingSystem os = OperatingSystem.valueOf(scanner.nextLine().toUpperCase());

            if (type == 1) {
                phones.add(new Phone(brand, model, price, storage, os));
                System.out.println("Об'єкт Phone успішно додано!");
            } else if (type == 3) {
                System.out.print("Чи є ліхтарик? (true/false): ");
                boolean flashlight = Boolean.parseBoolean(scanner.nextLine());
                phones.add(new KeypadPhone(brand, model, price, storage, os, flashlight));
                System.out.println("Об'єкт KeypadPhone успішно додано!");
            } else {
                System.out.print("Введіть кількість мегапікселів камери: ");
                double camera = Double.parseDouble(scanner.nextLine());

                if (type == 2) {
                    phones.add(new SmartPhone(brand, model, price, storage, os, camera));
                    System.out.println("Об'єкт SmartPhone успішно додано!");
                } else if (type == 4) {
                    System.out.print("Введіть тип системи охолодження: ");
                    String cooling = scanner.nextLine();
                    phones.add(new GamingPhone(brand, model, price, storage, os, camera, cooling));
                    System.out.println("Об'єкт GamingPhone успішно додано!");
                } else if (type == 5) {
                    System.out.print("Введіть кількість екранів: ");
                    int screens = Integer.parseInt(scanner.nextLine());
                    phones.add(new FoldablePhone(brand, model, price, storage, os, camera, screens));
                    System.out.println("Об'єкт FoldablePhone успішно додано!");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Помилка: Введіть коректне числове значення.");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка валідації: " + e.getMessage());
        }
    }

    private static void showAllDevices() {
        if (phones.isEmpty()) {
            System.out.println("Колекція порожня.");
            return;
        }

        System.out.println("\n--- Всі об'єкти в колекції ---");
        for (Phone phone : phones) {
            System.out.println(phone.toString());
        }
    }
}