package com.example.nisrina.task_12;

/**
 * Created by nisrina on 1/5/16.
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.widget.Toast;


public class BatteryStatus extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        String strAction;
        if(action == Intent.ACTION_BATTERY_LOW){
            strAction = "ACTION_BATTERY_LOW";
        }else if(action == Intent.ACTION_BATTERY_OKAY){
            strAction = "ACTION_BATTERY_OKAY";
        }else if(action == Intent.ACTION_POWER_CONNECTED){
            strAction = "ACTION_POWER_CONNECTED";
        }else if(action == Intent.ACTION_POWER_DISCONNECTED){
            strAction = "ACTION_POWER_DISCONNECTED";
        }else{
            strAction = "unknown!";
        }


        int status;

        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, ifilter);
        status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);


        String msg;
        switch(status){
            case BatteryManager.BATTERY_STATUS_CHARGING:
                msg = "status = BATTERY_STATUS_CHARGING\n";
                break;
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                msg = "status = BATTERY_STATUS_DISCHARGING\n";
                break;
            case BatteryManager.BATTERY_STATUS_FULL:
                msg = "status = BATTERY_STATUS_FULL\n";
                break;
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                msg = "status = BATTERY_STATUS_NOT_CHARGING\n";
                break;
            case BatteryManager.BATTERY_STATUS_UNKNOWN:
                msg = "status = BATTERY_STATUS_UNKNOWN\n";
                break;
            default:
                msg = "status = ...unknown!\n";
        }

        int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        if(chargePlug == BatteryManager.BATTERY_PLUGGED_USB){
            msg += "Plaugged USB\n";
        }
        if(chargePlug == BatteryManager.BATTERY_PLUGGED_AC){
            msg += "Plaugged AC\n";
        }

        Toast.makeText(
                context,
                "BatteryStatus: " + action + "\n"
                        + intent.toString()
                        + strAction + "\n"
                        + "\n"
                        + status + " : " + msg,
                Toast.LENGTH_LONG).show();
    }
}

