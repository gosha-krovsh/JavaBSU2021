package by.gosha_krovsh.quizer.tasks;

import by.gosha_krovsh.quizer.Result;
import by.gosha_krovsh.quizer.Task;

public class TextTask implements Task {
    public TextTask(String text, String answer) {
        this.text = text;
        this.answer = answer;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Result validate(String answer) {
        return answer.equals(this.answer) ? Result.OK : Result.WRONG;
    }

    private final String text;
    private final String answer;
}
