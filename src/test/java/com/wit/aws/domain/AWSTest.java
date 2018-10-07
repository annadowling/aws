package com.wit.aws.domain;

import org.hibernate.engine.spi.SessionImplementor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class AWSTest {
    private String serviceName, category;
    private AWS getAWS, setAWS;

    @Mock
    private SessionImplementor session;

    @Mock
    private Object object;

    @Before
    public void setUp() throws Exception {
        serviceName = "testService";
        category = "testCategory";

        getAWS = new AWS(serviceName, category);

        setAWS = new AWS();
    }


    @Test
    public void testGetService() throws Exception {
        Assert.assertEquals(serviceName, getAWS.getServiceName());
    }

    @Test
    public void testSetTitle() throws Exception {
        setAWS.setServiceName(serviceName);

        Assert.assertEquals(serviceName, setAWS.getServiceName());
    }

    @Test
    public void testGetCategory() throws Exception {
        Assert.assertEquals(category, getAWS.getCategory());
    }

    @Test
    public void testSetCategory() throws Exception {
        setAWS.setCategory(category);

        Assert.assertEquals(category, setAWS.getCategory());
    }
}