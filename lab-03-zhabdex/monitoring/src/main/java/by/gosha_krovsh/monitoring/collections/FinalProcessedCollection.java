package by.gosha_krovsh.monitoring.collections;

import java.util.Collection;

public interface FinalProcessedCollection<T, E> {
    void renew(Collection<? extends  T> elements);
    E currentState();
}
