package com.example.geoquiz;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mQuestionTextView;
    private Button trueButton, falseButton;
    private ImageButton nextButton, backButton;
    private Questions[] mQuestionBank = new Questions[] {
            new Questions(R.string.question_australia, true),
            new Questions(R.string.question_oceans, true),
            new Questions(R.string.question_mideast, false),
            new Questions(R.string.question_africa, false),
            new Questions(R.string.question_americas, true),
            new Questions(R.string.question_asia, true),
    };
    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionTextView = (TextView) findViewById(R.id.mQuestionTextView);
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);


        trueButton = (Button) findViewById(R.id.true_button);
        trueButton.setOnClickListener(this);

        falseButton = (Button) findViewById(R.id.false_button);
        falseButton.setOnClickListener(this);

        nextButton = (ImageButton) findViewById(R.id.next_button);
        nextButton.setOnClickListener(this);

        backButton = (ImageButton) findViewById(R.id.back_button);
        backButton.setOnClickListener(this);

        updateQuestion();
    }

    // Implement the OnClickListener callback
    public void onClick(View v) {
        if (v.getId() == R.id.true_button) {
            System.out.println("True");
            checkAnswer(true);

        } else if (v.getId() == R.id.false_button) {
            System.out.println("False");
            checkAnswer(false);

        } else if (v.getId() == R.id.next_button) {
            System.out.println("Next");

            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
            int question = mQuestionBank[mCurrentIndex].getTextResId();
            mQuestionTextView.setText(question);

        } else if (v.getId() == R.id.back_button) {
            System.out.println("Back");

            mCurrentIndex = mCurrentIndex - 1;
            int question = mQuestionBank[mCurrentIndex].getTextResId();
            mQuestionTextView.setText(question);
        }
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;
        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show();
    }
}