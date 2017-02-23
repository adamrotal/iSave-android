package baja.isave;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class myClock extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_clock);
    }


    public void alarmPressed (View view){
        Intent i = new Intent(myClock.this,myAlarm.class);
        startActivity(i);
//        finish();
    }
}
