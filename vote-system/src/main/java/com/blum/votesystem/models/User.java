package com.blum.votesystem.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String groupName;
    private String interests;
    private boolean enabled;
    private int age;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    @ManyToMany
    @JoinTable(
            name = "users_answers",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "answer_id", referencedColumnName = "id"))
    private Collection<Answer> answers;

    public void setId(Long id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAnswers(Collection<Answer> answers) {
        this.answers = answers;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public Collection<Answer> getAnswers() {
        return answers;
    }

    public Long getId() {
        return id;
    }

    @Column(name = "email",unique = true, nullable = false)
    public String getEmail() {
        return email;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    @Column(name = "age", nullable = false)
    public int getAge() {
        return age;
    }

    @Column(name = "firstname", nullable = false, length = 30)
    public String getFirstname() {
        return firstname;
    }


    @Column(name = "group_name", nullable = false, length = 30)
    public String getGroupName() {
        return groupName;
    }


    @Column(name = "lastname", nullable = false, length = 30)
    public String getLastname() {
        return lastname;
    }

    @Column(name = "interests", nullable = false, length = 100)
    public String getInterests() {
        return interests;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", groupName='" + groupName + '\'' +
                ", age=" + age +
                '}';
    }
}
