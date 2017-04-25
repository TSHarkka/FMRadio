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

    public SeekBar seekBar;
    public TextView seekbarTxtView;
    public TextView radioBTxtView;
    public RadioGroup radioGroup;
    public ImageButton iBVolUpBig;
    public int kanava1 = 910-870;
    public int kanava2 = 937-870;
    public int kanava3 = 937-870;
    public boolean kanava1Valittu = false;
    public String kanava="";

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
        seekbarTxtView.setText((double)(seekBar.getProgress() + minimiArvo)/10 + " MHz" );


        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue + minimiArvo ; // lisataan arvoon aina minimi
                seekbarTxtView.setText((double)progress/10 + " MHz" );    // muutetaan teksti heti kun arvo muttuu
                //Toast.makeText(getApplicationContext(), "Changing seekbar's progress", Toast.LENGTH_SHORT).show();
                System.out.println("SEEKBAR AKTION 1");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                progress += minimiArvo; // miniarvo aina koskettaessa lsiättävä lukuun.
                /*
                if (kanava1Valittu) {
                    progress = kanava1;
                    System.out.println(progress);
                } else {
                    progress = kanava2;
                    System.out.println(progress);
                }
                */
                seekbarTxtView.setText((double)progress/10 + " MHz" );    // tulostetaan aloutusarvo +20
                //Toast.makeText(getApplicationContext(), "Started tracking seekbar", Toast.LENGTH_SHORT).show();
                System.out.println("SEEKBAR AKTION 2");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekbarTxtView.setText((double)progress/10 + " MHz" );
                //Toast.makeText(getApplicationContext(), "Stopped tracking seekbar", Toast.LENGTH_SHORT).show();

                if (kanava1Valittu) {
                    if (kanava == "CH1") {
                        kanava1 = progress-870;
                    } else if (kanava == "CH2") {
                        kanava2 = progress-870;
                    } else {
                        kanava3 = progress-870;
                    }

                    System.out.println(progress);
                }
                System.out.println("SEEKBAR AKTION 3");

            }
            });
    }

    public void radioB1(View v)
    {
        radioBTxtView.setText("Radio 1");
        System.out.println("                           !!!!!!!!!!!!!   Radio 1 painettu  "   + kanava1);
        kanava1Valittu = true;
        kanava = "CH1";
        System.out.println("                           !!!!!!!!!!!!!   Radio 1 painettu  "   + kanava1);
        seekBar.setProgress(kanava1); // huomioidaan minimiarvo
        System.out.println(seekBar.getProgress());
        System.out.println("                           !!!!!!!!!!!!!   Radio 1 tallennettu  "   + kanava1);

    }

    public void radioB2(View v)
    {
        radioBTxtView.setText("Radio 2");
        System.out.println("                           !!!!!!!!!!!!!   Radio 2 painettu  "   + kanava2);
        kanava1Valittu = true;
        kanava = "CH2";
        seekBar.setProgress(kanava2);
        System.out.println(seekBar.getProgress());
    }

    public void radioB3(View v)
    {
        radioBTxtView.setText("Radio 3");
        System.out.println("                           !!!!!!!!!!!!!   Radio 3 painettu  "   + kanava3);
        kanava1Valittu = true;
        kanava = "CH3";
        seekBar.setProgress(kanava3);
        System.out.println(seekBar.getProgress());
    }

    public void radioOff(View v)
    {
        radioBTxtView.setText("Radio 3");
        System.out.println("Radio off painettu");
        kanava1Valittu = false;
        kanava="";
    }

    public void kasvataIsosti(View v)
    {
        // tsekataan että taajuus 87.0-108.0 MHz
        if (seekBar.getProgress() >= 1080-870)
        {
            seekBar.setProgress(1080-870);
            Toast.makeText(getApplicationContext(), "Taajuuden oltava 87.7 ja 108.0 välissä", Toast.LENGTH_SHORT).show();
        } else {
            System.out.println("!!!!!!!!!!!!!!         SEEKBAR PROGRESS   !!!!!!                  " + seekBar.getProgress() );
            int progress = seekBar.getProgress() + 10;
            seekBar.setProgress(progress);
            if (kanava1Valittu) {
                if (kanava == "CH1") {
                    this.kanava1 = progress;
                    System.out.println("!!!!!!!!!!!!!!         KANAVA 1 PROGRESS   !!!!!!                  " + kanava1 );

                    System.out.println("ch1 " + kanava1);
                } else if (kanava == "CH2") {
                    this.kanava2 = progress;
                    System.out.println("ch2" + kanava2);
                } else {
                    this.kanava3 = progress;
                    System.out.println("ch3" + kanava3);
                }
            }
        }

    }

    public void pienennaIsosti(View v)
    {
        // tsekataan että taajuus 87.0-108.0 MHz
        if (seekBar.getProgress() <= 0)
        {
            seekBar.setProgress(0);
            Toast.makeText(getApplicationContext(), "Taajuuden oltava 87.7 ja 108.0 välissä", Toast.LENGTH_SHORT).show();
        } else {
            System.out.println("!!!!!!!!!!!!!!         SEEKBAR PROGRESS   !!!!!!                  " + seekBar.getProgress() );
            int progress = seekBar.getProgress() - 10;
            seekBar.setProgress(progress);
            if (kanava1Valittu) {
                if (kanava == "CH1") {
                    this.kanava1 = progress;
                    System.out.println("!!!!!!!!!!!!!!         KANAVA 1 PROGRESS   !!!!!!                  " + kanava1 );

                    System.out.println("ch1 " + kanava1);
                } else if (kanava == "CH2") {
                    this.kanava2 = progress;
                    System.out.println("ch2" + kanava2);
                } else {
                    this.kanava3 = progress;
                    System.out.println("ch3" + kanava3);
                }
            }
        }

    }

    public void pienennaPienesti(View v)
    {
        // tsekataan että taajuus 87.0-108.0 MHz
        if (seekBar.getProgress() <= 0)
        {
            seekBar.setProgress(0);
            Toast.makeText(getApplicationContext(), "Taajuuden oltava 87.7 ja 108.0 välissä", Toast.LENGTH_SHORT).show();
        } else {
            System.out.println("!!!!!!!!!!!!!!         SEEKBAR PROGRESS   !!!!!!                  " + seekBar.getProgress() );
            int progress = seekBar.getProgress() - 1;
            seekBar.setProgress(progress);
            if (kanava1Valittu) {
                if (kanava == "CH1") {
                    this.kanava1 = progress;
                    System.out.println("!!!!!!!!!!!!!!         KANAVA 1 PROGRESS   !!!!!!                  " + kanava1 );

                    System.out.println("ch1 " + kanava1);
                } else if (kanava == "CH2") {
                    this.kanava2 = progress;
                    System.out.println("ch2" + kanava2);
                } else {
                    this.kanava3 = progress;
                    System.out.println("ch3" + kanava3);
                }
            }
        }

    }

    public void kasvataPienesti(View v)
    {
        // tsekataan että taajuus 87.0-108.0 MHz
        if (seekBar.getProgress() >= 1080-780)
        {
            seekBar.setProgress(1080-780);
            Toast.makeText(getApplicationContext(), "Taajuuden oltava 87.7 ja 108.0 välissä", Toast.LENGTH_SHORT).show();
        } else {
            System.out.println("!!!!!!!!!!!!!!         SEEKBAR PROGRESS   !!!!!!                  " + seekBar.getProgress() );
            int progress = seekBar.getProgress() + 1;
            seekBar.setProgress(progress);
            if (kanava1Valittu) {
                if (kanava == "CH1") {
                    this.kanava1 = progress;
                    System.out.println("!!!!!!!!!!!!!!         KANAVA 1 PROGRESS   !!!!!!                  " + kanava1 );

                    System.out.println("ch1 " + kanava1);
                } else if (kanava == "CH2") {
                    this.kanava2 = progress;
                    System.out.println("ch2" + kanava2);
                } else {
                    this.kanava3 = progress;
                    System.out.println("ch3" + kanava3);
                }
            }
        }

    }

}
