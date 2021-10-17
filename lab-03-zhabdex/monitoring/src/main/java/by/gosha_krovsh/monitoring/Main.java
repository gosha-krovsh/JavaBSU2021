package by.gosha_krovsh.monitoring;

import by.derovi.service_monitoring.visualizer.Table;
import by.derovi.service_monitoring.visualizer.TerminalRenderer;
import by.zhabdex.common.Service;
import by.zhabdex.common.Tools;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class Main {
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

    public static void main(String[] args) throws IOException, InterruptedException {
        TerminalRenderer renderer = TerminalRenderer.init(1);
        System.out.println(renderer);
        while (true) {
            List<Service> services;
            try {
                 services = Tools.JSON.readValue(new URL("http://zhabdex.ovi.by/status"),
                         Tools.JSON.getTypeFactory().constructCollectionType(List.class, Service.class));
                 renderer.render(List.of(generateTableRow(services)));
            } catch (Exception e) {
                e.printStackTrace();
                renderer.render(List.of(new Table("Server DEAD").addRow("Server DEAD")));
            }
            Thread.sleep(1000);
            // TODO(Jora) fix exit on render close
        }
    }
}
