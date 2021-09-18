package by.gosha_krovsh.quizer.exceptions;

public class QuizNotFinishedException extends RuntimeException {
    public QuizNotFinishedException(String errorMessage) {
        super(errorMessage);
    }
}
