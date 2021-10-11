package by.gosha_krovsh.quizer_swing;

import by.gosha_krovsh.quizer.Quiz;
import by.gosha_krovsh.quizer.Result;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;

public class QuizerGUI extends JFrame {
    public QuizerGUI(String name) {
        super(name);
        this.setMinimumSize(new Dimension(300, 300));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUI();
        setData();
        setButtons();
    }

    private void setUI() {
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(6, 1));

        container.add(comboQuizBox);
        container.add(startButton);
        container.add(questionLabel);
        container.add(resultLabel);
        container.add(answerField);
        container.add(provideAnswerButton);
    }

    private void setButtons() {
        startButton.addActionListener((ActionEvent e) -> {
            if (currentQuiz == null || currentQuiz.isFinished()) {
                String key = comboQuizBox.getSelectedItem().toString();
                currentQuiz = quizMap.get(key);
                setQuestion(currentQuiz.nextTask().getText());
            } else {
                JOptionPane.showMessageDialog(null,
                        "Current Quiz is not finished");
            }
        });
        provideAnswerButton.addActionListener((ActionEvent e) -> {
            if (currentQuiz != null) {
                Result result = currentQuiz.provideAnswer(answerField.getText());
                resultLabel.setText("Verification showed: " + result.toString());
                if (currentQuiz.isFinished()) {
                    StringBuilder outcomeStringBuilder = new StringBuilder();
                    currentQuiz.getOutcomes().forEach(outcome -> {
                        outcomeStringBuilder
                                .append(outcome)
                                .append("\n");
                    });

                    outcomeStringBuilder.append(currentQuiz.getMark());
                    JOptionPane.showMessageDialog(null, outcomeStringBuilder.toString());
                    ClearFields();
                    currentQuiz = null;
                } else {
                    setQuestion(currentQuiz.nextTask().getText());
                }
            }
        });
    }

    private void setData() {
        quizMap.forEach((key, value) -> comboQuizBox.addItem(key));
    }

    private void setQuestion(String question) {
        questionLabel.setText("Question: " + question);
    }

    private void ClearFields() {
        questionLabel.setText("Question: ");
        resultLabel.setText("Verification showed: ");
        answerField.setText("");
    }

    private final HashMap<String, Quiz> quizMap = Quiz.getQuizMap();
    // 'null' means Quiz hasn't been chosen
    private Quiz currentQuiz = null;

    private final JComboBox<String> comboQuizBox = new JComboBox<>();
    private final JLabel questionLabel = new JLabel("Question: ");
    private final JLabel resultLabel = new JLabel("Verification showed: ");
    private final JTextField answerField = new JTextField("", 10);
    private final JButton provideAnswerButton = new JButton("Provide Answer");
    private final JButton startButton = new JButton("Start");
}
