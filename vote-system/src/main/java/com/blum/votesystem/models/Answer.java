package com.blum.votesystem.models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;

    @ManyToMany(mappedBy = "answers", fetch = FetchType.EAGER)
    private Collection<User> users;


    public Answer(){
        super();
    }

    public Answer(final String text){
        super();
        this.text = text;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public void setText(String text) {
        this.text = text;
    }



    public Collection<User> getUsers() {
        return users;
    }

    public String getText() {
        return text;
    }


    public Long getId() {
        return id;
    }
}
