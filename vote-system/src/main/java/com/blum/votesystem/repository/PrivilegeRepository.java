package com.blum.votesystem.repository;

import com.blum.votesystem.models.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege,Long> {
    Privilege findByName(String name);

    @Override
    void delete(Privilege privilege);
}
