package edu.hw4;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ValidationErrorsTest {

    @ParameterizedTest
    @DisplayName("Животные с нулевыми полями")
    @MethodSource("animalsWithNullInFieldsFactory")
    void checkingNullInFields(List<Animal> animals, String exceptionMessage) {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Tasks.sortAnimalsByHeightAscending(animals);
        }, "No exception!");

        Assertions.assertEquals(exceptionMessage, thrown.getMessage());
    }

    static Stream<Arguments> animalsWithNullInFieldsFactory() {
        return Stream.of(
            Arguments.of(
                new ArrayList<>(
                    List.of(
                        Animal.builder()
                            .name(null)
                            .type(Animal.Type.FISH)
                            .sex(Animal.Sex.M)
                            .age(13)
                            .height(120)
                            .weight(100)
                            .bites(true).build())),
                """

                    Exception in animal: null
                    Name field is null!

                    """
            ),
            Arguments.of(
                new ArrayList<Animal>(
                    List.of(
                        Animal.builder().
                            name("nullType")
                            .type(null)
                            .sex(Animal.Sex.F)
                            .age(1)
                            .height(1)
                            .weight(0)
                            .bites(false).build())),
                """

                    Exception in animal: nullType
                    Type field is null!

                    """
            ),
            Arguments.of(
                new ArrayList<Animal>(
                    List.of(
                        Animal.builder()
                            .name("nullSex")
                            .type(Animal.Type.FISH)
                            .sex(null)
                            .age(1)
                            .height(1)
                            .weight(0)
                            .bites(false).build())),
                """

                    Exception in animal: nullSex
                    Sex field is null!

                    """
            )
        );
    }

    @ParameterizedTest
    @DisplayName("Животные с ошибками в полях")
    @MethodSource("animalsWithExceptionInFieldsFactory")
    void checkingExceptionInFields(List<Animal> animals, String exceptionMessage) {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Tasks.sortAnimalsByHeightAscending(animals);
        }, "No exception!");

        Assertions.assertEquals(exceptionMessage, thrown.getMessage());
    }

    public static Stream<Arguments> animalsWithExceptionInFieldsFactory() {
        List<Animal> animals = new ArrayList<>();
        animals.add(Animal.builder().
            name("Cat cat 1").
            type(Animal.Type.CAT).
            sex(Animal.Sex.F).
            age(5).
            height(40).
            weight(6)
            .bites(true).build());

        int invalidAge = 2222;
        int invalidHeight = -34;
        int invalidWeight = 15000000;

        animals.add(
            Animal.builder()
                .name("Cat cat 1")
                .type(Animal.Type.CAT)
                .sex(Animal.Sex.M)
                .age(34)
                .height(40)
                .weight(6)
                .bites(true).build());

        animals.add(Animal.builder()
            .name("ageError")
            .type(Animal.Type.DOG)
            .sex(Animal.Sex.F)
            .age(invalidAge)
            .height(40)
            .weight(6)
            .bites(true).build());

        animals.add(Animal.builder().
            name("heightError")
            .type(Animal.Type.BIRD)
            .sex(Animal.Sex.F)
            .age(5)
            .height(invalidHeight)
            .weight(6)
            .bites(true).build());

        animals.add(Animal.builder().
            name("weightError")
            .type(Animal.Type.FISH)
            .sex(Animal.Sex.M)
            .age(5)
            .height(40)
            .weight(invalidWeight)
            .bites(true).build());

        StringBuilder result = new StringBuilder();

        return Stream.of(
            Arguments.of(
                animals.subList(0, 2),
                result.append("""

                    Exception in animal: Cat cat 1
                    Name field is already used!

                    """)
                    .toString()
            ),
            Arguments.of(
                animals.subList(0, 3),
                result.append("""
                        Exception in animal: ageError
                        Age field greater than maximum value!  (Age =\040""")
                    .append(invalidAge).append("  MAX_AGE = ").append(150).append(")\n")
                    .toString() + "\n"
            ),
            Arguments.of(
                animals.subList(0, 4),
                result.append("""

                        Exception in animal: heightError
                        Height field less than minimum value!  (Height =\040""")
                    .append(invalidHeight).append("  MIN_HEIGHT = ").append(0).append(")\n")
                    .toString() + "\n"
            ),
            Arguments.of(
                animals,
                result.append("""

                        Exception in animal: weightError
                        Weight field greater than maximum value!  (Weight =\040""")
                    .append(invalidWeight).append("  MAX_WEIGHT = ").append(150000).append(")\n")
                    .toString() + "\n"
            )
        );
    }
}
