package com.example.tuner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;
import be.tarsos.dsp.io.android.AudioDispatcherFactory;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchProcessor;

public class AfghaniRubab extends AppCompatActivity {
    TextView tunedorNOT;
    TextView pitchText;
    TextView noteText;
    TextView resultText;
    RadioGroup radioGroup;
    float pitchInHz;
    RadioButton radioButton;
    int radioID;

    float TARGET_PITCH = 168;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afghani_rubab);
        pitchText = (TextView) findViewById(R.id.pitch);
        noteText = (TextView) findViewById(R.id.note);

        radioGroup = findViewById(R.id.radioGroup);
        resultText = findViewById(R.id.result);
        resultText.setText("E "+ TARGET_PITCH);
        radioID = R.id.radio_one;

        tunedorNOT = findViewById(R.id.tunedOrNot);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio_one) {
                    TARGET_PITCH = 200;
                    resultText.setText("E "+ TARGET_PITCH);
                    radioID = checkedId;
                } else if (checkedId == R.id.radio_two) {
                    TARGET_PITCH = 260;
                    resultText.setText("A "+ TARGET_PITCH);

                    radioID = checkedId;
                } else if (checkedId == R.id.radio_three) {
                    TARGET_PITCH = 340;
                    resultText.setText("E "+ TARGET_PITCH);
                    radioID = checkedId;
                } else if (checkedId == R.id.radio_four) {
                    resultText.setText("B");
                    TARGET_PITCH = 380;
                    radioID = checkedId;
                } else if (checkedId == R.id.radio_five) {
                    resultText.setText("F");
                    TARGET_PITCH = 500;
                    radioID = checkedId;
                } else if (checkedId == R.id.radio_six) {
                    resultText.setText("B");
                    radioID = checkedId;
                }

            }
        });



        AudioDispatcher dispatcher =
                AudioDispatcherFactory.fromDefaultMicrophone(22050, 1024, 0);




        PitchDetectionHandler pdh = new PitchDetectionHandler() {
            @Override
            public void handlePitch(PitchDetectionResult res, AudioEvent e) {
                final float pitchInHz = res.getPitch();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        animateNeedle(pitchInHz);
                        processPitch(pitchInHz);

                    }
                });
            }
        };

        Thread audioThread = new Thread(dispatcher, "Audio Thread");
        audioThread.start();

        AudioProcessor pitchProcessor =
                new PitchProcessor(PitchProcessor.PitchEstimationAlgorithm.FFT_YIN, 22050, 1024, pdh);
        dispatcher.addAudioProcessor(pitchProcessor);

    }
    public void animateNeedle(float currentPitch) {
        ImageView needle = findViewById(R.id.needle);
        int angle = (int) (currentPitch - TARGET_PITCH);
        RotateAnimation animation = new RotateAnimation(angle, 0, Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(500);
        animation.setFillAfter(true);
        needle.startAnimation(animation);

    }

    public void processPitch(float pitchInHz) {

        pitchText.setText(""+pitchInHz);
//        pitchText.setText(String.format("%2.f",pitchInHz));

        if (pitchInHz >= 110 && pitchInHz < 123.47) {
            //A
            noteText.setText("A");
            tunedorNOT.setText("Tune the string");
            if (radioID == R.id.radio_two) {
                tunedorNOT.setText("Second string is tuned");
            }

        } else if (pitchInHz >= 123.47 && pitchInHz < 130.81) {
            //B
            noteText.setText("B");
        } else if (pitchInHz >= 130.81 && pitchInHz < 146.83) {

            noteText.setText("C");
        } else if (pitchInHz >= 146.83 && pitchInHz < 164.81) {

            noteText.setText("D");
        } else if ((pitchInHz >= 164.81 && pitchInHz <= 174.61) && (radioID == R.id.radio_one)){
            noteText.setText("E");
            tunedorNOT.setText("First string is tuned");
        }else if ((pitchInHz >= 164.81 && pitchInHz <= 174.61) && (radioID == R.id.radio_three)){
            noteText.setText("E");
            tunedorNOT.setText("Third string is tuned");
        }
        else if (pitchInHz >= 174.61 && pitchInHz < 185) {

            noteText.setText("F");
            if (radioID == R.id.radio_five) {
                tunedorNOT.setText("Fifth string is tuned");
            }
        } else if (pitchInHz >= 185 && pitchInHz < 196) {
            noteText.setText("G");

        } else if ((pitchInHz >= 214 && pitchInHz < 227) && (radioID == R.id.radio_two)){
            noteText.setText("A1");
            tunedorNOT.setText("Second string is tuned");

        }
        else if (pitchInHz >= -10 && pitchInHz < 10) {
            noteText.setText("Please play any string");
            tunedorNOT.setText("Tune the string");

        }


    }
}