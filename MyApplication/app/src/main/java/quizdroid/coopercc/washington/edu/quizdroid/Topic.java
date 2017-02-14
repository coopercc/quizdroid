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
    private int correctQuestions; //count of correct questions
    private int currentQuestion; //current question in the array (1 less than the question number. IE question 1 is 0)

    public Topic() {
        questions = new ArrayList<Question>();
    }

    //resets the topic correct count and current question number so users can take the same quiz again
    public void reset() {
        correctQuestions = 0;
        currentQuestion = 0;
    }

    //gets the current question for the user
    public Question getCurrentQuestion() {
        return questions.get(currentQuestion);
    }

    //increments the current question so users can get next question
    public void incrementQuestion() {
        currentQuestion++;
    }

    //gets number of correct questions
    public int getCorrectQuestions() {
        return correctQuestions;
    }

    //gets the current question position so users can see how many are correct
    public int getCurrentQuestionInt() {return currentQuestion; }

    //returns true if it is the last question in the list of questions. Else false
    public boolean isLastQuestion(int tmp) {
        if (tmp == (questions.size() - 1)) {
            return true;
        } else {
            return false;
        }
    }

    //adds 1 to the number of correct questions
    public void incrementCorrect() {
        correctQuestions++;
    }

    //sets the topic title
    public void setTitle(String t) {
        title = t;
    }

    //gets the topic title
    public String getTitle() {
        return title;
    }

    //set the description text for the topic
    public void setDesc(String d) {
        desc = d;
    }

    //gets the description text for the topic
    public String getDesc() { return desc;}

    //sets the questions for the topic to a list of questions
    public void setQuestions(List<Question> qs) {
        for (Question q: qs) {
            questions.add(q);
        }
    }

}
