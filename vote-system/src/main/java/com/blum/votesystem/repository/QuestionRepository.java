package com.blum.votesystem.repository;

import com.blum.votesystem.models.Answer;
import com.blum.votesystem.models.Question;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Collection;

public interface QuestionRepository extends JpaRepository<Question,Long> {

}
