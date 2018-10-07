package com.wit.aws.root;

import com.wit.aws.data.LocalJpaRepositoryConfig;
import com.wit.aws.repositories.AWSRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {AWSRepository.class, LocalJpaRepositoryConfig.class})
public class RepositoryConfig {
}

