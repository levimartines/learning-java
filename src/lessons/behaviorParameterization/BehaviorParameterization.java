package lessons.behaviorParameterization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BehaviorParameterization {

    public static void main(String[] args) {
        List<Car> cars = Arrays
            .asList(
                new Car("Saveiro", "white", 2012), new Car("Voyage", "white", 1998),
                new Car("Golf", "black", 2022), new Car("Uno", "black", 2012),
                new Car("Ferrari", "red", 2022));
        List<Car> whiteCars = filter(cars, car -> car.color.equals("white"));
        forEach(whiteCars, System.out::println);

        List<Car> yearGreaterOrEqual = filter(cars, car -> car.year >= 2012);
        System.out.println(yearGreaterOrEqual);

        System.out.println();

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 99, 999);
/*        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 99, 999)
            .parallelStream().filter(n -> n >= 9).collect(Collectors.toList());*/
        List<Integer> valuesGreaterOrEqual = filter(numbers, number -> number >= 9);
        System.out.println(valuesGreaterOrEqual);

        System.out.println();

        List<String> strings = Arrays.asList("Naruto", "Alucard", "Sasuke", "Goku");
        List<String> stringsUpper = map(strings, String::toUpperCase);
        System.out.println(stringsUpper);

        List<Integer> stringsLength = map(strings, String::length);
        System.out.println(stringsLength);
    }

    private static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
/*        List<T> filteredList = new ArrayList<>();
        for (T object : list) {
            if (predicate.test(object)) {
                filteredList.add(object);
            }
        }
        return filteredList;*/
        return list.stream().filter(predicate).collect(Collectors.toList());
    }

    private static <T> void forEach(List<T> list, Consumer<T> consumer) {
        for (T t : list) {
            consumer.accept(t);
        }
    }

    private static <T, R> List<R> map(List<T> list, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(function.apply(t));
        }
        return result;
    }
}

class Car {
    String name;
    String color;
    Integer year;

    public Car(String name, String color, Integer year) {
        this.name = name;
        this.color = color;
        this.year = year;
    }

    @Override
    public String toString() {
        return "Name='" + name + '\'' +
            ", color='" + color + '\'' +
            ", year=" + year;
    }
}
