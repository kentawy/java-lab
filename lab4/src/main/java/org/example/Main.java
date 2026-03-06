package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Головний клас для керування списком телефонів через консольне меню.
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Phone> phones = new ArrayList<>();

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Меню керування телефоном ---");
            System.out.println("1. Створити новий об’єкт");
            System.out.println("2. Вивести інформацію про всі об’єкти");
            System.out.println("3. Завершити роботу");
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
                    exit = true;
                    break;
                default:
                    System.out.println("Помилка: невідома опція.");
                    break;
            }
        }
    }

    /**
     * Метод для зчитування даних та створення нового об'єкта Phone.
     */
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

            Phone phone = new Phone(brand, model, price, storage);
            phones.add(phone);
            System.out.println("Об'єкт успішно додано!");

        } catch (NumberFormatException e) {
            System.out.println("Помилка: Введіть числове значення для ціни та пам'яті.");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка валідації: " + e.getMessage());
        }
    }

    private static void showAllPhones() {
        if (phones.isEmpty()) {
            System.out.println("Список порожній.");
        } else {
            phones.forEach(System.out::println);
        }
    }
}