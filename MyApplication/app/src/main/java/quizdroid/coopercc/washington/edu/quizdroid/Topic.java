package quizdroid.coopercc.washington.edu.quizdroid;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by coopercain on 2/12/17.
 */

public class Topic {
    private String title;
    private String desc;
    private List<Question> questions;
    private int correctQuestions;
    private int currentQuestion;

    public Topic() {
        questions = new ArrayList<Question>();
    }

    public void reset() {
        correctQuestions = 0;
        currentQuestion = 0;
    }

    public int getCurrentQuestion() {
        return currentQuestion;
    }

    public void incrementQuestion() {
        currentQuestion++;
    }

    public int getCorrectQuestions() {
        return correctQuestions;
    }

    public void incrementCorrect() {
        correctQuestions++;
    }

    public void setTitle(String t) {
        title = t;
    }

    public String getTitle() {
        return title;
    }

    public void setDesc(String d) {
        desc = d;
    }

    public void setQuestions(List<Question> qs) {
        for (Question q: qs) {
            questions.add(q);
        }
    }

}
