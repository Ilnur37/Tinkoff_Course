package edu.hw4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Tasks {
    private static final int MIN_AGE = 0;
    private static final int MAX_AGE = 150;
    private static final int MIN_HEIGHT = 1;
    private static final int MAX_HEIGHT = 3300;
    private static final int MIN_WEIGHT = 0;
    private static final int MAX_WEIGHT = 150000;

    private Tasks() {

    }

    private static void checkNull(List<Animal> animals) {
        for (Animal animal : animals) {
            if (animal == null) {
                throw new NullPointerException("List of animals contains null element!");
            }
        }
    }

    private static void checkValid(List<Animal> animals) {
        for (Animal animal : animals) {
            if ((animal.age() < MIN_AGE || animal.age() > MAX_AGE)) {
                throw new IllegalArgumentException("The list contains animals with incorrect ages!");
            }
            if ((animal.height() < MIN_HEIGHT || animal.height() > MAX_HEIGHT)) {
                throw new IllegalArgumentException("The list contains animals with incorrect growth!");
            }

            if ((animal.weight() < MIN_WEIGHT || animal.weight() > MAX_WEIGHT)) {
                throw new IllegalArgumentException("The list contains animals with incorrect weight!");
            }
        }
    }

    public static List<Animal> task1(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .toList();
    }

    public static List<Animal> task2(List<Animal> animals) {
        return animals.stream()
            .sorted((a1, a2) -> a2.weight() - a1.weight())
            .toList();
    }

    public static Map<Animal.Type, Long> task3(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.counting()));
    }

    public static Animal task4(List<Animal> animals) {
        return animals.stream()
            .max((a1, a2) -> a2.name().length() - a1.name().length()).orElseThrow();
    }

    public static Animal.Sex task5(List<Animal> animals) {
        long maleCount = animals.stream()
            .filter(animal -> animal.sex() == Animal.Sex.M)
            .count();

        long femaleCount = animals.stream()
            .filter(animal -> animal.sex() == Animal.Sex.F)
            .count();

        if (maleCount > femaleCount) {
            return Animal.Sex.M;
        } else {
            return Animal.Sex.F;
        }
    }

    public static Map<Animal.Type, Animal> task6(List<Animal> animals) {

        return animals.stream()
            .collect(Collectors.toMap(
                Animal::type,
                animal -> animal,
                (a1, a2) -> a1.weight() > a2.weight() ? a1 : a2
            ));
    }

    public static Animal task7(List<Animal> animals, int k) {
        int skip = k - 1;
        if (skip < 0 || skip >= animals.size()) {
            throw new IllegalArgumentException("Parameter K is invalid!");
        }

        return animals.stream()
            .sorted((a1, a2) -> a2.age() - a1.age())
            .skip(skip)
            .findFirst().orElseThrow();
    }

    public static Optional<Animal> task8(List<Animal> animals, int k) {
        return animals.stream()
            .filter(animal -> animal.height() < k).max(Comparator.comparingInt(Animal::height));
    }

    public static Integer task9(List<Animal> animals) {
        return animals.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    public static List<Animal> task10(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.paws() != animal.age())
            .toList();
    }

    public static void main(String[] args) {
        Animal cat1 = Animal.builder()
            .name("Cat1")
            .type(Animal.Type.CAT)
            .sex(Animal.Sex.F)
            .age(5)
            .height(40)
            .weight(6)
            .bites(true)
            .build();

        Animal cat2 = Animal.builder()
            .name("Cat2")
            .type(Animal.Type.CAT)
            .sex(Animal.Sex.M)
            .age(10)
            .height(50)
            .weight(3)
            .bites(true)
            .build();

        Animal dog1 = Animal.builder()
            .name("Dog1")
            .type(Animal.Type.DOG)
            .sex(Animal.Sex.F)
            .age(7)
            .height(60)
            .weight(6)
            .bites(true)
            .build();

        Animal dog2 = Animal.builder()
            .name("Dog2")
            .type(Animal.Type.DOG)
            .sex(Animal.Sex.M)
            .age(15)
            .height(30)
            .weight(3)
            .bites(true)
            .build();

        Animal bird1 = Animal.builder()
            .name("Bird1")
            .type(Animal.Type.BIRD)
            .sex(Animal.Sex.F)
            .age(1)
            .height(10)
            .weight(1)
            .bites(false)
            .build();

        Animal bird2 = Animal.builder()
            .name("Bird2")
            .type(Animal.Type.BIRD)
            .sex(Animal.Sex.M)
            .age(5)
            .height(40)
            .weight(4)
            .bites(false)
            .build();

        Animal fish1 = Animal.builder()
            .name("Fish1")
            .type(Animal.Type.FISH)
            .sex(Animal.Sex.F)
            .age(3)
            .height(35)
            .weight(5)
            .bites(false)
            .build();

        Animal fish2 = Animal.builder()
            .name("Fish2")
            .type(Animal.Type.FISH)
            .sex(Animal.Sex.M)
            .age(13)
            .height(120)
            .weight(100)
            .bites(true)
            .build();

        Animal spider1 = Animal.builder()
            .name("Spider1")
            .type(Animal.Type.SPIDER)
            .sex(Animal.Sex.F)
            .age(1)
            .height(1)
            .weight(0)
            .bites(false)
            .build();

        Animal spider2 = Animal.builder()
            .name("Spider2")
            .type(Animal.Type.SPIDER)
            .sex(Animal.Sex.M)
            .age(2)
            .height(10)
            .weight(0)
            .bites(true)
            .build();

        List<Animal> animals = new ArrayList<>();
        animals.add(cat1);
        animals.add(cat2);
        animals.add(dog1);
        animals.add(dog2);
        animals.add(bird1);
        animals.add(bird2);
        animals.add(fish1);
        animals.add(fish2);
        animals.add(spider1);
        animals.add(spider2);

        //System.out.println(task6(animals));
        //System.out.println(task7(animals, 19));
        //System.out.println(task8(animals, 40));
    }
}
