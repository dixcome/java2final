package com.blum.votesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@SpringBootApplication
public class VoteSystemApplication {


    public static void main(String[] args) {
        SpringApplication.run(VoteSystemApplication.class, args);
    }

}




