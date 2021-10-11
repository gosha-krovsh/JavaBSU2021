package by.gosha_krovsh.quizer;

public class Outcome {
    public Outcome(String question,
            String answer,
            Result result) {
        this.question = question;
        this.answer = answer;
        this.result = result;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public Result getResult() {
        return result;
    }

    private final String question;
    private final String answer;
    private final Result result;

    @Override
    public String toString() {
        return "Question: " + question + " Answer: " + answer
                + " Result: " + result;
    }
}
