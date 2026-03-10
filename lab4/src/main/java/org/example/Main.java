package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Головний клас (Драйвер) для керування списком телефонів через консольне меню.
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Phone> phones = new ArrayList<>();

    public static void main(String[] args) {
        printHeader();
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Меню керування телефоном ---");
            System.out.println("1. Створити новий об’єкт");
            System.out.println("2. Вивести інформацію про всі об’єкти");
            System.out.println("3. Створити копію існуючого телефону (Конструктор копіювання)");
            System.out.println("4. Створити замовлення з усіма телефонами (Агрегація)");
            System.out.println("5. Вивести загальну кількість створених об'єктів Phone (Статичне поле)");
            System.out.println("6. Завершити роботу");
            System.out.print("Виберіть опцію: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addPhone();
                    break;
                case "2":
                    showAllPhones();
                    break;
                case "3":
                    copyPhone();
                    break;
                case "4":
                    createOrder();
                    break;
                case "5":
                    System.out.println("Загалом створено об'єктів Phone: " + Phone.getTotalPhonesCreated());
                    break;
                case "6":
                    exit = true;
                    break;
                default:
                    System.out.println("Помилка: невідома опція.");
                    break;
            }
        }
    }

    /**
     * Метод для виведення інформаційної шапки.
     */
    private static void printHeader() {
        System.out.println("======================================================");
        System.out.println("Практична робота №6");
        System.out.println("Виконав: студент групи ІН-33-4, Дмитренко Богдан Леонідович");
        System.out.println("Спеціальність: 122 Комп'ютерні науки");
        System.out.println("Тема: Класи, статичні члени, агрегація, перерахування");
        System.out.println("======================================================");
    }

    private static void addPhone() {
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

            Phone phone = new Phone(brand, model, price, storage, os);
            phones.add(phone);
            System.out.println("Об'єкт успішно додано!");

        } catch (NumberFormatException e) {
            System.out.println("Помилка: Введіть числове значення для ціни та пам'яті.");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка валідації: Перевірте введені дані. " + e.getMessage());
        }
    }

    private static void copyPhone() {
        if (phones.isEmpty()) {
            System.out.println("Немає доступних телефонів для копіювання.");
            return;
        }

        System.out.println("Доступні телефони:");
        for (int i = 0; i < phones.size(); i++) {
            System.out.println((i + 1) + ". " + phones.get(i).getBrand() + " " + phones.get(i).getModel());
        }

        System.out.print("Введіть номер телефону для копіювання: ");
        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            if (index >= 0 && index < phones.size()) {
                Phone originalPhone = phones.get(index);
                Phone copiedPhone = new Phone(originalPhone); // Виклик конструктора копіювання
                phones.add(copiedPhone);
                System.out.println("Телефон успішно скопійовано та додано до списку!");
            } else {
                System.out.println("Помилка: Невірний номер.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Помилка: Введіть коректне число.");
        }
    }

    private static void createOrder() {
        if (phones.isEmpty()) {
            System.out.println("Список телефонів порожній. Немає що додавати до замовлення.");
            return;
        }

        System.out.print("Введіть номер нового замовлення: ");
        String orderId = scanner.nextLine();

        try {
            Order order = new Order(orderId);
            for (Phone p : phones) {
                order.addPhone(p);
            }
            System.out.println("Замовлення сформовано!");
            order.printOrderDetails();
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка створення замовлення: " + e.getMessage());
        }
    }

    private static void showAllPhones() {
        if (phones.isEmpty()) {
            System.out.println("Список порожній.");
        } else {
            // Без використання Stream API
            for (Phone phone : phones) {
                System.out.println(phone.toString());
            }
        }
    }
}