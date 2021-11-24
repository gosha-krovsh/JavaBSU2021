package by.gosha_krovsh.monitoring.monitoring_lib.scanners;

import by.gosha_krovsh.monitoring.monitoring_lib.Monitoring;
import by.gosha_krovsh.monitoring.monitoring_lib.MonitoringScanner;
import by.gosha_krovsh.monitoring.monitoring_lib.annotation.ActiveMonitoring;
import org.reflections.Reflections;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public class ClassMonitoringScanner implements MonitoringScanner {
    @Override
    public Collection<Monitoring> scan(Reflections reflections) {
        return  reflections.getTypesAnnotatedWith(ActiveMonitoring.class).stream()
                .filter(Monitoring.class::isAssignableFrom)
                .map(aClass -> {
                    try {
                        return aClass.getDeclaredConstructor();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .map(constructor -> {
                    constructor.setAccessible(true);
                    try {
                        return (Monitoring) constructor.newInstance();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .collect(Collectors.toList());
    }
}
