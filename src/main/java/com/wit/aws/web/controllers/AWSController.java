package com.wit.aws.web.controllers;

import com.wit.aws.domain.AWS;
import com.wit.aws.repositories.AWSRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/aws")
public class AWSController {
    private static final Logger logger = LoggerFactory.getLogger(AWSController.class);
    private AWSRepository repository;

    @Autowired
    public AWSController(AWSRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<AWS> aws() {
        return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public AWS add(@RequestBody @Valid AWS aws) {
        logger.info("Adding AWS " + aws.getId());
        return repository.save(aws);
    }

    @RequestMapping(method = RequestMethod.POST)
    public AWS update(@RequestBody @Valid AWS aws) {
        logger.info("Updating AWS Service " + aws.getId());
        return repository.save(aws);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<AWS> getById(@PathVariable String id) {
        logger.info("Getting AWS Service " + id);
        return repository.findById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable String id) {
        logger.info("Deleting AWS Service " + id);
        repository.deleteById(id);
    }
}