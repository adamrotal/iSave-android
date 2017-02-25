package baja.isave;

import android.*;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Rotal on 2/25/2017.
 */

public class dismissAlarm extends AppCompatActivity implements SensorEventListener {

    MediaPlayer player;

    public static final String PREFS_NAME = "settings";
    public static final String KEY_ID_TYPE_ALARM = "type";
    public static final String TYPE_DEFAULT = "1";
    public static final String TYPE_FLIP = "2";
    public static final String TYPE_SHAKE = "3";
    public static final String TYPE_MATH = "4";
    public static final String TYPE_FIREBASE = "5";

    int number1 = 0;
    int number2 = 0;
    int operan = 0;

    private SensorManager mSensorManager;
    private Sensor mProximity, mAccelerometer;

    private static final float SHAKE_THRESHOLD_GRAVITY = 2.7F;
    private static final int SHAKE_SLOP_TIME_MS = 500;
    private static final int SHAKE_COUNT_RESET_TIME_MS = 3000;

    private long mShakeTimestamp;
    private int mShakeCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String type = settings.getString(KEY_ID_TYPE_ALARM,TYPE_DEFAULT);

        if(type.equals(TYPE_DEFAULT)) {
            setContentView(R.layout.activity_default);
        } else if (type.equals(TYPE_FLIP)) {
            mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

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


        } else if(type.equals(TYPE_SHAKE)){ // Shaked
            System.out.println("masuk shake");
            mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

            setContentView(R.layout.activity_shake);
        } else {
            setContentView(R.layout.activity_notification);
        }


        String stringUri = getIntent().getStringExtra("SEC_RINGTONE_URI");
        Uri uri = Uri.parse(stringUri);

        player = MediaPlayer.create(this, uri);
        player.setLooping(true);
        player.start();

    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String type = settings.getString(KEY_ID_TYPE_ALARM,TYPE_DEFAULT);

        if (type.equals(TYPE_FLIP)) {
            mSensorManager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
        } else if (type.equals(TYPE_SHAKE)){ // Shaked
            mSensorManager.registerListener(this, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    
    public void listenerDefault(View view){
        if (player != null) {
            player.stop();
            player = null;
        }

        Intent i = new Intent(dismissAlarm.this,myAlarm.class);
        startActivity(i);
        finish();
    }
    public void listenerCall(View view){
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(KEY_ID_TYPE_ALARM, TYPE_DEFAULT);
        // Commit the edits!
        editor.commit();
        if (player != null) {
            player.stop();
            player = null;
        }

        String posted_by = "081260609519";
        final int REQUEST_GRANTEE = 99;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    REQUEST_GRANTEE);

            return;
        }

        String uri = "tel:" + posted_by.trim() ;
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
        finish();
    }

    public void listenerMath(View view){
        EditText editTextAnswer = (EditText) findViewById(R.id.result);
        String textResult = editTextAnswer.getText().toString().trim();
        int answer;
        if(textResult.isEmpty() || textResult.length() == 0 || textResult.equals("") || textResult == null)
        {
            Context context = getApplicationContext();
            CharSequence text = "Answer can't be null";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else
        {
            answer = Integer.parseInt(textResult);
            System.out.println("ini masuk math");
//            System.out.println(answer);
            System.out.println(number1);
            System.out.println(number2);
            System.out.println(operan);
            if(operan == 0) {
//                textOperan.setText("+");
                if(answer == (number1+number2)) {
                    this.stopAlarm();
                }
            } else if(operan == 1) {
//                textOperan.setText("-");
                if(answer == (number1-number2)) {
                    this.stopAlarm();
                }
            } else if(operan == 2) {
//                textOperan.setText("*");
                if(answer == (number1*number2)) {
                    this.stopAlarm();
                }
            } else {
//                textOperan.setText("/");
                if(answer == (number1/number2)) {
                    this.stopAlarm();
                }
            }


        }
    }

    private void stopAlarm() {
        if(player != null){
            player.stop();
            player = null;
        }

        Intent i = new Intent(dismissAlarm.this,myAlarm.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        System.out.println("chnged");

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String type = settings.getString(KEY_ID_TYPE_ALARM,TYPE_DEFAULT);

        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            System.out.println("ini masuk sensor proximity");
            if (event.values[0] >= -0.01 && event.values[0] <= 0.01) {
                if(type.equals(TYPE_FLIP)) {
                    stopAlarm();
                }
            }
        } else if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            System.out.println("Count : " + mShakeCount);

                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

                float gX = x / SensorManager.GRAVITY_EARTH;
                float gY = y / SensorManager.GRAVITY_EARTH;
                float gZ = z / SensorManager.GRAVITY_EARTH;

                // gForce will be close to 1 when there is no movement.
                float gForce = (float) Math.sqrt(gX * gX + gY * gY + gZ * gZ);

                System.out.println("Shake me : " + gForce);

                if (gForce > SHAKE_THRESHOLD_GRAVITY) {
                    final long now = System.currentTimeMillis();
                    // ignore shake events too close to each other (500ms)
                    if (mShakeTimestamp + SHAKE_SLOP_TIME_MS > now) {
                        return;
                    }

                    // reset the shake count after 3 seconds of no shakes
//                if (mShakeTimestamp + SHAKE_COUNT_RESET_TIME_MS < now) {
//                    mShakeCount = 0;
//                }

                    mShakeTimestamp = now;
                    mShakeCount++;
                    System.out.println(mShakeCount);
                    if (mShakeCount >= 12) {

                        Toast.makeText(dismissAlarm.this,"Your Message", Toast.LENGTH_LONG).show();
                        stopAlarm();
                    }
                }
//            }

        }
    }

}
