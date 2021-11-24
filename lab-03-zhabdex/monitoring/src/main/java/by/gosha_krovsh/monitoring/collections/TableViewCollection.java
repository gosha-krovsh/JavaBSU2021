package by.gosha_krovsh.monitoring.collections;

import by.derovi.service_monitoring.visualizer.Table;
import lombok.Getter;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TableViewCollection<T> implements FinalProcessedCollection<T, Table> {
    public static class ColumnProvider<U> {
        private ColumnProvider(String title, Function<U, ?> extractor) {
            this.title = title;
            this.extractor = extractor;
        }

        public static <T> ColumnProvider<T> of(String title, Function<T, ?> extractor) {
            return new ColumnProvider<T>(title, extractor);
        }

        public String Apply(U t) {
            return extractor.apply(t).toString();
        }

        @Getter
        private final String title;
        private Function<U, ?> extractor;
    }

    public TableViewCollection(String title, List<ColumnProvider<T>> providers) {
        this.title = title;
        this.providers = providers;
    }

    @Override
    public void renew(Collection<? extends T> elements) {
        table = new Table(title);

        table.addRow(providers.stream().
                map(ColumnProvider::getTitle)
                .collect(Collectors.toList()));
        for (var element : elements) {
            table.addRows(List.of(providers.stream()
                    .map(provider -> provider.Apply(element))
                    .collect(Collectors.toList())));
        }
    }

    @Override
    public Table currentState() {
        return table;
    }

    private final String title;
    private List<ColumnProvider<T>> providers;
    private Table table;
}
