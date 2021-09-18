package by.gosha_krovsh.quizer.tasks.math_tasks;

import by.gosha_krovsh.quizer.Operator;
import by.gosha_krovsh.quizer.Result;
import by.gosha_krovsh.quizer.Task;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.EnumSet;
import java.util.concurrent.ThreadLocalRandom;

public abstract class AbstractMathTask implements MathTask {
    public static abstract class Generator implements MathTask.Generator {
        public Generator(int precision, double minNumber, double maxNumber,
                         EnumSet<Operator> operatorsToUse) {
            if (precision < 0) {
                throw new IllegalArgumentException("Precision can't be below 0");
            }
            if (minNumber > maxNumber) {
                throw  new IllegalArgumentException(
                        "maxNumber must be bigger than minNumber");
            }

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

            return new EquationTask(this.precision, leftNumber, rightNumber, operatorArray[operatorIndex]);
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
