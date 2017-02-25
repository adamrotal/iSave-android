package baja.isave;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class Firebase extends FirebaseMessagingService {
    public static final String PREFS_NAME = "settings";
    public static final String KEY_ID_TYPE_ALARM = "type";
    public static final String TYPE_DEFAULT = "1";
    public static final String TYPE_FLIP = "2";
    public static final String TYPE_SHAKE = "3";
    public static final String TYPE_MATH = "4";
    public static final String TYPE_FIREBASE = "5";

    public Firebase() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        System.out.println("From :" + remoteMessage.getFrom());
        System.out.println("Notofication Message Body :" + remoteMessage.getNotification().getBody());
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(KEY_ID_TYPE_ALARM, TYPE_FIREBASE);
        // Commit the edits!
        editor.commit();
        Uri uriAlarm = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        String uriString = uriAlarm.toString();
        Context context = getApplicationContext();
        Intent secIntent = new Intent(context, dismissAlarm.class);
        secIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        secIntent.putExtra("SEC_RINGTONE_URI", uriString);
        context.startActivity(secIntent);

//        Intent i = new Intent(getApplicationContext(), dismissAlarm.class);
//        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(i);
    }
}
