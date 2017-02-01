package quizdroid.coopercc.washington.edu.quizdroid;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class IntroFragment extends Fragment {


    public IntroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_intro, container, false);

        String subject = getArguments().getString("subject");
        String desc = getArguments().getString("description");

        TextView title = (TextView) rootView.findViewById(R.id.introTitle);
        TextView descView = (TextView) rootView.findViewById(R.id.description);

        title.setText(subject + " Quiz");
        descView.setText(desc);

        Button btn = (Button) rootView.findViewById(R.id.begin);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putInt("question", 1);
                bundle.putInt("correct", 0);
                QuestionFragment questionFrag = new QuestionFragment();
                questionFrag.setArguments(bundle);


                FragmentTransaction tx = getFragmentManager().beginTransaction();
                tx.replace(R.id.fragment_placeholder, questionFrag);
                tx.commit();
                //start question fragment
            }
        });
        return rootView;

    }

}
