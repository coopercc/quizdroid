package quizdroid.coopercc.washington.edu.quizdroid;

import android.app.Application;
import android.util.Log;

/**
 * Created by coopercain on 2/12/17.
 */

public class QuizApp extends Application {

    private TopicRepository instance = new TopicRepository();

    public TopicRepository getRepository() {
        return instance;
    }

    @Override
    public void onCreate() {
        Log.i("QuizApp", "QuizApp onCreate has fired");
    }
}
