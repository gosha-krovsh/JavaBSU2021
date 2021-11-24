package by.gosha_krovsh.monitoring.tests;

import by.gosha_krovsh.monitoring.collections.SortedCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

public class SortedCollectionTest {
    @Test
    void IntegerTest() {
        List<Integer> integers = List.of(1, 2, 3, 4, 5, 6, 7, 11);
        SortedCollection<Integer> collection =
                new SortedCollection<>(Integer::compare);
        collection.renew(List.of(1, 3, 7, 4, 11, 6, 2, 5));
        Assertions.assertArrayEquals(integers.toArray(), collection.currentState().toArray());
    }

    @Test
    void StringTest() {
        List<String> strings = List.of("MCR", "Papa", "Nananana", "Green Day");
        SortedCollection<String> collection =
                new SortedCollection<>(Comparator.comparingInt(String::length));
        collection.renew(List.of("Green Day", "MCR", "Nananana", "Papa"));
        Assertions.assertArrayEquals(strings.toArray(), collection.currentState().toArray());
    }
}
