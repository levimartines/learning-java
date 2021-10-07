package lessons.flatMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FlatMap {

    public static void main(String[] args) {
        List<List<String>> employees = new ArrayList<>();
        List<String> designers = List.of("Artur", "Fernanda", "Luiz");
        List<String> developers = List.of("Levi", "Julia", "Caíque");
        List<String> interns = List.of("Joãozinho", "Ricardo", "Intern");

        employees.add(designers);
        employees.add(developers);
        employees.add(interns);

        employees.stream().flatMap(Collection::stream).forEach(System.out::println);

        List<String> words = List.of("Gomu", "Gomu", "No", "Mi");
        //String[] letters = words.get(0).split("");

        List<String> letters = words.stream()
            .map(w -> w.split(""))
            .flatMap(Arrays::stream)
            .collect(Collectors.toList());
        System.out.println(letters);
    }

}
