package com.vineet.cliqueraft;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

        Button validbutton, notibutton, popupbutton, textbutton;
        EditText notemptytxt;

        TextView t1;
         AlertDialog.Builder ab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        validbutton = findViewById(R.id.checkbutton1);
        notibutton = findViewById(R.id.checkbutton2);
        popupbutton = findViewById(R.id.checkbutton3);
        textbutton = findViewById(R.id.checkbutton4);


        notemptytxt = findViewById(R.id.editText);


        t1 = findViewById(R.id.textcomplete);

        validbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String TEXT = notemptytxt.getText().toString();
                if(TEXT.isEmpty()){
                    Toast.makeText(MainActivity.this, "Empty field not allowed", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Thank you..." + TEXT, Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Generate Notification
        notibutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String TEXT = notemptytxt.getText().toString();
                if(TEXT.isEmpty()){
                    Toast.makeText(MainActivity.this, "Empty field not allowed", Toast.LENGTH_SHORT).show();
                }
                else{
                    createNoti();
                    }
                }
        });

        popupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String TEXT = notemptytxt.getText().toString();
                if(TEXT.isEmpty()){
                    Toast.makeText(MainActivity.this, "Empty field not allowed", Toast.LENGTH_SHORT).show();
                }
                else{
                    ab = new AlertDialog.Builder(MainActivity.this);

                    ab.setTitle("POP UP");
                    ab.setIcon(R.drawable.ic_launcher_background);
                    ab.setMessage(Html.fromHtml("<font color='#ff0000'>Welcome " + TEXT + "</font>"));
                   // ab.setMessage("welcome" + TEXT);
                    ab.setCancelable(false);

                    ab.setNegativeButton("DISMISS", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    ab.show();
                }
            }
        });

        textbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.setText("COMPLETED !!!");
            }
        });

    }


    private void createNoti(){
        String TEXT = notemptytxt.getText().toString();
        String id = "noti_id";

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Intent intent1 = new Intent(MainActivity.this, Extra_activity.class);

        intent1.putExtra("DISMISS", true);

        PendingIntent pendingIntent1 = PendingIntent.getActivity(MainActivity.this,0, intent1, PendingIntent.FLAG_ONE_SHOT);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = manager.getNotificationChannel(id);
            if(channel ==null){
                channel = new NotificationChannel(id, "Title", NotificationManager.IMPORTANCE_HIGH);

                channel.setDescription("Description");
                channel.enableVibration(true);
                channel.setVibrationPattern(new long[]{100, 1000, 200, 340});
                channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
                manager.createNotificationChannel(channel);
            }
        }
         Intent notiIntent = new Intent(this, Extra_activity.class);
        notiIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

      PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notiIntent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, id)
                .setContentTitle("This is notification")
                .setContentText("Hello " + TEXT)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVibrate(new long[]{100,1000,200,340})
                .setAutoCancel(false)
                .setTicker("Notification")
                .addAction(R.drawable.ic_launcher_foreground,"DISMISS", pendingIntent1)
                .setContentIntent(contentIntent);

        NotificationManagerCompat m = NotificationManagerCompat.from(getApplicationContext());
        m.notify(new Random().nextInt(), builder.build());

    }

}