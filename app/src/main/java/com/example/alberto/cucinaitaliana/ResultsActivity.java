package com.example.alberto.cucinaitaliana;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.alberto.cucinaitaliana.MainActivity.score;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_activity);

        String scoreString = score + "/14";

        TextView scoreText = (TextView) findViewById(R.id.score_text);
        scoreText.setText(scoreString);

        Button resetButton = (Button) findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.questionNumber = 0;
                MainActivity.score = 0;
                Intent i=new Intent(ResultsActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}