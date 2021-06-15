package com.blum.votesystem.service;


import com.blum.votesystem.models.Role;
import com.blum.votesystem.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepo;

    @Async
    public CompletableFuture<ArrayList<Role>> getAllRoles(){
        ArrayList<Role> list = new ArrayList<>();

        for (Role role: roleRepo.findAll()) {
            list.add(role);
        }
        return CompletableFuture.completedFuture(list);

    }
}
