package by.gosha_krovsh.monitoring.collections;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilteredCollection<T> implements ProcessedCollection<T, T> {
    public FilteredCollection(Predicate<T> predicate) {
        this.predicate = predicate;
    }

    @Override
    public void renew(Collection<? extends T> elements) {
        data = elements.stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends T> currentState() {
        return data;
    }

    private final Predicate<T> predicate;
    private Collection<? extends T> data;
}
