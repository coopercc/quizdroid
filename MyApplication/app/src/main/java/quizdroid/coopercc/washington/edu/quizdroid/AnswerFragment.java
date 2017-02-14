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

import java.util.List;


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

        QuizApp app = (QuizApp)getActivity().getApplication();
        final Topic topic = ((QuizActivity)getActivity()).getTopic();
        Question question = topic.getCurrentQuestion();
        final String questionTitle = question.getQuestion();
        final int correctAnsPos = question.getCorrectAnswer(); //.get will work on this

        final String answer = getArguments().getString("answer");


        TextView title = (TextView) rootView.findViewById(R.id.title);
        title.setText(questionTitle);

        List<String> answerList = question.getAnswers();

        RadioGroup rg1 = (RadioGroup) rootView.findViewById(R.id.answer_group);
        RadioButton button;
        for(int i = 0; i < answerList.size(); i++) {
            String tempAns = answerList.get(i);
            button = new RadioButton(getActivity());
            button.setText(tempAns);

            //if this equals the answer
            if (tempAns.equals(answer)) {
                button.setBackgroundColor(Color.parseColor("#ffcc0000"));
                button.setSelected(true);
            }
            //if
            if (i == correctAnsPos) {
                button.setBackgroundColor(Color.parseColor("#ff669900"));
            }
            button.setEnabled(false);
            rg1.addView(button);
        }


        //do correct logic here, calculate correct/question
        if (answer.equals(answerList.get(correctAnsPos))) {
            topic.incrementCorrect();
        }

        //
        int correctCt = topic.getCorrectQuestions();
        int currQ = topic.getCurrentQuestionInt();
        TextView correctView = (TextView) rootView.findViewById(R.id.correct_count);
        correctView.setText(correctCt + " out of " + currQ + " correct");



        Button finish = (Button) rootView.findViewById(R.id.next);
        if (topic.isLastQuestion(currQ)) {

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

                    topic.incrementQuestion();

                    QuestionFragment questionFrag = new QuestionFragment();
                    FragmentTransaction tx = getFragmentManager().beginTransaction();
                    tx.replace(R.id.fragment_placeholder, questionFrag);
                    tx.commit();
                }
            });
        }

        return rootView;
    }

}
