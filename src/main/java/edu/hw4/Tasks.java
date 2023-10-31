package edu.hw4;

import edu.hw4.validation.ValidationError;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Tasks {

    private Tasks() {

    }

    /**
     * Task 1
     * Отсортировать животных по росту от самого маленького к самому большому -> {@Code List<Animal>}
     *
     * @param animals список животных
     * @return список животных
     */
    public static List<Animal> sortAnimalsByHeightAscending(List<Animal> animals) {
        validationError(animals);
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .toList();
    }

    /**
     * Task 2
     * Отсортировать животных по весу от самого тяжелого к самому легкому, выбрать k первых -> {@Code List<Animal>}
     *
     * @param animals список животных
     * @param limit   первые k животных
     * @return список животных
     */
    public static List<Animal> sortAndSelectTopKHeaviestAnimals(List<Animal> animals, int limit) {
        validationError(animals);
        return animals.stream()
            .sorted((a1, a2) -> a2.weight() - a1.weight())
            .limit(limit)
            .toList();
    }

    /**
     * Task 3
     * Сколько животных каждого вида -> {@Code Map<Animal.Type, Integer>}
     *
     * @param animals список животных
     * @return Map, где key - это тип животного, а value - их количество
     */
    public static Map<Animal.Type, Long> countAnimalsByType(List<Animal> animals) {
        validationError(animals);
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.counting()));
    }

    /**
     * Task 4
     * У какого животного самое длинное имя -> Animal
     *
     * @param animals список животных
     * @return животое
     */
    public static Animal findAnimalWithLongestName(List<Animal> animals) {
        validationError(animals);
        return animals.stream()
            .max(Comparator.comparingInt(a -> a.name().length())).orElseThrow();
    }

    /**
     * Task 5
     * Каких животных больше: самцов или самок -> Sex
     *
     * @param animals список животных
     * @return пол
     */
    public static Animal.Sex findDominantSex(List<Animal> animals) {
        validationError(animals);
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

    /**
     * Task 6
     * Самое тяжелое животное каждого вида -> {@Code Map<Animal.Type, Animal>}
     *
     * @param animals список животных
     * @return Map, где key - это тип животного, а value - животное
     */
    public static Map<Animal.Type, Animal> findHeaviestAnimalPerType(List<Animal> animals) {
        validationError(animals);

        return animals.stream()
            .collect(Collectors.toMap(
                Animal::type,
                animal -> animal,
                (a1, a2) -> a1.weight() > a2.weight() ? a1 : a2
            ));
    }

    /**
     * Task 7
     * K-е самое старое животное -> Animal
     *
     * @param animals список животных
     * @param number  прядковый номер животного
     * @return животное
     */
    public static Animal findKthOldestAnimal(List<Animal> animals, int number) {
        validationError(animals);
        int skip = number - 1;
        if (skip < 0 || skip >= animals.size()) {
            throw new IllegalArgumentException("Parameter number is invalid!");
        }

        return animals.stream()
            .sorted((a1, a2) -> a2.age() - a1.age())
            .skip(skip)
            .findFirst().orElseThrow();
    }

    /**
     * Task 8
     * Самое тяжелое животное среди животных ниже k см -> {@Code Optional<Animal>}
     *
     * @param animals   список животных
     * @param minHeight минимальный рост
     * @return животное или null
     */
    public static Optional<Animal> findHeaviestAnimalBelowHeight(List<Animal> animals, int minHeight) {
        validationError(animals);
        return animals.stream()
            .filter(animal -> animal.height() < minHeight).max(Comparator.comparingInt(Animal::height));
    }

    /**
     * Task 9
     * Сколько в сумме лап у животных в списке -> Integer
     *
     * @param animals список животных
     * @return количество лап
     */
    public static Integer sumNumberOfLegs(List<Animal> animals) {
        validationError(animals);
        return animals.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    /**
     * Task 10
     * Список животных, возраст у которых не совпадает с количеством лап -> {@Code List<Animal>}
     *
     * @param animals список животных
     * @return список животных
     */
    public static List<Animal> findAnimalsWithAgeMismatchPaws(List<Animal> animals) {
        validationError(animals);
        return animals.stream()
            .filter(animal -> animal.paws() != animal.age())
            .toList();
    }

    /**
     * Task 11
     * Список животных, которые могут укусить (bites == null или true)
     * и рост которых превышает 100 см -> {@Code List<Animal>}
     *
     * @param animals список животных
     * @return список животных
     */
    public static List<Animal> findAnimalsWithBitesAndHeightAbove(List<Animal> animals) {
        validationError(animals);
        final int MAX_HEIGHT = 100;
        return animals.stream()
            .filter(Animal::bites)
            .filter(animal -> animal.height() > MAX_HEIGHT)
            .collect(Collectors.toList());
    }

    /**
     * Task 12
     * Сколько в списке животных, вес которых превышает рост -> Integer
     *
     * @param animals список животных
     * @return количество животных
     */
    public static Integer countAnimalsWeightGreaterThanHeight(List<Animal> animals) {
        validationError(animals);
        return Math.toIntExact(animals.stream()
            .filter(animal -> animal.weight() > animal.height())
            .count());
    }

    /**
     * Task 13
     * Список животных, имена которых состоят из более чем двух слов -> {@Code List<Animal>}
     *
     * @param animals список животных
     * @return список животных
     */
    public static List<Animal> findAnimalsWithMultiWordNames(List<Animal> animals) {
        validationError(animals);
        return animals.stream()
            .filter(animal -> animal.name().split(" ").length > 2)
            .collect(Collectors.toList());
    }

    /**
     * Task 14
     * Есть ли в списке собака ростом более k см -> Boolean
     *
     * @param animals   список животных
     * @param minHeight минимальный рост
     * @return равда или ложь
     */
    public static Boolean isThereADogTallerThan(List<Animal> animals, int minHeight) {
        validationError(animals);
        return animals.stream()
            .anyMatch(animal -> animal.type() == Animal.Type.DOG && (animal.height() > minHeight));
    }

    /**
     * Task 15
     * Найти суммарный вес животных каждого вида, которым от k до l лет -> Integer
     *
     * @param animals список животных
     * @param minAge  минимальный возраст
     * @param maxAge  максимальный возраст
     * @return равда или ложь
     */
    public static Map<Animal.Type, Integer> sumWeightOfAnimalsByTypeBetweenAges(
        List<Animal> animals,
        int minAge,
        int maxAge
    ) {
        validationError(animals);
        return animals.stream()
            .filter(animal -> animal.age() >= minAge && animal.age() <= maxAge)
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(Animal::weight)));
    }

    /**
     * Task 16
     * Список животных, отсортированный по виду, затем по полу, затем по имени -> {@Code List<Integer>}
     *
     * @param animals список животных
     * @return список животных
     */
    public static List<Animal> sortAnimalsByTypeGenderName(List<Animal> animals) {
        validationError(animals);
        return animals.stream()
            .sorted(Comparator
                .comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name))
            .toList();
    }

    /**
     * Task 17
     * Правда ли, что пауки кусаются чаще, чем собаки -> Boolean (если данных для ответа недостаточно, вернуть false)
     *
     * @param animals список животных
     * @return правда или ложь
     */
    public static Boolean doSpidersBiteMoreThanDogs(List<Animal> animals) {
        validationError(animals);
        long spiderCount = animals.stream()
            .filter(animal -> animal.type() == Animal.Type.SPIDER && Boolean.TRUE.equals(animal.bites()))
            .count();

        long dogCount = animals.stream()
            .filter(animal -> animal.type() == Animal.Type.DOG && Boolean.TRUE.equals(animal.bites()))
            .count();

        if (spiderCount == 0 && dogCount == 0) {
            return false;
        }

        return spiderCount > dogCount;
    }

    /**
     * Task 18
     * Найти самую тяжелую рыбку в 2-х или более списках -> Animal
     *
     * @param animals список животных
     * @return животное
     */
    public static Animal findHeaviestFishInLists(List<List<Animal>> animals) {
        for (List<Animal> animalsSublist : animals) {
            validationError(animalsSublist);
        }
        return animals.stream()
            .flatMap(List::stream)
            .filter(animal -> animal.type() == Animal.Type.FISH)
            .max(Comparator.comparingInt(Animal::weight))
            .orElse(null);
    }

    /**
     * Task 20
     * Сделать результат предыдущего задания более читабельным:
     * вернуть имя и названия полей с ошибками, объединенные в строку -> {@Code Map<String, String>}
     *
     * @param animals список животных
     */
    private static void validationError(List<Animal> animals) {
        Map<String, String> errors = ValidationError.iterateThroughList(animals);
        if (!errors.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder("\n");
            for (Map.Entry<String, String> entry : errors.entrySet()) {
                stringBuilder.append("Exception in animal: ").append(entry.getKey()).append("\n");
                stringBuilder.append(entry.getValue()).append("\n");
            }
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    /**
     * Task 19
     * Животные, в записях о которых есть ошибки:
     * вернуть имя и список ошибок -> {@Code Map<String, Set<ValidationError>>}
     * Класс ValidationError и набор потенциальных проверок нужно придумать самостоятельно.
     *
     * @param animals список животных
     * @return Map, где key - это имя животного, а value - набор ошибок
     */
    public static Map<String, Set<IllegalArgumentException>> task19(List<Animal> animals) {
        Map<String, Set<IllegalArgumentException>> errors = new HashMap<>();

        for (Animal animal : animals) {
            Set<IllegalArgumentException> setOfErrors = ValidationError.validation(animal);
            if (!setOfErrors.isEmpty()) {
                errors.put(animal == null ? null : animal.name(), setOfErrors);
            }
        }
        return errors;
    }
}
