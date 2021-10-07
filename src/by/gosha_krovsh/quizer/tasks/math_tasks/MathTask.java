package by.gosha_krovsh.quizer.tasks.math_tasks;

import by.gosha_krovsh.quizer.Task;

public interface MathTask extends Task {
    interface Generator extends Task.Generator {
        double getMaxNumber();
        double getMinNumber();

        default double getDiffNumber() {
            return getMaxNumber() - getMinNumber();
        }
    }
}
