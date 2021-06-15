package com.blum.votesystem;

import java.util.LinkedList;

public class AnswerList {
    private LinkedList<Long> answers;

    public void setAnswers(LinkedList<Long> answers) {
        this.answers = answers;
    }

    public LinkedList<Long> getAnswers() {
        return answers;
    }
}
