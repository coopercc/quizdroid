package quizdroid.coopercc.washington.edu.quizdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class AnswerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        //if question == 4, set button text to == finish and click will take back to main
        //else, button text == next and goes to question (+1)
        //for both, show the correct answer in green, and their answer in red with correct in green
        /*
        intent.putExtra("question", question); int
                intent.putExtra("correct", correct); int
                intent.putExtra("answers", answers); str array
                intent.putExtra("answer", answer); str
         */
        Intent intent = getIntent();
        int question = intent.getIntExtra("question", 1);
        int correct = intent.getIntExtra("correct", 0);
        String[] answers = intent.getStringArrayExtra("answers");
        String answer = intent.getStringExtra("answer");

        TextView title = (TextView) findViewById(R.id.title);
        String newTitle = title.getText().toString() + question;
        title.setText(newTitle);

        if (question == 4) {
            Button finish = (Button)
        }

    }
}
