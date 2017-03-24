package com.example.alberto.cucinaitaliana;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static int score = 0;
    public static int questionNumber = 0;
    public static int[] correctAnswer = new int[8];
    public static String[] text;
    final public static int totalQuestionNumber = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start = (Button) findViewById(R.id.start_button);
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nextQuestion();
            }
        });
    }

    public void nextQuestion(){
        Intent nextQuestion;
        switch (questionNumber) {
            case 0: nextQuestion = new Intent(this, MultipleChoiceActivity.class);
                text = getResources().getStringArray(R.array.multiple_1);
                correctAnswer[0] = 1;
                startActivity(nextQuestion);
                break;
            case 1: nextQuestion = new Intent(this, MultipleChoiceActivity.class);
                text = getResources().getStringArray(R.array.multiple_2);
                correctAnswer[0] = 3;
                startActivity(nextQuestion);
                break;
            case 2: nextQuestion = new Intent(this, CheckListActivity.class);
                text = getResources().getStringArray(R.array.check_list_1);
                correctAnswer = getResources().getIntArray(R.array.correct_open_answer_1);
                startActivity(nextQuestion);
                break;
            case 3: nextQuestion = new Intent(this, OpenAnswerActivity.class);
                text = getResources().getStringArray(R.array.open_1);
                startActivity(nextQuestion);
                break;
            case 4: nextQuestion = new Intent(this, MultipleChoiceActivity.class);
                text = getResources().getStringArray(R.array.multiple_3);
                correctAnswer[0] = 0;
                startActivity(nextQuestion);
                break;
            case 5: nextQuestion = new Intent(this, CheckListActivity.class);
                text = getResources().getStringArray(R.array.check_list_2);
                correctAnswer = getResources().getIntArray(R.array.correct_open_answer_2);
                startActivity(nextQuestion);
                break;
            case 6: nextQuestion = new Intent(this, MultipleChoiceActivity.class);
                text = getResources().getStringArray(R.array.multiple_4);
                correctAnswer[0] = 1;
                startActivity(nextQuestion);
                break;
            default: nextQuestion = new Intent(this, ResultsActivity.class);
                startActivity(nextQuestion);
                break;
        }
    }

    @Override
    public void onBackPressed(){
        if (questionNumber > 0) {
            questionNumber--;
        }
        super.onBackPressed();
    }
}