package by.gosha_krovsh.monitoring.monitoring_lib;

import by.derovi.service_monitoring.visualizer.Table;
import by.derovi.service_monitoring.visualizer.TerminalRenderer;
import by.zhabdex.common.Service;
import by.zhabdex.common.Tools;
import lombok.Builder;
import lombok.Singular;
import org.reflections.Reflections;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class MonitoringApplication {

    @Builder(buildMethodName = "start")
    private MonitoringApplication(String directory,
                                  String serviceURL,
                                  @Singular List<MonitoringScanner> addScanners) {
        this.directory = directory;
        this.serviceURL = serviceURL;
        this.scanners = addScanners;

        scanMonitoring();
        try {
            start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void scanMonitoring() {
        scanners.forEach(scanner -> monitoringList.addAll(scanner.scan(new Reflections(directory))));
    }

    private void updateMonitoring() {
        monitoringList.forEach(monitoring -> monitoring.update(services));
    }

    private void fetchServices() throws IOException {
        services = Tools.JSON.readValue(new URL(serviceURL),
                Tools.JSON.getTypeFactory().constructCollectionType(List.class, Service.class));
    }

    private void start() throws InterruptedException, IOException {
        TerminalRenderer renderer = TerminalRenderer.init(monitoringList.size());
        while (true) {
            try {
                fetchServices();
                updateMonitoring();
                tables = monitoringList.stream()
                        .map(Monitoring::getStatistics)
                        .collect(Collectors.toList());

                renderer.render(tables);
            } catch (IOException exception) {
                renderer.render(List.of(new Table("¡Server is DEAD!")));
                exception.printStackTrace();
            }

            TimeUnit.SECONDS.sleep(1);
        }
    }

    private List<Service> services;
    private List<Table> tables;

    private final String directory;
    private final String serviceURL;
    private final List<MonitoringScanner> scanners;
    private final List<Monitoring> monitoringList = new ArrayList<>();
}
