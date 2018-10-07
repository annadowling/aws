package com.wit.aws.repositories;

import com.wit.aws.domain.AWS;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("mysql")
public interface AWSRepository extends CrudRepository<AWS, String> {
}
