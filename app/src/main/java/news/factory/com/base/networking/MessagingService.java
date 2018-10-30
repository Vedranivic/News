package news.factory.com.base.networking;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.media.RingtoneManager;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import java.util.Dictionary;
import java.util.Hashtable;

import news.factory.com.R;
import news.factory.com.base.Constants;
import news.factory.com.single.activity.view.SingleActivity;

public class MessagingService extends FirebaseMessagingService {

    private final String TAG = MessagingService.class.getSimpleName();

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
        Intent intent = new Intent(this, SingleActivity.class);
        intent.putExtra(Constants.ARTICLE_KEY,parameters.get(Constants.ARTICLE_KEY));
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.logo_app_round)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.logo_app))
                .setContentTitle(parameters.get(Constants.TITLE))
                .setContentText(parameters.get(Constants.ARTICLE_TITLE))
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_HIGH)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(pendingIntent)
                .build();

        ((NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE)).notify(0, notification);
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }

}
