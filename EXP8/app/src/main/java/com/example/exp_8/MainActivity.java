package com.example.exp_8;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button playBtn, nextBtn, prevBtn;
    SeekBar seekBar;
    TextView textView;
    ImageView imageView;

    MediaPlayer mediaPlayer;

    int currentSong = 0;

    int songs[] = {R.raw.song1, R.raw.song2};
    String names[] = {"Song 1", "Song 2"};
    int images[] = {R.drawable.image1, R.drawable.image2};

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playBtn = findViewById(R.id.playBtn);
        nextBtn = findViewById(R.id.nextBtn);
        prevBtn = findViewById(R.id.prevBtn);

        seekBar = findViewById(R.id.seekBar);
        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);

        loadSong();

        playBtn.setOnClickListener(v -> {

            if(mediaPlayer.isPlaying()){
                mediaPlayer.pause();
                playBtn.setText("Play");
            }
            else{
                mediaPlayer.start();
                playBtn.setText("Pause");
            }

        });

        nextBtn.setOnClickListener(v -> {

            mediaPlayer.stop();
            mediaPlayer.release();

            currentSong++;

            if(currentSong == songs.length){
                currentSong = 0;
            }

            loadSong();

        });

        prevBtn.setOnClickListener(v -> {

            mediaPlayer.stop();
            mediaPlayer.release();

            currentSong--;

            if(currentSong < 0){
                currentSong = songs.length - 1;
            }

            loadSong();

        });

    }

    void loadSong(){

        mediaPlayer = MediaPlayer.create(this, songs[currentSong]);

        textView.setText(names[currentSong]);
        imageView.setImageResource(images[currentSong]);

        mediaPlayer.start();

        playBtn.setText("Pause");

        seekBar.setMax(mediaPlayer.getDuration());

        updateSeekBar();

    }

    void updateSeekBar(){

        seekBar.setProgress(mediaPlayer.getCurrentPosition());

        if(mediaPlayer.isPlaying()){

            Runnable runnable = new Runnable() {
                @Override
                public void run() {

                    updateSeekBar();

                }
            };

            handler.postDelayed(runnable, 1000);

        }

    }

}