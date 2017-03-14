package com.example.kayttaja.fmradio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;
import android.media.AudioManager;
import android.media.ToneGenerator;


public class MainActivity extends AppCompatActivity {

    private SeekBar seekBar;
    private TextView seekbarTxtView;
    private TextView radioBTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = (SeekBar) findViewById(R.id.seekBar1);
        seekbarTxtView = (TextView) findViewById(R.id.seekbarTxt);
        radioBTxtView = (TextView) findViewById(R.id.radioBTxtView);

        final double minimiArvo = 87.0;
        seekBar.setProgress(0);
        seekBar.setMax(108);


        // Initialize the textview with '0'.
        seekbarTxtView.setText("Covered: " + (seekBar.getProgress() + minimiArvo) + "/" + (seekBar.getMax() + minimiArvo) );

        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            double progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue + minimiArvo ; // lisataan arvoon aina minimi
                seekbarTxtView.setText("Covered: " + progress + "/" + (seekBar.getMax() + minimiArvo) );    // muutetaan teksti heti kun arvo muttuu
                Toast.makeText(getApplicationContext(), "Changing seekbar's progress", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                progress += minimiArvo;
                seekbarTxtView.setText("Covered: " + progress + "/" + (seekBar.getMax() + minimiArvo) );    // tulsotetaan aloutusarvo +20
                Toast.makeText(getApplicationContext(), "Started tracking seekbar", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekbarTxtView.setText("Covered: " + progress + "/" + (seekBar.getMax() + minimiArvo) );
                Toast.makeText(getApplicationContext(), "Stopped tracking seekbar", Toast.LENGTH_SHORT).show();
            }
            });
    }

    public void radioB1(View v)
    {
        radioBTxtView.setText("Radio 1");
        System.out.println("Radio 1 painettu");
    }

    public void radioB2(View v)
    {
        radioBTxtView.setText("Radio 2");
        System.out.println("Radio 2 painettu");
    }
}
