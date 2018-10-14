package com.example.janethdelgado.flashcardapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    boolean isShowingOptions = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.flashcard_question).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_answer).setVisibility(View.VISIBLE);
                findViewById(R.id.flashcard_question).setVisibility(View.INVISIBLE);
            }
        });

        findViewById(R.id.flashcard_answer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcard_question).setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.flashcard_option1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_option1).setBackgroundColor(getResources().getColor(R.color.wrongAnswer));
                findViewById(R.id.flashcard_option2).setBackgroundColor(getResources().getColor(R.color.rightAnswer));
            }
        });

        findViewById(R.id.flashcard_option2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_option2).setBackgroundColor(getResources().getColor(R.color.rightAnswer));
            }
        });

        findViewById(R.id.flashcard_option3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_option3).setBackgroundColor(getResources().getColor(R.color.wrongAnswer));
                findViewById(R.id.flashcard_option2).setBackgroundColor(getResources().getColor(R.color.rightAnswer));
            }
        });

        findViewById(R.id.toggle_options_visibility).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShowingOptions == true)
                {
                    ((ImageView) findViewById(R.id.toggle_options_visibility)).setImageResource(R.drawable.view_icon);
                    findViewById(R.id.flashcard_option1).setVisibility(View.INVISIBLE);
                    findViewById(R.id.flashcard_option2).setVisibility(View.INVISIBLE);
                    findViewById(R.id.flashcard_option3).setVisibility(View.INVISIBLE);
                    isShowingOptions = false;
                }
                else
                {
                    ((ImageView) findViewById(R.id.toggle_options_visibility)).setImageResource(R.drawable.hide_icon);
                    findViewById(R.id.flashcard_option1).setVisibility(View.VISIBLE);
                    findViewById(R.id.flashcard_option2).setVisibility(View.VISIBLE);
                    findViewById(R.id.flashcard_option3).setVisibility(View.VISIBLE);
                    isShowingOptions = true;
                }
            }
        });

    }
}
