package com.functionalprogramming.validation;

public class ValidatorUtil {

    public static final Validation<String> notNullString = GenericValidation.from( s -> s != null);

    public static final Validation<String> notEmptyString = GenericValidation.from( s -> !s.isEmpty());

    public static final Validation<Integer> notNullInteger = GenericValidation.from( s -> s != null);

    public static final Validation<Integer> greaterThanZero = GenericValidation.from( s -> s > 0);

    public static final Validation<String> stringMoreThan(int size) {
        return GenericValidation.from( s -> (s).length() > size);
    }

    public static final Validation<String> stringLessThan(int size) {
        return GenericValidation.from( s -> (s).length() < size);
    }

    public static final Validation<String> stringBetween(int moreThan, int lessThan) {
        return stringMoreThan(moreThan).and(stringLessThan(lessThan));
    }

    public static final Validation<Integer> integerMoreThan(int limit) {
        return GenericValidation.from( s -> s > limit);
    }

    public static final Validation<Integer> integerLessThan(int limit) {
        return GenericValidation.from( s -> s < limit);
    }

    public static final Validation <Integer> integerBetween(int morethan, int lessThan) {
        return integerMoreThan(morethan).and(integerLessThan(lessThan));
    }

    public static final Validation<Employee> namesSame =
            GenericValidation.from( s -> s.getFirstName().equals(s.getLastName()));

}
