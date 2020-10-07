package com.mir.ex4_colortriviafragment.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mir.ex4_colortriviafragment.R;
import com.mir.ex4_colortriviafragment.callback.TriviaListener;
import com.mir.ex4_colortriviafragment.model.Question;

import java.util.LinkedList;
import java.util.List;

public class TriviaFragment extends Fragment implements View.OnClickListener {

    private ImageView questionIV;
    private List<Button> buttons = new LinkedList<>();
    private Question currentQuestion;

    public TriviaFragment(Question question) {
        this.currentQuestion = question;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_trivia, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        questionIV = view.findViewById(R.id.question_iv);

        buttons.add((Button) view.findViewById(R.id.first_btn));
        buttons.add((Button) view.findViewById(R.id.second_btn));
        buttons.add((Button) view.findViewById(R.id.third_btn));
        buttons.add((Button) view.findViewById(R.id.fourth_btn));

        for (Button button : buttons) {
            button.setOnClickListener(this);
        }

        updateView(currentQuestion);
    }

    @SuppressWarnings("ConstantConditions")
    private void updateView(Question question) {
        questionIV.setImageResource(
                getResources().getIdentifier(
                        question.getText(),
                        "drawable",
                        getActivity().getPackageName()
                )
        );
        question.shuffleAlternatives();
        for (int i = 0; i < 4; i++) {
            buttons.get(i).setText(question.getAlternatives().get(i));
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        ((TriviaListener) getActivity()).onQuestionAnswered(button.getText().toString());
    }
}
