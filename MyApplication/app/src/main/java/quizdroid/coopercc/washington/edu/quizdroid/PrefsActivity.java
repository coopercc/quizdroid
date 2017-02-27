package quizdroid.coopercc.washington.edu.quizdroid;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;

public class PrefsActivity extends Activity {

    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private TopicRepository repo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefs);



        QuizApp app = (QuizApp)this.getApplication();
        repo = app.getRepository();

        alarmManager = (AlarmManager) PrefsActivity.this.getSystemService(Context.ALARM_SERVICE);

        final PrefsManager prefs = app.getPrefs();

        EditText url = (EditText) findViewById(R.id.urlText);
        url.setText(prefs.getUrl(), EditText.BufferType.EDITABLE);

        EditText minText = (EditText) findViewById(R.id.minutes);
        minText.setText(prefs.getRefreshMins() + "", EditText.BufferType.EDITABLE);

        Button update = (Button) findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView url = (TextView) findViewById(R.id.urlText);
                String urlText = url.getText().toString();
                EditText minText = (EditText) findViewById(R.id.minutes);
                int mins = parseInt(minText.getText().toString());
                if (mins != prefs.getRefreshMins()) {
                    prefs.setRefreshMins(mins);
                }
                if (!urlText.equals(prefs.getUrl())) {
                    prefs.setUrl(urlText);
                }

                //call AlarmReceiver
                Intent intent = new Intent(PrefsActivity.this, AlarmReceiver.class);
                intent.putExtra("Url", url.getText().toString());
                pendingIntent = PendingIntent.getBroadcast(PrefsActivity.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                int refresh = Integer.parseInt(minText.getText().toString()) * 6000;
                alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                        SystemClock.elapsedRealtime() + refresh, refresh, pendingIntent);

                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        HttpURLConnection c = null;
                        try {
                            Log.i("Prefs", url.getText().toString());
                            URL u = new URL(url.getText().toString());
                            c = (HttpURLConnection) u.openConnection();
                            c.setRequestMethod("GET");
                            c.setRequestProperty("Content-length", "0");
                            c.setUseCaches(false);
                            c.setAllowUserInteraction(false);
                            c.connect();
                            int status = c.getResponseCode();
                            if (status == 201) {
                                BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                                StringBuilder sb = new StringBuilder();
                                String line;
                                while ((line = br.readLine()) != null) {
                                    sb.append(line + "\n");
                                }
                                br.close();
                                String json = sb.toString();
                                //write to local json file
                                // "/Android/data/questions.json"
                                String uri = Environment.getExternalStorageDirectory().toString();
                                File file = new File(uri, "/Android/data/questions.json");
                                FileOutputStream fs = new FileOutputStream(file, false);
                                fs.write(json.getBytes());
                                fs.close();
                            }
                        } catch (MalformedURLException ex) {
                            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Log.i("Prefs", ex.toString());
                            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                };
                thread.start();


                Context context = getApplicationContext();
                CharSequence text = "Preferences have been updated!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }

        });

    }
}
