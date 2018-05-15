package com.ibm.mobile.jsonstoreofflinesync.activities;

import android.app.AlarmManager;
import android.app.Application;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

// {{mobilefoundationIncludes}}
import com.ibm.mobile.jsonstoreofflinesync.background.JSONStoreAlarmReceiver;
import com.ibm.mobile.jsonstoreofflinesync.manager.FlightAttendantManager;
import com.worklight.wlclient.api.WLClient;


/**
 * Created by abhilash_m on 02/03/18.
 */

//This class will be called on the start of the application
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        //Registering the network connectivity filter
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkReceiver, filter);
        super.onCreate();
        //Creating an instance of WLClient
        WLClient.createInstance(MainApplication.this);
        try {
            FlightAttendantManager.getInstance().initJsonStoreCollections(this);
            scheduleAlarm(1);
        } catch (Exception e) {
            Log.d("Error:","error in initjsonstorecollections"+e.getStackTrace());
        }

    }

    //This object holds needed information about mobile network connectivity
    private BroadcastReceiver networkReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() == ConnectivityManager.CONNECTIVITY_ACTION) {
                NetworkInfo networkInfo = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
                //If network connection is identified, then post a toast notification stating "Connected"
                if (networkInfo != null && networkInfo.getDetailedState() == NetworkInfo.DetailedState.CONNECTED) {
                    Log.d("Internet", "We have internet connection. Good to go.");
                    //An upstream sync is performed on connection
                    FlightAttendantManager.getInstance().upstreamsync();
                    Toast.makeText(getApplicationContext(), "Synced With Server ", Toast.LENGTH_SHORT).show();
                }
                //If network is disconnected, theN post a toast notification stating "Disconnected"
                else if (networkInfo != null && networkInfo.getDetailedState() == NetworkInfo.DetailedState.DISCONNECTED) {
                    Log.d("Internet", "We have lost internet connection");
                    Toast.makeText(getApplicationContext(), "Disconnected ", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    // Setup a recurring alarm based on the timeout interval provided by the user
    private void scheduleAlarm(int timeInterval) {
            // Construct an intent that will execute the AlarmReceiver
            Intent intent = new Intent(MainApplication.this, JSONStoreAlarmReceiver.class);
            // Create a PendingIntent to be triggered when the alarm goes off
            final PendingIntent pIntent = PendingIntent.getBroadcast(MainApplication.this, JSONStoreAlarmReceiver.REQUEST_CODE,
                    intent, PendingIntent.FLAG_UPDATE_CURRENT);
            // Setup periodic alarm every every half hour from this point onwards
            long firstMillis = System.currentTimeMillis(); // alarm is set right away
            AlarmManager alarm = (AlarmManager) MainApplication.this.getSystemService(Context.ALARM_SERVICE);
            // First parameter is the type: ELAPSED_REALTIME, ELAPSED_REALTIME_WAKEUP, RTC_WAKEUP
            // Interval can be INTERVAL_FIFTEEN_MINUTES, INTERVAL_HALF_HOUR, INTERVAL_HOUR, INTERVAL_DAY
            alarm.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    SystemClock.elapsedRealtime(),
                    timeInterval*1000,pIntent);
        }


}
