package ro.pub.cs.systems.eim.practicaltest01var08;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;



public class PracticalTest01Var08Service extends Service {
    public PracticalTest01Var08Service() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        ProcessingThread thread = new ProcessingThread(this, intent.getStringExtra("ServiceAnswer"));
        thread.start();

        return START_REDELIVER_INTENT;
    }
}
