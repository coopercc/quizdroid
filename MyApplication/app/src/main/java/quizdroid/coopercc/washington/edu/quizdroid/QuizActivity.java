package quizdroid.coopercc.washington.edu.quizdroid;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import java.util.List;

public class QuizActivity extends Activity {

    String qCount = "There are 4 questions in this quiz.";
    public static Topic topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Intent intent = getIntent();
        int pos = intent.getIntExtra("position", 0);

        QuizApp app = (QuizApp)this.getApplication();
        //get title and desc

        topic = app.getRepository().getTopics().get(pos);
        String title = topic.getTitle();
        String desc = topic.getDesc();

        Bundle bundle = new Bundle();
        bundle.putInt("pos", pos);

        IntroFragment introFrag = new IntroFragment();
        introFrag.setArguments(bundle);
        FragmentTransaction tx = getFragmentManager().beginTransaction();
        tx.replace(R.id.fragment_placeholder, introFrag);
        tx.commit();
    }

    public Topic getTopic() {
        return topic;
    }
}
