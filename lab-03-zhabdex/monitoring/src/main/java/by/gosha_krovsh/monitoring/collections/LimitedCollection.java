package by.gosha_krovsh.monitoring.collections;

import java.util.Collection;
import java.util.stream.Collectors;

public class LimitedCollection<T> implements ProcessedCollection<T, T> {
    public LimitedCollection(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public void renew(Collection<? extends T> elements) {
        data = elements.stream().limit(maxSize).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends T> currentState() {
        return data;
    }

    private final int maxSize;
    private Collection<? extends T> data;
}
