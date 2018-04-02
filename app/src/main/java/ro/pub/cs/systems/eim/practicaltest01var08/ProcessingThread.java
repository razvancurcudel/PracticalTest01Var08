package ro.pub.cs.systems.eim.practicaltest01var08;

import android.app.Service;
import android.content.Intent;
import android.util.Log;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Razvan on 02-Apr-18.
 */

class ProcessingThread extends Thread {

    public boolean running;
    private Service service;
    String answer;

    ProcessingThread(Service service, String answer) {
        this.service = service;
        this.running = false;
        this.answer = answer;

        Log.i("[PRACTICAL_TEST]", "answer");
    }

    void broadcast(String text) {
        Intent intent = new Intent();
        intent.setAction(Constants.ACTION);
        intent.putExtra("Hint ", text);
        service.sendBroadcast(intent);
    }

    public void run() {
        running = true;
        Random rand = new Random();
        while (running) {
            char[] chars = new char[answer.length()];
            Arrays.fill(chars, '*');
            int index = rand.nextInt(answer.length());
            chars[index] = answer.charAt(index);
            String text = new String(chars);

            Log.i("[PRACTICAL_TEST]", "sending text " + text);

            broadcast(text);
            sleep();
        }
    }

    public void sleep()
    {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stopRun() {
        running = false;
    }

}

