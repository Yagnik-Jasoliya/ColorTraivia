package com.mir.ex4_colortriviafragment.service;

import com.mir.ex4_colortriviafragment.model.Question;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class TriviaColor {

    private Collection<String> concepts;
    private Deque<Question> questionsBuffer = new ArrayDeque<>();
    private String currentCorrectAnswer;
    private int numQuestionsLoaded = 0;

    public TriviaColor(Collection<String> concepts) {
        this.concepts = new LinkedList<>(concepts);
    }

    private void reloadQuestionsBuffer() {
        List<String> colors = new LinkedList<>(concepts);
        Collections.shuffle(colors);
        for (String color : colors) {
            Question question = new Question();
            question.setText(color);
            question.setCorrectAnswer(color);
            List<String> alternatives = new LinkedList<>(colors);
            alternatives.remove(color);
            Collections.shuffle(alternatives);
            question.setAlternatives(alternatives.subList(0, 3));
            question.getAlternatives().add(color);
            questionsBuffer.push(question);
        }
    }

    public Question nextQuestion() {
        if (questionsBuffer.size() == 0) {
            reloadQuestionsBuffer();
        }
        Question question = questionsBuffer.pop();
        currentCorrectAnswer = question.getCorrectAnswer();
        numQuestionsLoaded += 1;
        return question;
    }

    public String getCurrentCorrectAnswer() {
        return currentCorrectAnswer;
    }

    public int getNumQuestionsLoaded() {
        return numQuestionsLoaded;
    }
}
