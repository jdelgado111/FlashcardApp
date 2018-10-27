package com.example.janethdelgado.flashcardapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

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

                Intent data = new Intent(); // create a new Intent to put our data
                data.putExtra("question", question); // puts one string into the Intent (key, string)
                data.putExtra("answer", answer); // puts another string into the Intent (key, string)
                setResult(RESULT_OK, data); // set result code and bundle data for response
                finish(); // closes this activity and pass data to the original activity that launched this activity
            }
        });
    }
}
