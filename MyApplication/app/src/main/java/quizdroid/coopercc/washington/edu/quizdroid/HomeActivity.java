package quizdroid.coopercc.washington.edu.quizdroid;


import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.*;
import android.view.View;
import android.widget.AdapterView.*;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        QuizApp app = (QuizApp)this.getApplication();
        List<Topic> topics = app.getRepository().getTopics();
        final String[] topicTitles = new String[topics.size()];
        for (int i = 0; i < topics.size(); i++) {
            String title = topics.get(i).getTitle();
            topicTitles[i] = title;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, topicTitles);
        final ListView listView = (ListView) findViewById(R.id.homeList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //change it to pass the subject to the next activity
                Intent intent = new Intent(HomeActivity.this, QuizActivity.class);
                intent.putExtra("subject",  topicTitles[position]);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

    }
}
