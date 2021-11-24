package by.gosha_krovsh.monitoring.collections;

import java.util.Collection;

public interface ProcessedCollection<T, E>
        extends FinalProcessedCollection<T, Collection<? extends E>> {

    default <U> ProcessedCollection<T, U> compose(ProcessedCollection<E, U> collection) {
        ProcessedCollection<T, E> object = this;
        return new ProcessedCollection<T, U>() {
            @Override
            public void renew(Collection<? extends T> elements) {
                object.renew(elements);
                collection.renew(object.currentState());
            }

            @Override
            public Collection<? extends U> currentState() {
                return collection.currentState();
            }
        };
    }

    default <U> FinalProcessedCollection<T, U> compose(FinalProcessedCollection<E, U> collection) {
        ProcessedCollection<T, E> object = this;
        return new FinalProcessedCollection<T, U>() {
            @Override
            public void renew(Collection<? extends T> elements) {
                object.renew(elements);
                collection.renew(object.currentState());
            }

            @Override
            public U currentState() {
                return collection.currentState();
            }
        };
    }
}
