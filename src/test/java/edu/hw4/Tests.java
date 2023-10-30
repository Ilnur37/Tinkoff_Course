package edu.hw4;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Tests {
    private Animal cat1 = Animal.builder()
        .name("Cat1")
        .type(Animal.Type.CAT)
        .sex(Animal.Sex.F)
        .age(5)
        .height(40)
        .weight(6)
        .bites(true)
        .build();

    private Animal cat2 = Animal.builder()
        .name("Cat2")
        .type(Animal.Type.CAT)
        .sex(Animal.Sex.M)
        .age(10)
        .height(50)
        .weight(3)
        .bites(true)
        .build();

    private Animal dog1 = Animal.builder()
        .name("Dog1")
        .type(Animal.Type.DOG)
        .sex(Animal.Sex.F)
        .age(7)
        .height(60)
        .weight(6)
        .bites(true)
        .build();

    private Animal dog2 = Animal.builder()
        .name("Dog2")
        .type(Animal.Type.DOG)
        .sex(Animal.Sex.M)
        .age(15)
        .height(30)
        .weight(3)
        .bites(true)
        .build();

    private Animal bird1 = Animal.builder()
        .name("Bird1")
        .type(Animal.Type.BIRD)
        .sex(Animal.Sex.F)
        .age(1)
        .height(10)
        .weight(1)
        .bites(false)
        .build();

    private Animal bird2 = Animal.builder()
        .name("Bird2")
        .type(Animal.Type.BIRD)
        .sex(Animal.Sex.M)
        .age(5)
        .height(40)
        .weight(4)
        .bites(false)
        .build();

    private Animal fish1 = Animal.builder()
        .name("Fish1")
        .type(Animal.Type.FISH)
        .sex(Animal.Sex.F)
        .age(3)
        .height(35)
        .weight(5)
        .bites(false)
        .build();

    private Animal fish2 = Animal.builder()
        .name("Fish2")
        .type(Animal.Type.FISH)
        .sex(Animal.Sex.M)
        .age(13)
        .height(120)
        .weight(100)
        .bites(true)
        .build();

    private Animal spider1 = Animal.builder()
        .name("Spider1")
        .type(Animal.Type.SPIDER)
        .sex(Animal.Sex.F)
        .age(1)
        .height(1)
        .weight(0)
        .bites(false)
        .build();

    private Animal spider2 = Animal.builder()
        .name("Spider2")
        .type(Animal.Type.SPIDER)
        .sex(Animal.Sex.M)
        .age(2)
        .height(10)
        .weight(0)
        .bites(true)
        .build();


    private static final List<Animal> animals = new ArrayList<>();
    {
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
    }

    private static final List<Animal> animalsWithNull = new ArrayList<>(animals);
    {
        animalsWithNull.add(null);
    }

    /*@Test
    @DisplayName("Null")
    void countK_whenRuntimeExceptionLess0() {

        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> {
            Tasks.task1(animalsWithNull);
        }, "Null element not found");

        Assertions.assertEquals("List of animals contains null element!", thrown.getMessage());
    }

    @Test
    @DisplayName("task 1")
    void task1() {
        List<Animal> res = Tasks.task1(animals);

        List<Animal> trueRes = new ArrayList<>();
        trueRes.add(spider1);
        trueRes.add(bird1);
        trueRes.add(spider2);
        trueRes.add(dog2);
        trueRes.add(fish1);
        trueRes.add(cat1);
        trueRes.add(bird2);
        trueRes.add(cat2);
        trueRes.add(dog1);
        trueRes.add(fish2);
        Assertions.assertEquals(trueRes, res);

    }

    @Test
    @DisplayName("task 2")
    void task2() {
        List<Animal> res = Tasks.task2(animals);

        List<Animal> trueRes = new ArrayList<>();
        trueRes.add(fish2);
        trueRes.add(cat1);
        trueRes.add(dog1);
        trueRes.add(fish1);
        trueRes.add(bird2);
        trueRes.add(cat2);
        trueRes.add(dog2);
        trueRes.add(bird1);
        trueRes.add(spider1);
        trueRes.add(spider2);
        Assertions.assertEquals(trueRes, res);

    }*/

}
