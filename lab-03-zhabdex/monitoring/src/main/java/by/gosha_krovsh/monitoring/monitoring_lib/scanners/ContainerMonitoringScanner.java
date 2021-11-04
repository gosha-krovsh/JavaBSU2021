package by.gosha_krovsh.monitoring.monitoring_lib.scanners;

import by.derovi.service_monitoring.visualizer.Table;
import by.gosha_krovsh.monitoring.collections.FinalProcessedCollection;
import by.gosha_krovsh.monitoring.monitoring_lib.Monitoring;
import by.gosha_krovsh.monitoring.monitoring_lib.MonitoringScanner;
import by.gosha_krovsh.monitoring.monitoring_lib.annotation.ActiveMonitoring;
import by.gosha_krovsh.monitoring.monitoring_lib.annotation.MonitoringContainer;
import by.zhabdex.common.Service;
import org.reflections.Reflections;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class ContainerMonitoringScanner implements MonitoringScanner {
    @Override
    public Collection<Monitoring> scan(Reflections reflections) {
        return reflections.getTypesAnnotatedWith(MonitoringContainer.class).stream()
                .map(Class::getDeclaredMethods)
                .flatMap(Arrays::stream)
                .filter(method -> method.isAnnotationPresent(ActiveMonitoring.class))
                .filter(method -> FinalProcessedCollection.class.isAssignableFrom(method.getReturnType()))
                .map(method -> {
                    method.setAccessible(true);
                    FinalProcessedCollection<Service, Table> processedCollection = null;
                    try {
                        processedCollection = (FinalProcessedCollection<Service, Table>) method.invoke(null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    FinalProcessedCollection<Service, Table> finalProcessedCollection = processedCollection;
                    return new Monitoring() {
                        final FinalProcessedCollection<Service, Table> collection = finalProcessedCollection;

                        @Override
                        public void update(Collection<? extends Service> collection) {
                            this.collection.renew(collection);
                        }

                        @Override
                        public Table getStatistics() {
                            return collection.currentState();
                        }
                    };
                }).collect(Collectors.toList());
    }
}
