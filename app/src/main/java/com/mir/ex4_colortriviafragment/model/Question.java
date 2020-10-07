package com.mir.ex4_colortriviafragment.model;

import java.util.Collections;
import java.util.List;

import lombok.Data;

@Data
public class Question {

    private String text;
    private String correctAnswer;
    private List<String> alternatives;

    public void shuffleAlternatives() {
        Collections.shuffle(this.alternatives);
    }
}
