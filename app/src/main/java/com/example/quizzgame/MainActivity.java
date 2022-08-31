package com.example.quizzgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizzgame.models.Answer;
import com.example.quizzgame.models.Question;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView timerTextView, questionTextView, pointsTextView;
    private Button answer1Button, answer2Button, answer3Button;
    private int points = 0;
    private int questionIndex = 0;
    private List<Question> allQuestions;
    private ProgressBar timerProgressBar;
    public static final int COUNT_DOWN_INTERVAL = 1000;
    public static final int TOTAL_TIME = 30000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerTextView = findViewById(R.id.timerTextView);
        questionTextView = findViewById(R.id.questionTextView);
        answer1Button = findViewById(R.id.answer1Button);
        answer2Button = findViewById(R.id.answer2Button);
        answer3Button = findViewById(R.id.answer3Button);
        timerProgressBar = findViewById(R.id.timeProgressBar);

        allQuestions = Question.getAllQuestions();
        displayQuestion(allQuestions.get(questionIndex));
        startTimer();
    }

    private void startTimer() {
        //   int countDownInterval = 1000;
        CountDownTimer countDownTimer = new CountDownTimer(TOTAL_TIME, COUNT_DOWN_INTERVAL) {
            @Override
            public void onTick(long millisUntilFinish) {
                int progress = (int)(millisUntilFinish * 100.0 / TOTAL_TIME);
                Log.i("Timer bug", "Time remaining"+millisUntilFinish);
                Log.i("Timer bug", ""+millisUntilFinish*100.0/TOTAL_TIME);
                Log.i("Progress", ""+progress);
                timerProgressBar.setProgress(progress);

                timerTextView.setText(getString(R.string.time_with_placeholder, progress));
            }

            @Override
            public void onFinish() {
gameOver();
            }
        };
        countDownTimer.start();
    }

    private void doNothing(String... args){}

    private void displayQuestion(Question question) {
        questionTextView.setText(question.getText());
        answer1Button.setText(question.getAnswer1().getText());
        answer2Button.setText(question.getAnswer2().getText());
        answer3Button.setText(question.getAnswer3().getText());

        answer1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswerCorrect(question.getAnswer1(), question.getPoints());
            }
        });
        answer2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswerCorrect(question.getAnswer2(), question.getPoints());
            }
        });
        answer3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswerCorrect(question.getAnswer3(), question.getPoints());
            }
        });
    }

    private void checkAnswerCorrect(Answer answer, int points) {

        if (answer.isCorrect()) {
            this.points+=points;
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show();
        }
        updatePoints();
        questionIndex++;
        if (questionIndex == allQuestions.size()) {
            gameOver();
        } else {
            displayQuestion(allQuestions.get(questionIndex));

        }
    }

    private void updatePoints() {
        pointsTextView.setText(getString(R.string.points_with_placeholder, points));
    }

    private void gameOver() {
        Toast.makeText(this, "Game over", Toast.LENGTH_SHORT).show();
    }
}