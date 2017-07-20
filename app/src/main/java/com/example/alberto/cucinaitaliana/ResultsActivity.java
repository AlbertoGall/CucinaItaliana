package com.example.alberto.cucinaitaliana;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ResultsActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_activity);

        String toastText = "Congratulation, you completed the quiz with a score of " + score + "/14";
        Toast resultToast = Toast.makeText(getApplicationContext(), toastText, Toast.LENGTH_SHORT);
        LinearLayout layout = (LinearLayout) resultToast.getView();
        if (layout.getChildCount() > 0) {
            TextView toastView = (TextView) layout.getChildAt(0);
            toastView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        }
        resultToast.setGravity(Gravity.CENTER,0,0);
        resultToast.show();

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