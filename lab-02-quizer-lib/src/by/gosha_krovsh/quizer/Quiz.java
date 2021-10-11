package by.gosha_krovsh.quizer;

import by.gosha_krovsh.quizer.exceptions.QuizNotFinishedException;
import by.gosha_krovsh.quizer.task_generators.GroupTaskGenerator;
import by.gosha_krovsh.quizer.task_generators.PoolTaskGenerator;
import by.gosha_krovsh.quizer.tasks.TextTask;
import by.gosha_krovsh.quizer.tasks.math_tasks.EquationTask;
import by.gosha_krovsh.quizer.tasks.math_tasks.ExpressionTask;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;

public class Quiz {
    public static HashMap<String, Quiz> getQuizMap() {
        HashMap<String, Quiz> map = new HashMap<>();
        // TextTask
        PoolTaskGenerator poolTaskGenerator = new PoolTaskGenerator(false,
                new TextTask("Да?", "Да"),
                new TextTask("Нет?", "Нет"));
        Quiz textQuiz = new Quiz(poolTaskGenerator, 2);
        map.put("TextTask", textQuiz);
        // IntegerEquationTask
        EquationTask.Generator integerEquationMathTaskGenerator = new EquationTask.Generator(
                0, 1, 10, EnumSet.of(Operator.PLUS, Operator.MINUS));
        Quiz integerEquationQuiz = new Quiz(integerEquationMathTaskGenerator, 3);
        map.put("IntegerEquationTask", integerEquationQuiz);

        // IntegerExpressionTask
        ExpressionTask.Generator integerExpressionMathTaskGenerator =
                new ExpressionTask.Generator(0, 1, 10, EnumSet.of(Operator.DIVIDE));
        Quiz integerExpressionQuiz = new Quiz(integerExpressionMathTaskGenerator, 3);
        map.put("IntegerExpressionTask", integerExpressionQuiz);

        // RealEquationTask
        EquationTask.Generator realEquationMathTaskGenerator =
                new EquationTask.Generator(2, 1, 10, EnumSet.of(Operator.MINUS));
        Quiz realEquationQuiz = new Quiz(realEquationMathTaskGenerator, 1);
        map.put("RealEquationTask", realEquationQuiz);

        // RealExpressionTask
        ExpressionTask.Generator realExpressionMathTaskGenerator =
                new ExpressionTask.Generator(3, 1, 10, EnumSet.of(Operator.MULTIPLY));
        Quiz realExpressionQuiz = new Quiz(realExpressionMathTaskGenerator, 1);
        map.put("RealExpressionTask", realExpressionQuiz);

        // GroupTaskGenerator
        GroupTaskGenerator groupTaskGenerator =
                new GroupTaskGenerator(integerEquationMathTaskGenerator, realExpressionMathTaskGenerator);
        Quiz groupQuiz = new Quiz(groupTaskGenerator, 2);
        map.put("GroupTask", groupQuiz);

        return map;
    }

    public Quiz(Task.Generator generator, int taskCount) {
        this.generator = generator;
        this.taskCount = taskCount;
    }

    public Task nextTask() throws RuntimeException {
        if (taskCount == 0) {
            throw new RuntimeException("Quiz is finished");
        }

        if (isInputCorrect) {
            currentTask = generator.generate();
            --taskCount;
        }
        return currentTask;
    }

    public Result provideAnswer(String answer) {
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
        outcomes.add(new Outcome(currentTask.getText(), answer, result));
        return result;
    }

    public double getMark() throws RuntimeException {
        if (taskCount > 0) {
            throw new QuizNotFinishedException("Quiz must be finished to get Mark");
        }

        return 1. * correctAnswerNumber / (correctAnswerNumber + wrongAnswerNumber);
    }

    public List<Outcome> getOutcomes() {
        return outcomes;
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

    private final Task.Generator generator;
    private int taskCount;

    private final List<Outcome> outcomes = new ArrayList<>();
}
