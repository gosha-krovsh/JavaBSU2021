package by.gosha_krovsh.monitoring.collections;

import java.util.Collection;
import java.util.Optional;
import java.util.function.BinaryOperator;

public class ReducedCollection<T> implements FinalProcessedCollection<T, Optional<T>> {
    public ReducedCollection(BinaryOperator<T> reducer) {
        this.reducer = reducer;
        this.data = Optional.empty();
    }

    @Override
    public void renew(Collection<? extends T> elements) {
        /* Проблема в том, что тип '? extends T' в BinaryOperator<? extends T> может
            может не совпадать с '? extends T' в Collections<? extends T> и потенциально для выполнения
            нужно делать cast типа, который в BinaryOperator к типу который в Collections. И получается
            ситуация, где возможен downcast.

            Решение - выполнять операцию над типами T, а в коллекции делать upcast. Одно из решений:
            data = elements.stream().map(t -> (T) t).reduce(reducer);
            Но в силу ковариантности коллекции наследников и коллекции родителей имеем:
        * */
        data = ((Collection<T>) elements).stream().reduce(reducer);
    }

    @Override
    public Optional<T> currentState() {
        return data;
    }

    private Optional<T> data;
    private final BinaryOperator<T> reducer;
}
