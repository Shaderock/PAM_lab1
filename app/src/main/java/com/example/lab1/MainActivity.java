package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    final static int REQUEST_IMAGE_CAPTURE = 1;
    private Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b_notify = findViewById(R.id.b_send_not);

        b_notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                final Notification notify = new Notification.Builder(getApplicationContext())
                        .setContentText("It's time to dance!")
                        .setContentTitle("What time is it?")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .build();
                notify.flags |= Notification.FLAG_AUTO_CANCEL;
//                if (nm != null) {
//                    nm.notify(0, notify);
//                }

                new CountDownTimer(10000, 1000) {

                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
//                        if (nm != null) {
//                            nm.cancel(0);
//                        }
                        if (nm != null) {
                            nm.notify(0, notify);
                        }
                    }
                }.start();
            }
        });

        Button b_search = findViewById(R.id.b_search);
        final EditText search_key = findViewById(R.id.search_key);

        b_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key_word = search_key.getText().toString();
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, key_word);
                startActivity(intent);
            }
        });

        Button b_launch = findViewById(R.id.b_launch_cam);
        final RadioGroup choice_cam = findViewById(R.id.choice);
        final TextView textView = findViewById(R.id.no_choice);

        b_launch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int decision = choice_cam.getCheckedRadioButtonId();
                if (decision != -1) {
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    switch (decision) {
                        case R.id.front_cam:
                            takePictureIntent.putExtra("android.intent.extras.CAMERA_FACING", 1);
                            break;
                        case R.id.back_cam:
                            takePictureIntent.putExtra("android.intent.extras.CAMERA_FACING", 0);
                            break;
                        default:
                            break;
                    }
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                    textView.setText("");
                } else
                    textView.setText("Please, make choice");
            }
        });

        Button ch_activ = findViewById(R.id.ch_activ);
        ch_activ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ImageCapture.class);
                intent.putExtra("bitmap", imageBitmap);
                startActivity(intent);
            }
        });

        Button ch_to_calc = findViewById(R.id.calc);
        ch_to_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Calculator.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            imageBitmap = (Bitmap) data.getExtras().get("data");
        }
    }

}
