package com.example.quizzgame.models;

public class Answer {
    private String text;
    private boolean isCorrect;

    public String getText() {
        return text;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public Answer(String text, boolean isCorrect) {
        this.text = text;
        this.isCorrect = isCorrect;
    }
}
