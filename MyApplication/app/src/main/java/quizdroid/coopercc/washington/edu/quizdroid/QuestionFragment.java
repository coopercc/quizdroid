package quizdroid.coopercc.washington.edu.quizdroid;


import android.app.FragmentTransaction;
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
public class QuestionFragment extends Fragment {

    private RadioGroup grp;
    private String[] answers = {"Answer 1", "Answer 2", "Answer 3", "Answer 4"};

    public QuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_question, container, false);

        final int question = getArguments().getInt("question");
        final int correct = getArguments().getInt("correct");

        TextView title = (TextView) rootView.findViewById(R.id.title);
        CharSequence chr = title.getText();
        String str = chr.toString() + question;
        title.setText(str);

        final Button submit = (Button) rootView.findViewById(R.id.submit);
        submit.setVisibility(View.INVISIBLE);
        grp = (RadioGroup) rootView.findViewById(R.id.questionGrp);
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
                RadioButton answerButton = (RadioButton) rootView.findViewById(selectedId);
                String answer = answerButton.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putInt("question", question);
                bundle.putInt("correct", correct);
                bundle.putStringArray("answers", answers);
                bundle.putString("answer", answer);

                AnswerFragment answerFrag = new AnswerFragment();
                answerFrag.setArguments(bundle);

                FragmentTransaction tx = getFragmentManager().beginTransaction();
                tx.replace(R.id.fragment_placeholder, answerFrag);
                tx.commit();
            }
        });

        return rootView;

    }

}
