package quizdroid.coopercc.washington.edu.quizdroid;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnswerFragment extends Fragment {


    public AnswerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_answer, container, false);

        final int question = getArguments().getInt("question");
        final int correct = getArguments().getInt("correct");
        final String answer = getArguments().getString("answer");
        final String[] answers = getArguments().getStringArray("answers");

        TextView title = (TextView) rootView.findViewById(R.id.title);
        String newTitle = title.getText().toString() + question;
        title.setText(newTitle);

        RadioGroup rg1 = (RadioGroup) rootView.findViewById(R.id.answer_group);
        RadioButton button;
        for(int i = 0; i < 4; i++) {
            button = new RadioButton(getActivity());
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

        TextView correctView = (TextView) rootView.findViewById(R.id.correct_count);
        correctView.setText(correctPut + " out of " + question + " correct");

        Button finish = (Button) rootView.findViewById(R.id.next);
        if (question == 4) {

            finish.setText("Finish");
            finish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                    startActivity(intent);
                }
            });

        } else {
            finish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent intent = new Intent(AnswerActivity.this, QuestionsActivity.class);
                    //intent.putExtra("question", question + 1);
                    //intent.putExtra("correct", correctPut);

                    //startActivity(intent);

                    Bundle bundle = new Bundle();
                    bundle.putInt("question", question + 1);
                    bundle.putInt("correct", correctPut);

                   QuestionFragment questionFrag = new QuestionFragment();
                    questionFrag.setArguments(bundle);

                    FragmentTransaction tx = getFragmentManager().beginTransaction();
                    tx.replace(R.id.fragment_placeholder, questionFrag);
                    tx.commit();
                }
            });
        }

        return rootView;
    }

}
