package com.blum.votesystem.models;


import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id")
    private Collection<Answer> answers;

    public Question(){
        super();
    }

    public Question(final String text){
        this.text = text;
    }

    public void setAnswers(Collection<Answer> answers) {
        this.answers = answers;
    }

    public ArrayList<Answer> getAnswers() {
        ArrayList<Answer> newList = new ArrayList<>(answers);
        return newList;
    }

    public void addAnswer(Answer answer){
        answers.add(answer);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
