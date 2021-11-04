package by.gosha_krovsh.monitoring.collections;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GroupingCollection<T, K> implements
        ProcessedCollection<T, Map.Entry<? extends K, ? extends List<? extends T>>> {

    public GroupingCollection(Function<? super T, ? extends K> classifier) {
        this.classifier = classifier;
    }

    @Override
    public void renew(Collection<? extends T> elements) {
        elements.forEach(element -> {
            var key = classifier.apply(element);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(element);
        });
    }

    @Override
    public Collection<? extends Map.Entry<? extends K, ? extends List<? extends T>>> currentState() {
        return map.entrySet().stream().collect(Collectors.toList());
    }

    private final Function<? super T, ? extends K> classifier;
    private final HashMap<K, List<T>> map = new HashMap<>();
}
