package quizdroid.coopercc.washington.edu.quizdroid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
        final int question = intent.getIntExtra("question", 1);
        final int correct = intent.getIntExtra("correct", 0);
        String[] answers = intent.getStringArrayExtra("answers");
        String answer = intent.getStringExtra("answer");
        Log.i("AnswerActivity", answer + " and " + answers[question - 1]);

        TextView title = (TextView) findViewById(R.id.title);
        String newTitle = title.getText().toString() + question;
        title.setText(newTitle);

        RadioGroup rg1 = (RadioGroup) findViewById(R.id.answer_group);
        RadioButton button;
        for(int i = 0; i < 4; i++) {
            button = new RadioButton(this);
            button.setText(answers[i]);

            if (answers[i].equals(answer)) {
                button.setBackgroundColor(Color.parseColor("#ffcc0000"));
                button.setSelected(true);
            }
            if (i == question - 1) {
                button.setBackgroundColor(Color.parseColor("#ff669900"));
            }
            button.setEnabled(false);
            rg1.addView(button);
        }

        int newCorrect = correct;
        //do correct logic here, calculate correct/question
        if (answer.equals(answers[question - 1])) {
            newCorrect++;
        }
        final int correctPut = newCorrect;

        TextView correctView = (TextView) findViewById(R.id.correct_count);
        correctView.setText(correctPut + " out of " + question + " correct");

        //change


        Button finish = (Button) findViewById(R.id.next);
        if (question == 4) {

            finish.setText("Finish");
            finish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(AnswerActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            });

        } else {
            finish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(AnswerActivity.this, QuestionsActivity.class);
                    intent.putExtra("question", question + 1);
                    intent.putExtra("correct", correctPut);

                    startActivity(intent);
                }
            });
        }

    }
}
