package quizdroid.coopercc.washington.edu.quizdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HeroIntroActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_intro);

        Button btn = (Button) findViewById(R.id.beginMath);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HeroIntroActivity.this, QuestionsActivity.class);
                intent.putExtra("question", 1);
                startActivity(intent);
            }
        });
    }
}
