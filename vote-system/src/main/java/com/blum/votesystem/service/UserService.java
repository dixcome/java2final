package com.blum.votesystem.service;



import com.blum.votesystem.AnswerList;
import com.blum.votesystem.models.Answer;
import com.blum.votesystem.models.Role;
import com.blum.votesystem.models.User;
import com.blum.votesystem.repository.AnswerRepository;
import com.blum.votesystem.repository.RoleRepository;
import com.blum.votesystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;
import java.util.concurrent.CompletableFuture;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private AnswerRepository answerRepo;



    @Async
    @Transactional
    public void userUpdate(String firstname, String lastname, String groupname, int age, String interests, String email){
        User user = userRepo.findByEmail(email);
        Long id = user.getId();


        if (firstname != ""){
            userRepo.updateFirstname(id,firstname);
        }
        if (lastname != ""){
            userRepo.updateLastname(id,lastname);
        }
        if (groupname != ""){
            userRepo.updateGroupName(id,groupname);
        }
        if (age != 0){
            userRepo.updateAge(id, age);
        }
        if (interests != ""){
            userRepo.updateInterests(id,interests);
        }


    }

    @Async
    public CompletableFuture<User> getUser(String email){
        User user = userRepo.findByEmail(email);

        return CompletableFuture.completedFuture(user);
    }

    @Async
    public CompletableFuture<ArrayList<User>> getUserList(){
        ArrayList<User> list = new ArrayList<>();

        for (User user: userRepo.findAll()) {
            list.add(user);
        }
        return CompletableFuture.completedFuture(list);
    }

    @Async
    public CompletableFuture<User> getUser(long id){
        User user = userRepo.getOne(id);
        return CompletableFuture.completedFuture(user);
    }

    @Async
    @Transactional
    public void updateUserRoles(long id, String roleName){
        User user = userRepo.getOne(id);
        Role role = roleRepo.findByName(roleName);
        List<Role> list = new LinkedList<Role>(Arrays.asList(role));
        user.setRoles(list);
        userRepo.save(user);
    }

    @Async
    @Transactional
    public void assignAnswers(AnswerList answerList, String email){
        User user = userRepo.findByEmail(email);
        Collection<Answer> collection = user.getAnswers();
        collection.clear();

        int i = 0;
        for (Answer answer: answerRepo.findAll()) {
            if (answer.getId() == answerList.getAnswers().get(i)){
                collection.add(answer);
            }
            i++;
        }

        userRepo.save(user);

    }




}
