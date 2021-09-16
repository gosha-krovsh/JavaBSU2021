package by.gosha_krovsh.quizer.task_generators.math_task_generators;

import by.gosha_krovsh.quizer.Operator;
import by.gosha_krovsh.quizer.Task;
import by.gosha_krovsh.quizer.tasks.math_tasks.RealExpressionMathTask;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.EnumSet;
import java.util.concurrent.ThreadLocalRandom;

public class RealExpressionMathTaskGenerator extends ExpressionMathTaskGenerator implements RealMathTaskGenerator {
    public RealExpressionMathTaskGenerator(int precision, double minNumber, double maxNumber,
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

        return new RealExpressionMathTask(this.precision, leftNumber, rightNumber, operatorArray[operatorIndex]);
    }

    @Override
    public double getMinNUmber() {
        return minNumber;
    }

    @Override
    public double getMaxNUmber() {
        return maxNumber;
    }

    private final int precision;
    private final double minNumber;
    private final double maxNumber;
    private final EnumSet<Operator> operatorsToUse;
}
