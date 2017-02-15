package quizdroid.coopercc.washington.edu.quizdroid;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Integer.parseInt;

public class PrefsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefs);

        QuizApp app = (QuizApp)this.getApplication();
        final PrefsManager prefs = app.getPrefs();

        EditText url = (EditText) findViewById(R.id.urlText);
        url.setText(prefs.getUrl(), EditText.BufferType.EDITABLE);

        EditText minText = (EditText) findViewById(R.id.minutes);
        minText.setText(prefs.getRefreshMins() + "", EditText.BufferType.EDITABLE);

        Button update = (Button) findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView url = (TextView) findViewById(R.id.urlText);
                String urlText = url.getText().toString();
                EditText minText = (EditText) findViewById(R.id.minutes);
                int mins = parseInt(minText.getText().toString());
                if (mins != prefs.getRefreshMins()) {
                    prefs.setRefreshMins(mins);
                }
                if (!urlText.equals(prefs.getUrl())) {
                    prefs.setUrl(urlText);
                }

                Context context = getApplicationContext();
                CharSequence text = "Preferences have been updated!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }

        });

    }
}
