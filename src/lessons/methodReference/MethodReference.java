package lessons.methodReference;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;

public class MethodReference {

    public static void main(String[] args) {
        List<Anime> animes = Arrays.asList(
            new Anime("Naruto", 500),
            new Anime("Dragon Ball Kai", 200),
            new Anime("One Piece", 1000)
        );
        AnimeComparators comparators = new AnimeComparators();
        animes.sort(comparators::compareByEpisodeQuantityNonStatic);
        animes.sort((a1, a2) -> comparators.compareByEpisodeQuantityNonStatic(a1, a2));

        Function<String, Integer> stringToInteger = Integer::parseInt;
        Integer num = stringToInteger.apply("10");
        System.out.println(num);

        List<String> strings = Arrays.asList("Levi", "João", "Paulo", "Fernando", "Jorge");
        BiPredicate<List<String>, String> checkName = List::contains;
        boolean containsName = checkName.test(strings, "João");
        System.out.println(containsName);

    }

}

class Anime {
    String name;
    Integer episodes;

    public Anime(String name, Integer episodes) {
        this.name = name;
        this.episodes = episodes;
    }
}

class AnimeComparators {

    public static int compareByEpisodeQuantity(Anime a1, Anime a2) {
        return Integer.compare(a1.episodes, a2.episodes);
    }

    public int compareByEpisodeQuantityNonStatic(Anime a1, Anime a2) {
        return Integer.compare(a1.episodes, a2.episodes);
    }
}
