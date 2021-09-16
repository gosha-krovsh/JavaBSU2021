package by.gosha_krovsh.quizer.task_generators.math_task_generators;

import by.gosha_krovsh.quizer.Operator;
import by.gosha_krovsh.quizer.Task;
import by.gosha_krovsh.quizer.tasks.math_tasks.IntegerExpressionMathTask;

import java.util.EnumSet;
import java.util.concurrent.ThreadLocalRandom;

public class IntegerExpressionMathTaskGenerator
        extends ExpressionMathTaskGenerator implements IntegerMathTaskGenerator {
    public IntegerExpressionMathTaskGenerator(int minNumber, int maxNumber,
                                              EnumSet<Operator> operatorsToUse) {
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
        this.operatorsToUse = operatorsToUse;
    }

    @Override
    public Task generate() {
        int leftNumber = ThreadLocalRandom.current().nextInt(minNumber, maxNumber + 1);
        int rightNumber = ThreadLocalRandom.current().nextInt(minNumber, maxNumber + 1);
        int operatorIndex = ThreadLocalRandom.current().nextInt(0, this.operatorsToUse.size());

        Operator[] operatorArray = operatorsToUse.toArray(new Operator[0]);
        return new IntegerExpressionMathTask(leftNumber, rightNumber, operatorArray[operatorIndex]);
    }

    @Override
    public int getMinNUmber() {
        return minNumber;
    }

    @Override
    public int getMaxNUmber() {
        return maxNumber;
    }

    private final EnumSet<Operator> operatorsToUse;
    private final int minNumber;
    private final int maxNumber;
}
