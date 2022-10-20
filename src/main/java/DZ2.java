/**
 * FileName: DZ2
 * Author: aspid
 * Date: 02.10.2022 15:02
 * Description:
 *

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;


public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        List<Animal> animals = new ArrayList<>();


        while (true) {
            System.out.println("Введите команду:");
            String inputString = scanner.next().toUpperCase(Locale.ROOT).trim();

//            if (contains(inputString)) {
                switch (Commands.valueOf(inputString)) {
                    case ADD:
                        animals.add(initAnimal());
                        break;
                    case LIST:
                        if (animals.size() == 0) {
                            System.out.println("Список животных пустой.");
                        }

                        for (Animal animal : animals) {
                            System.out.println(animal.toString());
                        }
                        break;
                    case EXIT:
                        System.exit(0);
                }
            } else {
                System.out.println("Неверная команда. Доступные команды: add/list/exit. Попробуйте снова.");
            }
        }
    }

    private static Animal initAnimal() {

        Animal animal = null;
        int age = 0;
        int weight = 0;
        while (animal == null) {

            System.out.println("Какое животное (cat/dog/duck)?");
            String animalType = scanner.next().toLowerCase(Locale.ROOT).trim();

            switch (animalType) {
                case "cat":
                    animal = new Cat();
                    break;
                case "dog":
                    animal = new Dog();
                    break;
                case "duck":
                    animal = new Duck();
                    break;
                default:
                    System.out.println("Такого животного нет в списке. Попробуйте снова.");


            }
        }


        System.out.println("Введите имя: ");
        animal.setName(scanner.next());

        System.out.println("Введите возраст: ");
        while (age <= 0) {
            while (!scanner.hasNextInt()) {

                System.out.println("Возраст должен быть числом. Попробуйте снова.");
                scanner.next();
            }
            age = scanner.nextInt();
            animal.setAge(age);
            if (age <= 0) {
                System.out.println("Возраст должен быть положительным числом. Попробуйте снова");
            }
        }

        System.out.println("Введите вес: ");
        while (weight <= 0) {
            while (!scanner.hasNextInt()) {
                System.out.println("Вес должен быть числом. Попробуйте снова.");
                scanner.next();
            }
            weight = scanner.nextInt();
            animal.setWeight(weight);
            if (weight <= 0) {
                System.out.println("Вес должен быть положительным числом. Попробуйте снова");

            }
        }

        System.out.println("Введите цвет: ");
        animal.setColor(scanner.next());

        System.out.println("Животное добавлено.");

        return animal;
    }

    private static boolean contains(String inputCommand) {

        for (Commands c : Commands.values()) {
            if (c.name().equals(inputCommand)) {
                return true;
            }
        }

        return false;
    }
}



