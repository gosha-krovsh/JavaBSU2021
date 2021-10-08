package by.gosha_krovsh.quizer;

/* TEST CODE
import java.util.HashMap;
import java.util.Scanner;
 */

@Deprecated
public class Main {
    /* TEST CODE, LEFT HERE TO SAVE IT
    public static void main(String[] args) {
        HashMap<String, Quiz> quizMap = Quiz.getQuizMap();
        for (var name : quizMap.keySet()) {
            System.out.println(name);
        }

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
            System.out.println(quiz.provideAnswer(answer));
        }
        System.out.println(quiz.getMark());
    }
    */
}
