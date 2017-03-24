package com.example.alberto.cucinaitaliana;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class OpenAnswerActivity extends MainActivity {

    public boolean done = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_answer);

        final TextView questionView = (TextView) findViewById(R.id.question_open);
        questionView.setText(text[0]);

        final Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!done) {
                    final EditText openAnswerView = (EditText) findViewById(R.id.open_answer);
                    String answer = openAnswerView.getText().toString();
                    if (answer.equalsIgnoreCase(text[1])){
                        score+=2;
                        openAnswerView.setBackgroundResource(R.color.correctAnswerDark);
                    }
                    else {
                        openAnswerView.setBackgroundResource(R.color.wrongAnswerDark);
                        final TextView correctAnswerView = (TextView) findViewById(R.id.open_answer_correct);
                        correctAnswerView.setText(text[1]);
                        correctAnswerView.setVisibility(View.VISIBLE);
                    }
                    openAnswerView.setEnabled(false);
                    done = true;
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

}
