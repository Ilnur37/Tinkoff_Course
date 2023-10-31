package edu.hw4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnimalTest {
    private final Animal cat1 = Animal.builder()
        .name("Cat cat 1")
        .type(Animal.Type.CAT)
        .sex(Animal.Sex.F)
        .age(5)
        .height(40)
        .weight(6)
        .bites(true)
        .build();

    private final Animal cat2 = Animal.builder()
        .name("Cat2")
        .type(Animal.Type.CAT)
        .sex(Animal.Sex.M)
        .age(10)
        .height(50)
        .weight(3)
        .bites(true)
        .build();

    private final Animal dog1 = Animal.builder()
        .name("Dog1")
        .type(Animal.Type.DOG)
        .sex(Animal.Sex.F)
        .age(7)
        .height(60)
        .weight(6)
        .bites(true)
        .build();

    private final Animal dog2 = Animal.builder()
        .name("Dog2")
        .type(Animal.Type.DOG)
        .sex(Animal.Sex.M)
        .age(15)
        .height(30)
        .weight(3)
        .bites(true)
        .build();

    private final Animal bird1 = Animal.builder()
        .name("Bird1")
        .type(Animal.Type.BIRD)
        .sex(Animal.Sex.M)
        .age(5)
        .height(40)
        .weight(4)
        .bites(false)
        .build();

    private final Animal fish1 = Animal.builder()
        .name("Fish1")
        .type(Animal.Type.FISH)
        .sex(Animal.Sex.M)
        .age(13)
        .height(120)
        .weight(100)
        .bites(true)
        .build();

    private final Animal spider1 = Animal.builder()
        .name("Spider spider 1")
        .type(Animal.Type.SPIDER)
        .sex(Animal.Sex.F)
        .age(1)
        .height(1)
        .weight(0)
        .bites(false)
        .build();

    private final List<Animal> animals = new ArrayList<>();
    {
        animals.add(cat1);
        animals.add(cat2);
        animals.add(dog1);
        animals.add(dog2);
        animals.add(bird1);
        animals.add(fish1);
        animals.add(spider1);
    }

    @Test
    @DisplayName("task 1")
    void task1() {
        List<Animal> res = Tasks.sortAnimalsByHeightAscending(animals);

        List<Animal> trueRes = new ArrayList<>();
        trueRes.add(spider1);
        trueRes.add(dog2);
        trueRes.add(cat1);
        trueRes.add(bird1);
        trueRes.add(cat2);
        trueRes.add(dog1);
        trueRes.add(fish1);
        Assertions.assertEquals(trueRes, res);
    }

    @Test
    @DisplayName("task 2")
    void task2() {
        int k = 3;
        List<Animal> res = Tasks.sortAndSelectTopKHeaviestAnimals(animals, k);

        List<Animal> trueRes = new ArrayList<>();
        trueRes.add(fish1);
        trueRes.add(cat1);
        trueRes.add(dog1);
        Assertions.assertEquals(trueRes, res);
    }

    @Test
    @DisplayName("task 3")
    void task3() {
        Map<Animal.Type, Long> res = Tasks.countAnimalsByType(animals);

        Map<Animal.Type, Long> trueRes = new HashMap<>();
        trueRes.put(Animal.Type.CAT, 2L);
        trueRes.put(Animal.Type.DOG, 2L);
        trueRes.put(Animal.Type.BIRD, 1L);
        trueRes.put(Animal.Type.FISH, 1L);
        trueRes.put(Animal.Type.SPIDER, 1L);
        Assertions.assertEquals(trueRes, res);
    }

    @Test
    @DisplayName("task 4")
    void task4() {
        Animal res = Tasks.findAnimalWithLongestName(animals);

        Assertions.assertEquals(spider1, res);
    }

    @Test
    @DisplayName("task 5")
    void task5() {
        Animal.Sex res = Tasks.findDominantSex(animals);

        Animal.Sex trueRes = Animal.Sex.M;
        Assertions.assertEquals(trueRes, res);
    }

    @Test
    @DisplayName("task 6")
    void task6() {
        Map<Animal.Type, Animal> res = Tasks.findHeaviestAnimalPerType(animals);

        Map<Animal.Type, Animal> trueRes = new HashMap<>();
        trueRes.put(Animal.Type.CAT, cat1);
        trueRes.put(Animal.Type.DOG, dog1);
        trueRes.put(Animal.Type.BIRD, bird1);
        trueRes.put(Animal.Type.FISH, fish1);
        trueRes.put(Animal.Type.SPIDER, spider1);
        Assertions.assertEquals(trueRes, res);
    }

    @Test
    @DisplayName("task 7")
    void task7() {
        int k = 3;
        Animal res = Tasks.findKthOldestAnimal(animals, k);

        Assertions.assertEquals(cat2, res);
    }

    @Test
    @DisplayName("task 8")
    void task8() {
        int k = 30;
        Optional<Animal> res = Tasks.findHeaviestAnimalBelowHeight(animals, k);

        Assertions.assertTrue(res.isPresent() && spider1.equals(res.get()));
    }

    @Test
    @DisplayName("task 9")
    void task9() {
        Integer res = Tasks.sumNumberOfLegs(animals);

        Assertions.assertEquals(26, res);
    }

    @Test
    @DisplayName("task 10")
    void task10() {
        List<Animal> res = Tasks.findAnimalsWithAgeMismatchPaws(animals);

        List<Animal> trueRes = new ArrayList<>(animals);

        Assertions.assertEquals(trueRes, res);
    }

    @Test
    @DisplayName("task 11")
    void task11() {
        List<Animal> res = Tasks.findAnimalsWithBitesAndHeightAbove(animals);

        List<Animal> trueRes = new ArrayList<>();
        trueRes.add(fish1);

        Assertions.assertEquals(trueRes, res);
    }

    @Test
    @DisplayName("task 12")
    void task12() {
        Integer res = Tasks.countAnimalsWeightGreaterThanHeight(animals);

        Assertions.assertEquals(0, res);
    }

    @Test
    @DisplayName("task 13")
    void task13() {
        List<Animal> res = Tasks.findAnimalsWithMultiWordNames(animals);

        List<Animal> trueRes = new ArrayList<>();
        trueRes.add(cat1);
        trueRes.add(spider1);

        Assertions.assertEquals(trueRes, res);
    }

    @Test
    @DisplayName("task 14")
    void task14() {
        int k = 50;
        Boolean res = Tasks.isThereADogTallerThan(animals, k);

        Assertions.assertTrue(res);
    }

    @Test
    @DisplayName("task 15")
    void task15() {
        int k = 2;
        int l = 5;
        Map<Animal.Type, Integer> res = Tasks.sumWeightOfAnimalsByTypeBetweenAges(animals, k, l);

        Map<Animal.Type, Integer> trueRes = new HashMap<>();
        trueRes.put(Animal.Type.CAT, 6);
        trueRes.put(Animal.Type.BIRD, 4);
        Assertions.assertEquals(trueRes, res);
    }

    @Test
    @DisplayName("task 16")
    void task16() {
        List<Animal> res = Tasks.sortAnimalsByTypeGenderName(animals);

        List<Animal> trueRes = new ArrayList<>();
        trueRes.add(cat2);
        trueRes.add(cat1);
        trueRes.add(dog2);
        trueRes.add(dog1);
        trueRes.add(bird1);
        trueRes.add(fish1);
        trueRes.add(spider1);
        Assertions.assertEquals(trueRes, res);
    }

    @Test
    @DisplayName("task 17")
    void task17() {
        Boolean res = Tasks.doSpidersBiteMoreThanDogs(animals);

        Assertions.assertFalse(res);
    }


}
