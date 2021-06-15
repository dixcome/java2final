package com.blum.votesystem.repository;

import com.blum.votesystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);

    @Modifying
    @Query("update User u set u.age = :age where u.id = :id")
    void updateAge(@Param(value = "id") long id, @Param(value = "age") int age);

    @Modifying
    @Query("update User u set u.firstname = :firstname where u.id = :id")
    void updateFirstname(@Param(value = "id") long id, @Param(value = "firstname") String firstname);

    @Modifying
    @Query("update User u set u.lastname = :lastname where u.id = :id")
    void updateLastname(@Param(value = "id") long id, @Param(value = "lastname") String lastname);

    @Modifying
    @Query("update User u set u.interests = :interests where u.id = :id")
    void updateInterests(@Param(value = "id") long id, @Param(value = "interests") String interests);

    @Modifying
    @Query("update User u set u.groupName = :group_name where u.id = :id")
    void updateGroupName(@Param(value = "id") long id, @Param(value = "group_name") String group_name);

    @Override
    void delete(User user);
}
