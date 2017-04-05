package com.example.kayttaja.fmradio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.RadioGroup;
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
    private RadioGroup radioGroup;
    private ImageButton iBVolUpBig;
    int kanava1 = 910;
    int kanava2 = 937;
    boolean kanava1Valittu = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = (SeekBar) findViewById(R.id.seekBar1);
        seekbarTxtView = (TextView) findViewById(R.id.seekbarTxt);
        radioBTxtView = (TextView) findViewById(R.id.radioBTxtView);
        radioGroup = (RadioGroup) findViewById(R.id.kanavat);
        iBVolUpBig = (ImageButton) findViewById(R.id.isoNuoliOikealle);

        final int minimiArvo = 870;
        seekBar.setProgress(0);
        seekBar.setMax(1080-870);

        /*
        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                if (checkedId == 1) {

                }
            }
        });
        */



        // Initialize the textview with '0'.
        seekbarTxtView.setText("Covered: " + (seekBar.getProgress() + minimiArvo) + "/" + (seekBar.getMax() + minimiArvo) );


        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue + minimiArvo ; // lisataan arvoon aina minimi
                seekbarTxtView.setText("Covered: " + progress + "/" + (seekBar.getMax() + minimiArvo) );    // muutetaan teksti heti kun arvo muttuu
                Toast.makeText(getApplicationContext(), "Changing seekbar's progress", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                progress += minimiArvo;
                /*
                if (kanava1Valittu) {
                    progress = kanava1;
                    System.out.println(progress);
                } else {
                    progress = kanava2;
                    System.out.println(progress);
                }
                */
                seekbarTxtView.setText("Covered: " + progress + "/" + (seekBar.getMax() + minimiArvo) );    // tulostetaan aloutusarvo +20
                Toast.makeText(getApplicationContext(), "Started tracking seekbar", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekbarTxtView.setText("Covered: " + progress + "/" + (seekBar.getMax() + minimiArvo) );
                Toast.makeText(getApplicationContext(), "Stopped tracking seekbar", Toast.LENGTH_SHORT).show();

                if (kanava1Valittu) {
                    kanava1 = progress;
                    System.out.println(progress);
                } else {
                    kanava2 = progress;
                    System.out.println(progress);
                }

            }
            });
    }

    public void radioB1(View v)
    {
        radioBTxtView.setText("Radio 1");
        System.out.println("Radio 1 painettu");
        kanava1Valittu = true;
        seekBar.setProgress(kanava1-870);
        System.out.println(seekBar.getProgress());
    }

    public void radioB2(View v)
    {
        radioBTxtView.setText("Radio 2");
        System.out.println("Radio 2 painettu");
        kanava1Valittu = false;
        seekBar.setProgress(kanava2-870);
        System.out.println(seekBar.getProgress());
    }

    public void kasvataIsosti(View v)
    {
        // tsekataan että taajuus 87.0-108.0 MHz
        if (seekBar.getProgress() > 1080-870 || seekBar.getProgress() < 0)
        {
            Toast.makeText(getApplicationContext(), "Taajuuden oltava 87.7 ja 108.0 välissä", Toast.LENGTH_SHORT).show();
        } else {
            int progress = seekBar.getProgress() + 10;
            seekBar.setProgress(progress);
            if (kanava1Valittu) {
                kanava1 = progress + 870;
                System.out.println(progress);
            } else {
                kanava2 = progress + 870;
                System.out.println(progress);
            }
        }

    }

}
