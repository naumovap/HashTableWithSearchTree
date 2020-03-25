package main;

import java.util.Scanner;

public class Interface {
    public static void main(String[] args) {
        HashTable table;
        Scanner input = new Scanner(System.in);
        System.out.print("Введите значение таблицы: ");
        try {
            int size = input.nextInt();
            if (size > 0) {
                table = new HashTable(size);
            } else throw new ArrayIndexOutOfBoundsException();
        } catch (Exception e) {
            System.out.print("Введите правильное значение размера! ");
            return;
        }
        while (true) {
            try {
                boolean exit = true;
                while (exit) {
                    getMenu();
                    System.out.print("Ваш выбор: ");
                    int choice = input.nextInt();
                    switch (choice) {
                        case 1:
                            System.out.print("Введите ключ и значение ");
                            String key = input.next();
                            String value = input.next();
                            table.put(key, value);
                            break;
                        case 2:
                            System.out.print("Введите значение: ");
                            System.out.println(table.get(input.next()));
                            break;
                        case 3:
                            System.out.print("Введите значение: ");
                            table.remove(input.next());
                            System.out.println();
                            break;
                        case 4:
                            table.print();
                            break;
                        case 5:
                            exit = false;
                            break;
                        default:
                            System.out.println("Введите правильное действие ");
                    }
                }
                break;
            } catch (Exception e) {
                input.next();
            }
        }
    }

    public static void getMenu() {
        System.out.println("1. Добавление значения (метод put())");
        System.out.println("2. Поиск значения по ключу (метод get())");
        System.out.println("3. Удаление по ключу (метод remove())");
        System.out.println("4. Печать всех пар ключ/значение (метод print())");
        System.out.println("5. Выйти из программы");
        System.out.println();
    }
}
