package quizdroid.coopercc.washington.edu.quizdroid;


import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.*;
import android.view.View;
import android.widget.AdapterView.*;
import android.content.Intent;

public class HomeActivity extends Activity {

    String[] subjects = {"Math", "Physics", "Marvel Super Heroes"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, subjects);
        ListView listView = (ListView) findViewById(R.id.homeList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0 ) {
                    //Math
                    Intent intent = new Intent(HomeActivity.this, MathIntroActivity.class);
                    startActivity(intent);

                } else if (position == 1) {
                    //Phys
                    Intent intent = new Intent(HomeActivity.this, PhysicsIntroActivity.class);
                    startActivity(intent);

                } else {
                    //superheroes
                    Intent intent = new Intent(HomeActivity.this, HeroIntroActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}
