package by.gosha_krovsh.monitoring.tests;

import by.gosha_krovsh.monitoring.collections.ReducedCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.BinaryOperator;

public class ReducedCollectionTest {
    @Test
    void IntegerTest() {
        ReducedCollection<Integer> collection =
                new ReducedCollection<>(Integer::sum);
        collection.renew(List.of(15, 1, 2, 0, 6, 8, -1, 3));
        Assertions.assertEquals(34,
                collection.currentState().isPresent() ? collection.currentState().get() : -1);
    }

    @Test
    void EmptyTest() {
        ReducedCollection<Integer> collection =
                new ReducedCollection<>(Integer::sum);
        Assertions.assertTrue(collection.currentState().isEmpty());
    }
}
