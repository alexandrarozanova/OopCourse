package ru.academits.rozanova.lambdas;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("Иван", 22),
                new Person("Сергей", 30),
                new Person("Петр", 45),
                new Person("Иван", 5),
                new Person("Сергей", 12),
                new Person("Петр", 18),
                new Person("Иван", 22),
                new Person("Ольга", 18),
                new Person("Максим", 28));

        List<String> uniqueNamesList = persons.stream()
                .map(Person::name)
                .distinct()
                .toList();

        System.out.println("Список уникальных имен: " + uniqueNamesList);

        String uniqueNamesString = persons.stream()
                .map(Person::name)
                .distinct()
                .collect(Collectors.joining(", ", "Имена: ", "."));

        System.out.println("Список уникальных имен в формате \"Имена: Петр, Иван.\":");
        System.out.println(uniqueNamesString);

        OptionalDouble minorsAverageAge = persons.stream()
                .filter(p -> p.age() < 18)
                .mapToInt(Person::age)
                .average();

        if (minorsAverageAge.isPresent()) {
            System.out.println("Средний возраст людей из списка, младше 18 лет: " + minorsAverageAge.getAsDouble());
        } else {
            System.out.println("В списке нет людей младше 18 лет.");
        }

        Map<String, Double> averageAgesByNames = persons.stream()
                .collect(Collectors.groupingBy(Person::name, Collectors.averagingDouble(Person::age)));

        System.out.println("Средний возраст людей, сгруппированных по именам:");

        averageAgesByNames.forEach((name, age) ->
                System.out.println("Имя: " + name + ", средний возраст: " + age));

        String personsWithAgeBetween20And45 = persons.stream()
                .filter(p -> p.age() >= 20 && p.age() <= 45)
                .sorted(Comparator.comparingInt(Person::age).reversed())
                .map(Person::name)
                .collect(Collectors.joining(", "));

        System.out.println("Список людей в возрасте от 20 до 45 лет, отсортированный по убыванию возраста: " + personsWithAgeBetween20And45);
    }
}