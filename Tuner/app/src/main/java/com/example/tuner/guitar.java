package com.example.tuner;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
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


public class guitar extends BaseActivity {
    TextView tunedorNOT;
    TextView sosText;
    TextView pitchText;
    TextView noteText;
    TextView prevNote;
    TextView nextNote;

    String wavecolor = "red";
    TextView resultText;
    RadioGroup radioGroup;
    float pitchInHz;
    RadioButton radioButton;
    int radioID;

    int TARGET_PITCH = 330;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guitar);
        TextView actionBarTitle = (TextView) findViewById(R.id.action_bar_title);
        actionBarTitle.setText(R.string.guitar);


        pitchText = (TextView) findViewById(R.id.pitch);
        noteText = (TextView) findViewById(R.id.note);
        prevNote = (TextView) findViewById(R.id.prevNote);
        nextNote = (TextView) findViewById(R.id.nextNote);
        sosText =(TextView)findViewById(R.id.sos);

        radioGroup = findViewById(R.id.radioGroup);
        resultText = findViewById(R.id.result);
        resultText.setText("E "+ TARGET_PITCH);
        radioID = R.id.radio_one;

        tunedorNOT = findViewById(R.id.tunedOrNot);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio_one) {
                    TARGET_PITCH = 330;
                    resultText.setText("E "+ TARGET_PITCH);
                    radioID = checkedId;
                } else if (checkedId == R.id.radio_two) {
                    TARGET_PITCH = 247;
                    resultText.setText("B "+ TARGET_PITCH);

                    radioID = checkedId;
                } else if (checkedId == R.id.radio_three) {
                    TARGET_PITCH = 196;
                    resultText.setText("G "+ TARGET_PITCH);

                    radioID = checkedId;
                } else if (checkedId == R.id.radio_four) {
                    TARGET_PITCH = 147;
                    resultText.setText("D "+ TARGET_PITCH);
                    radioID = checkedId;
                } else if (checkedId == R.id.radio_five) {
                    TARGET_PITCH = 110;
                    resultText.setText("A "+ TARGET_PITCH);
                    radioID = checkedId;
                } else if (checkedId == R.id.radio_six) {
                    TARGET_PITCH = 82;
                    resultText.setText("E "+ TARGET_PITCH);
                    radioID = checkedId;
                }

            }
        });



        AudioDispatcher dispatcher =
                AudioDispatcherFactory.fromDefaultMicrophone(22050, 1024, 0);

        final WaveformView waveformView = findViewById(R.id.waveformView);



        PitchDetectionHandler pdh = new PitchDetectionHandler() {
            @Override
            public void handlePitch(PitchDetectionResult res, AudioEvent e) {
                final float pitchInHz = res.getPitch();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        animateNeedle(pitchInHz);
                        processPitch(pitchInHz,TARGET_PITCH);
                    }
                });
            }
        };

        Thread audioThread = new Thread(dispatcher, "Audio Thread");
        audioThread.start();

        ///
        AudioProcessor audioProcessor = new AudioProcessor() {
            @Override
            public boolean process(AudioEvent audioEvent) {
                float[] audioData = audioEvent.getFloatBuffer().clone();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        waveformView.updateAudioData(audioData,wavecolor);
                    }
                });
                return true;
            }

            @Override
            public void processingFinished() {
            }
        };
        dispatcher.addAudioProcessor(audioProcessor);

        ///

        AudioProcessor pitchProcessor =
                new PitchProcessor(PitchProcessor.PitchEstimationAlgorithm.FFT_YIN, 22050, 1024, pdh);
        dispatcher.addAudioProcessor(pitchProcessor);

    }


    public void animateNeedle(float currentPitch) {
        ImageView needle = findViewById(R.id.needle);
        int angle = (int) (currentPitch - TARGET_PITCH);

        // Limit the angle to the range between -90 and 90 degrees
        angle = Math.max(-90, Math.min(angle, 90));

        RotateAnimation animation = new RotateAnimation(angle,
                0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.7f);
        animation.setDuration(5000);
        animation.setInterpolator(new LinearInterpolator());
        animation.setFillAfter(true);
        needle.startAnimation(animation);
    }






    public void processPitch(float pitchInHz , int TARGET_PITCH) {

        pitchText.setText(String.format("%.2f", pitchInHz));
//
//        if (Math.abs(pitchInHz - TARGET_PITCH) <= 10) {
//            wavecolor = "green";
//        } else {
//            wavecolor = "red";
//        }

        float difference = Math.abs(pitchInHz - TARGET_PITCH);
        int maxDifference = 10; // Maximum difference for full green color

        // Calculate the percentage of difference
        double differencePercentage = (double) difference / maxDifference;

        // Calculate the red and green components based on the difference percentage
        int green = (int) (255 * (1 - differencePercentage));
        int red = (int) (255 * differencePercentage);

        wavecolor = String.format("#%02x%02x%02x", red, green, 0); // Format the RGB color value



//        pitchText.setText(String.format("%2.f",pitchInHz));

        if (pitchInHz >= 110 && pitchInHz < 123.47) {
            //A
//            noteText.setText("A");


        } else if ((pitchInHz >= 123.47 && pitchInHz < 130.81) && ((radioID == R.id.radio_three) || (radioID == R.id.radio_four)||(radioID==R.id.radio_six) )) {
            //B
//            noteText.setText("B");
            tunedorNOT.setText("Tune the string");
            if (radioID==R.id.radio_three){
                tunedorNOT.setText("Third string is tuned");
            }else if(radioID==R.id.radio_four){
                tunedorNOT.setText("Fourth string is tuned");
            } else if (radioID == R.id.radio_six) {
                tunedorNOT.setText("Sixth string is tuned");
            }
        } else if (pitchInHz >= 130.81 && pitchInHz < 146.83) {

//            noteText.setText("C");
        } else if (pitchInHz >= 146.83 && pitchInHz < 164.81) {

//            noteText.setText("D");
        } else if ((pitchInHz >= 164.81 && pitchInHz <= 174.61) && ((radioID == R.id.radio_one)||(radioID == R.id.radio_two))){
            noteText.setText("E");
            tunedorNOT.setText("Tune the string");
            if (radioID==R.id.radio_one){
                tunedorNOT.setText("First string is tuned");

            }else if(radioID==R.id.radio_two) {
                tunedorNOT.setText("Second string is tuned");
            }
        }else if ((pitchInHz >= 164.81 && pitchInHz <= 174.61) && (radioID == R.id.radio_three)){
//            noteText.setText("E");
            tunedorNOT.setText("Third string is tuned");
        }
        else if ((pitchInHz >= 174.61 && pitchInHz < 185) && (radioID == R.id.radio_five)) {
//            noteText.setText("F");
            tunedorNOT.setText("Tune the string");
            tunedorNOT.setText("Fifth string is tuned");

        } else if (pitchInHz >= 185 && pitchInHz < 196) {
            noteText.setText("G");

        }
        else if (pitchInHz >= -10 && pitchInHz < 10) {
            noteText.setText("Please play any string");
            tunedorNOT.setText("Tune the string");

        }
        else if(pitchInHz > 300){
            sosText.setText(R.string.alert);
            tunedorNOT.setText("MAKE SURE RIGHT STRING IS SELECTED");
        }
        else if (pitchInHz<300){
            sosText.setText("");

        }

        String[] notes = {"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"};
        int noteIndex = (int) Math.round(12 * (Math.log(Math.abs(pitchInHz) / 440) / Math.log(2))) % 12;
        if (noteIndex < 0) {
            noteIndex += 12;
        }
        noteText.setText(notes[noteIndex]);

        int prevNoteIndex = noteIndex - 1;
        if (prevNoteIndex < 0) {
            prevNoteIndex += 12;
        }
        prevNote.setText(notes[prevNoteIndex]);

        int nextNoteIndex = (noteIndex + 1) % 12;
        nextNote.setText(notes[nextNoteIndex]);

    }

}