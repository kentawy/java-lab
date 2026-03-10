package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Головний клас (Драйвер) для демонстрації наслідування та поліморфізму.
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    // Колекція базового типу зберігає об'єкти як базового, так і похідних типів
    private static final List<Phone> phones = new ArrayList<>();

    public static void main(String[] args) {
        printHeader();
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Меню керування пристроями ---");
            System.out.println("1. Створити звичайний телефон (Phone)");
            System.out.println("2. Створити смартфон (SmartPhone)");
            System.out.println("3. Створити кнопковий телефон (KeypadPhone)");
            System.out.println("4. Вивести інформацію про всі пристрої (Поліморфізм)");
            System.out.println("5. Завершити роботу");
            System.out.print("Виберіть опцію: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addDevice(1);
                    break;
                case "2":
                    addDevice(2);
                    break;
                case "3":
                    addDevice(3);
                    break;
                case "4":
                    showAllDevices();
                    break;
                case "5":
                    exit = true;
                    break;
                default:
                    System.out.println("Помилка: невідома опція.");
                    break;
            }
        }
    }

    private static void printHeader() {
        System.out.println("======================================================");
        System.out.println("Практична робота №7");
        System.out.println("Виконав: студент групи ІН-33-4, Дмитренко Богдан Леонідович");
        System.out.println("Спеціальність: 122 Комп'ютерні науки");
        System.out.println("Тема: Наслідування, поліморфізм, колекції");
        System.out.println("======================================================");
    }

    /**
     * Загальний метод для створення об'єктів різних класів ієрархії.
     * @param type 1 - Phone, 2 - SmartPhone, 3 - KeypadPhone
     */
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
                System.out.println("Звичайний телефон додано!");
            } else if (type == 2) {
                System.out.print("Введіть кількість мегапікселів камери: ");
                double camera = Double.parseDouble(scanner.nextLine());
                phones.add(new SmartPhone(brand, model, price, storage, os, camera));
                System.out.println("Смартфон додано!");
            } else if (type == 3) {
                System.out.print("Чи є ліхтарик? (true/false): ");
                boolean flashlight = Boolean.parseBoolean(scanner.nextLine());
                phones.add(new KeypadPhone(brand, model, price, storage, os, flashlight));
                System.out.println("Кнопковий телефон додано!");
            }

        } catch (NumberFormatException e) {
            System.out.println("Помилка: Введіть коректне числове значення.");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка валідації: " + e.getMessage());
        }
    }

    private static void showAllDevices() {
        if (phones.isEmpty()) {
            System.out.println("Список порожній.");
            return;
        }

        System.out.println("\n--- Всі пристрої в єдиній колекції ---");
        // Демонстрація поліморфізму:
        // для кожного об'єкта викличеться СВІЙ перевизначений метод toString()
        for (Phone phone : phones) {
            System.out.println(phone.toString());
        }
    }
}