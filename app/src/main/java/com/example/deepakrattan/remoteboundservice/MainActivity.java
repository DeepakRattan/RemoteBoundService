package com.example.deepakrattan.remoteboundservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnSendMessage;
    Messenger messenger;
    boolean isBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //findViewByID
        btnSendMessage = (Button) findViewById(R.id.btnSend);

        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message message = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putString("message","Message to Remote service from Client");
                message.setData(bundle);
                try {
                    messenger.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        Intent intent = new Intent(MainActivity.this,RemoteService.class);
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);

    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            messenger = new Messenger(iBinder);
            isBound = true;


        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            messenger = null;
            isBound = false;

        }
    };
}
