package edu.hw4.validation;

import edu.hw4.Animal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ValidationError {
    private final List<String> USED_NAMES = new ArrayList<>();

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
        if (USED_NAMES.contains(animal.name())) {
            throw new IllegalArgumentException("Name field is already used!");
        } else {
            USED_NAMES.add(animal.name());
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
        int MAX_AGE = 150;
        if (animal.age() > MAX_AGE) {
            throw new IllegalArgumentException(
                "Age field greater than maximum value!  (Age = " + animal.age() +
                    "  MAX_AGE = " + MAX_AGE + ")");
        }
        int MIN_AGE = 0;
        if (animal.age() < MIN_AGE) {
            throw new IllegalArgumentException(
                "Age field less than maximum value!  (Age = " + animal.age() +
                    "  MIN_AGE = " + MIN_AGE + ")");
        }
    }

    private void checkHeight(Animal animal) {
        int MAX_HEIGHT = 3300;
        if (animal.height() > MAX_HEIGHT) {
            throw new IllegalArgumentException(
                "Height field greater than maximum value!  (Height = " + animal.height() +
                    "  MAX_HEIGHT = " + MAX_HEIGHT + ")");
        }
        int MIN_HEIGHT = 0;
        if (animal.height() < MIN_HEIGHT) {
            throw new IllegalArgumentException(
                "Height field less than minimum value!  (Height = " + animal.height() +
                    "  MIN_HEIGHT = " + MIN_HEIGHT + ")");
        }
    }

    private void checkWeight(Animal animal) {
        int MAX_WEIGHT = 150000;
        if (animal.weight() > MAX_WEIGHT) {
            throw new IllegalArgumentException(
                "Weight field greater than maximum value!  (Weight = " + animal.weight() +
                    "  MAX_WEIGHT = " + MAX_WEIGHT + ")");
        }
        int MIN_WEIGHT = 0;
        if (animal.weight() < MIN_WEIGHT) {
            throw new IllegalArgumentException(
                "Weight field less than minimum value!  (Weight = " + animal.weight() +
                    "  MIN_WEIGHT = " + MIN_WEIGHT + ")");
        }
    }

    private void clearUsedNames() {
        USED_NAMES.clear();
    }
}
