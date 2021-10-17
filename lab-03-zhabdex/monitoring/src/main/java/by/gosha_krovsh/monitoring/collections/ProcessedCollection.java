package by.gosha_krovsh.monitoring.collections;

import java.util.Collection;

public interface ProcessedCollection<T, E>
        extends FinalProcessedCollection<T, Collection<? extends E>> { }
