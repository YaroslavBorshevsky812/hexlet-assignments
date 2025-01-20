package exercise;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN

public class App {
    public static List<String> buildApartmentsList(List<Home> appartmentList, int itemsAmount) {
        return appartmentList.stream()
                             .sorted(Comparator.comparing(Home::getArea))
                             .limit(itemsAmount)
                             .map(Object::toString)
                             .collect(Collectors.toList());
    }
}
// END
