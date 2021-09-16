package by.gosha_krovsh.quizer.tasks.math_tasks;

import by.gosha_krovsh.quizer.Operator;

public class IntegerEquationMathTask extends EquationMathTask implements IntegerMathTask {
    public IntegerEquationMathTask(int leftNumber, int rightNumber, Operator operator) {
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

    private int calculateResult(Operator operator) throws ArithmeticException {
        switch (operator) {
            case PLUS:
                return rightNumber - leftNumber;
            case MINUS:
                return leftNumber - rightNumber;
            case MULTIPLY: {
                if (leftNumber == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return rightNumber / leftNumber;
            }
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
