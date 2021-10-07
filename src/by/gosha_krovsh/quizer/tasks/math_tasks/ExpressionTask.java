package by.gosha_krovsh.quizer.tasks.math_tasks;

import by.gosha_krovsh.quizer.Operator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.EnumSet;

/** leftNumber <operator> rightNumber = answer */
public class ExpressionTask extends AbstractMathTask {
    public static class Generator extends AbstractMathTask.Generator {
        public Generator(int precision, double minNumber, double maxNumber,
                         EnumSet<Operator> operatorsToUse) {
            super(precision, minNumber, maxNumber, operatorsToUse);
        }
    }

    public ExpressionTask(int precision, double leftNumber, double rightNumber, Operator operator) {
        this.precision = precision;
        this.leftNumber = leftNumber;
        this.rightNumber = rightNumber;

        this.text = DoubleStringConverter.doubleToString(precision, leftNumber)
                    + operator.toString()
                    + DoubleStringConverter.doubleToString(precision, rightNumber);
        String answer = "Exception";
        try {
            answer = DoubleStringConverter.doubleToString(precision, calculateResult(operator));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.answer = answer;
        }
    }

    private double calculateResult(Operator operator) {
        double result = 0;
        switch (operator) {
            case PLUS: {
                result = leftNumber + rightNumber;
                break;
            }
            case MINUS: {
                result = leftNumber - rightNumber;
                break;
            }
            case MULTIPLY: {
                result = leftNumber * rightNumber;
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

    private final double leftNumber;
    private final double rightNumber;
}
