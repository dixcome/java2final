package com.blum.votesystem.service;

import com.blum.votesystem.models.Answer;
import com.blum.votesystem.models.Question;
import com.blum.votesystem.repository.AnswerRepository;
import com.blum.votesystem.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

@Service
public class QuestionAnswerService {
    @Autowired
    private QuestionRepository questionRepo;

    @Autowired
    private AnswerRepository answerRepo;

    @Async
    public CompletableFuture<ArrayList<Question>> getQuestionList(){
        ArrayList<Question> list = new ArrayList<>();
        for (Question question: questionRepo.findAll()) {
            list.add(question);
        }
        return CompletableFuture.completedFuture(list);
    }


    @Async
    @Transactional
    public void assignAnswer(long questionId, long answerId){
        Question question = questionRepo.getOne(questionId);
        Answer answer = answerRepo.getOne(answerId);
        System.out.println(answer.getText());
        question.addAnswer(answer);
        System.out.println(question.getAnswers().get(0).getText());
        questionRepo.save(question);
    }

    @Async
    public void addQuestion(String text){
        Question question = new Question();
        question.setText(text);
        questionRepo.save(question);
    }

    @Async
    public void deleteQuestion(long id){
        Question question = questionRepo.findById(id).get();
        questionRepo.delete(question);
    }

    @Async
    @Transactional
    public void updateQuestion(long id,String text){
        Question question = questionRepo.getOne(id);
        question.setText(text);
        questionRepo.save(question);
    }

    @Async
    public CompletableFuture<ArrayList<Answer>> getAnswerList(){
        ArrayList<Answer> list = new ArrayList<>();
        for (Answer answer: answerRepo.findAll()) {
            list.add(answer);
        }
        return CompletableFuture.completedFuture(list);
    }

    @Async
    public void addAnswer(String text){
        Answer answer = new Answer();
        answer.setText(text);
        answerRepo.save(answer);
    }

    @Async
    @Transactional
    public void updateAnswer(long id,String text){
        Answer answer = answerRepo.getOne(id);
        answer.setText(text);
        answerRepo.save(answer);
    }

    @Async
    public void deleteAnswer(long id){
        Answer answer = answerRepo.findById(id).get();
        answerRepo.delete(answer);
    }

}
