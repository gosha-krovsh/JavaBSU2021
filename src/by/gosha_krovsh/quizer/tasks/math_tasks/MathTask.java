package by.gosha_krovsh.quizer.tasks.math_tasks;

import by.gosha_krovsh.quizer.Task;

import java.math.BigDecimal;
import java.math.RoundingMode;

public interface MathTask extends Task {
    interface Generator extends Task.Generator {
        double getMaxNumber();
        double getMinNumber();
        default double getDiffNumber() {
            return getMaxNumber() - getMinNumber();
        }
    }
}
