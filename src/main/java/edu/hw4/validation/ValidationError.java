package edu.hw4.validation;

import edu.hw4.Animal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ValidationError {
    private final int maxAge = 150;
    private final int minAge = 0;
    private final int maxHeight = 3300;
    private final int minHeight = 0;
    private final int maxWeight = 150000;
    private final int minWeight = 0;
    private final List<String> usedNames = new ArrayList<>();

    public ValidationError() {

    }

    public Map<String, String> iterateThroughList(List<Animal> animals) {
        Map<String, String> errors = new HashMap<>();

        for (Animal animal : animals) {
            Set<IllegalArgumentException> setOfErrors = validation(animal);
            if (!setOfErrors.isEmpty()) {
                StringBuilder exception = new StringBuilder();
                for (IllegalArgumentException ex : setOfErrors) {
                    exception.append(ex.getMessage()).append("\n");
                }
                errors.put(animal == null ? null : animal.name(), exception.toString());
            }
        }
        clearUsedNames();

        return errors;
    }

    public Set<IllegalArgumentException> validation(Animal animal) {
        Set<IllegalArgumentException> validationErrors = new HashSet<>();

        try {
            checkNull(animal);
        } catch (IllegalArgumentException animalIsNull) {
            validationErrors.add(animalIsNull);
            return validationErrors;
        }

        try {
            checkName(animal);
            try {
                checkForAlreadyUsedName(animal);
            } catch (IllegalArgumentException nameIsUsed) {
                validationErrors.add(nameIsUsed);
            }
        } catch (IllegalArgumentException nameIsNull) {
            validationErrors.add(nameIsNull);
        }

        try {
            checkType(animal);
        } catch (IllegalArgumentException typeInvalid) {
            validationErrors.add(typeInvalid);
        }

        try {
            checkSex(animal);
        } catch (IllegalArgumentException sexInvalid) {
            validationErrors.add(sexInvalid);
        }

        try {
            checkAge(animal);
        } catch (IllegalArgumentException ageInvalid) {
            validationErrors.add(ageInvalid);
        }

        try {
            checkHeight(animal);
        } catch (IllegalArgumentException heightInvalid) {
            validationErrors.add(heightInvalid);
        }

        try {
            checkWeight(animal);
        } catch (IllegalArgumentException weightInvalid) {
            validationErrors.add(weightInvalid);
        }

        return validationErrors;
    }

    private void checkNull(Animal animal) {
        if (animal == null) {
            throw new IllegalArgumentException("Animal is null!");
        }
    }

    private void checkName(Animal animal) {
        if (animal.name() == null) {
            throw new IllegalArgumentException("Name field is null!");
        }
    }

    private void checkForAlreadyUsedName(Animal animal) {
        if (usedNames.contains(animal.name())) {
            throw new IllegalArgumentException("Name field is already used!");
        } else {
            usedNames.add(animal.name());
        }
    }

    private void checkType(Animal animal) {
        if (animal.type() == null) {
            throw new IllegalArgumentException("Type field is null!");
        }
    }

    private void checkSex(Animal animal) {
        if (animal.sex() == null) {
            throw new IllegalArgumentException("Sex field is null!");
        }
    }

    private void checkAge(Animal animal) {
        if (animal.age() > maxAge) {
            throw new IllegalArgumentException(
                "Age field greater than maximum value!  (Age = " + animal.age()
                    + "  MAX_AGE = " + maxAge + ")");
        }
        if (animal.age() < minAge) {
            throw new IllegalArgumentException(
                "Age field less than maximum value!  (Age = " + animal.age()
                    + "  MIN_AGE = " + minAge + ")");
        }
    }

    private void checkHeight(Animal animal) {
        if (animal.height() > maxHeight) {
            throw new IllegalArgumentException(
                "Height field greater than maximum value!  (Height = " + animal.height()
                    + "  MAX_HEIGHT = " + maxHeight + ")");
        }
        if (animal.height() < minHeight) {
            throw new IllegalArgumentException(
                "Height field less than minimum value!  (Height = " + animal.height()
                    + "  MIN_HEIGHT = " + minHeight + ")");
        }
    }

    private void checkWeight(Animal animal) {
        if (animal.weight() > maxWeight) {
            throw new IllegalArgumentException(
                "Weight field greater than maximum value!  (Weight = " + animal.weight()
                    + "  MAX_WEIGHT = " + maxWeight + ")");
        }
        if (animal.weight() < minWeight) {
            throw new IllegalArgumentException(
                "Weight field less than minimum value!  (Weight = " + animal.weight()
                    + "  MIN_WEIGHT = " + minWeight + ")");
        }
    }

    private void clearUsedNames() {
        usedNames.clear();
    }
}
