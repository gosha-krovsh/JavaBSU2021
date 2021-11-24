package by.gosha_krovsh.monitoring;

import by.derovi.service_monitoring.visualizer.Table;
import by.gosha_krovsh.monitoring.monitoring_lib.MonitoringApplication;
import by.gosha_krovsh.monitoring.monitoring_lib.scanners.ClassMonitoringScanner;
import by.gosha_krovsh.monitoring.monitoring_lib.scanners.ContainerMonitoringScanner;
import by.zhabdex.common.Service;
import by.zhabdex.common.Tools;

import java.io.IOException;
import java.net.URL;
import java.util.List;

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

    @Deprecated
    static List<Service> fetchServices() throws IOException {
        return Tools.JSON.readValue(new URL("http://zhabdex.ovi.by/status"),
                Tools.JSON.getTypeFactory().constructCollectionType(List.class, Service.class));
    }

    public static void main(String[] args) {
        MonitoringApplication
                .builder()
                .directory("by.gosha_krovsh.monitoring")
                .serviceURL("http://zhabdex.ovi.by/status")
                .addScanner(new ClassMonitoringScanner())
                .addScanner(new ContainerMonitoringScanner())
                .start();
    }
}
