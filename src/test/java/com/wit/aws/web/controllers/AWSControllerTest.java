package com.wit.aws.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wit.aws.domain.AWS;
import com.wit.aws.repositories.AWSRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class AWSControllerTest {
    @Mock
    private AWSRepository repository;

    @InjectMocks
    private AWSController awsController;

    private MockMvc mockMvc;

    @Before
    public void setup() throws ServletException {
        this.mockMvc = MockMvcBuilders.standaloneSetup(awsController).build();
    }

    @Test
    public void testAwsServices() throws Exception {
        List<AWS> AWS = new ArrayList<>();
        AWS.add(new AWS("test1", "dns"));
        AWS.add(new AWS("test2", "dns"));

        ObjectMapper mapper = new ObjectMapper();
        String aws_json = mapper.writeValueAsString(AWS);

        when(repository.findAll()).thenReturn(AWS);

        MvcResult result = this.mockMvc.perform(get("/aws")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_UTF8.toString()))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        Assert.assertEquals(aws_json, result.getResponse().getContentAsString());

    }

    @Test
    public void testAdd() throws Exception {
        AWS AWS = new AWS("test3", "security");
        ObjectMapper mapper = new ObjectMapper();
        String aws_json = mapper.writeValueAsString(AWS);

        when(repository.save(any(AWS.class))).thenReturn(AWS);

        MvcResult result = this.mockMvc.perform(post("/aws").content(aws_json)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_UTF8.toString()))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        Assert.assertEquals(aws_json, result.getResponse().getContentAsString());
    }

    @Test
    public void testUpdate() throws Exception {
        AWS AWS = new AWS("test4", "security");
        ObjectMapper mapper = new ObjectMapper();
        String aws_json = mapper.writeValueAsString(AWS);

        when(repository.save(any(AWS.class))).thenReturn(AWS);

        // AlbumController.update is actually a HTTP.POST with Id, not an HTTP.PUT or HTTP.PATCH...
        MvcResult result = this.mockMvc.perform(post("/aws").content(aws_json)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_UTF8.toString()))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        Assert.assertEquals(aws_json, result.getResponse().getContentAsString());
    }
}