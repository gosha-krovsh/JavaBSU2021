package by.gosha_krovsh.quizer;

import by.gosha_krovsh.quizer.task_generators.EquationTaskGenerator;
import by.gosha_krovsh.quizer.task_generators.ExpressionTaskGenerator;
import by.gosha_krovsh.quizer.task_generators.GroupTaskGenerator;
import by.gosha_krovsh.quizer.task_generators.PoolTaskGenerator;
import by.gosha_krovsh.quizer.task_generators.math_task_generators.*;
import by.gosha_krovsh.quizer.tasks.TextTask;

import java.util.EnumSet;
import java.util.HashMap;

public class Quiz {
    static HashMap<String, Quiz> getQuizMap() {
        HashMap<String, Quiz> map = new HashMap<>();
        // TextTask
        map.put("TextTask",
                new Quiz(
                        new PoolTaskGenerator(true,
                                new TextTask("Да?", "Да"),
                                new TextTask("Нет?", "Нет")), 2));
        // EquationTaskTask
        EquationTaskGenerator equationTaskGenerator =
                new EquationTaskGenerator(1, 10, EnumSet.of(Operator.PLUS, Operator.MINUS));
        Quiz equationQuiz = new Quiz(equationTaskGenerator, 5);
        map.put("EquationTask", equationQuiz);
        // ExpressionTask
        ExpressionTaskGenerator expressionTaskGenerator =
                new ExpressionTaskGenerator(1, 10, EnumSet.of(Operator.MULTIPLY, Operator.MINUS));
        Quiz expressionQuiz = new Quiz(expressionTaskGenerator, 3);
        map.put("ExpressionTask", expressionQuiz);
        // IntegerEquationMathTask
        IntegerEquationMathTaskGenerator integerEquationMathTaskGenerator =
                new IntegerEquationMathTaskGenerator(1, 10, EnumSet.of(Operator.PLUS, Operator.MINUS));
        Quiz integerEquationQuiz = new Quiz(integerEquationMathTaskGenerator, 3);
        map.put("IntegerEquationMathTask", integerEquationQuiz);
        // IntegerExpressionMathTask
        IntegerExpressionMathTaskGenerator integerExpressionMathTaskGenerator =
                new IntegerExpressionMathTaskGenerator(1, 10, EnumSet.of(Operator.DIVIDE));
        Quiz integerExpressionQuiz = new Quiz(integerExpressionMathTaskGenerator, 3);
        map.put("IntegerExpressionMathTask", integerExpressionQuiz);
        // RealEquationMathTask
        RealEquationMathTaskGenerator realEquationMathTaskGenerator =
                new RealEquationMathTaskGenerator(2, 1, 10, EnumSet.of(Operator.MINUS));
        Quiz realEquationQuiz = new Quiz(realEquationMathTaskGenerator, 1);
        map.put("RealEquationMathTask", realEquationQuiz);
        // RealExpressionMathTask
        RealExpressionMathTaskGenerator realExpressionMathTaskGenerator =
                new RealExpressionMathTaskGenerator(3, 1, 10, EnumSet.of(Operator.MULTIPLY));
        Quiz realExpressionQuiz = new Quiz(realExpressionMathTaskGenerator, 1);
        map.put("RealExpressionMathTask", realExpressionQuiz);
        // GroupTaskGenerator
        GroupTaskGenerator groupTaskGenerator =
                new GroupTaskGenerator(integerEquationMathTaskGenerator, realExpressionMathTaskGenerator);
        Quiz groupQuiz = new Quiz(groupTaskGenerator, 2);
        map.put("GroupTask", groupQuiz);

        return map;
    }

    Quiz(TaskGenerator generator, int taskCount) {
        this.generator = generator;
        this.taskCount = taskCount;
    }

    Task nextTask() throws RuntimeException {
        if (taskCount == 0) {
            throw new RuntimeException("Quiz is finished");
        }

        if (isInputCorrect) {
            currentTask = generator.generate();
            --taskCount;
        }
        return currentTask;
    }

    Result provideAnswer(String answer) {
        Result result = currentTask.validate(answer);
        switch (result) {
            case OK: {
                isInputCorrect = true;
                ++correctAnswerNumber;
                break;
            }
            case WRONG: {
                isInputCorrect = true;
                ++wrongAnswerNumber;
                break;
            }
            case INCORRECT_INPUT: {
                isInputCorrect = false;
                ++incorrectInputNumber;
                break;
            }
        }
        return result;
    }

    public double getMark() throws RuntimeException {
        if (taskCount > 0) {
            throw new RuntimeException("Quiz is not finished");
        }

        return 1. * correctAnswerNumber / (correctAnswerNumber + wrongAnswerNumber);
    }

    public boolean isFinished() {
        return taskCount == 0;
    }

    public int getCorrectAnswerNumber() {
        return correctAnswerNumber;
    }

    public int getWrongAnswerNumber() {
        return wrongAnswerNumber;
    }

    public int getIncorrectInputNumber() {
        return incorrectInputNumber;
    }

    private int correctAnswerNumber;
    private int wrongAnswerNumber;
    private int incorrectInputNumber;

    private Task currentTask;
    private boolean isInputCorrect = true;

    private final TaskGenerator generator;
    private int taskCount;
}
