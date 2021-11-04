package by.gosha_krovsh.monitoring;

import by.derovi.service_monitoring.visualizer.Table;
import by.derovi.service_monitoring.visualizer.TerminalRenderer;
import by.gosha_krovsh.monitoring.collections.GroupingCollection;
import by.gosha_krovsh.monitoring.collections.MappedCollection;
import by.gosha_krovsh.monitoring.collections.SortedCollection;
import by.gosha_krovsh.monitoring.collections.TableViewCollection;
import by.zhabdex.common.Service;
import by.zhabdex.common.Tools;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

public class Main {
    @Deprecated
    static Table generateTableRow(List<Service> services) {
        Table table = new Table("Services");
        table.addRow("Name",
                "Data Center",
                "Uptime seconds",
                "Average PING",
                "Requests per second");
        for (var service : services) {
            String name = service.getName();
            String dataCenter = service.getDataCenter();
            String uptimeSecond = Long.toString(service.getUptimeSeconds());
            String averagePing = Long.toString(service.getAveragePing());
            String requestsPerSecond = Long.toString(service.getRequestsPerSecond());
            table.addRow(name,
                    dataCenter,
                    uptimeSecond,
                    averagePing,
                    requestsPerSecond);
        }
        return table;
    }

    static List<Service> fetchServices() throws IOException {
        return Tools.JSON.readValue(new URL("http://zhabdex.ovi.by/status"),
                Tools.JSON.getTypeFactory().constructCollectionType(List.class, Service.class));
    }

    public static void main(String[] args) throws IOException, InterruptedException {
//        var collection =
//                new SortedCollection<>(Service::getRequestsForUptime).compose(
//                        new TableViewCollection<>("Test", List.of(
//                                TableViewCollection.ColumnProvider
//                                        .of("Name", Service::getName),
//                                TableViewCollection.ColumnProvider
//                                        .of("Data center", Service::getDataCenter),
//                                TableViewCollection.ColumnProvider
//                                        .of("Ping", Service::getAveragePing),
//                                TableViewCollection.ColumnProvider
//                                        .of("Available nodes", Service::getNodesCount),
//                                TableViewCollection.ColumnProvider
//                                        .of("Requests/sec", Service::getRequestsPerSecond),
//                                TableViewCollection.ColumnProvider
//                                        .of("Started time", Service::getStartedTime),
//                                TableViewCollection.ColumnProvider
//                                        .of("Current time", Service::getCurrentTime)
//                        ))
//                );
        var collection =
                new GroupingCollection<>(Service::getDataCenter)
                        .compose(
                                new MappedCollection<>(
                                        entry -> new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue().stream().mapToLong(Service::getRequestsPerSecond).sum())
                                )
                        ).compose(
                                new TableViewCollection<>("Summary ping", List.of(
                                        TableViewCollection.ColumnProvider.of("Name", Map.Entry::getKey),
                                        TableViewCollection.ColumnProvider.of("Available nodes", Map.Entry::getValue)
                                ))
                        );

        TerminalRenderer renderer = TerminalRenderer.init(1);
        while (true) {
            try {
                collection.renew(fetchServices());
                renderer.render(List.of(collection.currentState()));
            } catch (Exception e) {
                e.printStackTrace();
                renderer.render(List.of(new Table("Server DEAD").addRow("Server DEAD")));
            }

            Thread.sleep(1000);
        }
        // Todo(George) Fix exit on render close
    }
}
