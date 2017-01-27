package quizdroid.coopercc.washington.edu.quizdroid;


import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.*;

public class HomeActivity extends Activity {

    String[] subjects = {"Math", "Physics", "Marvel Super Heroes"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, subjects);
        ListView listView = (ListView) findViewById(R.id.homeList);
        listView.setAdapter(adapter);

    }
}
