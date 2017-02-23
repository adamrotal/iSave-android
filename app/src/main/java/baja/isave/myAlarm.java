package baja.isave;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class myAlarm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_alarm);
    }

    public void weatherPressed (View view){
        Intent i = new Intent(myAlarm.this,myClock.class);
        startActivity(i);
//        finish();
    }

    public void settingsPressed (View view){
        Intent i = new Intent(myAlarm.this,settings.class);
        startActivity(i);
//        finish();
    }

}
