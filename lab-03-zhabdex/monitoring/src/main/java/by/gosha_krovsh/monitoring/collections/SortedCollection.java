package by.gosha_krovsh.monitoring.collections;

import java.util.Collection;
import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SortedCollection<T> implements ProcessedCollection<T, T> {
    public SortedCollection(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public <K extends Comparable<? super K>> SortedCollection(Function<T, K> keyExtractor) {
        this.comparator = Comparator.comparing(keyExtractor);
    }

    public <K extends Comparable<? super K>> SortedCollection(Function<T, K> keyExtractor,
                                                              boolean reversed) {
        this.comparator = Comparator.comparing(keyExtractor,
                reversed ? Comparator.reverseOrder() : Comparator.naturalOrder());
    }

    @Override
    public void renew(Collection<? extends T> elements) {
        data = elements.stream().sorted(comparator).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends T> currentState() {
        return data;
    }

    private final Comparator<T> comparator;
    private Collection<T> data;
}
