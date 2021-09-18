package by.gosha_krovsh.quizer.tasks.math_tasks;

import by.gosha_krovsh.quizer.Result;

public abstract class AbstractMathTask implements MathTask {
    public static abstract class Generator implements MathTask.Generator {
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Result validate(String answer) {
        if (DoubleStringConverter.isDoubleStringCorrect(precision, answer)) {
            return Result.INCORRECT_INPUT;
        }

        return answer.equals(this.answer) ? Result.OK : Result.WRONG;
    }

    protected String text;
    protected String answer;
    protected int precision;
}
