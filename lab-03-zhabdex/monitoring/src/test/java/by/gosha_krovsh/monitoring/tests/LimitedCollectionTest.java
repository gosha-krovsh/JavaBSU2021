package by.gosha_krovsh.monitoring.tests;

import by.gosha_krovsh.monitoring.collections.LimitedCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LimitedCollectionTest {
    @Test
    void IntegerTest1() {
        List<Integer> integers = List.of(1, 3, 2, 4);
        LimitedCollection<Integer> collection =
                new LimitedCollection<>(4);
        collection.renew(List.of(1, 3, 2, 4, 5, 6, 7, 11));
        Assertions.assertArrayEquals(integers.toArray(), collection.currentState().toArray());
    }

    @Test
    void IntegerTest2() {
        List<Integer> integers = List.of(15);
        LimitedCollection<Integer> collection =
                new LimitedCollection<>(1);
        collection.renew(List.of(15, 1, 2, 0, 6, 8, -1, 3));
        Assertions.assertArrayEquals(integers.toArray(), collection.currentState().toArray());
    }

    @Test
    void StringTest() {
        List<String> strings = List.of("Papa", "MCR");
        LimitedCollection<String> collection =
                new LimitedCollection<>(2);
        collection.renew(List.of("Papa", "MCR", "Nananana", "Green Day"));
        Assertions.assertArrayEquals(strings.toArray(), collection.currentState().toArray());
    }
}
