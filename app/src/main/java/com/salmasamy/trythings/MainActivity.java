package com.salmasamy.trythings;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.AlarmClock;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button plus = (Button) findViewById(R.id.plus);
        plus.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                TextView number = (TextView) findViewById(R.id.number);
                int newNumber = Integer.parseInt(number.getText().toString())+1;
                number.setText(newNumber+"");
            }
        });

        Button minus = (Button) findViewById(R.id.minus);
        minus.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                TextView number = (TextView) findViewById(R.id.number);
                int newNumber = Integer.parseInt(number.getText().toString())-1;
                if (newNumber < 0) newNumber = 0;
                number.setText(newNumber+"");
            }
        });

        Button order = (Button) findViewById(R.id.order);
        order.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                okay();
            }
        });

        Button reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                reset();
            }
        });

    }



    private void reset() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.bombom);
        linearLayout.setVisibility(View.INVISIBLE);
        TextView quantity = (TextView) findViewById(R.id.number);
        quantity.setText("0");
        TextView price = (TextView) findViewById(R.id.pNumber);
        price.setText("$0.00");
        CheckBox cb = (CheckBox) findViewById(R.id.weee);
        cb.setChecked(false);
    }

    private void okay() {

        TextView number = (TextView) findViewById(R.id.number);
        int newNumber = Integer.parseInt(number.getText().toString());
        TextView price = (TextView) findViewById(R.id.pNumber);
        int curPrice = newNumber*5;
        CheckBox weee = (CheckBox) findViewById(R.id.weee);
        if(weee.isChecked())
            curPrice+=1;
        price.setText("$"+curPrice+".00");

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.bombom);
        linearLayout.setVisibility(View.VISIBLE);

    }

    public void email(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, "sal.semony@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "I'm learning android");
        intent.putExtra(Intent.EXTRA_TEXT, "Dear Salma,\n\t keep going please, don't ever give up," +
                "your doing well, I'm proud of you.\n\nRegards,\nSemony");

        //Is there an application on the phone that can handle this request?
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void gmap(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:30.039932, 31.265597"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    public void alarm(View view) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, "Hello from the other side")
                .putExtra(AlarmClock.EXTRA_HOUR, 7)
                .putExtra(AlarmClock.EXTRA_MINUTES, 30);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void photo(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,CAMERA_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ImageView imageView = (ImageView) findViewById(R.id.imageView1);
            imageView.setImageBitmap(photo);
        }
    }

    public void numbersActivity(View view) {
        Intent intent = new Intent(this,NumbersActivity.class);
        startActivity(intent);
    }

    public void fadeAway(View view) {
        Intent intent = new Intent(this,FadeAway.class);
        startActivity(intent);
    }
}
