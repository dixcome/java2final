package com.blum.votesystem.controllers;

import com.blum.votesystem.AnswerList;
import com.blum.votesystem.component.IAuthenticationFacade;
import com.blum.votesystem.models.Answer;
import com.blum.votesystem.models.Question;
import com.blum.votesystem.models.Role;
import com.blum.votesystem.models.User;
import com.blum.votesystem.service.QuestionAnswerService;
import com.blum.votesystem.service.RoleService;
import com.blum.votesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Controller
public class AppController {

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionAnswerService questionAnswerService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private IAuthenticationFacade authenticationFacade;



    @GetMapping("/answerassign")
    public RedirectView assignAnswers(@RequestParam(name="question") long questionId,
                                      @RequestParam(name="answer") long answerId
                                      ) {

        System.out.println(questionId);
        System.out.println(answerId);
        questionAnswerService.assignAnswer(questionId,answerId);

        return new RedirectView("questionsanswers");
    }

    @GetMapping("/answerassignform")
    public String showMyQuestionsAndAnswers(Model model) {
        try {
            ArrayList<Question> questionsList = questionAnswerService.getQuestionList().get();
            ArrayList<Answer> answerslList = questionAnswerService.getAnswerList().get();
            model.addAttribute("questionList",questionsList);
            model.addAttribute("answersList", answerslList);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return "forms/answer-assign";
    }

    @GetMapping("/questionsanswers")
    public String showMyQuestions(Model model) {
        try {
            ArrayList<Question> questionsList = questionAnswerService.getQuestionList().get();
            model.addAttribute("questionList",questionsList);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return "menus/question-answer";
    }

    @GetMapping("/answers")
    public String showAnswers(Model model) {
        try {
            ArrayList<Answer> answerList = questionAnswerService.getAnswerList().get();
            model.addAttribute("answerList",answerList);

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return "menus/answer";
    }

    @GetMapping("/profile")
    public String showMyUser(Model model) {
        try {
            String email = getCurrentUsername().get();
            User user = userService.getUser(email).get();
            model.addAttribute("user",user);


        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return "profile";
    }

    @GetMapping("/users")
    public String showUsers(Model model) {
        try {
            ArrayList<User> userList = userService.getUserList().get();

            model.addAttribute("userList",userList);


        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return "menus/user";
    }


    @GetMapping("/questionupdateform")
    public String showUpdateQuestion(@RequestParam(name="questionId") long questionId, Model model) {
        model.addAttribute("questionId",questionId);
        return "forms/question-update";
    }

    @GetMapping( "/questioncreate")
    public RedirectView createQuestion(@RequestParam(name="text") String text) {
        questionAnswerService.addQuestion(text);
        return new RedirectView("questionsanswers");
    }

    @GetMapping("/questionupdate")
    public RedirectView updateQuestion(@RequestParam(name="text") String text,
                                       @RequestParam(name="questionId") long questionId) {
        questionAnswerService.updateQuestion(questionId,text);
        return new RedirectView("questionsanswers");
    }

    @GetMapping("/questiondelete")
    public RedirectView deleteQuestion(@RequestParam(name="questionId") long questionId) {
        questionAnswerService.deleteQuestion(questionId);
        return new RedirectView("questionsanswers");
    }


    @GetMapping( "/answercreate")
    public RedirectView createAnswer(@RequestParam(name="text") String text) {
        questionAnswerService.addAnswer(text);
        return new RedirectView("answers");
    }

    @GetMapping("/answerupdateform")
    public String showUpdateAnswer(@RequestParam(name="answerId") long answerId, Model model) {
        model.addAttribute("answerId",answerId);
        return "forms/answer-update";
    }

    @GetMapping("/answerupdate")
    public RedirectView updateAnswer(@RequestParam(name="text") String text,
                                       @RequestParam(name="answerId") long answerId) {
        questionAnswerService.updateAnswer(answerId,text);
        return new RedirectView("answers");
    }

    @GetMapping("/answerdelete")
    public RedirectView deleteAnswer(@RequestParam(name="answerId") long answerId) {
        questionAnswerService.deleteAnswer(answerId);
        return new RedirectView("answers");
    }

    @GetMapping("/userupdate")
    public RedirectView userUpdate(@RequestParam(name="firstname") String firstname,
                           @RequestParam(name="lastname") String lastname,
                           @RequestParam(name="groupname") String groupName,
                           @RequestParam(name="age") String age,
                           @RequestParam(name="interests") String interests
                           ) {
        try {
        int ageInt;


            String email = getCurrentUsername().get();

        if (age != "") {
            ageInt = Integer.parseInt(age);
        }else {
            ageInt = 0;
        }
        userService.userUpdate(firstname, lastname, groupName, ageInt, interests, email);

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }


        return new RedirectView("profile");
    }

    @GetMapping("/updateuser")
    public RedirectView updateUser(@RequestParam(name="userId") long userId,
                                   @RequestParam(name="role") String roleName){

        userService.updateUserRoles(userId,roleName);
        return new RedirectView("users");
    }

    @GetMapping("/userupdateform")
    public String showUpdateUser(@RequestParam(name="userId") long userId, Model model) {
        try {

            User user = userService.getUser(userId).get();
            ArrayList<Role> roleList = roleService.getAllRoles().get();
            model.addAttribute("roleList",roleList);
            model.addAttribute("userId",userId);
            model.addAttribute("user",user);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return "forms/user-update";
    }

    @GetMapping("/voteform")
    public String showVoteForm(Model model){
        ArrayList<Question> questionsList = null;
        try {
            questionsList = questionAnswerService.getQuestionList().get();
            model.addAttribute("questionList",questionsList);
            model.addAttribute("list",new AnswerList());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return "menus/vote";
    }

    @GetMapping("/voteprocess")
    public RedirectView voteProcess(@ModelAttribute AnswerList answerList){

        try {
            String email = getCurrentUsername().get();
            userService.assignAnswers(answerList, email);

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return new RedirectView("home");
    }

    @GetMapping("/home")
    public String showHome(Model model){

        try {
            ArrayList<Question> questionList = questionAnswerService.getQuestionList().get();
            model.addAttribute("questionList",questionList);

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }


        return "home";
    }

    @Async
    public CompletableFuture<String> getCurrentUsername(){
        Authentication authentication = authenticationFacade.getAuthentication();
        return CompletableFuture.completedFuture(authentication.getName());
    }




}
