package quizdroid.coopercc.washington.edu.quizdroid;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

public class QuizActivity extends Activity {
    String[] descriptions = {
            "This will test your ability in basic math questions.",
            "This will test your ability in basic physics questions.",
            "This will test your knowledge of the marvel superhero universe."
    };
    String qCount = "There are 4 questions in this quiz.";
    int currectQuestion = 1;
    int countCorrect = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Intent intent = getIntent();
        String subj = intent.getStringExtra("subject");
        int pos = intent.getIntExtra("position", 0);


        Bundle bundle = new Bundle();
        bundle.putString("subject", subj);
        bundle.putString("description", descriptions[pos] + " " + qCount);

        IntroFragment introFrag = new IntroFragment();
        introFrag.setArguments(bundle);
        FragmentTransaction tx = getFragmentManager().beginTransaction();
        tx.replace(R.id.fragment_placeholder, introFrag);
        tx.commit();



    }
}
