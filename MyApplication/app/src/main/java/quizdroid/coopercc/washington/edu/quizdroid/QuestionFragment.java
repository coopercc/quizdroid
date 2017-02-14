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

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment {

    private RadioGroup grp;

    public QuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_question, container, false);


        QuizApp app = (QuizApp)getActivity().getApplication();
        final Topic topic = ((QuizActivity)getActivity()).getTopic();
        Question question = topic.getCurrentQuestion();

        final String questionTitle = question.getQuestion();



        TextView titleView = (TextView) rootView.findViewById(R.id.title);
        titleView.setText(questionTitle);

        List<String> answerList = question.getAnswers();

        final Button submit = (Button) rootView.findViewById(R.id.submit);
        submit.setVisibility(View.INVISIBLE);
        grp = (RadioGroup) rootView.findViewById(R.id.questionGrp);

        RadioButton button;
        for(int i = 0; i < answerList.size(); i++) {
            String temp = answerList.get(i);
            button = new RadioButton(getActivity());
            button.setText(temp);
            grp.addView(button);

        }


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
