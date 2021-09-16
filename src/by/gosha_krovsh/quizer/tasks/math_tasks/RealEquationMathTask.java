package by.gosha_krovsh.quizer.tasks.math_tasks;

import by.gosha_krovsh.quizer.Operator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RealEquationMathTask extends EquationMathTask implements RealMathTask {
    public RealEquationMathTask(int precision, double leftNumber, double rightNumber, Operator operator) {
        this.precision = precision;
        this.leftNumber = leftNumber;
        this.rightNumber = rightNumber;
        this.operator = operator;

        this.text = leftNumber + " " + operator.toString() + " x = " + rightNumber;
        String answer = "Exception";
        try {
            answer = Double.toString(calculateResult(operator));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.answer = answer;
        }
    }

    private double calculateResult(Operator operator) throws ArithmeticException {
        double result = 0;
        switch (operator) {
            case PLUS: {
                result = rightNumber - leftNumber;
                break;
            }
            case MINUS: {
                result = leftNumber - rightNumber;
                break;
            }
            case MULTIPLY: {
                if (Double.compare(leftNumber, 0) == 0) {
                    throw new ArithmeticException("Division by zero");
                }

                result = rightNumber / leftNumber;
                break;
            }
            case DIVIDE: {
                if (Double.compare(rightNumber, 0) == 0) {
                    throw new ArithmeticException("Division by zero");
                }

                result = leftNumber / rightNumber;
                break;
            }
        }

        return BigDecimal.valueOf(result)
                .setScale(this.precision, RoundingMode.HALF_UP)
                .doubleValue();
    }

    @Override
    protected String getAnswerRegex() {
        return "^\\+?\\-?(\\d+[\\.]?([\\d]{" + precision + "})?)$";
    }

    private final int precision;
    private final double leftNumber;
    private final double rightNumber;
    private final Operator operator;
}
