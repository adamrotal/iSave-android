package baja.isave;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class myAlarm extends AppCompatActivity {

    TimePicker myTimePicker;
    ImageButton buttonstartSetDialog;

    Uri uriAlarm;

    final static int RQS_RINGTONEPICKER = 1;
    final static int RQS_1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_alarm);

        myTimePicker = (TimePicker) findViewById(R.id.timePicker);
        myTimePicker.setIs24HourView(true);

        uriAlarm = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        buttonstartSetDialog = (ImageButton)findViewById(R.id.sleepButton);

        buttonstartSetDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("START");
                openTimePickerDialog();
            }
        });

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


    private void openTimePickerDialog(){
        System.out.println("HAHAHAHAHAHAHAHAHAHAHA");
        Calendar calNow = Calendar.getInstance();
        Calendar calSet = (Calendar) calNow.clone();

        calSet.set(Calendar.HOUR_OF_DAY, myTimePicker.getCurrentHour());
        calSet.set(Calendar.MINUTE, myTimePicker.getCurrentMinute());
        calSet.set(Calendar.SECOND, 0);
        calSet.set(Calendar.MILLISECOND, 0);

        if(calSet.compareTo(calNow) <= 0) {
            //Today Set time passed, count to tomorrow
            calSet.add(Calendar.DATE, 1);
        }

        setAlarm(calSet, uriAlarm);
    }


    private void setAlarm(Calendar targetCal, Uri passuri){
        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);

        String passString = passuri.toString();

        intent.putExtra("KEY_TONE_URL", passString);

        Toast.makeText(this, ("Alarm is set@ " + targetCal.getTime() + "Uri: " + passString), Toast.LENGTH_LONG).show();

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS_1, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
    }
}
