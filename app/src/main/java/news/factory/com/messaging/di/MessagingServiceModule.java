package news.factory.com.messaging.di;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;

import dagger.Module;
import dagger.Provides;
import news.factory.com.R;
import news.factory.com.base.dependency_injection.PerServiceScope;
import news.factory.com.messaging.MessagingService;
import news.factory.com.single.activity.view.SingleActivity;

@Module
public class MessagingServiceModule {

    @PerServiceScope
    @Provides
    public Intent provideActionIntent(MessagingService messagingService){
        Intent actionIntent = new Intent(messagingService, SingleActivity.class);
        actionIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return actionIntent;
    }

    @PerServiceScope
    @Provides
    public Notification.Builder provideNotification(MessagingService messagingService){
        return new Notification.Builder(messagingService)
                .setSmallIcon(R.drawable.logo_app_round)
                .setLargeIcon(BitmapFactory.decodeResource(messagingService.getResources(), R.drawable.logo_app))
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_HIGH)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
    }

    @PerServiceScope
    @Provides
    public NotificationManager provideNotificationManager(MessagingService messagingService){
        return (NotificationManager)messagingService.getSystemService(Context.NOTIFICATION_SERVICE);
    }
}
