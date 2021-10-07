package by.gosha_krovsh.quizer.exceptions;

public class TaskPoolEmpty extends RuntimeException {
    public TaskPoolEmpty(String errorMessage) {
        super(errorMessage);
    }
}
