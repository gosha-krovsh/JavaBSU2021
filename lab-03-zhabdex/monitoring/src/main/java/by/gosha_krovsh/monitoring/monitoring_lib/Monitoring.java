package by.gosha_krovsh.monitoring.monitoring_lib;

import by.derovi.service_monitoring.visualizer.Table;
import by.zhabdex.common.Service;

import java.util.Collection;

public interface Monitoring {
    void update(Collection<? extends Service> collection);
    Table getStatistics();
}
