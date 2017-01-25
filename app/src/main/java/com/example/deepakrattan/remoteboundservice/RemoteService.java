package com.example.deepakrattan.remoteboundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by deepak.rattan on 1/24/2017.
 */

public class RemoteService extends Service {

    Messenger messenger = new Messenger(new IncomingHandler());

    public RemoteService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

    //Incoming messages handler
    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //Getting message from Client
            Bundle bundle = msg.getData();
            String message = bundle.getString("message");
            Toast.makeText(RemoteService.this, message, Toast.LENGTH_LONG).show();
        }
    }


}
