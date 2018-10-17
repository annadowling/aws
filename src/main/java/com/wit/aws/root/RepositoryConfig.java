package com.wit.aws.root;

import com.wit.aws.data.LocalJpaRepositoryConfig;
import com.wit.aws.repositories.AWSRepository;
import com.wit.aws.web.controllers.AWSController;
import com.wit.aws.web.controllers.HomeController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {AWSRepository.class, LocalJpaRepositoryConfig.class, AWSController.class, HomeController.class})
public class RepositoryConfig {
}

