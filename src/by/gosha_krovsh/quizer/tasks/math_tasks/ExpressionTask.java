package by.gosha_krovsh.quizer.tasks.math_tasks;

import by.gosha_krovsh.quizer.Operator;
import by.gosha_krovsh.quizer.Task;
import jdk.jfr.Experimental;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.EnumSet;
import java.util.concurrent.ThreadLocalRandom;

// leftNumber <operator> rightNumber = answer
public class ExpressionTask extends AbstractMathTask {
    @Experimental
    public static class Generator extends AbstractMathTask.Generator {
        public Generator(int precision, double minNumber, double maxNumber,
                                               EnumSet<Operator> operatorsToUse) {
            this.precision = precision;
            this.minNumber = minNumber;
            this.maxNumber = maxNumber;
            this.operatorsToUse = operatorsToUse;
        }

        @Override
        public Task generate() {
            double leftNumber = ThreadLocalRandom.current().nextDouble(minNumber, maxNumber + 1);
            double rightNumber = ThreadLocalRandom.current().nextDouble(minNumber, maxNumber + 1);
            leftNumber = BigDecimal.valueOf(leftNumber)
                    .setScale(this.precision, RoundingMode.HALF_UP)
                    .doubleValue();
            rightNumber = BigDecimal.valueOf(rightNumber)
                    .setScale(this.precision, RoundingMode.HALF_UP)
                    .doubleValue();

            int operatorIndex = ThreadLocalRandom.current().nextInt(0, this.operatorsToUse.size());
            Operator[] operatorArray = operatorsToUse.toArray(new Operator[0]);

            return new ExpressionTask(this.precision, leftNumber, rightNumber, operatorArray[operatorIndex]);
        }

        @Override
        public double getMinNumber() {
            return minNumber;
        }

        @Override
        public double getMaxNumber() {
            return maxNumber;
        }

        private final int precision;
        private final double minNumber;
        private final double maxNumber;
        private final EnumSet<Operator> operatorsToUse;
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
