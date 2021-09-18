package by.gosha_krovsh.quizer.task_generators;

import by.gosha_krovsh.quizer.Task;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GroupTaskGenerator implements Task.Generator {
    public GroupTaskGenerator(Task.Generator... taskGenerators) {
        this.taskGenerators = List.of(taskGenerators);
    }

    public GroupTaskGenerator(List<Task.Generator> taskGenerators) {
        this.taskGenerators = taskGenerators;
    }

    @Override
    public Task generate() throws RuntimeException {
        if (taskGenerators.size() == 0) {
            throw new RuntimeException("No Tasks left");
        }

        int index = ThreadLocalRandom.current().nextInt(0, this.taskGenerators.size());
        return taskGenerators.get(index).generate();
    }

    private final List<Task.Generator> taskGenerators;
}
