package by.gosha_krovsh.quizer.tasks;

import by.gosha_krovsh.quizer.Operator;
import by.gosha_krovsh.quizer.Result;
import by.gosha_krovsh.quizer.Task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// leftNumber <operator> x = rightNumber
public class EquationTask implements Task {
    public EquationTask(int leftNumber, int rightNumber, Operator operator) {
        this.leftNumber = leftNumber;
        this.rightNumber = rightNumber;
        this.operator = operator;

        this.text = leftNumber + " " + operator.toString() + " x = " + rightNumber;

        String answer = "Exception";
        try {
            answer = Integer.toString(calculateResult(operator));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.answer = answer;
        }
    }

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

    private int calculateResult(Operator operator) throws ArithmeticException {
        switch (operator) {
            case PLUS:
                return rightNumber - leftNumber;
            case MINUS:
                return rightNumber + rightNumber;
            case MULTIPLY: {
                if (leftNumber == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return rightNumber / leftNumber;
            }
            case DIVIDE:
                return rightNumber * leftNumber;
        }
        return 0;
    }

    private String getAnswerRegex() {
        return "^\\+?\\-?[0-9]*";
    }

    private final int leftNumber;
    private final int rightNumber;
    private final Operator operator;

    private final String text;
    private final String answer;
}
