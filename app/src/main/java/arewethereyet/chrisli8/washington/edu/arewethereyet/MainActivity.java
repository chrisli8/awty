package arewethereyet.chrisli8.washington.edu.arewethereyet;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    public static final String MESSAGE = "Message";
    public static final String PHONE_NUMBER = "PhoneNumber";
    private static final String TAG = "MainActivity";


    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;
    public Button start;
    public boolean started = false;

    public EditText editNumber;
    public EditText editText;
    public EditText editDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (Button) findViewById(R.id.btnStart);

        editDuration = (EditText) findViewById(R.id.editDuration);
        editText = (EditText) findViewById(R.id.editText);
        editNumber = (EditText) findViewById(R.id.txtPhoneNumber);

        start.setOnClickListener(new MyListener(editNumber, editText, editDuration));

        editDuration.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence c, int i, int j, int k) {

            }

            @Override
            public void onTextChanged(CharSequence c, int i, int j, int k) {

            }

            @Override
            public void afterTextChanged(Editable edt) {
                if (edt.length() == 1 && edt.toString().equals("0"))
                    editDuration.setText("");
            }

            // ...
        });

    }

    private class MyListener implements View.OnClickListener {
        public EditText numberET;
        public EditText messageET;
        public EditText durationET;

        public MyListener(EditText number, EditText text, EditText duration) {
            this.numberET = number;
            this.messageET = text;
            this.durationET = duration;
        }

        @Override
        public void onClick(View view) {
            // numberET = (EditText)findViewById(R.id.txtPhoneNumber);
            String phoneNumber = numberET.getText().toString();
            String message = messageET.getText().toString();

            int duration = 1;
            //EditText durationET = (EditText) findViewById(R.id.editDuration);
            if (durationET.getText().length() > 0) {
                duration = Integer.parseInt(durationET.getText().toString());
            }

            Log.e(TAG, "phone number: " + phoneNumber + " end");
            if (!started) { // for debug -> should be !started
                started = !started;
                start.setText("Stop");
                Context context = getApplicationContext();
                alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(context, AlarmReceiver.class);
                intent.putExtra(MainActivity.MESSAGE, message);
                intent.putExtra(MainActivity.PHONE_NUMBER, phoneNumber);
                alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

                alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                        SystemClock.elapsedRealtime() + duration * 1000,
                        duration * 1000, alarmIntent);
            } else {
                started = !started;
                start.setText("Start");
            }
        }
    }

//    public void onClick(View view) {
//        if (!started) {
//            started = !started;
//            start.setText("Stop");
//            Context context = this.getApplicationContext();
//            alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//            Intent intent = new Intent(context, AlarmReceiver.class);
//            alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
//
//            alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
//                    SystemClock.elapsedRealtime() + 5 * 1000,
//                    5 * 1000, alarmIntent);
//        } else {
//            started = !started;
//            start.setText("Start");
//        }

        // Fires once
//        alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
//        Intent intent = new Intent(context, AlarmReceiver.class);
//        alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
//
//        alarmMgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
//                SystemClock.elapsedRealtime() +
//                        5 * 1000, alarmIntent);
//    }
}
