package com.example.alberto.cucinaitaliana;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Arrays;

public class MultipleChoiceActivity extends MainActivity {

    static final String DONE = "questionAnswered";
    static final String COLOR = "answerColor";

    public int[] backgroundColor = new int[4];

    final RadioButton[] answerView = new RadioButton[4];


    public boolean done = false;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_choice);

        final TextView questionView = (TextView) findViewById(R.id.question_multiple);
        questionView.setText(MainActivity.text[0]);

        answerView[0] = (RadioButton) findViewById(R.id.answers_1);
        answerView[1] = (RadioButton) findViewById(R.id.answers_2);
        answerView[2] = (RadioButton) findViewById(R.id.answers_3);
        answerView[3] = (RadioButton) findViewById(R.id.answers_4);

        String textButton = getString(R.string.done_button);

        for (int i = 0; i < 4; i++) {
            answerView[i].setText(MainActivity.text[i + 1]);
        }

        if (savedInstanceState != null) {
            done = savedInstanceState.getBoolean(DONE);
            backgroundColor = savedInstanceState.getIntArray(COLOR);
            if (done) {
                textButton = getString(R.string.next_button);
                for (int i = 0; i < 4; i++) {
                    answerView[i].setEnabled(false);
                    if (backgroundColor[i] != 0) {
                        answerView[i].setBackgroundResource(backgroundColor[i]);
                    }
                }
            }
        }

        final Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setText(textButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (questionNumber < totalQuestionNumber && done) {
                    questionNumber++;
                    nextQuestion();
                } else if (!done) {
                    for (int i = 0; i < 4; i++) {
                        answerView[i].setEnabled(false);
                    }
                    backgroundColor[correctAnswer[0]] = R.color.correctAnswerColor;
                    answerView[correctAnswer[0]].setBackgroundResource(R.color.correctAnswerColor);
                    nextButton.setText(R.string.next_button);
                    done = true;
                }
            }
        });

        for (RadioButton radioButton : answerView) {
            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    nextButton.setText(R.string.next_button);
                    done = true;
                    RadioButton answer = (RadioButton) view;
                    for (int i = 0; i < 4; i++) {
                        answerView[i].setEnabled(false);
                    }
                    int answerIndex = Arrays.asList(answerView).indexOf(answer);
                    if (answerIndex == correctAnswer[0]) {
                        backgroundColor[answerIndex] = R.color.correctAnswerDark;
                        answer.setBackgroundResource(R.color.correctAnswerDark);
                        score++;
                    } else {
                        backgroundColor[answerIndex] = R.color.wrongAnswerDark;
                        answer.setBackgroundResource(R.color.wrongAnswerDark);
                        backgroundColor[correctAnswer[0]] = R.color.correctAnswerColor;
                        answerView[correctAnswer[0]].setBackgroundResource(R.color.correctAnswerColor);
                    }
                }
            });
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean(DONE, done);
        savedInstanceState.putIntArray(COLOR, backgroundColor);

        super.onSaveInstanceState(savedInstanceState);
    }

}