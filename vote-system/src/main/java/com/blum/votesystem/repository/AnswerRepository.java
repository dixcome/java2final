package com.blum.votesystem.repository;

import com.blum.votesystem.models.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer,Long> {

}
