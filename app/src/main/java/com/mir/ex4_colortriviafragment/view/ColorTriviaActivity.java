package com.mir.ex4_colortriviafragment.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.mir.ex4_colortriviafragment.R;
import com.mir.ex4_colortriviafragment.callback.TriviaListener;
import com.mir.ex4_colortriviafragment.fragment.TriviaFragment;
import com.mir.ex4_colortriviafragment.model.Question;
import com.mir.ex4_colortriviafragment.offline.TriviaColorsOffline;
import com.mir.ex4_colortriviafragment.service.TriviaColor;

public class ColorTriviaActivity extends AppCompatActivity
        implements View.OnClickListener, TriviaListener {

    private TriviaColor trivia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_trivia);

        findViewById(R.id.skip_btn).setOnClickListener(this);

        // Is our program running in offline mode? YES
        // IF (offline) THEN
        trivia = new TriviaColor(TriviaColorsOffline.colors);
        // ELSE
        // load concepts from API using HTTP GET request

        createTriviaFragment(trivia.nextQuestion());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void createTriviaFragment(Question question) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        TriviaFragment triviaFragment = new TriviaFragment(question);
        transaction.add(R.id.fragment_container, triviaFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void shouldLoadNextQuestion() {
        if (trivia.getNumQuestionsLoaded() < TriviaColorsOffline.maxNumQuestions) {
            createTriviaFragment(trivia.nextQuestion());
        } else {
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.skip_btn) {
            shouldLoadNextQuestion();
        }
    }

    @Override
    public void onQuestionAnswered(String answer) {
        if (answer.equals(trivia.getCurrentCorrectAnswer())) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show();
        }
        shouldLoadNextQuestion();
    }
}
