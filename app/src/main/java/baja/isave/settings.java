package baja.isave;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class settings extends AppCompatActivity {
    public static final String PREFS_NAME = "settings";
    public static final String KEY_ID_TYPE_ALARM = "type";
    public static final String TYPE_DEFAULT = "1";
    public static final String TYPE_FLIP = "2";
    public static final String TYPE_SHAKE = "3";
    public static final String TYPE_MATH = "4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        System.out.println("HOHOH!@#$%)(*&^%@&^%");
    }

    public void logout(View view) {
        SharedPreferences user = getSharedPreferences("user", 0);
        SharedPreferences.Editor editor = user.edit();
        editor.putString("idArduino", "");
        editor.commit();
        Intent intent = new Intent(getApplicationContext(), Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void normalPressed (View view){
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(KEY_ID_TYPE_ALARM, TYPE_DEFAULT);
        // Commit the edits!
        editor.commit();

        Intent i = new Intent(settings.this,myAlarm.class);
        startActivity(i);
//        finish();
    }

    public void flipPressed (View view){
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(KEY_ID_TYPE_ALARM, TYPE_FLIP);
        // Commit the edits!
        editor.commit();

        Intent i = new Intent(settings.this,myAlarm.class);
        startActivity(i);
//        finish();
    }

    public void shakePressed (View view){
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(KEY_ID_TYPE_ALARM, TYPE_SHAKE);
        // Commit the edits!
        editor.commit();

        Intent i = new Intent(settings.this,myAlarm.class);
        startActivity(i);
//        finish();
    }

    public void mathPressed (View view){
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(KEY_ID_TYPE_ALARM, TYPE_MATH);
        // Commit the edits!
        editor.commit();

        Intent i = new Intent(settings.this,myAlarm.class);
        startActivity(i);
//        finish();
    }
}
