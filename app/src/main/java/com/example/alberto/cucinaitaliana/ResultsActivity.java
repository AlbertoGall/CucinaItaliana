package com.example.alberto.cucinaitaliana;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends MainActivity {

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
                MainActivity.questionNumber = -1;
                MainActivity.score = 0;
                Intent i=new Intent(ResultsActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}