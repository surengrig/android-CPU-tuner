package ch.amana.android.cputuner.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import ch.amana.android.cputuner.helper.Logger;
import ch.amana.android.cputuner.helper.Notifier;
import ch.amana.android.cputuner.helper.SettingsStorage;
import ch.amana.android.cputuner.receiver.BatteryReceiver;

public class BatteryService extends Service {

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
		if (Logger.DEBUG) {
			Logger.i("Starting BatteryService");
		}
		if (SettingsStorage.getInstance().isStatusbarAddto()) {
			Notifier.startStatusbarNotifications(this);
		} else {
			Notifier.stopStatusbarNotifications();
		}
		BatteryReceiver.registerBatteryReceiver(this);
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		Notifier.stopStatusbarNotifications();
		BatteryReceiver.unregisterBatteryReceiver(this);
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
