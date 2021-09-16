package by.gosha_krovsh.quizer.tasks.math_tasks;

import by.gosha_krovsh.quizer.Result;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractMathTask implements MathTask {
    @Override
    public String getText() {
        return text;
    }

    @Override
    public Result validate(String answer) {
        Pattern pattern = Pattern.compile(getAnswerRegex());
        Matcher matcher = pattern.matcher(answer);
        if (!matcher.matches()) {
            return Result.INCORRECT_INPUT;
        }

        return answer.equals(this.answer) ? Result.OK : Result.WRONG;
    }

    // ^(\d+[\.]?([\d]+)?)$
    protected String getAnswerRegex() {
        return "^\\+?\\-?[0-9]*";
    }

    protected String text;
    protected String answer;
}
