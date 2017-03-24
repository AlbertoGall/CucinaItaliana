package com.example.alberto.cucinaitaliana;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Arrays;

public class MultipleChoiceActivity extends MainActivity {

    final RadioButton[] answerView = new RadioButton[4];

    public boolean done = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_choice);

        final TextView questionView = (TextView) findViewById(R.id.question_multiple);
        questionView.setText(MainActivity.text[0]);

        answerView[0] = (RadioButton) findViewById(R.id.answers_1);
        answerView[1] = (RadioButton) findViewById(R.id.answers_2);
        answerView[2] = (RadioButton) findViewById(R.id.answers_3);
        answerView[3] = (RadioButton) findViewById(R.id.answers_4);

        for (int i=0; i<4; i++) {
            answerView[i].setText(MainActivity.text[i+1]);
        }

        final Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setText(R.string.done_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (questionNumber < totalQuestionNumber && done) {
                    questionNumber++;
                    nextQuestion();
                }
                else if (!done){
                    for (int i=0; i < 4; i++){
                        answerView[i].setEnabled(false);
                    }
                    answerView[correctAnswer[0]].setBackgroundResource(R.color.correctAnswerColor);
                    nextButton.setText(R.string.next_button);
                    done = true;
                }
            }
        });

        for (RadioButton radioButton:answerView) {
            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    nextButton.setText(R.string.next_button);
                    done = true;
                    RadioButton answer = (RadioButton) view;
                    for (int i=0; i < 4; i++){
                        answerView[i].setEnabled(false);
                    }
                    if (Arrays.asList(answerView).indexOf(answer) == correctAnswer[0]) {
                        answer.setBackgroundResource(R.color.correctAnswerDark);
                        score++;
                    }
                    else {
                        answer.setBackgroundResource(R.color.wrongAnswerDark);
                        answerView[correctAnswer[0]].setBackgroundResource(R.color.correctAnswerColor);
                    }
                }
            });
        }
    }

}