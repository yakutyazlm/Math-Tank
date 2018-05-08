package nyc.tanjim.mathshark;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class TimeTrials extends AppCompatActivity {
    TextView whichOneIsCorrect, timeLeftText, scoreText, userFeedback;
    Button button0, button1, button2, button3;
    ImageButton menuButtonTimeTrials;
    ArrayList<String> questions = new ArrayList<String>();
    int locationOfCorrectAnswer, score = 0, numberOfQuestions = 0, onARoll = 0, feedBackNum;
    CountDownTimer countDownTimer;
    Animation correctAnimation, feedBackAnimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_trials);
        whichOneIsCorrect = findViewById(R.id.whichOneIsCorrect);
        timeLeftText = findViewById(R.id.timeLeftText);
        scoreText = findViewById(R.id.scoreText);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        userFeedback = findViewById(R.id.userFeedback);
        menuButtonTimeTrials = findViewById(R.id.menuButtonTimeTrials);
        correctAnimation = AnimationUtils.loadAnimation(this,R.anim.correct_animation);
        feedBackAnimation = AnimationUtils.loadAnimation(this,R.anim.userfeedback_animation);
        generateQuestions();

        countDownTimer = new CountDownTimer(120000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(millisUntilFinished > 10000)
                    timeLeftText.setText(getString(R.string.time_left, (int)millisUntilFinished/1000));
                else
                    timeLeftText.setText(getString(R.string.time_left_ten_less,(int) millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    public String correctEquation(){
        Random rd = new Random();
        int a = rd.nextInt(10)+1;
        int b = rd.nextInt(10)+1;
        switch (rd.nextInt(4)) {
            case 0:
                return String.format(Integer.toString(a) + " + " + Integer.toString(b) + " = " + Integer.toString(a + b));
            case 1:
                a = rd.nextInt(10)+1;
                b = rd.nextInt(10)+a;
                return String.format(Integer.toString(b) + " - " + Integer.toString(a) + " = " + Integer.toString(b - a));
            case 2:
                while(b % a != 0){
                    a = rd.nextInt(10)+1;
                    b = rd.nextInt(10)+a;
                }
                return String.format(Integer.toString(b) + " / " + Integer.toString(a) + " = " + Integer.toString(b / a));
            case 3:
                return String.format(Integer.toString(a) + " x " + Integer.toString(b) + " = " + Integer.toString(a * b));

            default:
                return "Oops something broke";
        }

    }
    public String wrongEquation(){
        Random rd = new Random();
        int a = rd.nextInt(10)+1;
        int b = rd.nextInt(10)+1;

        switch (rd.nextInt(4)) {
            case 0:
                return String.format(Integer.toString(rd.nextInt(10)+1) + " + " + Integer.toString(rd.nextInt(10)+1) + " = " + Integer.toString(rd.nextInt(20)+1));
            case 1:
                a = rd.nextInt(10)+1;
                b = rd.nextInt(10)+a;
                return String.format(Integer.toString(b) + " - " + Integer.toString(rd.nextInt(10)+1) + " = " + Integer.toString(rd.nextInt(10)+1));
            case 2:
                while(b % a != 0){
                    a = rd.nextInt(10)+1;
                    b = rd.nextInt(10)+a;
                }
                return String.format(Integer.toString(b) + " / " + Integer.toString(a) + " = " + Integer.toString(rd.nextInt(10)+1));
            case 3:
                return String.format(Integer.toString(a) + " x " + Integer.toString(b) + " = " + Integer.toString(rd.nextInt(10)+1));

            default:
                return "Oops something broke";
        }

    }
    public void generateQuestions(){
        Random rd = new Random();
        locationOfCorrectAnswer = rd.nextInt(4);
        for(int i = 0; i < 4; i++){
            if(i == locationOfCorrectAnswer){
                questions.add(correctEquation());
            }else {
                questions.add(wrongEquation());
            }

        }
        Random feedbackRd = new Random();
        feedBackNum = feedbackRd.nextInt(8);
        scoreText.setText(getString(R.string.score, score));
        button0.setText(questions.get(0));
        button1.setText(questions.get(1));
        button2.setText(questions.get(2));
        button3.setText(questions.get(3));
        questions.clear();

    }

    public void choose(View view){
        userFeedback.startAnimation(feedBackAnimation);
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            numberOfQuestions++;
            generateQuestions();
            countDownTimer.start();
            onARoll++;
            if(feedBackNum == 0){
                userFeedback.setText(getString(R.string.good_job));
            }else if(feedBackNum == 1){
                userFeedback.setText(getString(R.string.amazing));
            }else if(feedBackNum == 2){
                userFeedback.setText(getString(R.string.fantastic));
            }else if(feedBackNum == 3){
                userFeedback.setText(getString(R.string.damn));
            }else if(feedBackNum == 4){
                userFeedback.setText(getString(R.string.genius));
            }else if(feedBackNum == 5){
                userFeedback.setText(getString(R.string.brilliant));
            }else if(feedBackNum == 6){
                userFeedback.setText(getString(R.string.crazy));
            }else if(feedBackNum == 7){
                userFeedback.setText(getString(R.string.keep));
            }else if(feedBackNum == 8){
                userFeedback.setText(getString(R.string.unbelievable));
            }
        }else {
            onARoll = 0;
            numberOfQuestions++;
            if(locationOfCorrectAnswer == 0){
                button0.startAnimation(correctAnimation);
            }else if(locationOfCorrectAnswer == 1){
                button1.startAnimation(correctAnimation);
            }else if(locationOfCorrectAnswer == 2){
                button2.startAnimation(correctAnimation);
            }else if(locationOfCorrectAnswer == 3){
                button3.startAnimation(correctAnimation);
            }
            Random rd = new Random();
            switch (rd.nextInt(3)){
                case 0:
                    userFeedback.setText(getString(R.string.ohno));
                    break;
                case 1:
                    userFeedback.setText(getString(R.string.next));
                    break;
                case 2:
                    userFeedback.setText(getString(R.string.sad));
                    break;
            }
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    generateQuestions();
                }
            }, 1000);


            /*
            switch (locationOfCorrectAnswer){
                case 0:
                    button0.startAnimation(correctAnimation);
                    break;
                case 1:
                    button1.startAnimation(correctAnimation);
                    break;
                case 2:
                    button2.startAnimation(correctAnimation);
                    break;
                case 3:
                    button3.startAnimation(correctAnimation);
                default:
                    Log.i("Error","Something went wrong");
            }*/
        }
    }

}
