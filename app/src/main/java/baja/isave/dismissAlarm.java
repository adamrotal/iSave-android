package baja.isave;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Rotal on 2/25/2017.
 */

public class dismissAlarm extends AppCompatActivity {
    TextView secInfo;

    ImageButton btnStop;

    Ringtone ringTone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_default);

        btnStop = (ImageButton)findViewById(R.id.imageButton);

        String stringUri = getIntent().getStringExtra("SEC_RINGTONE_URI");
        Uri uri = Uri.parse(stringUri);

        ringTone = RingtoneManager.getRingtone(getApplicationContext(), uri);
//        secInfo.append(ringTone.getTitle(dismissAlarm.this));

        ringTone.play();

        btnStop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(ringTone != null){
                    ringTone.stop();
                    ringTone = null;
                }

                Intent i = new Intent(dismissAlarm.this,myAlarm.class);
                startActivity(i);
                finish();
            }
        });

    }
}
