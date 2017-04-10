package com.example.alberto.cucinaitaliana;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class CheckListActivity extends MainActivity {

    static final String DONE = "questionAnswered";
    static final String COLOR = "answerColor";

    int backgroundColor[] = new int[8];

    final CheckBox[] boxes = new CheckBox[8];

    public boolean done = false;
    int partialScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_list);

        final TextView questionView = (TextView) findViewById(R.id.question_check_list);
        questionView.setText(text[0]);

        boxes[0] = (CheckBox) findViewById(R.id.check_1);
        boxes[1] = (CheckBox) findViewById(R.id.check_2);
        boxes[2] = (CheckBox) findViewById(R.id.check_3);
        boxes[3] = (CheckBox) findViewById(R.id.check_4);
        boxes[4] = (CheckBox) findViewById(R.id.check_5);
        boxes[5] = (CheckBox) findViewById(R.id.check_6);
        boxes[6] = (CheckBox) findViewById(R.id.check_7);
        boxes[7] = (CheckBox) findViewById(R.id.check_8);

        for (int i=0; i<8; i++) {
            boxes[i].setText(text[i+1]);
        }

        String textButton = getString(R.string.done_button);

        if (savedInstanceState != null) {
            done = savedInstanceState.getBoolean(DONE);
            backgroundColor = savedInstanceState.getIntArray(COLOR);
            if (done) {
                textButton = getString(R.string.next_button);
                for (int i = 0; i < 8; i++) {
                    boxes[i].setEnabled(false);
                    if (backgroundColor[i] != 0) {
                        boxes[i].setBackgroundResource(backgroundColor[i]);
                    }
                }
            }
        }

        final Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setText(textButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!done) {
                    for (int i = 0; i < 8; i++) {
                        if (boxes[i].isChecked()) {
                            if (correctAnswer[i] == 1) {
                                backgroundColor[i] = R.color.correctAnswerDark;
                                boxes[i].setBackgroundResource(R.color.correctAnswerDark);
                                partialScore++;
                            } else {
                                backgroundColor[i] = R.color.wrongAnswerDark;
                                boxes[i].setBackgroundResource(R.color.wrongAnswerDark);
                                partialScore--;
                            }
                        }
                        else if (correctAnswer[i] == 1) {
                            backgroundColor[i] = R.color.correctAnswerColor;
                            boxes[i].setBackgroundResource(R.color.correctAnswerColor);
                        }
                        boxes[i].setEnabled(false);
                    }
                    if (partialScore > 0) {
                        score += partialScore;
                    }
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

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean(DONE, done);
        savedInstanceState.putIntArray(COLOR, backgroundColor);

        super.onSaveInstanceState(savedInstanceState);
    }
}
