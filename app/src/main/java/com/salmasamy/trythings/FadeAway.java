package com.salmasamy.trythings;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class FadeAway extends AppCompatActivity {

    MediaPlayer mP;
    AudioManager audioManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fade_away);

        mP = MediaPlayer.create(this, R.raw.the_end_of_august);
        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        SeekBar volumeControl = (SeekBar)findViewById(R.id.volume);
        volumeControl.setMax(maxVolume);
        volumeControl.setProgress(curVolume);

        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

        });

        final SeekBar scrubber = (SeekBar) findViewById(R.id.seeking);
        scrubber.setMax(mP.getDuration());

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                scrubber.setProgress(mP.getCurrentPosition());
            }
        }, 0, 300);


        scrubber.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mP.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

    }

    public void FadeAway(View view) {
        ImageView img = (ImageView) findViewById(R.id.imageView2);
        img.animate().alpha(0f).setDuration(2000);

        //img.animate().xBy(1000f).setDuration(2000);

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setVisibility(View.INVISIBLE);
    }

    public void play(View view) {
        if(!mP.isPlaying()){
            mP.start();
        }
    }

    public void stop(View view) {
        if(mP.isPlaying()){
            mP.stop();
        }
    }
}
