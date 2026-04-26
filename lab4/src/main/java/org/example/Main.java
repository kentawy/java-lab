package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Comparator;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Store store = new Store("TechStore СумДУ");
    private static final String FILE_NAME = "lab4/input.txt";

    public static void main(String[] args) {
        printHeader();
        loadFromFile();

        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== Головне меню магазину '" + store.getStoreName() + "' ===");
            System.out.println("1. Пошук товару (за критеріями)");
            System.out.println("2. Створити та додати новий товар");
            System.out.println("3. Вивести інформацію про асортимент");
            System.out.println("4. Вивести відсортовану інформацію про всі товари");
            System.out.println("5. Завершити роботу програми (Зберегти у файл)");
            System.out.print("Виберіть опцію: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    showSearchMenu();
                    break;
                case "2":
                    showCreationMenu();
                    break;
                case "3":
                    showAllDevices();
                    break;
                case "4":
                    showSortedDevicesMenu();
                    break;
                case "5":
                    saveToFile();
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
        System.out.println("Практична робота №15");
        System.out.println("Виконав: студент групи ІН-33-4, Дмитренко Богдан Леонідович");
        System.out.println("Спеціальність: 122 Комп'ютерні науки");
        System.out.println("Тема: Lambda expressions");
        System.out.println("======================================================");
    }

    private static void showSearchMenu() {
        if (store.getInventory().isEmpty()) {
            System.out.println("Магазин порожній. Немає серед чого шукати.");
            return;
        }

        boolean back = false;
        while (!back) {
            System.out.println("\n--- Меню пошуку товару ---");
            System.out.println("1. Пошук за маркою (Brand)");
            System.out.println("2. Пошук за діапазоном ціни (Min - Max)");
            System.out.println("3. Пошук за операційною системою (OS)");
            System.out.println("4. Повернутися до головного меню");
            System.out.print("Оберіть критерій пошуку: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Введіть марку: ");
                    String brand = scanner.nextLine();
                    displaySearchResults(store.searchByBrand(brand), "маркою '" + brand + "'");
                    break;
                case "2":
                    try {
                        System.out.print("Введіть мінімальну ціну: ");
                        double minPrice = Double.parseDouble(scanner.nextLine());
                        System.out.print("Введіть максимальну ціну: ");
                        double maxPrice = Double.parseDouble(scanner.nextLine());
                        if (minPrice > maxPrice) {
                            System.out.println("Помилка: мінімальна ціна більша за максимальну.");
                        } else {
                            displaySearchResults(store.searchByPriceRange(minPrice, maxPrice), "ціною від " + minPrice + " до " + maxPrice);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Помилка: введіть коректне число.");
                    }
                    break;
                case "3":
                    System.out.print("Введіть ОС (ANDROID, IOS, HARMONY_OS, OTHER): ");
                    try {
                        OperatingSystem os = OperatingSystem.valueOf(scanner.nextLine().toUpperCase());
                        displaySearchResults(store.searchByOs(os), "ОС '" + os.name() + "'");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Помилка: такої ОС не існує.");
                    }
                    break;
                case "4":
                    back = true;
                    break;
                default:
                    System.out.println("Помилка: невідома опція.");
                    break;
            }
        }
    }

    private static void displaySearchResults(List<StoreItem> results, String criteriaDescription) {
        if (results.isEmpty()) {
            System.out.println("-> Жодного товару за критерієм " + criteriaDescription + " не знайдено.");
        } else {
            System.out.println("-> Знайдено " + results.size() + " запис(ів) за " + criteriaDescription + ":");
            for (StoreItem item : results) {
                System.out.println(item.toString());
            }
        }
    }

    private static void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("Файл " + FILE_NAME + " не знайдено. Буде створено новий.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.trim().isEmpty()) continue;

                try {
                    String[] parts = line.split(";");
                    String type = parts[0];
                    String brand = parts[1];
                    String model = parts[2];
                    double price = Double.parseDouble(parts[3]);
                    int storage = Integer.parseInt(parts[4]);
                    OperatingSystem os = OperatingSystem.valueOf(parts[5]);
                    int quantity = Integer.parseInt(parts[parts.length - 1]);
                    Phone phone = null;

                    switch (type) {
                        case "Phone":
                            System.out.println("Рядок " + lineNumber + ": пропущено (Phone тепер абстрактний).");
                            break;
                        case "SmartPhone":
                            phone = new SmartPhone(brand, model, price, storage, os, Double.parseDouble(parts[6]));
                            break;
                        case "KeypadPhone":
                            phone = new KeypadPhone(brand, model, price, storage, os, Boolean.parseBoolean(parts[6]));
                            break;
                        case "GamingPhone":
                            phone = new GamingPhone(brand, model, price, storage, os, Double.parseDouble(parts[6]), parts[7]);
                            break;
                        case "FoldablePhone":
                            phone = new FoldablePhone(brand, model, price, storage, os, Double.parseDouble(parts[6]), Integer.parseInt(parts[7]));
                            break;
                        default:
                            System.out.println("Рядок " + lineNumber + ": невідомий тип.");
                    }

                    if (phone != null) {
                        store.addNewPhone(phone, quantity);
                    }
                } catch (Exception e) {
                    System.out.println("Помилка парсингу на рядку " + lineNumber + ".");
                }
            }
            System.out.println("Завантаження завершено. Записів у магазині: " + store.getInventory().size());
        } catch (IOException e) {
            System.out.println("Помилка при читанні файлу: " + e.getMessage());
        }
    }

    private static void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (StoreItem item : store.getInventory()) {
                writer.write(item.toFileString());
                writer.newLine();
            }
            System.out.println("Успішно записано " + store.getInventory().size() + " запис(ів) у файл " + FILE_NAME + ".");
        } catch (IOException e) {
            System.out.println("Помилка при записі: " + e.getMessage());
        }
    }

    private static void showCreationMenu() {
        System.out.println("\n--- Меню створення товару ---");
        System.out.println("2. SmartPhone | 3. KeypadPhone | 4. GamingPhone | 5. FoldablePhone");
        System.out.print("Оберіть тип (2-5) або 0 для скасування: ");

        String choice = scanner.nextLine();
        if (choice.equals("0")) return;

        try {
            int type = Integer.parseInt(choice);
            if (type >= 2 && type <= 5) {
                System.out.print("Введіть кількість для додавання (шт): ");
                int quantity = Integer.parseInt(scanner.nextLine());
                addDevice(type, quantity);
            } else {
                System.out.println("Помилка: невірний тип.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Помилка: введіть коректне число.");
        }
    }

    private static void addDevice(int type, int quantity) {
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

            Phone phone = null;
            if (type == 3) {
                System.out.print("Чи є ліхтарик? (true/false): ");
                phone = new KeypadPhone(brand, model, price, storage, os, Boolean.parseBoolean(scanner.nextLine()));
            } else {
                System.out.print("Мегапікселі камери: ");
                double camera = Double.parseDouble(scanner.nextLine());
                if (type == 2) {
                    phone = new SmartPhone(brand, model, price, storage, os, camera);
                } else if (type == 4) {
                    System.out.print("Система охолодження: ");
                    phone = new GamingPhone(brand, model, price, storage, os, camera, scanner.nextLine());
                } else if (type == 5) {
                    System.out.print("Кількість екранів: ");
                    phone = new FoldablePhone(brand, model, price, storage, os, camera, Integer.parseInt(scanner.nextLine()));
                }
            }

            if (phone != null) {
                store.addNewPhone(phone, quantity);
                System.out.println("Товар успішно додано до інвентарю!");
            }
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static void showAllDevices() {
        if (store.getInventory().isEmpty()) {
            System.out.println("Асортимент порожній.");
            return;
        }
        System.out.println("\n--- Всі товари в магазині ---");
        for (StoreItem item : store.getInventory()) {
            System.out.println(item.toString());
        }
    }

    private static void showSortedDevicesMenu() {
        List<StoreItem> inventory = store.getInventory();
        if (inventory.isEmpty()) {
            System.out.println("Асортимент порожній.");
            return;
        }
        
        boolean back = false;
        while (!back) {
            System.out.println("\nОберіть критерій сортування:");
            System.out.println("1. Сортувати за ціною (зростання)");
            System.out.println("2. Сортувати за об'ємом пам'яті (спадання, а при збігу - за ціною)");
            System.out.println("3. Сортувати за маркою та моделлю (алфавітно)");
            System.out.println("0. Повернутися в головне меню");
            System.out.print("Ваш вибір: ");

            String choice = scanner.nextLine();
            if (choice.equals("0")) {
                back = true;
                continue;
            }

            // Створюємо копію списку, щоб не змінювати оригінальний порядок
            List<StoreItem> sortedInventory = new ArrayList<>(inventory);

            switch (choice) {
                case "1":
                    // Лямбда-вираз для сортування за ціною (зростання)
                    Comparator<StoreItem> priceComparator = (o1, o2) -> Double.compare(o1.getPhone().getPrice(), o2.getPhone().getPrice());
                    sortedInventory.sort(priceComparator);
                    printSortedList(sortedInventory, "за ціною (зростання)");
                    break;
                case "2":
                    // Лямбда-вираз для сортування за пам'яттю (спадання), 
                    // а при однаковій пам'яті - за ціною
                    Comparator<StoreItem> storagePriceComparator = (o1, o2) -> {
                        int storageCompare = Integer.compare(o2.getPhone().getStorageCapacity(), o1.getPhone().getStorageCapacity());
                        if (storageCompare == 0) {
                            return Double.compare(o1.getPhone().getPrice(), o2.getPhone().getPrice());
                        }
                        return storageCompare;
                    };
                    sortedInventory.sort(storagePriceComparator);
                    printSortedList(sortedInventory, "за об'ємом пам'яті (спадання, при збігу - за ціною)");
                    break;
                case "3":
                    // Лямбда-вираз для сортування за маркою і моделлю
                    Comparator<StoreItem> brandModelComparator = (o1, o2) -> {
                        int brandCompare = o1.getPhone().getBrand().compareToIgnoreCase(o2.getPhone().getBrand());
                        if (brandCompare != 0) {
                            return brandCompare;
                        }
                        return o1.getPhone().getModel().compareToIgnoreCase(o2.getPhone().getModel());
                    };
                    sortedInventory.sort(brandModelComparator);
                    printSortedList(sortedInventory, "за маркою та моделлю (алфавітно)");
                    break;
                default:
                    System.out.println("Помилка: невідома опція.");
                    break;
            }
        }
    }

    private static void printSortedList(List<StoreItem> list, String criteria) {
        System.out.println("\n--- Відсортовані товари " + criteria + " ---");
        for (StoreItem item : list) {
            System.out.println(item.toString());
        }
    }
}
