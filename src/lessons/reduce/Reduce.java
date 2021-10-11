package lessons.reduce;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Reduce {

    public static void main(String[] args) {
        List<Integer> integers = List.of(1, 2, 3, 4, 5, 6);

        integers.stream().reduce(Integer::sum).ifPresent(System.out::println);
        /* With identity value, result will no more be Optional */
        System.out.println(integers.stream().reduce(0, (x, y) -> x + y));

        System.out.println("-----------------");

        System.out.println(integers.stream().reduce(1, (x, y) -> x * y));

        System.out.println(integers.stream().max(Integer::compareTo).get());

        System.out.println("-----------------");

        List<Anime> animes = Arrays.asList(
            new Anime("Naruto", 8.99),
            new Anime("Dragon Ball Kai", 10.99),
            new Anime("One Piece", 4.90),
            new Anime("Monogatari", 4),
            new Anime("Full Metal Alchemist", 2.50)
        );
        animes.stream()
            .map(anime -> anime.price)
            .filter(price -> price > 3)
            .reduce(Double::sum)
            .ifPresent(System.out::println);

        double sum = animes.stream()
            .mapToDouble(anime -> anime.price)
            .filter(price -> price > 3)
            .sum();
        System.out.println(sum);

    }
}

class Anime {
    String name;
    double price;

    public Anime(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
