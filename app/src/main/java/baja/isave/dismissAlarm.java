package baja.isave;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by Rotal on 2/25/2017.
 */

public class dismissAlarm extends AppCompatActivity implements SensorEventListener {
    TextView secInfo;

    ImageButton btnStop;

    Ringtone ringTone;
    public static final String PREFS_NAME = "settings";
    public static final String KEY_ID_TYPE_ALARM = "type";
    public static final String TYPE_DEFAULT = "1";
    public static final String TYPE_FLIP = "2";
    public static final String TYPE_SHAKE = "3";
    public static final String TYPE_MATH = "4";

    int number1 = 0;
    int number2 = 0;
    int operan = 0;

    private SensorManager mSensorManager;
    private Sensor mProximity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String type = settings.getString(KEY_ID_TYPE_ALARM,TYPE_DEFAULT);

        if(type.equals(TYPE_DEFAULT)) {
            setContentView(R.layout.activity_default);
        } else if (type.equals(TYPE_FLIP)) {
            setContentView(R.layout.activity_flip);
        } else if (type.equals(TYPE_MATH)) {
            setContentView(R.layout.activity_math);
            Random rand = new Random();

            number1 = rand.nextInt(200) + 100;
            number2 = rand.nextInt(200) + 100;
            operan = rand.nextInt(200) + 100;
            operan = operan % 4;

            TextView textNumber1 = (TextView) findViewById(R.id.firstDigit);
            TextView textNumber2 = (TextView) findViewById(R.id.secondDigit);
            TextView textOperan = (TextView) findViewById(R.id.operation);

            textNumber1.setText(String.valueOf(number1));
            textNumber2.setText(String.valueOf(number2));

            if(operan == 0) {
                textOperan.setText(" + ");
            } else if(operan == 1) {
                textOperan.setText(" - ");
            } else if(operan == 2) {
                textOperan.setText(" * ");
            } else {
                textOperan.setText(" div ");
            }

        } else {
            setContentView(R.layout.activity_shake);
        }


//        btnStop = (ImageButton)findViewById(R.id.imageButton);

        String stringUri = getIntent().getStringExtra("SEC_RINGTONE_URI");
        Uri uri = Uri.parse(stringUri);

        ringTone = RingtoneManager.getRingtone(getApplicationContext(), uri);
//        secInfo.append(ringTone.getTitle(dismissAlarm.this));

        ringTone.play();

//        btnStop.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                if(ringTone != null){
//                    ringTone.stop();
//                    ringTone = null;
//                }
//
//                Intent i = new Intent(dismissAlarm.this,myAlarm.class);
//                startActivity(i);
//                finish();
//            }
//        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    public void listenerDefault(View view){
        if(ringTone != null){
            ringTone.stop();
            ringTone = null;
        }

        Intent i = new Intent(dismissAlarm.this,myAlarm.class);
        startActivity(i);
        finish();
    }

    public void listenerMath(){
//        EditText editTextAnswer = (EditText) findViewById(R.id.result);
//        int answer = Integer.getInteger(editTextAnswer.getText().toString());
        System.out.println("ini masuk math");
//        System.out.println(editTextAnswer.getText().toString());
//        System.out.println(number1);
//        System.out.println(number2);


//        if(operan == 0) {
////            textOperan.setText("+");
//            if(answer == (number1+number2)) {
//                this.stopAlarm();
//            } else {
//                ringTone.play();
//            }
//        } else if(operan == 1) {
////            textOperan.setText("-");
//            if(answer == (number1-number2)) {
//                this.stopAlarm();
//            } else {
//                ringTone.play();
//            }
//        } else if(operan == 2) {
////            textOperan.setText("*");
//            if(answer == (number1*number2)) {
//                this.stopAlarm();
//            } else {
//                ringTone.play();
//            }
//        } else {
////            textOperan.setText("/");
//            if(answer == (number1/number2)) {
//                this.stopAlarm();
//            } else {
//                ringTone.play();
//            }
//        }
//
//        ringTone.play();
//        this.stopAlarm();

    }

    private void stopAlarm() {
        if(ringTone != null){
            ringTone.stop();
            ringTone = null;
        }

        Intent i = new Intent(dismissAlarm.this,myAlarm.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String type = settings.getString(KEY_ID_TYPE_ALARM,TYPE_DEFAULT);

        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            System.out.println("ini masuk sensor proximity");
            if (event.values[0] >= -0.01 && event.values[0] <= 0.01) {
                if(type.equals(TYPE_FLIP)) {
                    if (ringTone != null) {
                        ringTone.stop();
                        ringTone = null;
                    }

                    Intent i = new Intent(dismissAlarm.this, myAlarm.class);
                    startActivity(i);
                    finish();
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }



}
