package quizdroid.coopercc.washington.edu.quizdroid;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by coopercain on 2/12/17.
 */

public class Question {
    private String question; //the text for the question
    private List<String> answers; //the list of possible answers
    private int correctAnswer; //the position in the list of the correct answer

    public Question() {
        answers = new ArrayList<String>();
    }

    //sets the text of the question
    public void setQuestion(String q) {
        question = q;
    }

    //gets the question text
    public String getQuestion() {
        return question;
    }

    //sets the answers to the question to the passed in list
    public void setAnswers(List<String> answ) {
        for (String ans : answ) {
            answers.add(ans);
        }
    }

    //returns the list of all answers
    public List<String> getAnswers() {
        return answers;
    }

    //sets the correct answer position
    public void setCorrectAnswer(int correct) {
        correctAnswer = correct;
    }

    //returns the position of the correct answer
    public int getCorrectAnswer() {
        return correctAnswer;
    }
}
