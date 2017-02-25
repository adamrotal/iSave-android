package baja.isave;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class Firebase extends FirebaseMessagingService {
    public Firebase() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        System.out.println("From :" + remoteMessage.getFrom());
        System.out.println("Notofication Message Body :" + remoteMessage.getNotification().getBody());
    }
}
