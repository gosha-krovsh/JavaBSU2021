package by.gosha_krovsh.monitoring.tests;

import by.gosha_krovsh.monitoring.collections.FilteredCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FilteredCollectionTest {
    @Test
    void IntegerTest1() {
        List<Integer> integers = List.of(1, 3, 2, 4);
        FilteredCollection<Integer> collection =
                new FilteredCollection<>(element -> element < 5);
        collection.renew(List.of(1, 3, 2, 4, 5, 6, 7, 11));
        Assertions.assertArrayEquals(integers.toArray(), collection.currentState().toArray());
    }

    @Test
    void IntegerTest2() {
        List<Integer> integers = List.of(15, 6, 8, 3);
        FilteredCollection<Integer> collection =
                new FilteredCollection<>(element -> element >= 3);
        collection.renew(List.of(1, 15, 2, 0, 6, 8, -1, 3));
        Assertions.assertArrayEquals(integers.toArray(), collection.currentState().toArray());
    }

    @Test
    void StringTest() {
        List<String> strings = List.of("Papa", "MCR");
        FilteredCollection<String> collection =
                new FilteredCollection<>(element -> element.length() < 5);
        collection.renew(List.of("Nananana", "Papa", "MCR", "Green Day"));
        Assertions.assertArrayEquals(strings.toArray(), collection.currentState().toArray());
    }
}
