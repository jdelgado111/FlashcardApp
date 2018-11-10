package com.example.janethdelgado.flashcardapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    boolean isShowingOptions = true;
    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
    int currentCardDisplayedIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        allFlashcards = flashcardDatabase.getAllCards();

        if (allFlashcards != null && allFlashcards.size() > 0) {
            ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(0).getAnswer());
        }


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

        findViewById(R.id.addCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });

        findViewById(R.id.editCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = ((TextView)findViewById(R.id.flashcard_question)).getText().toString();
                String answer = ((TextView)findViewById(R.id.flashcard_answer)).getText().toString();

                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                intent.putExtra("question", question);
                intent.putExtra("answer", answer);
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });

        findViewById(R.id.nextCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // advance our pointer index so we can show the next card
                //currentCardDisplayedIndex++;
                currentCardDisplayedIndex = getRandomNumber(0, allFlashcards.size());

                // make sure we don't get an IndexOutOfBoundsError if we are viewing the last indexed card in our list
                if (currentCardDisplayedIndex > allFlashcards.size() - 1)
                    currentCardDisplayedIndex = 0;

                // set the question and answer TextViews with data from the database
                ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());

                findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcard_question).setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.deleteCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashcardDatabase.deleteCard(((TextView)findViewById(R.id.flashcard_question)).getText().toString());
                allFlashcards = flashcardDatabase.getAllCards();

                currentCardDisplayedIndex++;
                if (currentCardDisplayedIndex > allFlashcards.size() - 1)
                    currentCardDisplayedIndex = 0;

                if (allFlashcards.isEmpty()) {
                    String question = "No cards left!";
                    String answer = "Add a card!";

                    ((TextView)findViewById(R.id.flashcard_question)).setText(question);
                    ((TextView)findViewById(R.id.flashcard_answer)).setText(answer);

                    //flashcardDatabase.insertCard(new Flashcard(question, answer));
                    //allFlashcards = flashcardDatabase.getAllCards();
                }
                else {
                    ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                    ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100 && resultCode == RESULT_OK) { // this 100 needs to match the 100 we used when we called startActivityForResult!
            String question = data.getExtras().getString("question"); // 'string1' needs to match the key we used when we put the string in the Intent
            String answer = data.getExtras().getString("answer");
            String option1 = data.getExtras().getString("option1");
            String option2 = data.getExtras().getString("option2");

            ((TextView)findViewById(R.id.flashcard_question)).setText(question);
            ((TextView)findViewById(R.id.flashcard_answer)).setText(answer);

            if (!option1.matches("") && !option2.matches("")) {
                ((TextView)findViewById(R.id.flashcard_option1)).setText(option1);
                ((TextView)findViewById(R.id.flashcard_option2)).setText(answer);
                ((TextView)findViewById(R.id.flashcard_option3)).setText(option2);
            }

            Snackbar.make(findViewById(android.R.id.content), "Card successfully created", Snackbar.LENGTH_LONG).show();

            flashcardDatabase.insertCard(new Flashcard(question, answer));
            allFlashcards = flashcardDatabase.getAllCards();
        }
    }

    public int getRandomNumber(int minNumber, int maxNumber){
        Random rand = new Random();
        return rand.nextInt((maxNumber - minNumber) + 1) + minNumber;
    }
}
