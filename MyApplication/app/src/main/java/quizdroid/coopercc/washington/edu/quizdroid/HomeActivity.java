package quizdroid.coopercc.washington.edu.quizdroid;


import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.*;
import android.view.View;
import android.widget.AdapterView.*;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends Activity {

    private static final int PERMISSION_REQUEST_CODE = 1;
    private TopicRepository repo;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_preferences) {
            Intent intent = new Intent(HomeActivity.this, PrefsActivity.class);

            startActivity(intent);
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        QuizApp app = (QuizApp)this.getApplication();
        repo = app.getRepository();

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkPermission()) {
                Log.i("Permissions", "Has Permission");

                if (repo.getTopics().size() == 0) {
                    Log.i("Permissions", "Initializing topics");
                    repo.getJson();
                    fillList();
                }
            } else {
                Log.i("Permissions", "Getting Permission");
                requestPermission();
                // Code for permission
            }
        }
        else {
            Log.i("Permissions", "doesn't need");
            if (repo.getTopics().size() == 0) {
                repo.getJson();
                fillList();
            }
        }

        if (repo.getTopics().size() > 0) {
            fillList();
        }

    }

    private void fillList() {
        List<Topic> topics = repo.getTopics();
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

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(HomeActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(HomeActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(HomeActivity.this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(HomeActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("value", "Permission Granted, Now you can use local drive .");
                    repo.getJson();
                    fillList();
                } else {
                    Log.e("value", "Permission Denied, You cannot use local drive .");
                }
                break;
        }
    }

}
