package com.example.vymajoris.myapplication;

import android.app.Application;
import android.util.Log;

import com.onesignal.OSPermissionObserver;
import com.onesignal.OSPermissionStateChanges;
import com.onesignal.OSSubscriptionObserver;
import com.onesignal.OSSubscriptionStateChanges;
import com.onesignal.OneSignal;

public class MyApplication extends Application {
@Override
public void onCreate() {
	super.onCreate();
	OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

	OneSignal.startInit(this)
					.inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
					.unsubscribeWhenNotificationsAreDisabled(true)
					.init();

	OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
		@Override
		public void idsAvailable(String userId, String registrationId) {
			Log.d("OneSignal","userId: "+userId);
			Log.d("OneSignal","registrationId: "+userId);
		}
	});

	OneSignal.addPermissionObserver(new OSPermissionObserver() {
		@Override
		public void onOSPermissionChanged(OSPermissionStateChanges stateChanges) {
			Log.d("OneSignal","permissionState: "+stateChanges.toString());

		}
	});

	OneSignal.addSubscriptionObserver(new OSSubscriptionObserver() {
		@Override
		public void onOSSubscriptionChanged(OSSubscriptionStateChanges stateChanges) {
			Log.d("OneSignal ","subsCriptionState: "+stateChanges.toString());
		}
	});

}
}
