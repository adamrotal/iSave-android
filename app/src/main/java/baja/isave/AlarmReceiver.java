package baja.isave;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Rotal on 2/25/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Alarm received", Toast.LENGTH_LONG).show();

        String uriString = intent.getStringExtra("KEY_TONE_URL");


        Intent secIntent = new Intent(context, dismissAlarm.class);
        secIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        secIntent.putExtra("SEC_RINGTONE_URI", uriString);
        context.startActivity(secIntent);

    }
}
