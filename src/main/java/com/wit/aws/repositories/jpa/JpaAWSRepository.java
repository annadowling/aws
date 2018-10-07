package com.wit.aws.repositories.jpa;

import com.wit.aws.domain.AWS;
import com.wit.aws.repositories.AWSRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaAWSRepository extends JpaRepository<AWS, Long>, AWSRepository {
}
