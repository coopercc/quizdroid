package quizdroid.coopercc.washington.edu.quizdroid;

import android.app.Application;
import android.util.Log;

/**
 * Created by coopercain on 2/12/17.
 */

public class QuizApp extends Application {

    private TopicRepository instance;
    private PrefsManager prefs;

    public TopicRepository getRepository() {
        return instance;
    }

    public PrefsManager getPrefs() { return prefs; }

    @Override
    public void onCreate() {
        instance = new TopicRepository();
        prefs = new PrefsManager();
        Log.i("QuizApp", "QuizApp onCreate has fired");
    }
}
