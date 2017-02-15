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

    public TopicRepository() {
        //site: coopercain.net/questions.json. Also on my desktop
    }

    public void getJson() {
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
                    JSONObject questionObj = questions.getJSONObject(j);

                    Question newQ = new Question();
                    String qTitle = questionObj.getString("question");
                    int correctAns = questionObj.getInt("answer");
                    List<String> answers = new ArrayList<String>();
                    JSONArray ansArr = questionObj.getJSONArray("answers");
                    for (int k = 0; k < ansArr.length(); k++) {
                        answers.add(ansArr.getString(k));
                    }
                    newQ.setQuestion(qTitle);
                    newQ.setCorrectAnswer(correctAns);
                    newQ.setAnswers(answers);
                    newQs.add(newQ);
                }
                newTopic.setQuestions(newQs);
                Topics.add(newTopic);

            } catch(JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
