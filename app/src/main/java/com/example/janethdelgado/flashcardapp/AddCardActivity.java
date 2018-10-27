package com.example.janethdelgado.flashcardapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        String question = getIntent().getStringExtra("question");
        String answer = getIntent().getStringExtra("answer");

        ((EditText) findViewById(R.id.questionText)).setText(question);
        ((EditText) findViewById(R.id.answerText)).setText(answer);

        findViewById(R.id.cancelCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.saveCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = ((EditText) findViewById(R.id.questionText)).getText().toString();
                String answer = ((EditText) findViewById(R.id.answerText)).getText().toString();
                String option1 = ((EditText) findViewById(R.id.option1Text)).getText().toString();
                String option2 = ((EditText) findViewById(R.id.option2Text)).getText().toString();
                if (question.matches("") || answer.matches("")) {
                    Toast.makeText(getApplicationContext(), "Must enter a question and answer!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent data = new Intent(); // create a new Intent to put our data
                data.putExtra("question", question); // puts one string into the Intent (key, string)
                data.putExtra("answer", answer); // puts another string into the Intent (key, string)
                data.putExtra("option1", option1);
                data.putExtra("option2", option2);
                setResult(RESULT_OK, data); // set result code and bundle data for response
                finish(); // closes this activity and pass data to the original activity that launched this activity
            }
        });
    }
}
