package by.gosha_krovsh.monitoring.monitorings;

import by.derovi.service_monitoring.visualizer.Table;
import by.gosha_krovsh.monitoring.collections.FinalProcessedCollection;
import by.gosha_krovsh.monitoring.collections.SortedCollection;
import by.gosha_krovsh.monitoring.collections.TableViewCollection;
import by.gosha_krovsh.monitoring.monitoring_lib.Monitoring;
import by.gosha_krovsh.monitoring.monitoring_lib.annotation.ActiveMonitoring;
import by.zhabdex.common.Service;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@ActiveMonitoring
@NoArgsConstructor
public class PingMonitoring implements Monitoring {
    FinalProcessedCollection<Service, Table> collection =
            new SortedCollection<>(Service::getAveragePing).compose(
                    new TableViewCollection<>("Ping monitoring", List.of(
                            TableViewCollection.ColumnProvider.of("Name", Service::getName),
                            TableViewCollection.ColumnProvider.of("Data center", Service::getDataCenter),
                            TableViewCollection.ColumnProvider.of("Ping", Service::getAveragePing),
                            TableViewCollection.ColumnProvider.of("Requests/sec", Service::getRequestsPerSecond),
                            TableViewCollection.ColumnProvider.of("Started time", Service::getStartedTime),
                            TableViewCollection.ColumnProvider.of("Current time", Service::getCurrentTime)
                    )));

    @Override
    public void update(Collection<? extends Service> services) {
        collection.renew(services);
    }

    @Override
    public Table getStatistics() {
        return collection.currentState();
    }
}
