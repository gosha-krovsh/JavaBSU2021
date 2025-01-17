package by.gosha_krovsh.quizer.task_generators;

import by.gosha_krovsh.quizer.Task;
import by.gosha_krovsh.quizer.exceptions.TaskPoolEmpty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PoolTaskGenerator implements Task.Generator {
    public PoolTaskGenerator(boolean allowDuplicate, Task... tasks) {
        this.allowDuplicate = allowDuplicate;
        this.tasks = new ArrayList<>(Arrays.asList(tasks));
    }

    public PoolTaskGenerator(boolean allowDuplicate, List<Task> tasks) {
        this.allowDuplicate = allowDuplicate;
        this.tasks = tasks;
    }

    @Override
    public Task generate() throws RuntimeException {
        if (tasks.size() == 0) {
            throw new TaskPoolEmpty("Pool of tasks is empty");
        }

        int index = ThreadLocalRandom.current().nextInt(0, this.tasks.size());
        Task task = tasks.get(index);
        if (!allowDuplicate) {
            tasks.remove(index);
        }
        return task;
    }

    private final boolean allowDuplicate;
    private final List<Task> tasks;
}
