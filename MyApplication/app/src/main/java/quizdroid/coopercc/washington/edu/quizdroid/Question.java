package quizdroid.coopercc.washington.edu.quizdroid;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by coopercain on 2/12/17.
 */

public class Question {
    private String question;
    private List<String> answers;
    private int correctAnswer;

    public Question() {
        answers = new ArrayList<String>();
    }

    public void setQuestion(String q) {
        question = q;
    }

    public String getQuestion() {
        return question;
    }

    public void setAnswers(List<String> answ) {
        for (String ans : answ) {
            answers.add(ans);
        }
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setCorrectAnswer(int correct) {
        correctAnswer = correct;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}
