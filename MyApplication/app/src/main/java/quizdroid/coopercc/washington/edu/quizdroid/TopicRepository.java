package quizdroid.coopercc.washington.edu.quizdroid;

import android.os.Environment;
import android.util.JsonReader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by coopercain on 2/12/17.
 */

public class TopicRepository {
    private List<Topic> Topics = new ArrayList<Topic>();

    public List<Topic> getTopics() {
        return Topics;
    }

    //need to change to read from the json file
    public TopicRepository() {
        //initialize all of the topics
        //site: coopercain.net/questions.json. Also on my desktop
        String json = null;
        try {

            String uri = Environment.getExternalStorageDirectory().toString();
            File file = new File(uri, "/Android/data/questions.json");

            InputStream is = new FileInputStream(file);

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Log.i("TopicRepo", json);

        JSONArray dataArr = null;
        try {
            dataArr = new JSONArray(json);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        Log.i("TopicRepo", dataArr.toString());

        //Now to parse a json array!!!!!!!
        for (int i = 0; i < dataArr.length(); i++) {
            try {
                JSONObject topic = dataArr.getJSONObject(i);
                Topic newTopic = new Topic();
                String title = topic.getString("title");
                String desc = topic.getString("desc");
                newTopic.setTitle(title);
                newTopic.setDesc(desc);

                JSONArray questions = topic.getJSONArray("questions");
                //loop through the questions
                List<Question> newQs = new ArrayList<Question>();
                for (int j = 0; j < questions.length(); j++) {
                    //DO WORK HERE
                }
                newTopic.setQuestions(newQs);

            } catch(JSONException e) {
                e.printStackTrace();
            }
        }

        initializeMath();
        initializeScience();
        initializeMarvel();

    }

    //Initializes the math topic with 4 questions
    private void initializeMath() {
        Topic math = new Topic();
        math.setTitle("Math");
        math.setDesc("This will test your ability in basic math questions.");

        Question q1 = createQuestion("What is 2 * 3 = ?", "23", "5", "6", "2.3", 2);
        Question q2 = createQuestion("Two angles of a triangle are 15 and 85 degrees. What is the angle of the third?",
                "35", "80", "60", "90", 1);
        Question q3 = createQuestion("On a Map, 1 inch represents 20 miles. The distance between 2 towns is 6 1/5 inches. How many miles are actually between the two towns?",
                "65 miles", "84 miles", "138 miles", "124 miles", 3);
        Question q4 = createQuestion("How many cubed pieces of fudge that are 3 inches on an edge can be packed into a Christmas tin that is 9 inches deep by 12 inches wide by 9 inches high with the lid still being able to be closed?",
                "36", "18", "32", "24", 0);
        List<Question> questions = new ArrayList<Question>();
        questions.add(q1);
        questions.add(q2);
        questions.add(q3);
        questions.add(q4);
        math.setQuestions(questions);
        Topics.add(math);
    }

    //initialized science topic with 4 questions
    private void initializeScience() {
        Topic sci = new Topic();
        sci.setTitle("Science");
        sci.setDesc("This will test your ability in basic science questions.");

        Question q1 = createQuestion("How many planets are there in our solar system?", "8", "9", "10", "12", 1);
        Question q2 = createQuestion("32,000 is written in scientific notation as:", "3.2x10^4", "32x10^4", "32x10^3", "3.2x10^3", 0);
        Question q3 = createQuestion("What is the acceleration of gravity on Earth?", "10 ft/s^2", "9.8 m/s", "9.8 m/s^2", "9.8 ft/s", 2);
        Question q4 = createQuestion("What layer of the Earth has the highest temperatures?", "Mantle", "Crust", "Core", "All the same", 2);
        List<Question> questions = new ArrayList<Question>();
        questions.add(q1);
        questions.add(q2);
        questions.add(q3);
        questions.add(q4);
        sci.setQuestions(questions);
        Topics.add(sci);
    }

    //initializes marvel topic with 4 questions
    private void initializeMarvel() {
        Topic marvel = new Topic();
        marvel.setTitle("Marvel");
        marvel.setDesc("This will test your ability in basic Marvel Universe questions.");

        Question q1 = createQuestion("Which Super Hero Team Does Johnny Storm Belong To?",
                "Ultimate Avengers", "The Justice League", "The Fantastic 4", "The Avengers", 2);
        Question q2 = createQuestion("Who founded the X men?", "Tony Stark",
                "Professor Charles Xavier", "The X-Institute", "Erik Lensherr", 1);
        Question q3 = createQuestion("How did Peter Parker become Spiderman?",
                "Bit by a radioactive spider", "Ate a radioactive spider",
                "He is a radioactive spider", "One of his parents was a spider", 0);
        Question q4 = createQuestion("Who is Iron Man?", "Nobody Knows", "Matt Damon", "Logan Howlett", "Tony Stark", 3);

        List<Question> questions = new ArrayList<Question>();
        questions.add(q1);
        questions.add(q2);
        questions.add(q3);
        questions.add(q4);
        marvel.setQuestions(questions);
        Topics.add(marvel);
    }

    private Question createQuestion(String question, String a1, String a2, String a3, String a4, int correct) {
        Question newQ = new Question();
        newQ.setQuestion(question);
        List<String> answers = new ArrayList<String>();
        answers.add(a1);
        answers.add(a2);
        answers.add(a3);
        answers.add(a4);
        newQ.setAnswers(answers);
        newQ.setCorrectAnswer(correct);
        return newQ;
    }



}
