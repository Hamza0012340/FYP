package com.example.fyp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;
import be.tarsos.dsp.io.android.AudioDispatcherFactory;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchProcessor;

public class MainActivity extends AppCompatActivity {




    TextView tunedorNOT;
    TextView pitchText;
    TextView noteText;
    TextView resultText;
    RadioGroup radioGroup;
    float pitchInHz;
    RadioButton radioButton;
    int radioID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pitchText = (TextView) findViewById(R.id.pitch);
        noteText = (TextView) findViewById(R.id.note);

        radioGroup = findViewById(R.id.radioGroup);
        resultText = findViewById(R.id.result);

        tunedorNOT = findViewById(R.id.tunedOrNot);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.radio_one){
                    resultText.setText("E");
                    radioID = checkedId;
                }
                else if (checkedId==R.id.radio_two){
                    resultText.setText("E");
                    radioID = checkedId;
                }
                else if (checkedId==R.id.radio_three){
                    resultText.setText("B");
                    radioID = checkedId;
                }
                else if (checkedId==R.id.radio_four){
                    resultText.setText("B");
                    radioID = checkedId;
                }

                else if (checkedId==R.id.radio_five){
                    resultText.setText("F");
                    radioID = checkedId;
                }

                else if (checkedId==R.id.radio_six){
                    resultText.setText("B");
                    radioID = checkedId;
                }

            }
        });
//Using Tarsos to get access to Microphone of the mobile this code was retrieved form Tarsos documentation 
        AudioDispatcher dispatcher =
                AudioDispatcherFactory.fromDefaultMicrophone(22050,1024,0);
        PitchDetectionHandler pdh = new PitchDetectionHandler() {
            @Override
            public void handlePitch(PitchDetectionResult res, AudioEvent e){
                pitchInHz = res.getPitch();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        processPitch(pitchInHz);
                    }
                });
            }
        };
        AudioProcessor pitchProcessor =
        new PitchProcessor(PitchProcessor.PitchEstimationAlgorithm.FFT_YIN, 22050, 1024, pdh);
        dispatcher.addAudioProcessor(pitchProcessor);

        Thread audioThread = new Thread(dispatcher, "Audio Thread");
        audioThread.start();
    }



    public void processPitch(float pitchInHz) {
//        int radioID = radioGroup.getCheckedRadioButtonId();

        pitchText.setText("" + pitchInHz+radioID);

        if(pitchInHz >= 110 && pitchInHz < 123.47) {
            //A
            noteText.setText("A");
        }
        else if(pitchInHz >= 123.47 && pitchInHz < 130.81) {
            //B
            noteText.setText("B");
            tunedorNOT.setText("Tune the string");
            if (radioID == R.id.radio_three){
//                Toast.makeText(this, "Third string is tuned", Toast.LENGTH_SHORT).show();
                tunedorNOT.setText("Third sting is tuned");
            }
            else if(radioID == R.id.radio_four){
//                Toast.makeText(this, "Fourth string is tuned", Toast.LENGTH_SHORT).show();
                tunedorNOT.setText("Fourth string is tuned");

            }
            else if(radioID == R.id.radio_six){
//                Toast.makeText(this, "Sixth string is tuned", Toast.LENGTH_SHORT).show();
                tunedorNOT.setText("Sixth string is tuned");
            }
           // tunedorNOT.setText("Tune the string");
        }
        else if(pitchInHz >= 130.81 && pitchInHz < 146.83) {

            noteText.setText("C");
        }
        else if(pitchInHz >= 146.83 && pitchInHz < 164.81) {

            noteText.setText("D");
        }
        else if(pitchInHz >= 164.81 && pitchInHz <= 174.61) {

            noteText.setText("E");
            tunedorNOT.setText("Tune the string");
            if (radioID == R.id.radio_one){
//                Toast.makeText(this, "First string is tuned ", Toast.LENGTH_SHORT).show();
                tunedorNOT.setText("First string is tuned");
            }
            else if(radioID == R.id.radio_two){
//                Toast.makeText(this, "Second string is tuned", Toast.LENGTH_SHORT).show();
                tunedorNOT.setText("Second string is tuned");
            }
           // tunedorNOT.setText("Tune the string");
        }
        else if(pitchInHz >= 174.61 && pitchInHz < 185) {

            noteText.setText("F");
            tunedorNOT.setText("Tune the string");
            if(radioID == R.id.radio_five){
//                Toast.makeText(this, "Fifth string is tuned ", Toast.LENGTH_SHORT).show();
                tunedorNOT.setText("Fifth string is tuned");
            }
            //tunedorNOT.setText("Tune the string");
        }
        else if(pitchInHz >= 185 && pitchInHz < 196) {

            noteText.setText("G");
        }else if(pitchInHz >= -10 && pitchInHz<10){
            noteText.setText("Please play any string");
        }


    }


}

