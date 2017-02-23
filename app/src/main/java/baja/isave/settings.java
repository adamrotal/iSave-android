package baja.isave;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        System.out.println("HOHOH!@#$%)(*&^%@&^%");
    }

    public void normalPressed (View view){
        Intent i = new Intent(settings.this,myAlarm.class);
        startActivity(i);
//        finish();
    }

    public void flipPressed (View view){
        Intent i = new Intent(settings.this,myAlarm.class);
        startActivity(i);
//        finish();
    }

    public void shakePressed (View view){
        Intent i = new Intent(settings.this,myAlarm.class);
        startActivity(i);
//        finish();
    }

    public void mathPressed (View view){
        Intent i = new Intent(settings.this,myAlarm.class);
        startActivity(i);
//        finish();
    }
}
