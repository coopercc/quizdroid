package quizdroid.coopercc.washington.edu.quizdroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Cooper Cain on 2/25/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String url = intent.getStringExtra("Url");
        Toast.makeText(context, url, Toast.LENGTH_SHORT).show();
        Log.i("Alarm", "received");

        HttpURLConnection c = null;
        try {
            URL u = new URL(url);
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
                    sb.append(line+"\n");
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
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);

            CharSequence ch = "There was an error downloading the file, so sorry.";
            Toast.makeText(context, ch, Toast.LENGTH_SHORT).show();
        }



        //read from url, write to file, then reread json
    }
}
