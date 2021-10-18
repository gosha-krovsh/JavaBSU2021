package by.gosha_krovsh.monitoring.tests;

import by.gosha_krovsh.monitoring.collections.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class CombinedCollectionsTest {
    @Test
    void SimpleProcessedTest1() {
        ProcessedCollection<Integer, Integer> integers = new FilteredCollection<Integer>(element -> element < 5)
                .compose(new LimitedCollection<Integer>(3));
        integers.renew(List.of(5, 7, 2, 1, 4, 8, 3));

        List<Integer> expected = List.of(2, 1, 4);
        Assertions.assertNotNull(integers);
        Assertions.assertArrayEquals(expected.toArray(), integers.currentState().toArray());
    }

    @Test
    void SimpleFinalProcessedTest1() {
        FinalProcessedCollection<Integer, Optional<Integer>> integers =
                new FilteredCollection<Integer>(element -> element < 5)
                .compose(new ReducedCollection<Integer>(Integer::sum));
        integers.renew(List.of(5, 7, 2, 1, 4, 8, 3));

        Assertions.assertNotNull(integers);
        Assertions.assertEquals(10, integers.currentState().get());
    }
}
