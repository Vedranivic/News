package news.factory.com.messaging;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import java.util.Dictionary;
import java.util.Hashtable;
import javax.inject.Inject;
import dagger.android.AndroidInjection;
import news.factory.com.base.Constants;

public class MessagingService extends FirebaseMessagingService {

    private final String TAG = MessagingService.class.getSimpleName();
    @Inject
    public Intent actionIntent;
    @Inject
    public Notification.Builder notificationBuilder;
    @Inject
    public NotificationManager notificationManager;

    @Override
    public void onCreate() {
        AndroidInjection.inject(this);
        super.onCreate();
    }

    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Dictionary<String,String> parameters = new Hashtable<>();
        if(remoteMessage.getNotification()!=null){
            parameters.put(
                    Constants.TITLE,
                    remoteMessage.getNotification().getTitle()
            );
        }
        if(remoteMessage.getData()!=null && remoteMessage.getData().size()>0){
            parameters.put(
                    Constants.ARTICLE_KEY,
                    remoteMessage.getData().get(Constants.ARTICLE_KEY)
            );
            parameters.put(
                    Constants.ARTICLE_TITLE,
                    remoteMessage.getData().get(Constants.ARTICLE_TITLE)
            );
        }
        sendNotification(parameters);
    }

    private void sendNotification(Dictionary<String,String> parameters) {
        actionIntent.putExtra(Constants.ARTICLE_KEY,parameters.get(Constants.ARTICLE_KEY));
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                actionIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        notificationBuilder.setContentTitle(parameters.get(Constants.TITLE))
                .setContentText(parameters.get(Constants.ARTICLE_TITLE))
                .setContentIntent(pendingIntent);
        notificationManager.notify(0, notificationBuilder.build());
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }

}
