package com.example.alberto.cucinaitaliana;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class OpenAnswerActivity extends MainActivity {

    static final String DONE = "answerGiven";

    public int done = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_answer);

        final EditText openAnswerView = (EditText) findViewById(R.id.open_answer);
        final TextView correctAnswerView = (TextView) findViewById(R.id.open_answer_correct);
        final TextView questionView = (TextView) findViewById(R.id.question_open);
        questionView.setText(text[0]);

        String textButton = getString(R.string.done_button);

        if (savedInstanceState != null) {
            done = savedInstanceState.getInt(DONE);
            if (done != 0) {
                textButton = getString(R.string.next_button);
                openAnswerView.setEnabled(false);
                if (done == 1){
                    openAnswerView.setBackgroundResource(R.color.correctAnswerDark);
                }
                else {
                    openAnswerView.setBackgroundResource(R.color.wrongAnswerDark);
                    correctAnswerView.setText(text[1]);
                    correctAnswerView.setVisibility(View.VISIBLE);
                }
            }
        }

        final Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setText(textButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (done == 0) {
                    String answer = openAnswerView.getText().toString();
                    if (answer.equalsIgnoreCase(text[1])){
                        done = 1;
                        score+=2;
                        openAnswerView.setBackgroundResource(R.color.correctAnswerDark);
                    }
                    else {
                        done = 2;
                        openAnswerView.setBackgroundResource(R.color.wrongAnswerDark);
                        correctAnswerView.setText(text[1]);
                        correctAnswerView.setVisibility(View.VISIBLE);
                    }
                    openAnswerView.setEnabled(false);
                    nextButton.setText(R.string.next_button);
                } else {
                    if (questionNumber < totalQuestionNumber) {
                        questionNumber++;
                        nextQuestion();
                    }
                }
            }

        });

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(DONE, done);

        super.onSaveInstanceState(savedInstanceState);
    }

}
