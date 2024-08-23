package com.mfa.mims.config;

import com.mfa.mims.service.TraineeJoiningDetailsService;
import com.mfa.mims.service.impl.TraineeJoiningDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public TraineeJoiningDetailsService traineeJoiningDetailsService()
    {
        return new TraineeJoiningDetailsServiceImpl();
    }

}
