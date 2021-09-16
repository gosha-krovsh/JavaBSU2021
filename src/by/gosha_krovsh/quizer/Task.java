package by.gosha_krovsh.quizer;

public interface Task {
    String getText();
    Result validate(String answer);
}
