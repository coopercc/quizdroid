package quizdroid.coopercc.washington.edu.quizdroid;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;






public class QuestionsActivity extends Activity {

    private RadioGroup grp;
    private String[] answers = {"Answer 1", "Answer 2", "Answer 3", "Answer 4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        Intent intent = getIntent();
        final int question = intent.getIntExtra("question", 1);
        final int correct = intent.getIntExtra("correct", 0);
        TextView title = (TextView) findViewById(R.id.title);
        CharSequence chr = title.getText();
        String str = chr.toString() + question;
        title.setText(str);

        final Button submit = (Button) findViewById(R.id.submit);
        submit.setVisibility(View.INVISIBLE);
        grp = (RadioGroup) findViewById(R.id.questionGrp);
        grp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                submit.setVisibility(View.VISIBLE);
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = grp.getCheckedRadioButtonId();

                RadioButton answerButton = (RadioButton) findViewById(selectedId);
                String answer = answerButton.getText().toString();
                Intent intent = new Intent(QuestionsActivity.this, AnswerActivity.class);
                intent.putExtra("question", question);
                intent.putExtra("correct", correct);
                intent.putExtra("answers", answers);
                intent.putExtra("answer", answer);
                startActivity(intent);

            }
        });
    }
}
