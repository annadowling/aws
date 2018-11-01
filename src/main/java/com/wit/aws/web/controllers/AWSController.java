package com.wit.aws.web.controllers;

import com.wit.aws.domain.AWS;
import com.wit.aws.repositories.AWSRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/aws")
public class AWSController {
    private static final Logger logger = LoggerFactory.getLogger(AWSController.class);
    private AWSRepository repository;
    private Integer requestToken = 0;

    @Autowired
    public AWSController(AWSRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<AWS> aws(HttpServletRequest request) {
        String ipAddress = IpHelper.getClientIp(request);
        requestToken = new Integer(requestToken.intValue() + 1);
        logger.info("REQUEST TOKEN ID IS: " + requestToken + " ,Request is:" + request.getProtocol() + " ,Spring-AWS App Received GET Request from Remote Ip Address: " + ipAddress + " , Getting All AWS Services");
        return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public AWS add(@RequestBody @Valid AWS aws, HttpServletRequest request) {
        String ipAddress = IpHelper.getClientIp(request);
        requestToken = new Integer(requestToken.intValue() + 1);
        logger.info("REQUEST TOKEN ID IS: " + requestToken + " ,Request is:" + request.getProtocol() + " ,Spring-AWS App Received POST Request from Remote Ip Address: " + ipAddress + " , Adding AWS Service: " + aws.getServiceName());
        return repository.save(aws);
    }

    @RequestMapping(method = RequestMethod.POST)
    public AWS update(@RequestBody @Valid AWS aws, HttpServletRequest request) {
        String ipAddress = IpHelper.getClientIp(request);
        requestToken = new Integer(requestToken.intValue() + 1);
        logger.info("REQUEST TOKEN ID IS: " + requestToken + " ,Request is:" + request.getProtocol() + " ,Spring-AWS App Received POST Request from Remote Ip Address: " + ipAddress + " , Adding AWS Service: " + aws.getServiceName());
        return repository.save(aws);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<AWS> getById(@PathVariable long id, HttpServletRequest request) {
        String ipAddress = IpHelper.getClientIp(request);
        requestToken = new Integer(requestToken.intValue() + 1);
        logger.info("REQUEST TOKEN ID IS: " + requestToken + " ,Request is:" + request.getProtocol() + " ,Spring-AWS App Received GET Request from Remote Ip Address: " + ipAddress + " , Getting AWS Service " + id);
        return repository.findById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable long id, HttpServletRequest request) {
        String ipAddress = IpHelper.getClientIp(request);
        requestToken = new Integer(requestToken.intValue() + 1);
        logger.info("REQUEST TOKEN ID IS: " + requestToken + " ,Request is:" + request.getProtocol() + " ,Spring-AWS App Received DELETE Request from Remote Ip Address: " + ipAddress + " , Deleting AWS Service " + id);
        repository.deleteById(id);
    }
}