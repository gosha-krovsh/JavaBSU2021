package by.gosha_krovsh.monitoring.collections;

import java.util.Collection;

public interface ProcessedCollection<T, E>
        extends FinalProcessedCollection<T, Collection<? extends E>> {

    default ProcessedCollection<T, E> compose(ProcessedCollection<T, E> collection) {
        ProcessedCollection<T, E> object = this;
        return new ProcessedCollection<T, E>() {
            @Override
            public void renew(Collection<? extends T> elements) {
                object.renew(elements);
                collection.renew((Collection<? extends T>) object.currentState());
            }

            @Override
            public Collection<? extends E> currentState() {
                return collection.currentState();
            }
        };
    }

    default <U> FinalProcessedCollection<T, U> compose(FinalProcessedCollection<T, U> collection) {
        ProcessedCollection<T, E> object = this;
        return new FinalProcessedCollection<T, U>() {
            @Override
            public void renew(Collection<? extends T> elements) {
                object.renew(elements);
                collection.renew((Collection<? extends T>) object.currentState());
            }

            @Override
            public U currentState() {
                return collection.currentState();
            }
        };
    }
}
