// Driver class to manage Phone objects
package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Phone> phones = new ArrayList<>();

        System.out.println("Введіть дані для 5 телефонів:");

        for (int i = 0; i < 5; i++) {
            System.out.println("\nТелефон #" + (i + 1));
            System.out.print("Марка: ");
            String brand = scanner.nextLine();
            System.out.print("Модель: ");
            String model = scanner.nextLine();
            System.out.print("Ціна: ");
            double price = scanner.nextDouble();
            scanner.nextLine();

            phones.add(new Phone(brand, model, price));
        }

        System.out.println("\n--- Список ваших телефонів ---");
        for (Phone p : phones) {
            System.out.println(p.toString());
        }
    }
}