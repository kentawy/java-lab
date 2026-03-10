package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Головний клас (Драйвер) для керування ієрархією з 5 класів.
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    // Внутрішня колекція порожня на початку роботи програми
    private static final List<Phone> phones = new ArrayList<>();

    public static void main(String[] args) {
        printHeader();
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== Головне меню ===");
            System.out.println("1. Створити новий об’єкт");
            System.out.println("2. Вивести інформацію про всі об’єкти");
            System.out.println("3. Завершити роботу програми");
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
                    exit = true;
                    System.out.println("Роботу завершено.");
                    break;
                default:
                    System.out.println("Помилка: невідома опція головного меню.");
                    break;
            }
        }
    }

    private static void printHeader() {
        System.out.println("======================================================");
        System.out.println("Практична робота №8");
        System.out.println("Виконав: студент групи ІН-33-4, Дмитренко Богдан Леонідович");
        System.out.println("Спеціальність: 122 Комп'ютерні науки");
        System.out.println("Тема: Розширення ієрархії класів та поліморфізм");
        System.out.println("======================================================");
    }

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
                    back = true; // Повертаємось у головне меню після успішного створення
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
                // Всі інші (2, 4, 5) є смартфонами і потребують камеру
                System.out.print("Введіть кількість мегапікселів камери: ");
                double camera = Double.parseDouble(scanner.nextLine());

                if (type == 2) {
                    phones.add(new SmartPhone(brand, model, price, storage, os, camera));
                    System.out.println("Об'єкт SmartPhone успішно додано!");
                } else if (type == 4) {
                    System.out.print("Введіть тип системи охолодження (наприклад, Liquid): ");
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