package arewethereyet.chrisli8.washington.edu.arewethereyet;

/**
 * Created by ChrisLi on 3/4/17.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    private String message;
    private String phoneNumber;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        Bundle b = intent.getExtras();
        message = intent.getStringExtra(MainActivity.MESSAGE);
        phoneNumber = intent.getStringExtra(MainActivity.PHONE_NUMBER);

        Log.e("onReceive", "(425) 555-1212: Are we there yet?");
        Toast.makeText(context, phoneNumber + message, Toast.LENGTH_SHORT).show();
    }

}
