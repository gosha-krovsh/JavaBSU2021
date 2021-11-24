package by.gosha_krovsh.monitoring.collections;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MappedCollection<T, U> implements ProcessedCollection<T, U> {
    public MappedCollection(Function<T, U> mapper) {
        this.mapper = mapper;
    }

    @Override
    public void renew(Collection<? extends T> elements) {
        data = elements.stream().map(mapper).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends U> currentState() {
        return data;
    }

    private final Function<T, U> mapper;
    private Collection<U> data;
}
