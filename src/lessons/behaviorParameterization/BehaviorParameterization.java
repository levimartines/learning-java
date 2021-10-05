package lessons.behaviorParameterization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        System.out.println(whiteCars);

        List<Car> yearGreaterOrEqual = filter(cars, car -> car.year >= 2012);
        System.out.println(yearGreaterOrEqual);

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 99, 999);
/*        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 99, 999)
            .parallelStream().filter(n -> n >= 9).collect(Collectors.toList());*/
        List<Integer> valuesGreaterOrEqual = filter(numbers, number -> number >= 9);
        System.out.println(valuesGreaterOrEqual);


    }

    private static <T> List<T> filter (List<T> list, Predicate<T> predicate) {
/*        List<T> filteredList = new ArrayList<>();
        for (T object : list) {
            if (predicate.test(object)) {
                filteredList.add(object);
            }
        }
        return filteredList;*/
        return list.stream().filter(predicate).collect(Collectors.toList());
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
