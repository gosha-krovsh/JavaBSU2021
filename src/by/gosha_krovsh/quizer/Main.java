package by.gosha_krovsh.quizer;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HashMap<String, Quiz> quizMap = Quiz.getQuizMap();
        Scanner scanner = new Scanner(System.in);

        String quizName;
        do {
            System.out.println("Введите название теста...");
            quizName = scanner.nextLine();
        } while (!quizMap.containsKey(quizName));

        Quiz quiz = quizMap.get(quizName);
        while (!quiz.isFinished()) {
            Task task = quiz.nextTask();
            System.out.println(task.getText());

            String answer = scanner.nextLine();
            quiz.provideAnswer(answer);
        }
        System.out.println(quiz.getMark());
    }
}
