package by.gosha_krovsh.quizer.tasks.math_tasks;

import by.gosha_krovsh.quizer.Operator;

public class IntegerExpressionMathTask extends ExpressionMathTask implements IntegerMathTask {
    public IntegerExpressionMathTask(int leftNumber, int rightNumber, Operator operator) {
        this.leftNumber = leftNumber;
        this.rightNumber = rightNumber;
        this.operator = operator;

        this.text = leftNumber + operator.toString() + rightNumber;
        String answer = "Exception";
        try {
            answer = Integer.toString(calculateResult(operator));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.answer = answer;
        }
    }

    private int calculateResult(Operator operator) {
        switch (operator) {
            case PLUS:
                return leftNumber + rightNumber;
            case MINUS:
                return leftNumber - rightNumber;
            case MULTIPLY:
                return leftNumber * rightNumber;
            case DIVIDE: {
                if (rightNumber == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return leftNumber / rightNumber;
            }
        }
        return 0;
    }

    private final int leftNumber;
    private final int rightNumber;
    private final Operator operator;
}
